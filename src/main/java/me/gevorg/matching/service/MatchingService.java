package me.gevorg.matching.service;


import lombok.extern.slf4j.Slf4j;
import me.gevorg.matching.model.Employee;
import me.gevorg.matching.model.EmployeeMatch;
import me.gevorg.matching.stolen.HungarianAlgorithm;

import java.util.*;

/**
 * Base for all matching services, in case you need new implementation for score algorithm.
 */
@Slf4j
public abstract class MatchingService {
    // Max score.
    public static final int MAX_SCORE = 100;

    /**
     * Finds the best matches based on provided employee list.
     *
     * @param employeeList employee list.
     * @return list of matches.
     */
    public List<EmployeeMatch> findMatches(List<Employee> employeeList) {
        int[][] matchMatrix = generateMatchMatrix(employeeList);
        for (int[] matchRow: matchMatrix) {
            log.info("Match matrix: {}", Arrays.toString(matchRow));
        }

        HungarianAlgorithm hungarianAlgorithm = new HungarianAlgorithm(matchMatrix);
        int[][] bestMatches = hungarianAlgorithm.findOptimalAssignment();
        for (int[] bestMatch: bestMatches) {
            log.info("Best match: {}", Arrays.toString(bestMatch));
        }

        return extractMatches(employeeList, bestMatches);
    }

    /**
     * Extracts matches using Hungarian algorithm execution result.
     *
     * @param employeeList employee list.
     * @param bestMatches result from Hungarian algorithm.
     * @return list of employee matches.
     */
    private List<EmployeeMatch> extractMatches(List<Employee> employeeList, int[][] bestMatches) {
        List<EmployeeMatch> foundMatches = new ArrayList<>();
        Set<Integer> usedIndexes = new HashSet<>(employeeList.size());
        for (int[] matchingPair: bestMatches) {
            int leftIndex = matchingPair[0];
            int rightIndex = matchingPair[1];

            if (usedIndexes.contains(matchingPair[0]) || usedIndexes.contains(matchingPair[1])) {
                continue;
            } else {
                usedIndexes.add(leftIndex);
                usedIndexes.add(rightIndex);
            }

            EmployeeMatch employeeMatch = extractMatch(employeeList, leftIndex, rightIndex);
            foundMatches.add(employeeMatch);
        }

        return foundMatches;
    }

    /**
     * Extracts match using index data.
     *
     * @param employeeList employee list.
     * @param leftIndex left index.
     * @param rightIndex right index.
     * @return employee match
     */
    private EmployeeMatch extractMatch(List<Employee> employeeList, int leftIndex, int rightIndex) {
        Employee left = employeeList.get(leftIndex);
        Employee right = employeeList.get(rightIndex);

        int score = getScore(left, right);

        return new EmployeeMatch(left.getName(), right.getName(), (byte) score);
    }

    /**
     * Generates match matrix using `reverse score` = 100 - `score` principle.
     *
     * @param employeeList employee list.
     * @return `reverse score` matrix.
     */
    private int[][] generateMatchMatrix(List<Employee> employeeList) {
        int matrixSize = employeeList.size();
        int[][] matchMatrix = new int[matrixSize][matrixSize];
        for (int[] row: matchMatrix) {
            Arrays.fill(row, MAX_SCORE);
        }

        for (int i = 0; i < employeeList.size(); ++ i) {
            for (int j = i + 1; j < employeeList.size(); ++ j) {
                Employee left = employeeList.get(i);
                Employee right = employeeList.get(j);

                int score = getScore(left, right);

                matchMatrix[i][j] = MAX_SCORE - score;
                matchMatrix[j][i] = MAX_SCORE - score;
            }
        }

        return matchMatrix;
    }

    abstract int getScore(Employee left, Employee right);
}

package me.gevorg.matching.service;

import lombok.extern.slf4j.Slf4j;
import me.gevorg.matching.model.Employee;
import org.springframework.stereotype.Service;

/**
 * Basic implementation for matching service.
 */
@Slf4j
@Service
public class BasicMatchingService extends MatchingService {

    /**
     * Basic implementation for score, can be changed in the future.
     *
     * @param left left operand.
     * @param right right operand.
     * @return final score for pair.
     */
    @Override
    public int getScore(Employee left, Employee right) {
        int score = 0;

        // If same division.
        if (left.getDivision().equals(right.getDivision())) {
            score += 30;
        }

        // Age diff is less or equal to 5.
        if (Math.abs(left.getAge() - right.getAge()) <= 5) {
            score += 30;
        }

        // Are in the same timezone.
        if (left.getTimezone().equals(right.getTimezone())) {
            score += 40;
        }

        return score;
    }
}

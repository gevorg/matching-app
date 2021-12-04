# Matching APP

Matching App.

## Requirements

I use OpenJDK 11, that should be the only dependency required to run app.

## How to run app in development mode

Just run following command in the root directory of project, it might take while to download dependencies the first
time.

```shell
./gradlew clean bootRun
...
Connected to the target VM, address: '127.0.0.1:55647', transport: 'socket'

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.6.1)

2021-12-04 15:49:40.219  INFO 125565 --- [           main] me.gevorg.matching.MatchingApplication   : Starting MatchingApplication using Java 11.0.12 on blackbox with PID 125565 (/home/gevorg/IdeaProjects/matching-app/build/classes/java/main started by gevorg in /home/gevorg/IdeaProjects/matching-app)
2021-12-04 15:49:40.221  INFO 125565 --- [           main] me.gevorg.matching.MatchingApplication   : No active profile set, falling back to default profiles: default
2021-12-04 15:49:40.674  INFO 125565 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-12-04 15:49:40.678  INFO 125565 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-12-04 15:49:40.678  INFO 125565 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.55]
2021-12-04 15:49:40.708  INFO 125565 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-12-04 15:49:40.708  INFO 125565 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 443 ms
2021-12-04 15:49:40.825  INFO 125565 --- [           main] o.s.b.a.w.s.WelcomePageHandlerMapping    : Adding welcome page: class path resource [static/index.html]
2021-12-04 15:49:40.877  INFO 125565 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-12-04 15:49:40.885  INFO 125565 --- [           main] me.gevorg.matching.MatchingApplication   : Started MatchingApplication in 0.909 seconds (JVM running for 1.896)
```

Application should open on `http://localhost:8080/`.

## Running tests

To run all tests:

```shell
./gradlew clean check
```
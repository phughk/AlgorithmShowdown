# AlgorithmShowdown
This is a project demonstrating my abilities in algorithms and languages.

## Project Structure

This project is very broad and while I would like to keep it simple with a single maven build, that isn't possible if I want to throw in Python, Go or Haskell.

Consequently, the structure I have chosen is as follows:

1. service (Hacker Rank, SPOJ, Facebook Hack Cup etc.)
1. problem (a unique code or identifier)
1. language

Regarding package names, they should all follow `com.kaznowski.hugh.service.problem` regardless of language

## Building

### JVM

Most JVM solutions are going to be maven jar builds.
You can run them with
```
mvn clean install
java -jar target/*SNAPSHOT.jar
```

It is possible that future solutions will be built with gradle as I would like to learn it more

### Clojure builds
It is possible to create leinigen builds using the init_clojure script, however
all my clojure solutions aren't projects, only single scripts that can be run with `clojure solution.clj`

If that doesn't work then source the file in clojure like so
```
clojure
(load-file "solution.clj")
```

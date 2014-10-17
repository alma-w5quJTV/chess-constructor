Chess constructor
=========================

About
--------------------

This project enumerates all possible configurations for given chess pieces for some MxN borad that do not capture each others. 
All parameters can be specified as program's parameters.

How to compile
--------------------

Project uses Maven standard repository, so you can easily use 

    mvn -DskipTests clean assembly:assembly

to create runnable JAR file

How to run it
--------------------

To start game just compile it, run 

    java -jar target/chess-constructor-0.1-SNAPSHOT-jar-with-dependencies.jar

and follow further instruction

Example of usage
--------------------

    java -jar target/chess-constructor-0.1-SNAPSHOT-jar-with-dependencies.jar

    Please enter width (X) of board: 7

    Please enter height (Y) of board: 7

    Please enter number (>=0) of Kings (K): 2

    Please enter number (>=0) of Queens (Q): 2

    Please enter number (>=0) of Knights (N): 1

    Please enter number (>=0) of Rooks (R): 0

    Please enter number (>=0) of Bishop (B): 2

    Print all possible cases? (Y/N) [Y]: n
    [7 x 7] Found 3063828 unique configurations by 3197 ms

How to test it
--------------------
Command

    mvn clean test

will start all tests. Some of them are appr. long, so be careful. 
To test program for more configurations please, see `/chess-constructor/src/test/java/com/almasoft/chessconstructor/strategy/StrategyTest.java` it could be considered as a functional test that shows program functionality.

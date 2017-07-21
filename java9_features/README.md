JDK-9's full-fledged version is yet to be released but here I am going to discuss about its main developer features
by referencing to Early Access Build of JDK-9 and Java Magazine that I got from Oracle in my email
last night around 11 PM NPT.

I will be explaining each feature with code examples as well and provide implementation demo in this repository itself.

Though the primary bone of JDK-9 is supposed to be JPMS( Java Platform Modular System), I am not going to mention it here as
it's yet to be finalized by Java community.

NOTE: if you are IntelliJ IDEA user, FYI, IDEA version lower than 17 doesn't support Java 9.

Nine Hot features inside of JDK-9 for Java hard-cores.

1) Factory Methods for Collections
See example: java-stuffs/java9_features/src/main/java/Java9Collections.java

2) Optional Class Enhancement
See example: java-stuffs/java9_features/src/main/java/OptionalClass.java

3) Stream API Enhancement

4) New tool: Read-Eval-Print-Loop(REPL) - JShell
Try: $JAVA_HOME/bin/jshell

5) Concurrency Updates
java 7 added fork/join, java 8 added parallel streams, and now here java 9 has added Publish-Subscribe model to streams

6) Milling Project Coin
private methods can be added to interfaces
single underscore is no longer valid as variable name, in jdk10 it will be used as lambda variable

7) Spin-Wait-Hints
onSpinWait() method added to Thread, supports optimization in JVM and Processor

8)Variable Handles

9) Process API Updates
In addition to Process and ProcessBuilder, jdk9 introduces new interface ProcessHandle interface.
This new interface is used to make a UNIX-style pipe line of Process Builders.

TODO: JPMS

Reference: Java 9 Magazine July/August issue.


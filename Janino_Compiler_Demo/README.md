What is Janino?

Janino is a super-small, super-fast Java compiler. Not only can it compile a set of
source files to a set of class files like JAVAC, but also can it compile a Java
expression, block, class body or source file in memory, load the bytecode and execute
it directly in the same JVM.

JANINO is integrated with Apache Commons JCI ("Java Compiler Interface") and JBoss Rules / Drools.

JANINO can also be used for static code analysis or code manipulation.

JANINO can be configured to use javax.tools.JavaCompiler (available since JDK 1.6),
i.e. JAVAC, which removes the Java 5-related limitations.

Referenced from : http://docs.codehaus.org/display/JANINO/Home#Home-what
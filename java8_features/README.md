Major Changes Categories in JDK8
================================
1. Java Language
2. Java Compiler
3. Java Libraries
4. Java Tools
5. Java Runtime(JVM)

1. New Java Language Changes
----------------------------
1.1  Lambdas and Functional Interfaces
1.2  Interface's Default and Static Methods
1.3  Method References
1.4  Repeating annotations
1.5  Better Type Inference
1.6  Extended Annotations Support


2. New Java Compiler Changes
----------------------------
2.1 Parameter names


3. New Java Libraries Changes
-----------------------------
3.1  Optional
3.2  Streams
3.3  Date/Time API
3.4  Nashorn JavaScript engine
3.5  Base64
3.6  Parallel Arrays
3.7  Concurrency

4. New Java Tools
-------------------
4.1  jdeps
4.2  jjs


5. New JVM Changes
--------------------
5.1 The JVM options -XX:PermSize and –XX:MaxPermSize have been replaced by -XX:MetaSpaceSize and -XX:MaxMetaspaceSize respectively.


NOTE:
=====

Heap Space:
The heap stores all of the objects created by your Java program.
The heap's contents is monitored by the garbage collector, which frees memory from the
heap when you stop using an object (i.e. when there are no more references to the object.

Stack:
Stack stores primitive types like ints and chars, and are typically local variables
and function return values. These are not garbage collected.

PermGen(MetaSpace):
The permanent generation is special because it holds meta-data describing user classes
classes that are not part of the Java language). Examples of such meta-data are objects describing
classes and methods and they are stored in the Permanent Generation. Applications with large code-base
can quickly fill up this segment of the heap which will cause java.lang.OutOfMemoryError:
PermGen no matter how high your -Xmx and how much memory you have on the machine.

Reference:https://www.javacodegeeks.com/2014/05/java-8-features-tutorial.html
Reference
-----------
- http://www.vogella.com/tutorials/JavaConcurrency/article.html
- http://tutorials.jenkov.com/java-concurrency/locks.html

Java concurrency
-----------------
It covers the concepts of
- parallel programming,
- immutability,
- threads,
- the executor framework (thread pools),
- futures,
- callables CompletableFuture and
- the fork-join framework
Additionally
- Race Conditions and Critical Sections
- Java Memory Model
- Deadlock, Deadlock Prevention, Starvation and Fairness
- Locks in Java, Semaphores

And the details of package
- java.util.concurrent


Java Memory Model
-------------------
- The Java memory model describes the communication between the memory of the threads and the main memory of the application.
- It defines the rules how changes in the memory done by threads are propagated to other threads.
- Volatile keyword is used to make change propagate to main memory immediately instead of only in cache so other thread gets latest value.

Parallel programming
---------------------
- it is the ability to run several programs or several parts of a program in parallel.
- If a time consuming task can be performed asynchronously or in parallel, this improve the throughput and the interactivity of the program.
- A thread is a so called lightweight process.
- It has its own call stack, but can access shared data of other threads in the same process as there is no share among processes.
- Every thread has its own memory cache. If a thread reads shared data it stores this data in its own memory cache.
- A thread can re-read the shared data.
- A Java application runs by default in one process and by default in one thread withing that process.
- And within a Java application you work with several threads to achieve parallel processing or asynchronous behavior.

Threads pools with the Executor Framework
-----------------------------------------
- Thread pools manage a pool of worker threads.
- The thread pools contains a work queue which holds tasks waiting to get executed.
- The Thread class itself provides a method, e.g. execute(Runnable r) to add a new Runnable object to the work queue.
- The Executor framework provides example implementation of the java.util.concurrent.Executor interface.
-  e.g. Executors.newFixedThreadPool(int n) which will create n worker threads.
-  The ExecutorService adds life cycle methods to the Executor, which allows to shutdown the Executor and to wait for termination.

MultiThreaded Servers
---------------------





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
- What separates the single threaded server from a multithreaded server is that
  the single threaded server processes the incoming requests in the same thread
  that accepts the client connection.
- A multithreaded server passes the connection on to a worker thread that processes the request.

ThreadPooled Server
-------------------
- Rather than starting a new thread per incoming connection, the connection is wrapped in a Runnable and
  handed off to a thread poool with a fixed number of threads.
- The Runnable's are kept in a queue in the thread pool. When a thread in the thread pool is idle it will
  take a Runnable from the queue and execute it.
- The advantages of a thread pooled server compared to a multithreaded server is that you can control
  the maximum number of threads running at the same time. This has certain advantages.

Futures and Callables
---------------------
- Unfortunately a Runnable cannot return a result to the caller.
- In case you expect your threads to return a computed result you can use Callable.
- If you submit a Callable object to an Executor, the framework returns an object of type Future.
- Future exposes methods allowing a client to monitor the progress of a task being executed by a different thread.
- Therefore a Future object can be used to check the status of a Callable and to retrieve the result from the Callable.

The Future interface is limited as a model of asynchronously executed tasks.
While Future allows a client to query a Callable task for its result, it does not provide the option to
register a callback method, which would allow to notified once a task is done. In Java 5 you could use
ExecutorCompletionService for this purpose but as of Java 8 you can use the CompletableFuture interface
which allows to provide a callback interface which is called once a task is completed.Fork-Join in Java 7


Fork-Join in Java 7
--------------------
- Java 7 introduce a new parallel mechanism for compute intensive tasks, the fork-join framework.
- The fork-join framework allows you to distribute a certain task on several workers and then wait for the result.
- To check the number of available processors in a machine use: Runtime.getRuntime().availableProcessors(), and
   later on, this number can be used to set lenght of thread pool.
- Fork/Join addresses the need for divide-and-conquer, or recursive task-processing in Java program.
- Fork/Join's logic is very simple:
(1) separate (fork) each large task into smaller tasks;
(2) process each task in a separate thread (separating those into even smaller tasks if necessary);
(3) join the results.

- Two types of task:
(1) RecursiveTask<Type>: can return value.
(2) RecursiveAction: cannot return value.

- Need to create a ForkJoinPool and pass the task to this pool. Task need to override the computer method.
- Compute method need to divide the task into chunks and fork them and join. Such forked chunks of task can run on separate CUP/Threads.


Locks in Java, Semaphores
-------------------------
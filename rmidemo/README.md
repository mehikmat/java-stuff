RMI:
----
- The RMI (Remote Method Invocation) is an API that provides a mechanism to create distributed application in java.
- The RMI allows an object to invoke methods on an object running in another JVM.
- Stub: The stub is an object, acts as a gateway for the client side.
- Skeleton: The skeleton is an object, acts as a gateway for the server side object.

RMI Application Architecture:

![alt](./docs/rmi_arch.jpg)


6 steps to write the RMI program
--------------------------------
- Create the remote interface
- Provide the implementation of the remote interface
- Compile the implementation class and create the stub and skeleton objects using the rmic tool: NOTE NEEDE if jdk >5.0
- Start the registry service by rmiregistry tool
- Create and start the remote application
- Create and start the client application

- In the Java 2 SDK, an stub protocol was introduced that eliminates the need for skeletons.
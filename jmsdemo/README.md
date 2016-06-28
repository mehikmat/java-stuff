JMS(Java Messaging Service)
============================
What Is Messaging?
- Messaging is a method of communication between software components or applications.
- A messaging system is a peer-to-peer facility: A messaging client can send messages to,and receive messages from, any other client.
- Each client connects to a messaging `agent` that provides facilities for creating, sending, receiving, and reading messages.
- Messaging enables distributed communication that is Loosely Coupled,Asynchronous,Reliable unlike RMI and unlike Email.
- The Java Message Service is a Java API that allows applications to create, send, receive, and read messages.

JMS API
--------
- JMS API defines a common set of interfaces and associated semantics that allow programs written
  in the Java programming language to communicate with other messaging implementations.

JMS API Architecture
---------------------
A JMS application is composed of the following parts.
- `A JMS provider` is a messaging system that implements the JMS interfaces and provides administrative and control features. An implementation of the Java EE platform includes a JMS provider.
- `JMS clients` are the programs or components, written in the Java programming language, that produce and consume messages. Any Java EE application component can act as a JMS client.
- `Messages` are the objects that communicate information between JMS clients.
- `Administered objects` are preconfigured JMS objects created by an administrator for the use of clients. The two kinds of JMS administered objects are destinations and connection factories, described in JMS Administered Objects.

Messaging Domains
-----------------
- Point-to-Point Messaging : PTP application is built on the concept of: sender--->message queues--->receiver.
- Publish/Subscribe Messaging: Client addresses messages to a topic: sender--->topics--->receiver1(topic1),receiver2(topic2)..

Message Consumption
-------------------
- Messaging products are inherently asynchronous

However, the JMS specification uses this term in a more precise sense. Messages can be consumed in either of two ways:
- Synchronously: A subscriber or a receiver explicitly fetches the message from the destination by calling the receive method.
                 The receive method can block until a message arrives or can time out if a message does not arrive within a specified time limit.
- Asynchronously: A client can register a message listener with a consumer. A message listener is similar to an event listener.
                  Whenever a message arrives at the destination, the JMS provider delivers the message by calling the listener’s onMessage method,
                  which acts on the contents of the message.


JMS programming Model
----------------------
![JMS Programming Model](docs/jms-programmingModel.gif")

- Two parts of a JMS application, destinations and connection factories, are best maintained administratively rather than programmatically.
- The management of these objects belongs administrative tasks and vary from provider to provider.
- Consumer and Producer both do communication via JMS server(a messaging agent wich maintains queues/topics).
- ActiveMQ is the default JMS provider in Apache TomEE and OpenEJB.
- If no broker is configured and JMS is used in the webapp, TomEE will create a broker.
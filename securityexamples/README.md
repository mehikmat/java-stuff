Overview of Java Security Models
========================================================================
This tutorial provides an overview of the security models in the
- Java Platform,Standard Edition (JavaSE),
- The Java Platform, Enterprise Edition (JavaEE),
- The Java Authentication and Authorization Service (JAAS) and,
- The Java Authorization Contract for Containers (JACC).

**Authentication** deals with the question "Who is trying to access services?"
 The entity or subject or caller can be a *human user, a business application, a host,
 or one entity acting on behalf of (or impersonating) another entity*.

 Authentication information, such as user names and passwords, is stored in a user repository,
 such as an XML file, database, or directory service.
 When a subject attempts to access an application, such as by logging in,
 the security provider looks up the subject in the user repository and verifies the subject's identity.

 A security provider is a module that provides an implementation of a specific security
 service such as authentication or authorization.
 The Oracle Internet Directory is an example of a repository.

**Authorization** deals with the question "Who can perform tasks on resources?"
 Resources are typically expressed in terms of URL patterns for Web applications,
 and method permissions for EJBs. Authorization is on a per-role basis,
 with appropriate permissions being assigned to each defined role in an application.

**Permission** is a set of permissible operations on some set of resources.

Java Security Model
===========================================================================
The Java security model is based on controlling the operations that a class can perform
when it is loaded into a running environment.
For this reason, this model is called code-centric or code-based.

JAAS
-------
- It extends the Java security model by allowing checks on the identity of the caller
  checks on the permissions granted to the code being run and subject-based authorization.
- It implements the standard Pluggable Authentication Module (PAM) framework,
  which decouples an application from its authentication service and supports the
  implementation of custom authentication modules.

- **Subject**:- In a security context, a subject is any entity that requests access to an object.
  These are generic terms used to denote the thing requesting access and the thing the request is
  made against. When you log onto an application you are the subject and the application
  is the object. When someone knocks on your door the visitor is the subject requesting
  access and your home is the object access is requested of.
- **Principal**:- A subset of subject that is represented by an account, role or other unique identifier.
  When we get to the level of implementation details, principals are the unique keys we use in access control lists.
  They may represent human users, automation, applications, connections, etc.
- **User**:- A subset of principal usually referring to a human operator.
  The distinction is blurring over time because the words "user" or "user ID"
  are commonly interchanged with "account". However, when you need to make the
  distinction between the broad class of things that are principals and the subset of these that are
  interactive operators driving transactions in a non-deterministic fashion, "user" is the right word.

Using JAAS authentication from your application typically involves the following steps:

- Create a `LoginContext`
- Optionally pass a `CallbackHandler` to the LoginContext, for gathering or processing authentication data
- Perform authentication by calling the `LoginContext's login()` method
- Perform privileged actions using the returned `Subject` (assuming login succeeds)

pass following JVM property while running app
```
-Djava.security.auth.login.config=$APP_HOME/conf/jaas.config"
```

JAAC-Java Authorization Contract for Containers
-----------------------------------------------
The Java Authorization Contract for Containers (JACC) specifies a contract between
 JavaEE containers and authorization modules, so that the container can provide the appropriate
 authorization. Specifically, JAAC performs the following tasks:

- Defines a role as a collection of permissions
- Grants principals permissions in roles
- Determines whether a principal has been granted the permissions in a role
- Maps identifiers (embedded in application code) to application-scoped roles

JACC allows the JavaEE security model fully to leverage the Java security model
by translating JavaEE security constraints into Java permissions. JACC is not enabled by default.


Open source Java Security Framework: Apache Shiro
=============================================================================
Apache Shiro is a powerful and easy to use Java security framework that offers
- Authentication,
- Authorization,
- Cryptography and,
- Session Management

Shiro can be run in any environment, from the simplest command line application to
the biggest enterprise web and clustered applications.


Java jar signing
================
On vendor side
----------------
**Generate keys:**
```
$ keytool -genkey -alias mydomain -keyalg RSA -keystore mykeystore.jks -keysize 2048
```
**Generate Jar**
Install security manager in code [System.getSecurityManger()]
```
$ mvn clean package
```
**Sign Jar**
```
$ jarsigner -keystore ../keys/mykeystore.jks -signedjar signed_shiroexample-1.jar shiroexample-1.jar mydomain
$ create a security.policy file granting the permission to jar and supply this policy file to receiver.
```
Then export public key from keystore and supply public key and signed jar to receivers

In order for the runtime system to authenticate the signature,
receiver's keystore needs to have the public key corresponding to the private key
used to generate the signature. You supply this by sending Receiver a copy
of the certificate authenticating the public key. Copy that certificate
from the keystore mykeystore to a file named mycert.cer via the following:
```
$ keytool -export -keystore mykeystore.jks -alias mydomain -file mycert.cer
```

On receivers side
-----------------
- Import supplied certificate as a trusted certificate using the keytool -import command and give it same alias.
- Use supplied policy file and JVM parameter
```
$ keytool -import -alias  mydomain -file ../keys/mycert.cert -keystore ../../yourkeystore.jks
```
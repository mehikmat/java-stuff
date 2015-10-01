Steps to create JAX-WS App
---------------------------

1. Create Service Interface with annotations

2. Create Service Interface Implementation

3. Generate Service Classes using wsgen or javac for java latter then 1.7

wsgen -s src -d build/classes -cp build/classes jaxws.test.ServiceInterface
(maven plugin can be used for this)


NOTE::
--------
JDK only contains the J2ee APIs not their implementations
it does not contain any method bodies. It’s fine for compilation
but not for run or deploy your application, because it will caused ”

 Absent Code attribute in method that is not native or abstract in class”
 or other method not found errors.

 Due to policy, it’s not possible to include this javaee.jar in any
  public Maven repository.

The best practice is always get the original full version of javaee.jar
(J2EE SDK ie. Glashfish server ) from the http://www.oracle.com/technetwork/java/javaee/overview/index.html,
 and include it into your project manually.
 Or Use GlashFish server for deployment which contains all the j2ee dependencies.

JAX-WS Dependencies (Tomcat deployment)
---------------------------------------

By default, Tomcat does not come with any JAX-WS dependencies, So, you have to include it manually.

  1. Go here http://jax-ws.java.net/.
  2. Download JAX-WS RI distribution.
  3. Unzip it and copy following JAX-WS dependencies to Tomcat library folder “{$TOMCAT}/lib“.
      jaxb-impl.jar
      jaxb-core.jar
      jaxws-api.jar
      jaxws-rt.jar
      gmbal-api-only.jar
      management-api.jar
      stax-ex.jar
      streambuffer.jar
      policy.jar
      + all other jars

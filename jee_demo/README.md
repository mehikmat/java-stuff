Complete JEE Tutorials
======================

##J2EE Architcture##
![alt](./docs//j2ee-introduction.jpg)

Web Container:
- The Web container is a runtime environment for JSP files and and servlets.

EJB Container:
- The EJB container is a runtime environment that controls the enterprise beans and provides
  them with important system-level services.

J2EE Server(Web Container + EBJ Container):
The J2EE server provides the following services:
- Naming and Directory: allows programs to locate services and components through the JNDI API.
- Authentication: enforces security by requiring users to log in.
- HTTP: enables Web browsers to access servlets and JavaServer Pages (JSP) files.
- EJB: allows clients to invoke methods on enterprise beans.

JEE servers:
- JBoss
- Glassfish
- TomEE (Tomcat is only the web container)

Download TomEE-1.7.4 from: <http://repo.maven.apache.org/maven2/org/apache/openejb/apache-tomee/1.7.4/apache-tomee-1.7.4-webprofile.tar.gz> 

Usage:
- Servlets can use JDBC,EJB,used for secure,transactional server-side processing or JNDI data source.

A Student Management System Use Case
------------------------------------

RootApplication (packaged as ear)
     + ServletModule (packaged as war)
     + EJBModule (packaged as jar)
     + JPAModule (packaged as jar)

[1] Configure JNDI data source
- To declare a JNDI DataSource for the MySQL database, create a Resource XML element as done in conf/context.xml.
  This file can be deployed as:
- Inside /META-INF directory of a web application: the JNDI DataSource is only available to the application itself,
  thus it cannot be shared among other ones. In addition, this makes the configuration dependent on the application.
- Inside $CATALINA_BASE/conf directory: this is the preferred place because the JNDI DataSource will be available to
  all web applications and it’s independent of any applications.

[2] Configure web.xml
- Create a resource-ref XML element in web.xml as done in conf/web.xml.
- This is necessary in order to make the JNDI DataSource available to the application under the specified namespace jdbc/UsersDB.

JNDI Naming Conversion:
- There are two categories of policies:
  - Naming policies that specify how objects are named relative to each other and the common names to use.
  - Directory policies, called schema, that specify the attributes that objects in the directory should have
    and the names and syntaxes of those attributes.

Portable JNDI Syntax
---------------------
- Three JNDI namespaces are used for portable JNDI lookups: java:global, java:module, and java:app.

[1] The java:global JNDI namespace is the portable way of finding remote enterprise beans using JNDI
    lookups.JNDI addresses are of the following form:
    - java:global[/application name]/module name/enterprise bean name[/interface name]
    - Application name and module name default to the name of the application and module minus the
      file extension. Application names are required only if the application is packaged within an EAR.
      The interface name is required only if the enterprise bean implements more than one business interface.

[2] The java:module namespace is used to look up local enterprise beans within the same module.
    JNDI addresses using the java:module namespace are of the following form:
   - java:module/enterprise bean name/[interface name]
     The interface name is required only if the enterprise bean implements more than one business interface.
   - The java:app namespace is used to look up local enterprise beans packaged within the same
     application. That is, the enterprise bean is packaged within an EAR file containing multiple
     Java EE modules.

[3]JNDI addresses using the java:app namespace are of the following form:
   - java:app[/module name]/enterprise bean name[/interface name]
     The module name is optional. The interface name is required only if the enterprise bean
     implements more than one business interface.


For example, if an enterprise bean, MyBean, is packaged within the web application archive myApp.war,
the module name is myApp. The portable JNDI name is java:module/MyBean An equivalent JNDI name using
the java:global namespace is java:global/myApp/MyBean.

When use Enterprise Java Bean?
------------------------------
- Application needs Remote Access. In other words, it is distributed.
- Application needs to be scalable. EJB applications supports load balancing, clustering and fail-over.
- Application needs encapsulated business logic. EJB application is separated from presentation and persistent layer.

--------------------------------------
- EJB is an essential part of a J2EE platform.
- J2EE platform have component based architecture to provide multi-tiered, distributed and highly transactional features to enterprise level applications.
- EJB are primarily of three types: Session Bean,Message-Driven Bean,Entity Bean.

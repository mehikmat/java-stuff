Prerequisites for java binding
------------------------------
Install Ant
```
$ sudo apt-get install ant
```
Install other libs
```
$ sudo apt-get install libboost-dev libboost-test-dev libboost-program-options-dev\
 libevent-dev automake libtool flex bison pkg-config g++ libssl-dev
```

Download and install thrift from tarball
-----------------------
```
$ wget http://www.apache.org/dyn/closer.cgi?path=/thrift/0.9.1/thrift-0.9.1.tar.gz
$ tar -xzf thrift-0.9.1.tar.gz
$ cd thrift-0.9.1
$ ./configure && make
$ sudo make install
$ thrift --verision
```

Put dependency for thrift in pom.xml file
-----------------------------------------
```
<dependency>
  <groupId>org.apache.thrift</groupId>
  <artifactId>libthrift</artifactId>
  <version>0.9.1</version>
</dependency>
```

Then put entry for your thrift fiel in pom and package which will generate java file of .thrift file
:)


For Avro base operation:
-----------------------
http://avro.apache.org/docs/1.7.6/gettingstartedjava.html
mybatis-spring-zk
=================

Sample application demonstrating integration of MyBatis 3.1, Spring 3.1, and ZK 6.5.0 (MVVM)

To build:
mvn clean install

To deploy:
Move the generated employee-maintenance.war file in target/ to your app server's deploy dir (eg tomcat/webapps) and start your server.

URL http://localhost:8080/employee-maintenance/

NOTES:
Application uses an internal in-memory HSQLDB defined in spring/services-config.xml. You'll see a commented out section
in the config demonstrating using your typical datasouce declaration using an external server DB instance.

You'll notice that I'm using both services classes and MyBatis mappers. Because this application is so simple, you really could just use
the Mappers directly in your ViewModels. All the service class methods do in this application is end up calling the appropriate mapper methods.
However in "real life" I've found that you often perform some other business logic in your service class methods before calling your mappers.
For example your service class for doing an insert might also involve updating some other tables after the insert, or possible sending out a notification message.
Having a service class, can keep unwanted business logic from creeping into your ViewModel.
Of course, feel free to skip using the service class approach if you so desire. There is nothing inherently wrong with
using your mappers directly in your ViewModel.
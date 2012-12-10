mybatis-spring-zk
=================

Sample application demonstrating integration of MyBatis 3.1, Spring 3.1, and ZK 6.5.1.

To build:
mvn clean install

To deploy:
Move the generated employee-maintenance.war file in target/ to your app server's deploy dir (eg tomcat/webapps) and start your server.

URL http://localhost:8080/employee-maintenance/

NOTE: application uses an internal in-memory HSQLDB defined in spring/services-config.xml. You'll see a commented out section
in the config demonstrating using your typical datasouce declaration using an external server DB instance.
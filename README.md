mybatis-spring-zk
=================

Sample application demonstrating integration of MyBatis, Spring, and ZK  

** NOTE ! **
I highly recommend you use the http://www.github.com/rickcr/mybatis-spring-zk-multimodule
project instead of this one. It's more realistic in how you would typically set up a true
project. This one is fine for a standalone self-contained application (for example you 
do not require a persistence jar to be shared with other projects.)

See it running on the RedHat OpenShift cloud at http://mybatisspringzk-simbo1905.rhcloud.com/

**To build:**<br/>
Requires maven<br/>
mvn clean install

**To run:**</br>
(Build first above) then
Type: mvn jetty:run
Use URL: http://localhost:8080
 
**To deploy Option 1:**<br/>
Move the generated mybatis-spring-zk.war file in target/ to your app server's deploy dir (eg tomcat/webapps) and start your server.
Url: http://localhost:8080/mybatis-spring-zk/

**OR To Deploy Option 2. To openshift:**<br/>
Install the tools etc as per https://openshift.redhat.com/community/get-started then:

	# create a diy app
	rhc app create mybatisspringzk diy-0.1
	
	#add the demo code repo to the folder
	cd mybatisspringzk
	git remote add upstream https://github.com/simbo1905/mybatis-spring-zk.git
	
	# in the next command just hit return if asked for a password for downloading the code
	git pull -s recursive -X theirs upstream master
	
	# you can check that 'upstream' points to my demo code and 'origin' points to your server with 
	git remote show upstream
	git remote show origin
	
	# insure the code builds
	mvn -Dmaven.test.skip=true package
	
	# push it up to 'origin' which should be your server which will build it and start the app
	git push
	
	#tail the logs in a second window
	rhc-tail-files -a mybatisspringzk 
	
	#check the details of the url of where the deployed app
	rhc app show --app mybatisspringzk


NOTES:
------
Application uses an internal in-memory HSQLDB defined in spring/services-config.xml. You'll see a commented out section
in the config demonstrating using your typical datasouce declaration using an external server DB instance.

You'll notice that I'm using both services classes and MyBatis mappers. Because this application is so simple, you really could just use
the Mappers directly in your ViewModels. All the service class methods do in this application is end up calling the appropriate mapper methods.
However in "real life" I've found that you often perform some other business logic in your service class methods before calling your mappers.
For example your service class for doing an insert might also involve updating some other tables after the insert, or possible sending out a notification message.
Having a service class, can keep unwanted business logic from creeping into your ViewModel.
Of course, feel free to skip using the service class approach if you so desire. There is nothing inherently wrong with
using your mappers directly in your ViewModel.

There are sample filters included. By default the local is used when you do a build. For this sample application the filters don't do much since we're using a standalone internal db, but in real life you'd have different datasource properties for different environments. The filter will replace the appropriate wildcards in anything under web-inf (you could change this in your pom.) The filter currently replaces the wildcards in web-application.properties and the spring config files (in real life the datasource you see commented out in services-config.xml would be replaced based on your filter.) To build using a different profile, for example the qa version, you'd just switch to building with a different profile from maven: mvn clean install -P qa

[ I still need to clean up the pom.xml some. I know I have some un-needed dependencies in there (I started this project as a port of a more complex project so not everything is completely cleaned up in this simplified application.) ]

MyBatis
-------
Many people might be coming at this from experience with ORMs such as Hibernate. MyBatis isn't an ORM tool but a sql mapping tool. I prefer using MyBatis, but not going to get into all the reasons why in this readme. The basics with Spring here are really simple.

In the services-config.xml you'll see the setup. This tells MyBatis what datasource to use, where the domain objects are located, and where the mapper files are locaated:

 
    <jdbc:embedded-database id="empDS" type="HSQL">
		<jdbc:script location="classpath:/db.script" />
	</jdbc:embedded-database>
	
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="empDS"/>
	</bean>
	
	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
		<property name="dataSource" ref="empDS"/>
		<property name="typeAliasesPackage" value="net.learntechnology.empmaint.domain"/>
	</bean>
	
	<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
		<property name="basePackage" value="net.learntechnology.empmaint.mapper"/>
		<property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
	</bean>

Now you just make a Mapper interface and the mapper xml that holds the sql:

     public interface EmployeeMapper {
		List getAllEmployees();
		void updateEmployee(Employee emp);
		void deleteEmployee(Integer id);
		public Employee getEmployee(Integer id);
		public void insertEmployee(Employee emp);
     }

EmployeMapper.xml

    <?xml version="1.0" encoding="UTF-8" ?> <!DOCTYPE mapper
			PUBLIC "-//ibatis.apache.org//DTD Mapper 3.0//EN"
			"http://ibatis.apache.org/dtd/ibatis-3-mapper.dtd">
	<mapper namespace="net.learntechnology.empmaint.mapper.EmployeeMapper">
	
		 <resultMap id="employeeResult" type="Employee">
			 <result property="id" column="id"/>
			 <result property="firstName" column="firstname"/>
			 <result property="lastName" column="lastname"/>
			 <result property="age" column="age"/>
			 <result property="department.id" column="departmentid"/>
			 <result property="department.name" column="department_name"/>
		 </resultMap>
	
		<select id="getAllEmployees" resultMap="employeeResult">
			SELECT
				emp.id,
				emp.firstname,
				emp.lastname,
				emp.age,
				emp.departmentid,
				dep.name as department_name
			FROM employee emp
			JOIN department dep ON dep.id = emp.departmentid
			ORDER BY firstname, lastname
		</select>
                
         .....
 
Note you don't always have to use a ResultMap when your columns match up to your properties:

Example not using a ResultMap needed:

	<select id="getAllDepartments" resultType="net.learntechnology.empmaint.domain.Department">
		SELECT id, name FROM DEPARTMENT
	</select>

To use our mapper we just declare it as a resource. SIMPLE!:
 
	@Service
	public class EmployeeServiceImpl implements EmployeeService {
	
		@Resource
		private EmployeeMapper employeeMapper;
	
		public List getAllEmployees() {
			return employeeMapper.getAllEmployees();
		}

         .....


Example service being used in a ZK ViewModel:

 
	public class EmployeeVM {
		 
		List<Employee> employees;
	
		@WireResource
		private EmployeeService employeeService;
	
		@Init
		public void init() {
			employees = employeeService.getAllEmployees();
		}






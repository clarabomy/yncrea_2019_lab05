# Lab 05

## Intro
Several goals for this practice:
* Create a SOA architecture in order to make 2 JEE apps communicate.
* You will reuse, once again, the core of Lab03
* A webapp (war) will communicate with a shell app (jar)

## DB 
* Reuse the schema named `yncrea_lab03`
* We assume that your DB credentials are `root:root`

## lab05-server
Create a new Maven module called **lab05-server**. Its packaging is `war`

For this module, you have to declare, in the `pom.xml` file, the following dependencies.

|groupId   |artifactId      |  version | scope|
|----------|-------------|------|---|
| ${project.groupId} | lab05-core | ${project.version}||
| ${project.groupId} | lab05-contract | ${project.version}||
| javax.servlet | javax.servlet-api | 3.1.0 | provided |
| ch.qos.logback | logback-classic | 1.2.3 ||
| org.springframework | spring-webmvc | 5.1.9.RELEASE || 
| org.apache.cxf | cxf-rt-frontend-jaxws | 3.3.3 ||
| org.apache.cxf | cxf-rt-transports-http | 3.3.3 ||

## lab05-client
Create a new Maven module called **lab05-client**.

For this module, you have to declare, in the `pom.xml` file, the following dependencies.

|groupId   |artifactId      |  version | scope|
|----------|-------------|------|---|
| ${project.groupId} | lab05-contract | ${project.version}||
| ch.qos.logback | logback-classic | 1.2.3 ||
| org.springframework | spring-context | 5.1.9.RELEASE ||
| org.apache.cxf | cxf-rt-frontend-jaxws | 3.1.9 ||
| org.apache.cxf | cxf-rt-transports-http | 3.1.9 ||

## lab05-contract
Create a new Maven module called **lab05-contract**.

No dependency for this module.

Create 2 packages:
* `yncrea.lab05.contract.dto`
* `yncrea.lab05.contract.facade`

You will declare `CompanyDTO`, a simple POJO with a name attribute. Don't forget to add all the boilerplate (getters, setters, empty constructor, ...)

For the facade, declare a `CompanyWS` interface, annotated with `@WebService`and defining 2 methods:
* `getAllCompanies` which returns a collection of `CompanyDTO`
* `saveCompany` which takes a `Company` parameter.

That's it for the contract, it is already finished!

## Back to the server
### The main service
In the `yncrea.lab05.web.service.impl`, create a new class `CompanyWSImpl` which implements an interface you have to guess :P

Annotate this new class with `@Named("companyWS")` and `@WebService(endpointInterface = "yncrea.lab05.contract.facade.CompanyWS")`

You now have to implement the methods of this service. The `CompanyService` will help you because it provides all the necessary code to fetch the DB.

Be smart, the `CompanyService` handles `Company` entities while your methods should handle DTO's! Write the translation between entities and DTO's.

### The Initializer
In `yncrea.lab05.web`, create an `Initializer` class inspired by the Initializer of Lab04.
* an additional root config class should be declared : `WSConfig`
* No servlet config class
* Override the `onStartup` method
  * call the inherited `onStartup`
  * call the `addServlet` method on the `servletContext`
    * The new servlet is named `cxfServlet`
    * The new servlet is an implementation of `CXFServlet`
    * The new servlet will be mapped to the `/services/*` URL pattern
    
Here is a [tip](https://stackoverflow.com/questions/21244066/spring-javaconfig-add-mapping-for-custom-servlet)

### Configuration
In `yncrea.lab05.web.config` create `WSConfig`
* Add the usual configuration annotations to let Spring detect that it is a configuration class and to specify which package should be scanned.
* Add `@ImportResource` which will import the `classpath:META-INF/cxf/cxf.xml` file.
* Inject a CXF `Bus` bean and the `StudentWS` bean as well
* Declare a `Endpoint` bean
   * instanciate an `EndpointImpl` object with the two injected beans passed in the constructor.
   * its address will be `"/company"`
   * publish the bean
   
## Deployment
Deploy you webapp then test the `/services/` URL, you should see something magic!

## Back to the client
### Configuration
In `yncrea.lab05.client.config`  package, declare `AppConfig`. Guess its annotation ;)

Declare a bean of type `CompanyWS`.
* Instanciates a `JaxWsProxyFactoryBean`
* its "service class" is the `CompanyWS` one.
* its address is the adress of your company endpoint in the server (guess it!)
* call the `create method` of this object in order to get a generated implementation of `CompanyWS`. Return this new implementation.

### The client app
In `yncrea.lab05.client`, create the `ClientApp` class.
It creates a Spring context, get the bean for `CompanyWS` then list in the console all the companies of the remote server.

Try to save a new company, you won't realize that there are network communication! It's SOAP magic!

 ## Logs
 A good developer write some logs ;)


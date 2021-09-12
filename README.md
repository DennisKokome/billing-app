
<h3>Purpose</h3>
The purpose of this application is to demonstrate simple object oriented principles using test driven approach
leveraging best coding practices while exposing and documenting api.

<h3>What's inside</h3>
This is a working application with a minimal setup. It contains:

Simple spring boot application
setup script to prepare project
swagger configuration for api documentation 

<h3>Running the application</h3>

- Open application.yml on path "src/main/resources/application.yml" to choose between running on postgres or mysql
- Create the database billing on mysql or postgres
- run the application with command "mvn clean spring-boot:run" or right clicking on the application's main file and selecting run
- Follow apis to add and view data as well as generate reports

<h3>Running the test</h3>

- test cases run on h2 database
- navigating to folder "src/test/java/kokome/billing" open the junit test you want to run and click the play button

<h3>Api Documentation</h3>
When the application is runnign api's are accessed on the url's below
- http://localhost:8080/swagger-ui/index.html?configUrl=/billing-docs/swagger-config#/
- http://localhost:8080/billing-docs

<h3>Code coverage</h3>
- run the test with maven
- on the project directory, navigate to "target/site/jacoco" and open the index.html file 
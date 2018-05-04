# Find Minimum Fleet Engineers to Help the Manager :)

## The Problem

Find the minimum number of FEs which are required to help the FM so that every scooter in each district of Berlin is maintained. Note that you may choose which district the FM should go to.


### Stack

 - String WebFlux + Spring Boot
 - Lombok (please don't forget to enable annotation processing in the compiler)
 - Netty as HTTP Non-blocking IO
 - Spring Test + Junit
 - Gradle For Building
 
There are comments through the code that explain some small decisions and the thought process


###Running the App

The Solution runs behind an HTTP layer that has the following endpoint 


/fleet-engineers/minimum-engineers

#####E.g:

http://localhost:8080/fleet-engineers/minimum-engineers

It can either be started from an Idea, such as IntelliJ IDEA, by running the class org.spring.web.experiments.springwebexperiments.SpringWebExperimentsApplication
or by executing the jar created from after the build.


Then an HTTP client, or curl should be used against the endpoint above in a POST request
using the format below:

```
{ scooters: [11, 15, 13],
C: 9,
P: 5
}
```


###Building 

The application is built using gradle. Once gradle is installed in the running machine, the artifact can be built
by using the command in a regular terminal, or Windows cmd window.

The command should be run on the project root folder

```
../spring-web-experiments$ gradle build 
```

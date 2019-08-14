[![Build Status](https://api.travis-ci.com/jullierme/revolut-test.svg?branch=master)](https://travis-ci.org/jullierme/revolut-test)


# Revolut test
Design and implement a RESTful API (including data model and the backing implementation)  for money transfers between accounts.  

## Explicit requirements:  

1. You can use Java, Scala or Kotlin.  
2. Keep it simple and to the point (e.g. no need to implement any authentication).  
3. Assume the API is invoked by multiple systems and services on behalf of end users.  
4. You can use frameworks/libraries if you like (except Spring), but don't forget about  
requirement #2 – keep it simple and avoid heavy frameworks.  
5. The datastore should run in-memory for the sake of this test.  
6. The final result should be executable as a standalone program (should not require  
a pre-installed container/server).  
7. Demonstrate with tests that the API works as expected.  

## Implicit requirements:  
9. The code produced by you is expected to be of high quality.  
10. There are no detailed requirements, use common sense.


# Information

### Libraries

#### Web container to serve the app 

   - Jetty

#### REST API (JAX-RS)
   
   - Jersey 
   
#### In-memory database 

   - H2
   
#### Database migration 
   
   - Flyway
 
#### System logs

   - Log4j2
    
#### Load application properties file

   - Commons Configuration2
   - Commons Beanutils

#### Unit test

   - JUnit 5
   - Mockito

#### Hosted continuous integration service

   - Travis CI
   
# Instructions

### Build

    $ gradle build

### Run

    $ java -jar revolut-1.0-SNAPSHOT-all.jar
    

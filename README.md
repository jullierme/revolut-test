[![Build Status](https://api.travis-ci.com/jullierme/revolut-test.svg?branch=master)](https://travis-ci.org/jullierme/revolut-test)
[![Coverage Status](https://coveralls.io/repos/github/jullierme/revolut-test/badge.svg?branch=master)](https://coveralls.io/github/jullierme/revolut-test?branch=master)
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
 
#### Load application properties file

   - Commons Configuration2
   - Commons BeanUtils

#### Unit test

   - JUnit 5
   - Mockito
   
#### Hosted continuous integration service

   - Travis CI
   
#### Hosted code coverage

   - Coveralls
    
#### Local code coverage 

   - JaCoCo
  
#### RestFul integration test
 
   - REST Assured
      
#### JDBC Connection Pool (Allow get async connections)

   - Tomcat JDBC
         
# Instructions

### Test

    $ gradlew test
    
### Build

    $ gradlew build

### Run

    $ java -jar revolut-1.0-SNAPSHOT-all.jar
    
    
### Server
   
   The Application starts a jetty server on localhost port 8080 with two sample accounts 
   
- http://localhost:8080/api/account/18181818
##### Response:
```sh
{
    "id": 18181818,
    "name": "Jullierme Barros",
    "balance": 1000000.00
} 
```
- http://localhost:8080/api/account/17171717
##### Response:
```sh
{
    "id": 17171717,
    "name": "Manoela Barros",
    "balance": 1000000.00
} 
```

### Available Services

| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| POST | /api/account | create a new account
| GET | /api/account/{accountId} | get account by accountId | 
| POST | /api/transaction | perform transaction between 2 accounts | 
| GET | /api/transaction/{transactionId} | get transaction by id | 
 
### Http Status
- 200 OK
- 400 Bad Request 
- 404 Not Found
- 500 Internal Server Error 

### Sample JSON for Account and Transaction
#### Account:
##### Request: 
```sh
{  
   "name":"Jullierme",
   "balance":100
} 
```
##### Response:
```sh
Header:
"Location":"http://localhost:8080/api/account/18181819"
```

#### Transaction:
##### Request:
```sh
{  
   "fromAccountId":18181818,
   "toAccountId":17171717
   "amount":10,
}
```

##### Response:
```sh
{
    "id": 1,
    "fromAccountId": 18181818,
    "toAccountId": 17171717,
    "amount": 10.00
}
```

#### More information

 - To ensure that an account does not transfer amounts without balance, the transfer method uses database transaction control.  
 - To check asynchronous tests, please see the classes * StressTest.java


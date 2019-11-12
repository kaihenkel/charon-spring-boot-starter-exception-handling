# charon-spring-boot-starter-exception-handling
A Desired Exception Handling example

This is a simple maven based  example project to demonstrate the problem.

## The Idea:

We want to use the charon reverse proxy to access some protected resources. These resources can be configured in/acitve. 
Depending on this status I want to allow the access or redirect to an error page with a customised status-code. 

I wanted to use the Spring-default exception handling for this problem and handle the status check of my resources 
within a custom ForwardingInterceptor - if the resource is not available I throw an exception.

## The Problem

When throwing an exception within the interceptor, even an ResponseStatusException, 
the status code is allways set to SC_INTERNAL_SERVER_ERROR by org.apache.catalina.core.StandardWrapperValve:exception.

The Spring Request exception handlers all depend on the MVC chain and exception throws within them. 
All custom exception handlers only work within them, namely exceptions throws within the Controllers.

The Charon - proxy - Interceptor chain is filter based and therefore outside of the spring mvc exception handling block.

## The Solution

The BasicErrorController is used to display error pages even from exceptions throws within the charon ecosystem. 
To utilize the default-error handling mechanisms of spring all we have to do is re-change the http-status of the 
response and the mapped attributes according to the underlying exception.

This can be done by extending the BasicErrorController and overriding the two methods. 

    protected HttpStatus getStatus(HttpServletRequest request)
    
    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace)
    
  
For more details see  [ErrorController](./src/main/java/com/example/controller/ErrorController.java)

This is only a simple example and works for error thrown within the "normal" spring filter system as well.

## Example

To demonstrate this error handling start the system and call

<http://localhost:8080/testerror> to throw an ResponseStatusException from withn the TestForwadingInterseptor.

<http://localhost:8080/message> will throw an exception from within a "normal" controller

<http://localhost:8080/filter> will throw an exception from within a "normal" spring filter



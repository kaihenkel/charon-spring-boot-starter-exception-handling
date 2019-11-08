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

## One Aproach

Implement an error-handling interceptor that mimics the spring-boot error handling aproach. 
I can do that but was wondering if there was a more elegant solution since this would be reinventing the weel :)

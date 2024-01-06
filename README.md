# Serverless Spring Boot 3 example
A basic application to convert exchange rate between UAH and CZK using Privatbank and Mastercard APIs. The `LambdaHandler` object is the main entry point for Lambda.

The application can be deployed in an AWS account using the Serverless Application Model. The `template.yml` file in the root folder contains the application definition.

## Pre-requisites
* [Java 17](https://jdk.java.net/archive/)

## Packaging
In a shell, navigate to the sample's folder and use the maven wrapper command to build a deployable package (zip file)
```
$ mvn clean package
```
University Name: http://www.sjsu.edu/ 
Course: Cloud Technologies
Professor Sanjay Garje 
ISA: Anushri Srinath Aithal 
Student: Chaitrali Deshmukh 


Project 1: FileZila

The FileZila application is a simple cloud-based web application which can be used for uploading, deleting and downloading the files on the cloud platform. This application leverages different cloud services including Route53, EC2, Cloud Watch, RDS, CloudFront, S3, SNS, load balancing and auto scaling. 


Pre-requisites to run this application:

Create an AWS account.
Create a S3 bucket to upload the files 
Create an IAM user with security policy to access through java application.
Create A Mysql RDS instance and update the application.properties file in spring boot application.

Softwares:
Java JDK 8
Eclipse Oxygen
Navicat or MySql workbench

Download the source code from GIT and import it as a maven project into eclipse. When the project is imported the maven pom.xml will resolve and import all the dependencies. The dependencies include AWS SDK, Spring Boot.
Run the spring boot project

![ArchitectureDiagram](https://github.com/ChaiDeshmukh/CloudProject1/blob/master/ArchitectureDiagram.PNG)



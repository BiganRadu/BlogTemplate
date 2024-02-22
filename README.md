# Blog Template Web App

Welcome to the Blog Template Web App! This project provides a robust foundation for creating your own fully functional blog platform. Built using Java Spring and Thymeleaf, it offers essential features such as user authentication, post management, comments, and various static pages.

## Features

- **Main Page:** Displays references to all posts.
- **Register Page:** Allows users to create a new account.
- **Login Page:** Provides authentication for registered users.
- **Contact Page:** Enables users to reach out with queries or feedback.
- **About Page:** Offers information about the blog and its creators.
- **Add Post Page:** Lets authenticated users create new blog posts.
- **Edit Post Page:** Allows authors to modify existing posts.
- **Individual Post Pages:** Dedicated pages for each blog post with comments section.

## Authentication

Basic Spring Security authentication is implemented, ensuring secure access to the app using email and password credentials.

## Database

The application is powered by a MySQL database, providing robust and scalable storage for blog posts, user data, and comments.


## Preview

![https://prnt.sc/42OGPWO--Yko](https://prnt.sc/42OGPWO--Yko) <!-- Insert screenshot of main page here -->
![Login Page Preview](https://prnt.sc/8TRzxdFX6hQ2) <!-- Insert screenshot of login page here -->
<!-- Add more screenshots as needed -->

## Live Demo

The application is deployed as a Docker container on Microsoft Azure. You can access the live demo for testing purposes at [http://blogtemplate.ffaefgc0fsccbthu.germanywestcentral.azurecontainer.io/](http://blogtemplate.ffaefgc0fsccbthu.germanywestcentral.azurecontainer.io/). If you want to use an admin account for testing, you can use an account with the following credentials : 
email : test@gmail.com 
password : test 

## Getting Started

To run the application locally, follow these steps:

1. Clone this repository.
2. Configure your environment variables for database connection and Spring Security.
3. Configure the database in MySQL using the sqlschema.sql provided.
4. Configure the email account in BlogController for sending emails to the owner when an user completes the contact form. 
5. Build and run the application using Maven.

```bash
git clone https://github.com/your_username/your_repository.git
cd your_repository
# Set up your environment variables
# Set up database
# Set up the email account to work on a SMTP server
mvn spring-boot:run

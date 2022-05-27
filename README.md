# Movie-List
A Movie Gallery Website where you can share movies, add comments to existing movies

## Features
In this project there are 3 types of roles (Guest, User, Admin)

### Guest
###### A Guest user which has no account in the database
+ Guests can see all the movies that have been added to the database
+ Guests can see movie's details page and all the comments that have been posted for it
+ Guests can search a movie (by its name or its genre or its actors)

### User
###### User account which has an entry in the database with 'USER' role
+ Users can do everything that a guest can do
+ Users can add Language/Genre/Actor and Movie
+ Users can post comment to a movie

### Admin
###### Admin account which has an entry in the database with 'ADMIN' role
+ Admins can do everything that a user can do
+ Admins can update & delete Language/Genre/Actor and Movie entries
+ Admins can delete any comment

## How To Run The Project
This project uses MySQL as its database so you will need to have it installed on your PC

The part below of application.properties (can be found on /src/main/resources/) should be edited with an IDE or text editor to match your MySql credentials.
For my case movielistdb is my MySql Schema name, you can leave it as it is or change it if you like. But you have to create the Schema manually from MySql Workbench 

![image](https://user-images.githubusercontent.com/83312431/170697679-e37a1481-c9b5-4c6b-89f1-5554e9fa34d3.png)

On the first run of this project while there is no user entry on the database an Admin account will be created automatically. The credentials are as follows:

Mail:     sefa@admin.com

Password: 123456

You can change this by editing MovielistApplication.java file and you can add new admin account(s) by changing its role from MySql Workbench after you create
an account from register page


#### Run With IDE (e.g. IntelliJ)
Just Run the project from IDE and type http://localhost:8080/ to your browser once the application is up and running

#### Run Without IDE
You can build the application with using Apache Maven

Download Maven: https://maven.apache.org/download.cgi

Install Maven: https://maven.apache.org/install.html

+ After you complete installing Maven go inside the project folder and open a Command Prompt with Administrative Privileges and type the codes below with order:
  * mvn test
  * mvn clean install
+ Once they finished you will get a .jar file inside /ProjectFolder/target/
+ Run the .jar file and type http://localhost:8080/ to your browser

## Screenshots
#### Home page where u can see all the movies that have been added
![image](https://user-images.githubusercontent.com/83312431/170699121-dc939601-bb17-4f50-9509-2f43ee66b288.png)

#### Login & Register Page
![image](https://user-images.githubusercontent.com/83312431/170699234-436dae2f-7c8a-483b-8e38-32367ffdd770.png)

#### Movie Details Page (Post Comment Section can only be seen and done by a User or an Admin)
![2022-05-27 14 57 52](https://user-images.githubusercontent.com/83312431/170695422-886ea10f-302d-4586-bbae-5e1e33650da3.png)

#### Navigation Panel (Can't be accessed by guests)
![image](https://user-images.githubusercontent.com/83312431/170700221-ae5b3257-9217-4fb8-b7be-df621623e8e4.png)

#### Add Movie
![image](https://user-images.githubusercontent.com/83312431/170700513-5dd94c47-8140-48d4-a3cc-12edf9006624.png)

## Screenshots (Admin Only)
#### Admin Panel
![image](https://user-images.githubusercontent.com/83312431/170700727-3cd7f8a1-8867-4636-ac0b-f8611196f5bc.png)

![image](https://user-images.githubusercontent.com/83312431/170701121-0456ca20-2944-44ce-8e91-4e45a44cf45b.png)

![image](https://user-images.githubusercontent.com/83312431/170701191-74436b2f-7cf9-4fcf-b944-5d9afc303122.png)

![2022-05-27 15 44 03](https://user-images.githubusercontent.com/83312431/170701915-35f026f2-8b24-41d5-92cd-4d57bc4893c5.png)








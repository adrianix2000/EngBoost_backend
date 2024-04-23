# EngBoost
## Backend part
---
EngBoost is application for learning English words and phrases. 


#### The main functionalities of the application.
Unregistered user:
-  Creating an account
-  Browsing collections of words created by other users, and tests quizzes composed of these words.

Registered user:
- can create their own collections of words, by adding words manually or by uploading a CSV file with data.
- User can also manage individual word collections by performing typical CRUD operations on the word collection: deleting, renaming, changing visibility to other users


#### The backend technologies I used:

To store data, I utilized a PostgreSQL database.

- Java, version 17
- Maven, version 3.9.3
- Spring boot, version 3.1.7
    *  Spring Data JPA to connect to the database using the ORM protocol.
    * . Spring Web, to communicate with Angular client by exposing RESTful endpoints.
    *  Spring Security to ensure the security of the application, for example by securing endpoints that expose data only to authorized users. For this purpose, I use JWT token technology.
    *  Driver for connecting to a PostgreSQL database

#### What I plan to add in the application's functionalities

Adding the ability to solve tests and keep statistics about the number of learned phrases and words.
Deploying the application in Docker, utilizing Docker Compose
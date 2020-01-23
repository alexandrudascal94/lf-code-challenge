# Labforward Code Challenge for Backend Engineer Candidate

This is a simple Hello World API for recruiting purposes. You, as a candidate, should work on the challenge on your own account. Please clone the repo to your account and create a PR with your solution. 

## Introduction

You can run the application by typing

	./gradlew bootRun

This will start up a Spring Boot application with Tomcat server running on 8080.

Show all other possible tasks:

	./gradlew tasks
	
## Your Task	

You need to add a new endpoint to the API to allow users to *update the greetings they created*. 


## Implementation

I took advantage of beeing and open-ended task and many changes in the code that how would see the code has to look like.

Changes:

1. Added an repository layer which decoupled data layer from service. Added JPA repository and H2 databse for storing data.
2. Added DTO for REST apis even entity is is very basic and mapping looks like an code duplication, it decouples user input and data that is exposed to the client and make service layer decoupled.
3. Renaming from 'hello' -> 'greeting'

### 1. GET /hello  sample
Request for default greeting.

response body:
```json
{
    "id": "default",
    "message": "Hello World!",
    "language": "en"
}
```

### 2. GET /hello/{id}/ sample 
Request greeting by id.

response body:
```json
{
    "id": "c2b57a87-bb5f-43d6-95e8-1cb39f87937e",
    "message": "Boungiorno",
    "language": "it"
}
```

### 3. POST /hello  sample
Create greeting.

request body:
```json
{
	"message": "Boungiorno",
	"language": "it"
}
```

response body:
```json
{
    "id": "b0343212-d57b-42a8-ac63-ab6827e586ea",
    "message": "Boungiorno",
    "language": "it"
}
```

### 4. PUT /hello/{id} sample
Update greeting.

request body:
```json
{
	"message": "Hello",
	"language": "en"
}
```

response body:
```json
{
    "id": "b0343212-d57b-42a8-ac63-ab6827e586ea",
    "message": "Hello",
    "language": "en"
}
```

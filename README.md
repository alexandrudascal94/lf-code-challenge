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


Request for default greeting:

### GET /hello  sample

response:
```json
{
    "id": "default",
    "message": "Hello World!",
    "language": "en"
}
```

### GET /hello/{id}/ sample 

response:
```json
{
    "id": "c2b57a87-bb5f-43d6-95e8-1cb39f87937e",
    "message": "Boungiorno",
    "language": "it"
}
```

### POST /hello  sample

request body:
```json
{
	"message": "Boungiorno",
	"language": "it"
}
```

response:
```json
{
    "id": "b0343212-d57b-42a8-ac63-ab6827e586ea",
    "message": "Boungiorno",
    "language": "it"
}
```

### PUT /hello/{id} sample

request:
```json
{
	"message": "Hello",
	"language": "en"
}
```

response:
```json
{
    "id": "b0343212-d57b-42a8-ac63-ab6827e586ea",
    "message": "Hello",
    "language": "en"
}
```
# inventory

[![Build Status](https://travis-ci.org/robertoduessmann/inventory.svg?branch=master)](https://travis-ci.org/robertoduessmann/inventory)

> Microservice to control a product inventory. <br>
> Also, a Spring Boot REST app template, with Hibernate, PostgreSQL and Docker.


## Installation

### Build
```console
$ mvn clean install
```

### Running
Create a database and configure in properties file: [application.properties](https://github.com/robertoduessmann/inventory/blob/master/src/main/resources/application.properties)

After, run the app:
```console
$ mvn spring-boot:run
```

### Running with Docker

Build image from a Dockerfile:
```console
$ docker build -t inventory:latest .
```

Run docker:
```console
$ docker run -p 8080:8080 --env DATABASE_URL=<DATABASE_URL> --env DATABASE_USER=<DATABASE_USER> --env DATABASE_PASSWORD=<DATABASE_PASSWORD> <DOCKER_APP_ID>
```
## Usage

### POST /products
```console
$ curl -X POST https://keepinventory.herokuapp.com/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Ball","description":"Soccer ball","quantity":10}'
```

### GET /products/{id}
```console
$ curl -X GET https://keepinventory.herokuapp.com/products/2
```
```json
{
   id: 2,
   name: "Ball",
   description: "Soccer ball",
   quantity: 10
}
```

### PUT /products
```console
$ curl -X PUT https://keepinventory.herokuapp.com/products/2 \
  -H "Content-Type: application/json" \
  -d '{"name":"Ball","description":"Soccer ball","quantity":11}'
```

### DELETE /products/{id}
```console
$ curl -X DELETE https://keepinventory.herokuapp.com/products/2
```

### GET /products/find/{name}
```console
$ curl -X GET https://keepinventory.herokuapp.com/products/find/Ball
```
```json
[
	{
	   id: 2,
	   name: "Ball",
	   description: "Soccer ball",
	   quantity: 10
	}
]
```

## License
The MIT License (MIT)
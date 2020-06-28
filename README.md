# Micronaut Example Project #

This is a sample micronaut app to demonstrate usage of some basic capabilities used in microservices.

### Default credentials
Default credentials are (defined in application.yml):
```
username: admin
password: admin
```

Run the application and send a request:


### List all documents
```bash
curl --user admin:admin localhost:8080/devices
```

### Create a document
```bash
curl -X POST --user admin:admin localhost:8080/devices -d '{"name": "MyTestDevice","hardwareUuid": "54855d5d-b02b-472b-92b2-56bbb18beece"}' -H 'Content-Type:application/json'
```

### Read a document
```bash
curl --user admin:admin localhost:8080/devices/MyTestDevice
```

### Delete a document by name
```bash
curl -X DELETE --user admin:admin localhost:8080/devices/MyTestDevice
```

You can run Mongo in a Docker container:
```bash
$ docker run  -p 27017:27017 bitnami/mongodb:latest
```
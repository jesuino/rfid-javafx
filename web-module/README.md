# People management
Application to expose a people operations using a secured REST interface

# Building 
We use Maven, have maven installed (plz google it) then use:

```
mvn clean package -DskipTests
```

# Running
It should run on any application server, but it was tested on JBoss AS 7.2. You can copy the resulting `people-management.war` in the `target` to the `$WILDFLY_HOME/standalone/deployment` directory and start wildfly using `$WILDFLY_HOME/bin/standalone.sh`.
Then you can access a sample resource on http://localhost:8080/people-management/rest/person/rfid/abc


# Testing

To see all the people on the DB

```
curl http://localhost:8080/people-management/rest/person
```

Add a new one

```
curl -X POST --data '{"firstName":"John","lastName":"Marx","rfid":"bca","job":"Lawer","age":30,"roles":[{"id":1,"name":"Admin"}]}' -H 'Content-type: Application/json' http://localhost:8080/people-management/rest/person
```

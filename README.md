## Running
Using java 11
```
mvn clean install
```

Then
```
java -jar target/CryptoPunksApi-1.0-SNAPSHOT.jar server config.yml
```

That will have run the tests too, but to run them alone:
```
mvn test
```

## Endpoints
### Return all of the crypto-punks currently on sale and the info returned from the contract
```
curl http://localhost:8080/crypto-punks/
```

### Return this crypto punk and the attributes associated with them if there are some stored
```
curl http://localhost:8080/crypto-punks/[int]
```

First foray into blockchain and kept everything quite light around it.

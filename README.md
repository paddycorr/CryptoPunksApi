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
config.yml:
```
ethereum.endpoint: [Local or public ethereum network (i used infura.io)]
contract.address: 0xb47e3cd837dDF8e4c57F05d70Ab865de6e193BBB (CryptoPunks address)
private.key: d454a7825c2af4a4ff2144f9eb6ef28916081f6310c86624efce70ceb16679e1 (randomly generated dont worry)
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

Build each project
- workflow-lib
- workflow-worker
- workflow-connector
with `mvn install`.

~~In this root directory execute: `dapr run -f .`~~ (This isn't working)

Run the worker with
```
dapr run --app-id worker --dapr-grpc-port=50001  -- java -jar target/workflow-worker-0.0.1-SNAPSHOT.jar
```

And the connector with
```
java -jar target/workflow-connector-0.0.1-SNAPSHOT.jar
```

Fire a HTTP request like
```
curl http://localhost:8080/patient/1
```

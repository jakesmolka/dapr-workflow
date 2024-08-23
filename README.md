Build each project
- workflow-lib
- workflow-worker
- workflow-connector
with `mvn install`.

In this root directory execute: `dapr run -f .`

Fire a HTTP request like
```
curl http://localhost:8080/patient/test
```

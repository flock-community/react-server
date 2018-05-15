
Prerequisites
=============
- Docker
- Your favorite editor

Scripts
=======

### For Windows powershell:

```
.\make.ps1 fe build
.\make.ps1 fe run
```
```
.\make.ps1 be build
.\make.ps1 be run
```
```
.\make.ps1 stack build
.\make.ps1 stack run
```
---
### For Linux or Mac:
```
make fe-build
make fe-run
```
```
make be-build
make be-run
```
```
make stack-build
make stack-run
```

Development
===========
## Frontend
In docker container run:
```
npm i
npm run build
npm run serve
```
To start Express server and expose it on port 8080 to see server side rendering in action.
## Backend
In docker container run:
```
mvn clean install && java -jar target/server*.jar
```
To start Spring boot and expose it on port 8080 to see server side rendering in action.

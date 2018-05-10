
Prerequisites
=============
- Docker
- Your favorite editor

Scripts
=======
## To start development on the Frontend:
### For Windows powershell:

```
.\make.ps1 fe build
.\make.ps1 fe run
```
---
### For Linux or Mac:
```
make fe
make fe-run
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
To start Express server and expose it on port 4000 to see server side rendering in action.

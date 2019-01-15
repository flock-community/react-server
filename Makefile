fe-destroy:
	docker rmi react-server-fe
.PHONY: fe-destroy

fe-build:
	docker build -f Dockerfile-fe -t react-server-fe .
.PHONY: fe-build

fe-run:
	docker run -p 8080:8080 -it --name frontend --rm -v $(shell pwd)/src/main/frontend:/app react-server-fe bash
.PHONY: fe-run

be-destroy:
	docker rmi react-server-be
.PHONY: be-destroy

be-build:
	docker build -f Dockerfile-be -t react-server-be .
.PHONY: be-build

be-run:
	docker run -p 8081:8080 -it --name backend --rm -v $(shell pwd):/app -v ~/.m2:/root/.m2 react-server-be bash
.PHONY: be-run

graal-destroy:
	docker rmi react-server-graal
.PHONY: graal-destroy

graal-build:
	docker build -f Dockerfile-graal -t react-server-graal .
.PHONY: graal-build

graal-run:
	docker run -p 8081:8080 -it --name graal --rm -v $(shell pwd):/app -v ~/.m2:/root/.m2 react-server-graal bash
.PHONY: graal-run

stack-destroy:
	docker rmi react-server-stack
.PHONY: stack-destroy

stack-build:
	docker build -f Dockerfile-stack -t react-server-stack .
.PHONY: stack-build

stack-run:
	docker run -p 8080:8080 -d --name stack --rm react-server-stack
.PHONY: stack-run

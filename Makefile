stack-build:
	docker build -f Dockerfile-full -t react-server-full .
.PHONY: stack-build

fe-build:
	docker build -f Dockerfile-fe -t react-server-fe .
.PHONY: fe-build

stack-run:
	docker run -p 8080:8080 --name stack --rm react-server-full
.PHONY: stack-run

fe-run:
	docker run -p 4000:4000 -it --name fe --rm -v $(shell pwd)/src/main/frontend:/app react-server-fe bash
.PHONY: fe-run

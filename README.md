# grizzly-jersey-service

A Java REST API based on [Grizzly](https://grizzly.java.net/) and [Jersey](https://jersey.java.net).

## Feature:

- Jersey configuration
- Async request processing

## How-To:
Build the service in a docker container:
```
docker run --rm -v $(pwd):/app jaysong/gradle:8-2.13 installDist
```
Run the service in a docker container:
```
docker run -it --rm --expose 8080 -p 8080:8080 -v $(pwd)/tmp:/data -v $(pwd)/build/install/app:/app jaysong/jrunner:8-jre-alpine ./bin/app
```

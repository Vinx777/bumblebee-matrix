FROM sbtscala/scala-sbt:openjdk-17.0.2_1.8.0_2.12.17 as build
ENV TZ Asia/Tokyo

RUN apt update && apt install -y make

WORKDIR /code

COPY . /code

RUN make build



FROM openjdk:17-slim-buster

RUN apt update && apt install -y wget dnsutils unzip curl

RUN adduser --disabled-password --gecos '' spring
WORKDIR /app

COPY --from=build /code/server/target/universal/server-*.zip ./server.zip

RUN unzip ./server.zip && rm -rf server.zip && folder=`ls`  && mv $folder server

RUN mkdir -p /var/log/bumblebee-matrix && chmod 766 /var/log/bumblebee-matrix

WORKDIR /app/server/bin

USER spring:spring

ENTRYPOINT ["./server"]

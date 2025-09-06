FROM eclipse-temurin

WORKDIR /src/main

COPY . .

RUN mkdir data

RUN javac src/ExecutionCounter.java

CMD [ "java", "ExecutionCounter" ]
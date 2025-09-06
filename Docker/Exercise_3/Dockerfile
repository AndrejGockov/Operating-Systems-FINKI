FROM eclipse-temurin

WORKDIR /src

COPY . .

ENV PERSISTING_FILE_PATH=/usr/src/myapp/data/data.out

RUN javac src/Main.java

CMD [ "java", "Main" ]
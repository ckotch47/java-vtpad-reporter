FROM markhobson/maven-chrome:jdk-20

WORKDIR /app
COPY . /app/.
RUN cd /app


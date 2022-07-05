FROM openjdk:18.0.1-slim

EXPOSE 15602

ARG JAR_FILE=api_gateway-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ARG ONENET_IP
ARG SOFIA_IP

ENV ONENET_IP_ENV=${ONENET_IP}
ENV SOFIA_IP_ENV=${SOFIA_IP}

RUN touch ./entrypoint.sh
RUN echo "java -jar ./app.jar --onenet_ip=${ONENET_IP_ENV} --sofia_ip=${SOFIA_IP_ENV}" > ./entrypoint.sh
RUN chmod +x ./entrypoint.sh
CMD ./entrypoint.sh

FROM openjdk:17-oracle

EXPOSE 15545

ARG JAR_FILE=onenet-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

ARG DB_IP
ARG DB_PORT
ARG DB_NAME
ARG DB_USERNAME
ARG DB_PASSWORD

ARG SOFIA_URI
ARG ORION_CONSUMER_IP
ARG ORION_PROVIDER_IP

ENV DB_IP_ENV=${DB_IP}
ENV DB_PORT_ENV=${DB_PORT}
ENV DB_NAME_ENV=${DB_NAME}
ENV DB_USERNAME_ENV=${DB_USERNAME}
ENV DB_PASSWORD_ENV=${DB_PASSWORD}

ENV SOFIA_URI_ENV=${SOFIA_URI}
ENV ORION_CONSUMER_IP_ENV=${ORION_CONSUMER_IP}
ENV ORION_PROVIDER_IP_ENV=${ORION_PROVIDER_IP}

RUN touch ./entrypoint.sh
RUN echo "java -jar ./app.jar --sofia.db.ip=${DB_IP_ENV} --sofia.db.port=${DB_PORT_ENV} --sofia.db.name=${DB_NAME_ENV} --sofia.db.username=${DB_USERNAME_ENV} --sofia.db.password=${DB_PASSWORD_ENV} --sofia.uri=${SOFIA_URI} --orion.consumer.ip=${ORION_CONSUMER_IP} --orion.provider.ip=${ORION_PROVIDER_IP}" > ./entrypoint.sh
RUN chmod +x ./entrypoint.sh
CMD ./entrypoint.sh

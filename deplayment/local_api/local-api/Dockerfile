FROM adoptopenjdk/openjdk11:jre11u-centos-nightly

EXPOSE 30000

RUN java -version

#Install and configure Apache
#RUN yum -y install httpd mod_ssl
RUN yum install -y httpd*
RUN yum install -y mod_ssl

WORKDIR /etc/httpd/conf

COPY ./data/config/httpd/ssl.conf /etc/httpd/conf.d/
COPY ./data/config/httpd/certs/feg-local.crt /etc/pki/tls/certs/feg-local.crt
COPY ./data/config/httpd/certs/feg-local.key /etc/pki/tls/private/feg-local.key

#Add Jar File
COPY onenet-0.0.1-SNAPSHOT.jar /opt/feg-local/backend/


#Create start.sh
ARG SOFIA_URI
ENV SOFIA_URI_ENV=${SOFIA_URI}

#RUN touch /opt/scripts/entrypoint.sh
#RUN echo "java -jar ./app.jar --sofia.uri=${SOFIA_URI_ENV}" > /opt/scripts/entrypoint.sh
#RUN chmod +x /opt/scripts/entrypoint.sh
COPY data/scripts/start.sh /opt/scripts/
RUN chmod u+x /opt/scripts/start.sh

#Run
ENTRYPOINT ["/opt/scripts/start.sh"]

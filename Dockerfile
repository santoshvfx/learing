#FROM openjdk:8-jdk-alpine
FROM 31392devopsacr.azurecr.io/java/jdk:8
MAINTAINER db737h
WORKDIR /app

#RUN apk --no-cache add bash iptables curl busybox-extras

#RUN addgroup -S spring && adduser -S spring -G spring

#RUN apk add --no-cache --virtual .build-deps g++ python3-dev libffi-dev openssl-dev && \
#    apk add --no-cache --update python3 && \
#    pip3 install --upgrade pip setuptools

RUN apt-get update && apt-get install -y \
	vim \
	net-tools \
	iputils-ping \
	unzip \
	bash \
	iptables \
	curl 


#RUN addgroup -S spring && adduser -S spring -G spring
RUN addgroup --system spring
RUN adduser --system spring --ingroup spring

RUN apt-get install -y build-essential zlib1g-dev libncurses5-dev libgdbm-dev libnss3-dev libssl-dev libreadline-dev libffi-dev wget \
	python3 \
	python-setuptools

	
RUN mkdir -p /app/etc
RUN mkdir /app/glowroot

ARG DEPENDENCY=src/main/resources
COPY ${DEPENDENCY}/cadi.properties /app/etc/cadi.properties
COPY ${DEPENDENCY}/keyfile /app/keyfile
COPY ${DEPENDENCY}/truststore2018.jks /app/truststore2018.jks
COPY ./install/az.k8s.tapm.att.com.crt /app/az.k8s.tapm.att.com.crt
COPY ./install/glowroot.jar /app/glowroot/glowroot.jar
COPY ./bcam.keystore /app/bcam.keystore
COPY ./bcam.truststore /app/bcam.truststore
COPY ./src/main/resources/wildfly-config.xml.NONPROD /app/wildfly-config.xml.NONPROD
COPY ./src/main/resources/wildfly-config.xml.PROD /app/wildfly-config.xml.PROD

RUN keytool -noprompt -import -trustcacerts -noprompt -file /app/az.k8s.tapm.att.com.crt -alias az.k8s.tapm.att.com \
            -keystore $JAVA_HOME/jre/lib/security/cacerts -deststorepass changeit
            
RUN chmod 777 /app/
RUN chmod 777 /app/etc/

ADD target/lim-bis-rest.jar ./lim-bis-rest.jar

LABEL version="1.0" \
      app="LimBisRest" \
      desc="docker for LMS LIM BIS spring boot"

EXPOSE 8080:8080

ENV http_proxy=http://proxy.conexus.svc.local:3128
ENV https_proxy=http://proxy.conexus.svc.local:3128
# Note - this is overridden during deployment
ENTRYPOINT ["java","-Djasypt.encryptor.password=Aa123456","-Dcom.att.ajsc.aaf.cadi.prop.path=/app/etc/cadi.properties","-jar","./lim-bis-rest.jar"]
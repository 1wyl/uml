FROM jack666ie/javamaven:21396
RUN mkdir -p /ssadmin-server/source/picture
RUN mkdir -p /ssadmin-server/source/video
WORKDIR /ssadmin-server

COPY ./target/backend-0.0.1-SNAPSHOT.jar app.jar

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"
ENV ARGS=""

EXPOSE 28080

CMD java ${JAVA_OPTS} -jar app.jar $ARGS

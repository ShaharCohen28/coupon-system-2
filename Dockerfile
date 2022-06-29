FROM open:jsk:16-alpine
COPY ./target/coupon_system_spring-0.0.1-SNAPSHOT.jar /user/app/
WORKDIR /user/app
RUN sh -c 'touch coupon_system_spring-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","coupon_system_spring-0.0.1-SNAPSHOT.jar"]
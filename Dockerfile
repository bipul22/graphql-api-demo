FROM openjdk:8  
EXPOSE 8080
ADD target/graphql-api-demo.jar /graphql-api-demo.jar 
ENTRYPOINT ["java", "-jar","/graphql-api-demo.jar"]
FROM adoptopenjdk/openjdk11:alpine-jre
COPY ./para_test_Backend_Java_Kotlin.txt /
ADD target/consulta-precos-acoes-bolsa-api-*.jar consulta-precos-acoes-bolsa-api.jar
ENTRYPOINT ["java","-jar","consulta-precos-acoes-bolsa-api.jar"]
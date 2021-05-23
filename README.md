# consulta-precos-acoes-bolsa-api
##### Projeto destinado a resolução do desafio proposto pela pagSeguro
## Pré-requitos
- Maven 3.8.1
- Docker 3.3.3+
- Java 11

## Gerando .jar
É necessário rodar o comando abaixo no diretório principal antes de executar o projeto:
```
mvn clean install
```

## Executando o projeto

Ainda no diretório do projeto (raiz do projeto), basta executar o comando:
```
docker-compose up -d
```
O parâmetro -d é opcional, serve apenas para executar os containers em segundo plano.
_Obs.: Certifique-se de que a porta 8080 esteja disponível, ela será usada para expor os end-points da aplicação._
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
docker-compose up --build -d
```
O parâmetro -d é opcional, serve apenas para executar os containers em segundo plano.


_Obs.: Certifique-se de que a porta 8080 esteja disponível, ela será usada para expor os end-points da aplicação._

## Testando o projeto
Para facilitar os testes, foi adicionada a collection do postman referente aos end-points criados.

A baseUrl com base na execução acima é http://localhost:8080, os end-points desta aplicação são:

```
GET /v1/listar/acoes

GET /v1/listar/acoes/precomedio/dezmaiores

POST /v1/adicionar/acao - body required = { "codigo_acao": "String "nome_acao": "String", "quantidade_teorica": numero respeitando o objeto BigInteger, "participacao": numero respeitando o objeto BigDecimal}

GET /v1/buscar/acao/codigo/{codigo} - path param required = código da ação

GET /v1/buscar/acao/nome/{nome} - path param required = nome da ação

DELETE /v1/deletar/acao/{codigo} - path param required = código da ação
```
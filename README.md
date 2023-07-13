# Documentação da Aplicação CRUD Back End com Spring de Produtos

Esta documentação descreve uma aplicação back end utilizando o framework Spring para realizar operações CRUD (Create, Read, Update e Delete) em produtos. A aplicação é construída utilizando as seguintes dependências: Spring Web, Spring Data JPA, PostgreSQL como banco de dados e Validation para validação de dados.

## Estrutura do Projeto

O projeto é organizado em pacotes com o seguinte layout:

- **controller**: Contém a classe `ProductController` responsável por lidar com as requisições HTTP relacionadas aos produtos.
- **dtos**: Contém a classe `ProductRecordDTO`, que é um objeto de transferência de dados usado para receber e enviar informações sobre os produtos.
- **model**: Contém a classe `ProductModel`, que representa a entidade do produto com as variáveis `idProduct` (do tipo UUID), `name` (do tipo String) e `value` (do tipo BigDecimal).
- **repository**: Contém a interface `ProductRepository`, que define as operações de acesso aos dados relacionados aos produtos no banco de dados.

## Dependências Utilizadas

A aplicação utiliza as seguintes dependências:

- **Spring Web**: Fornece as funcionalidades para criar endpoints RESTful e lidar com as requisições HTTP.
- **Spring Data JPA**: Oferece uma camada de abstração para interagir com o banco de dados através de operações de banco de dados comuns.
- **PostgreSQL**: Banco de dados relacional utilizado para armazenar os dados dos produtos.
- **Validation**: Biblioteca utilizada para validar os dados recebidos pela aplicação.

## Configuração do Banco de Dados

A aplicação utiliza o PostgreSQL como banco de dados. Certifique-se de ter o PostgreSQL instalado e configurado corretamente antes de executar a aplicação.

As configurações de conexão com o banco de dados são especificadas no arquivo `application.properties` ou `application.yml`, dependendo da configuração escolhida. É necessário fornecer as seguintes informações:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/nome-do-banco
spring.datasource.username=usuario
spring.datasource.password=senha

```

Substitua "nome-do-banco", "usuario" e "senha" pelos valores corretos para o seu ambiente.

## Funcionalidades da API

A API disponibiliza os seguintes endpoints para realizar operações CRUD nos produtos:

- **GET /products**: Retorna uma lista com todos os produtos cadastrados.
- **GET /products/{id}**: Retorna as informações de um produto específico com base no seu ID.
- **POST /products**: Cria um novo produto com base nos dados fornecidos no corpo da requisição.
- **PUT /products/{id}**: Atualiza as informações de um produto existente com base no seu ID e nos dados fornecidos no corpo da requisição.
- **DELETE /products/{id}**: Exclui um produto específico com base no seu ID.

## Exemplo de Requisição e Resposta

A seguir, temos um exemplo de uma requisição e resposta utilizando a API:

**Requisição:**

```
POST /products
Content-Type: application/json

{
  "name": "Produto A",
  "value": 10.99
}

```

**Resposta:**

```
HTTP/1.1 201 Created
Content-Type: application/json

{
  "idProduct": "8f9914c6-47b0-4f63-9c8b-27c71a979c2a",
  "name": "Produto A",
  "value": 10.99
}

```

## Considerações Finais

Esta documentação fornece uma visão geral da aplicação CRUD Back End com Spring de Produtos, descrevendo sua estrutura, dependências utilizadas e funcionalidades disponíveis. Certifique-se de ter configurado corretamente o ambiente de banco de dados e as dependências antes de executar a aplicação.

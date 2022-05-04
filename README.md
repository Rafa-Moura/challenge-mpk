CRUD PROJETO MPK DEV JR

# Spring Boot, Spring Web, JPA, H2

API desenvolvida para prover um CRUD SIMLES de uma entidade Cliente.

## Steps to Setup

**1. Clonar a aplicação**

```bash
git clone https://github.com/Rafa-Moura/challenge-mpk.git
```

**2. Rodar a aplicação usando MAVEN**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

### Cliente

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /   | Lista todos os clientes cadastrados | |
| GET    | /{id}| Lista um cliente pelo iD | |
| POST   | /   | Insere cliente no banco de dados ||
| PUT    | /{id}| Atualiza um cliente com base no ID|
| DELETE | /{id}| Deleta um cliente com base no ID|

Test them using postman or any other rest client.


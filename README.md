CRUD PROJETO MPK DEV JR

# O Challenge
Criar uma API para realizar um CRUD com uma entidade CLIENTE contendo os dados nome, cnpj, idade e data de nascimento.

### Regras de negócio
- Não é permitido cadastrar clientes menores de 18 anos.
- Não é permitido cadatrar clientes com CNPJ duplicado.

# A solução

Foi desenvolvida uma API prover um CRUD SIMPLES de uma entidade Cliente.
Na resolução foi utilizado o SPRING BOOT, SPRING JPA, SPRING WEB, H2 e SPRING DEVTOOLS.

A solução foi construída utilizando o MAVEN e a versão 17 do Java.

## Passos Para o Teste

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
| POST   | http://localhost:8080/   | Insere cliente no banco de dados ||
| GET    | http://localhost:8080/   | Lista todos os clientes cadastrados | |
| GET    | http://localhost:8080/{id}| Lista um cliente pelo iD | |
| PUT    | http://localhost:8080/{id}| Atualiza um cliente pelo ID|
| DELETE | http://localhost:8080/{id}| Deleta um cliente pelo ID|


## Exemplos de inserção e atualização com CORPO.
- POST
{
    "nome":"Rafael",
    "cnpj":"999999",
    "idade":"18",
    "dataNascimento":"19/07/1995"
}

- PUT
{
    "nome":"Rafael",
    "cnpj":"999999",
    "idade":"18",
    "dataNascimento":"19/07/1995"
}

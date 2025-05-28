# 📦 Cliente API

API REST desenvolvida com **Spring Boot** para cadastro e validação de clientes. A aplicação permite persistir informações como **nome**, **CPF**, **endereços**, **e-mails** e **telefones**, com segurança integrada e tratamento global de exceções.

---

## 🚀 Tecnologias Utilizadas

- Java 8
- Spring Boot 2.7.14
- Spring Security
- Spring Data JPA
- Hibernate
- H2 Database (em memória)
- Lombok
- MapStruct
- Bean Validation (JSR-380)
- Swagger (OpenAPI)

---

## 🔐 Autenticação

A aplicação utiliza autenticação **HTTP Basic** com dois perfis de usuário:

| Usuário | Senha       | Perfil |
|--------|--------------|--------|
| admin  | `123qwe!@#`  | ADMIN  |
| user   | `123qwe123`  | USER   |

---

## 📬 Endpoints Principais

### `POST /clientes`

Cadastra um novo cliente, validando os seguintes dados:

- Nome (mínimo 3, máximo 100 caracteres, apenas letras, números e espaços)
- CPF (armazenado sem máscara, exibido com máscara)
- Telefones (mínimo 1, com tipo obrigatório)
- E-mails (mínimo 1, deve ser válido)
- Endereço (opcional, mas validado se informado)

### `GET /clientes`

Lista todos os clientes cadastrados, exibindo:

- Dados completos
- CPF **com máscara**

---

## 🧪 Testes e Validações

Você pode testar os endpoints usando:

- **Postman**
- **Swagger UI** (`http://localhost:8080/swagger-ui.html` ou `/swagger-ui/index.html`)

### 🛡️ Validações Incluídas

- Campos obrigatórios com mensagens personalizadas
- Máscaras e formato de CPF
- Tipos válidos para telefone
- Validação de e-mails
- Tratamento de erros com `GlobalExceptionHandler` para resposta 400 estruturada

---

## ▶️ Execução Local

### Pré-requisitos

- JDK 8 ou superior
- Maven 3.x

### Passos para rodar o projeto

```bash
mvn spring-boot:run

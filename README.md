# 🍲 LocalFood  

<p align="left">
  <img alt="Java" src="https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white">
  <img alt="Spring Security" src="https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?logo=springsecurity&logoColor=white">
  <img alt="PostgreSQL" src="https://img.shields.io/badge/PostgreSQL-15-4169E1?logo=postgresql&logoColor=white">
  <img alt="Flyway" src="https://img.shields.io/badge/Flyway-Migrations-CC0200?logo=flyway&logoColor=white">
  <img alt="Swagger" src="https://img.shields.io/badge/Swagger-OpenAPI-85EA2D?logo=swagger&logoColor=black">
  <img alt="JUnit" src="https://img.shields.io/badge/Tests-JUnit%205-25A162?logo=junit5&logoColor=white">
  <img alt="Mockito" src="https://img.shields.io/badge/Mockito-Unit%20%26%20Integration-1C1C1C">
  <img alt="Geo" src="https://img.shields.io/badge/Geolocation-Enabled-0EA5E9">
</p>

---

**LocalFood** é um **back-end em Java 21 com Spring Boot 3** para recomendação de comidas e lugares típicos.  
Focado em **boas práticas, arquitetura limpa e escalabilidade**.  

---

## ✨ Funcionalidades  

- 🔑 **Autenticação JWT** – controle seguro de usuários.  
- 📍 **Geolocalização** – busca de comidas e restaurantes próximos.  
- ⭐ **Avaliações e Favoritos** – engajamento e personalização.  
- 🗂 **Migrations com Flyway** – versionamento confiável do banco de dados.  
- 📖 **Swagger** – documentação interativa e fácil de consumir.  
- 🧹 **Arquitetura Limpa** – separação de camadas (domain, application, infra, web).  
- 🧪 **Testes Unitários e de Integração** – qualidade e manutenção do código.  

---

## 🛠️ Tecnologias  

- **Java 21**  
- **Spring Boot 3**  
- **Spring Security + JWT**  
- **PostgreSQL + Flyway**  
- **JUnit + Mockito**  
- **Swagger / OpenAPI**  

---

## 🚀 Como rodar o projeto  

### Pré-requisitos  
- Java 21  
- Maven 3.9+  
- PostgreSQL  

### Passos  

```bash
# Clone o repositório
git clone https://github.com/YujiArima/localfood.git
cd localfood

# Configure o banco no application.yml

# Rode as migrations
mvn flyway:migrate

# Start do projeto
mvn spring-boot:run
```

API:  
👉 `http://localhost:8080`  

Swagger:  
👉 `http://localhost:8080/swagger-ui.html`  

---

## 📂 Estrutura  

```
src/
 ├── main/java/com/yuji/localfood/
 │    ├── application   # Casos de uso (regras de aplicação)
 │    ├── domain        # Entidades, value objects, contratos
 │    ├── infra         # Repositórios, implementações, configs
 │    └── web           # Controllers (REST API)
 ├── main/resources/
 │    ├── application.yml
 │    └── db/migration   # Scripts Flyway
 └── test/               # Testes unitários e de integração
```

---

## 📌 Aprendizados e Boas Práticas  

✔️ Aplicação de **Clean Architecture**  
✔️ Separação entre **domínio e infraestrutura**  
✔️ Uso de **value objects** (ex: Email, UUID)  
✔️ Criação de **contratos e fakes** para testes  
✔️ Documentação e versionamento com **Swagger + Flyway**  
✔️ Pipeline de testes com **JUnit + Mockito**  

---

## 🧑‍💻 Autor  

**Yuji Arima**  
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?logo=linkedin&logoColor=white)](https://www.linkedin.com/in/yujiarima)  
[![GitHub](https://img.shields.io/badge/GitHub-000?logo=github&logoColor=white)](https://github.com/YujiArima)  

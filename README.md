# Device Manager API
Este projeto implementa uma API RESTful para gerenciar dispositivos (Device Manager). Ele permite adicionar, listar, atualizar, deletar e buscar dispositivos por marca. A aplicação utiliza Spring Boot e Spring Data JPA com um banco de dados em memória H2 para persistência.

Funcionalidades
Adicionar um novo dispositivo
Buscar um dispositivo pelo seu ID
Listar todos os dispositivos
Atualizar um dispositivo (total ou parcialmente)
Deletar um dispositivo
Buscar dispositivos por marca
Tecnologias Utilizadas
Java 17 (ou superior)
Spring Boot 2.x
Spring Data JPA
H2 Database (banco de dados em memória)
Maven para gerenciamento de dependências
Instalação e Configuração
Pré-requisitos
JDK 17 ou superior instalado
Maven instalado
Passos para rodar a aplicação:
Clone o repositório:

bash
Copy code
git clone https://github.com/seu-usuario/device-manager.git
Acesse o diretório do projeto:

bash
Copy code
cd device-manager
Compilar e rodar o projeto usando Maven:

bash
Copy code
mvn spring-boot:run
Acesse o console H2 para visualizar o banco de dados:

URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: password

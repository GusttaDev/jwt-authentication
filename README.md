# jwt-authentication
Utilizando Autenticação JWT com Spring Boot

Projeto com token JWT + Spring Boot, Spring Security, AUTH0 E JPA | JAVA E POSTGRESQL

JWT quer dizer JSON web token, definido no padrão RFC-7519, e é usado para armazenar e transferir de forma segura objetos JSON compactados entre duas partes.

# Introdução
# Criando docker-compose.yml

version: '3'

services:
  teste-postgres-compose:
    image: postgres
    environment:
      POSTGRES_PASSWORD: "postgres2021"
    ports:
      - "15432:5432"
    volumes:
      - ./data:/var/lib/postgresql/data 
    networks:
      - postgres-compose-network
      
  teste-pgadmin-compose:
    image: dpage/pgadmin4
    environment:
      PGADMIN_DEFAULT_EMAIL: "dev@yahoo.com.br"
      PGADMIN_DEFAULT_PASSWORD: "postgres2021"
    ports:
      - "16543:80"
    depends_on:
      - teste-postgres-compose
    networks:
      - postgres-compose-network

networks: 
  postgres-compose-network:
    driver: bridge
    
  # Executando comando docker
  Abra o terminal onde encontra-se seu arquivo docker-compose.yml
  
  docker-compose up
  
  # Acessando o pgadmin configurado no docker-compose
  
  http://localhost:16543
  
  # Criando um server no pgadmin
  
   Em Host name/address informar o nome do container que corresponde à instância do PostgreSQL (teste-postgres-compose);
   Em Port definir o valor 5432 (porta default de acesso ao container e disponível a partir da rede postgres-compose-network; não informar a porta em que o PostgreSQL foi mapeado no host);
   No atributo Username será informado o usuário default do PostgreSQL (postgres), bem como a senha correspondente em Password (postgres2021).
  

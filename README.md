## ProMonstraoServletJSP

### Requisitos do Trabalho

- Tomcat 7+
- JDK 
- Maven
- MySQL

### Tecnologia utilizada

- Servlet, JSP, JSTL & JDBC (Lado Servidor)
- Javascript & CSS (Lado Cliente)

### Arquitetura utilizada
- Modelo-Visão-Controlador

### Como executar

1. Utilize "./db/create.sql" para criar e alimentar o banco
- Aqui nesta etapa voce esta criando o 4 tabelas dentro do banco que sao elas: usuario,site,teatro,promocao;
- Dentro de usuario existem 3 papeis: ADMIN, SITE, TEATRO
- Papeis que serao usados para controle no nosso sistema

2. Dê o deploy no Monstro

```
mvn tomcat7:deploy
```

3. Acesse a Pagina inicial : http://localhost:8080/ProMonstrao/

4. Na tela de login exite 3 tipos de usuarios: Admin, Teatro e Site <br>
- Segue alguns logins que estao no banco que voce pode utilizar:

```
admin1@admin.com
admin1

teatro1@teatro.com
teatro1

site1@site.com
site1
```


### Implementação Requisitos

R1:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Pabolo, Gabriel<br>

R2:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Pabolo, Gabriel e Mayk<br>

R3:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Pabolo, Gabriel e Mayk<br>

R4:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Pabolo, Gabriel e Mayk<br>

R5:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Pabolo, Gabriel e  Mayk<br>

R6:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Pabolo, Gabriel e Mayk<br>

R7:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Pabolo, Gabriel<br>

R8:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Pabolo<br>

Refatorações: Pabolo<br>
Revisões: Pabolo, Gabriel e Mayk<br>
Front: Mayk<br>

<hr>


# ProMonstraoMVC

Implementação do projeto ProMonstrao usando Spring MVC

### Requisitos do Trabalho

- Tomcat 7+
- JDK 
- Maven
- MySQL

### Tecnologia utilizada

- Spring MVC, Spring Data JPA, Spring Security & Thymeleaf (Lado Servidor)
- Javascript & CSS (Lado Cliente)

### Arquitetura utilizada
- Modelo-Visão-Controlador

## Instruções

1. Executar o script `db/MySQL/create.sql`, para inicializar a base de dados MySQL.

2. Executar o comando `mvn spring-boot:run`. O sistema estará rodando em http://localhost:8080.

4. Na tela de login exite 3 tipos de usuarios: Admin, Teatro e Site <br>
- Segue alguns logins que estao no banco que voce pode utilizar:

```
admin@email.com
senha

website1@email.com
senha

website2@email.com
senha
```


### Implementação Requisitos

R1:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

R2:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

R3:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

R4:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

R5:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

R6:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

R7:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

R8:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

R9:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

R10:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

<hr>

# ProMonstraoREST

Implementação do projeto ProMonstrao utilizando REST API

### Requisitos do Trabalho

- Tomcat 7+
- JDK 
- Maven
- MySQL

### Tecnologia utilizada

- Spring MVC (Controladores REST), Spring Data JPA, Spring Security & Thymeleaf (Lado Servidor)
- Javascript & CSS (Lado Cliente)

### Arquitetura utilizada
- Modelo-Visão-Controlador

## Instruções

1. Executar o script `db/MySQL/create.sql`, para inicializar a base de dados MySQL.

2. Executar o comando `mvn spring-boot:run`. O sistema estará rodando em http://localhost:8080.

4. Na tela de login exite 3 tipos de usuarios: Admin, Teatro e Site <br>
- Segue alguns logins que estao no banco que voce pode utilizar:

```
admin@email.com
senha

website1@email.com
senha

website2@email.com
senha
```


### Implementação Requisitos

Cria um novo site de vendas de ingressos [Create - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Retorna a lista de sites de vendas de ingressos [Read - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Retorna o site de vendas de ingressos de id = {id} [Read - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Atualiza o site de vendas de ingressos de id = {id} [Update - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Remove o site de vendas de ingressos de id = {id} [Delete - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Cria uma novo teatro [Create - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Retorna a lista de teatros [Read - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Retorna o teatro de id = {id} [Read - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Retorna a lista de todos os teatros da cidade de nome = {nome}:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Atualiza o teatro de id = {id} [Update - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

Remove o teatro de id = {id} [Delete - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

REST API -- Retorna a lista de promoções [Read - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

REST API -- Retorna a promoção de id = {id} [Read - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

REST API -- Retorna a lista de promoções do site de id = {id} [Read - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>

REST API -- Retorna a lista de promoções do teatro de id = {id} [Read - CRUD]:<br>
(X) Implementado ( ) Parcialmente implementado ( ) Não implementado<br>
Divisão na implementação da funcionalidade: Gabriel e Mayk<br>
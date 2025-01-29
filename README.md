# Desafio Técnico Analista de Sistemas de Computação (Desenvolvedor)

## Instruções para configuração do ambiente(Windows):

* Baixar e insalar o JDK "17.0.12":  <a href="https://download.oracle.com/java/17/archive/jdk-17.0.12_windows-x64_bin.exe">Link para baixar o jdk</a>

* Baixar e instalar o Node.js: <a href="https://nodejs.org/dist/v22.13.1/node-v22.13.1-x64.msi">Link para baixar o Node.js</a>

* Instalar um Banco de Dados MySQL server. Recomendo baixar o 
MySQL Workbench para poder rodar o script SQL: <a href="https://www.youtube.com/watch?v=IEUgVwjXF0o">Vídeo de demostrando a instação do MySQL Server e a ferramenta MySQL Workbench</a> 

* Instalar o Visual Studio Code ou uma IDE que suporte o JAVA. <a href="https://code.visualstudio.com/docs/?dv=win64user">Link para baixar o Visual Studio Code (Windows 10/11) </a>

* Configurar o arquivo **application.properties** que se encontra: em  
```bash
src/
├── main/
│   ├── java/com/matheus/
│   ├── resources/
│   │   ├── static/
│   │   ├── templates/
│   │   ├── application.properties

```
com os dados do seu Banco de Dados.
```
spring.application.name=desafio
spring.datasource.url=jdbc:mysql://localhost:3306/desafio?useSSL=false&serverTimezone=UTC&useTimezone=true /* Configuração do localhost do meu banco de dados, colocar de acordo com o localhost do seu banco */
spring.datasource.username= /*Usar a senha do seu banco de dados*/
spring.datasource.password= /*Usar a senha do seu banco de dados */
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

security.jwt.expiration=30
security.jwt.key=bWF0aGV1cyBkYSBjcnV6IHNvdXphIDE5OTg3IG1heWhldXNtdDEyIHNvdXphIGNydXo=
```
## Passos para execução da aplicação

* Clonar o projeto **GitHub** <a href="https://github.com/matheusmt12/Desafio-tecnico.git">Link do para clonar o projeto</a>

* Executar o arquivo SQL **script_banco.sql** que se encontra: 
```bash
Desafio tecnico/
├── .vscode/
├── Codigo/
├── DB/
│   ├── script_banco.sql

```

* Abrir o arquivo **DesafioApplication.java**

```bash
├── Codigo/
    ├── Backend/desafio/
        ├── .mvn/
        ├── src/
        │   ├── main/
        │       ├── java/com/matheus/desafio/
        │       │   ├── controller/
        │       │   ├── dto/
        │       │   ├── entity/
        │       │   ├── exception/
        │       │   ├── repository/
        │       │   ├── security/
        │       │   ├── service/
        │       │   ├── DesafioApplication.java

```
Em seguida, executar o arquivo. 
<img src="./Imagem README/Captura de tela 2025-01-29 015523.png"></img> 

* Acesse pelo terminal a pasta Frontend/desafio-front:
```
Dado que seu terminal tem como ultima pasta \Desafaio Tecnico>

comandos para executar no terminal pra ter acesso a pasta /desafio-front:

\Desafaio Tecnico> cd codigo

\Desafaio Tecnico\codigo> cd frontend

\Desafaio Tecnico\codigo\frontend> cd desafio-front

\Desafaio Tecnico\codigo\frontend\desafio-front> npm install
\Desafaio Tecnico\codigo\frontend\desafio-front> npm run dev
```
Logo em seguida o terminal fornece uma url para acesso ao sistema: 

```
  VITE v6.0.11  ready in 508 ms

  ➜  Local:   http://localhost:5173/
  ➜  Network: use --host to expose
  ➜  Vue DevTools: Open http://localhost:5173/__devtools__/ as a separate window
  ➜  Vue DevTools: Press Alt(⌥)+Shift(⇧)+D in App to toggle the Vue DevTools
  ➜  press h + enter to show help

```
Apertar Ctrl + Click na URL fronecida aqui : ➜ Local: 

Ou copiar e colar na barra de endereço do seu navegador
# Desafio TécnicoAnalista de Sistemas de Computação (Desenvolvedor)

## Instruções para configuração do ambiente(Windows):

* Baixar e insalar o JDK "17.0.12":  <a href="https://download.oracle.com/java/17/archive/jdk-17.0.12_windows-x64_bin.exe">Link para baixar o jdk</a>

* Baixar e instalar o Node.js: <a href="https://nodejs.org/dist/v22.13.1/node-v22.13.1-x64.msi">Link para baixar o Node.js</a>

* Instalar um Banco de Dados MySQL server. Recomendo baixar o 
MySQL Workbench para poder rodar o script SQL: <a href="https://www.youtube.com/watch?v=IEUgVwjXF0o">Vídeo de demostrando a instação do MySQL Server e a ferramenta MySQL Workbench</a> 

* Instalar o Visual Studio Code ou uma IDE que suporte o JAVA. <a href="https://code.visualstudio.com/docs/?dv=win64user">Link para baixar o Visual Studio Code (Windows 10/11) </a>

* Configurar o arquivo **application.properties** que se encontra: em  
```
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

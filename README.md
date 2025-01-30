# Desafio Técnico Analista de Sistemas de Computação (Desenvolvedor)

## Instruções para configuração do ambiente(Windows):

* Baixar e insalar o JDK "17.0.12":  <a href="https://download.oracle.com/java/17/archive/jdk-17.0.12_windows-x64_bin.exe">Link para baixar o jdk</a>

* Baixar e instalar o Node.js: <a href="https://nodejs.org/dist/v22.13.1/node-v22.13.1-x64.msi">Link para baixar o Node.js</a>

* Instalar um Banco de Dados MySQL server. Recomendo baixar o 
MySQL Workbench para poder rodar o script SQL: <a href="https://www.youtube.com/watch?v=oi3UHWXLxLs">Vídeo de demostrando a instação do MySQL Server e a ferramenta MySQL Workbench</a> 

* Instalar o Visual Studio Code ou uma IDE que suporte o JAVA. <a href="https://code.visualstudio.com/docs/?dv=win64user">Link para baixar o Visual Studio Code (Windows 10/11) </a>

* Configurar o arquivo **application.properties** que se encontra em:  
```bash
├── Codigo/
    ├── Backend/desafio/
        ├── .mvn/
        ├── src/
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
spring.datasource.username= /*Usar o usuario do seu banco de dados*/
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

* Executar os comandos do arquivo SQL **script_banco.sql** que se encontra: 
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
Instalar extensão de pacotes Java no Visual Studio Code:

<img src="./Imagem README/extensio.png"></img>

Em seguida, executar o arquivo. 
<img src="./Imagem README/Captura de tela 2025-01-29 015523.png"></img> 

* Acesse pelo terminal a pasta Frontend/desafio-front:
```
Dado que seu terminal tem como ultima pasta \Desafaio Tecnico>

comandos para executar no terminal pra ter acesso a pasta /desafio-front:

\Desafio-tecnico>> cd codigo

\Desafio-tecnico>\codigo> cd frontend

\Desafio-tecnico>\codigo\frontend> cd desafio-front

\Desafio-tecnico>\codigo\frontend\desafio-front> npm install
\Desafio-tecnico>\codigo\frontend\desafio-front> npm run dev

/* Se por acaso esse erro no terminal : npm : O arquivo C:\Program Files\nodejs\npm.ps1 não pode ser carregado porque a execução de scripts foi desabilitada neste sistema. Para obter mais  
informações, consulte about_Execution_Policies em https://go.microsoft.com/fwlink/?LinkID=135170.*/

Executar esse comando:
\Desafio-tecnico>\codigo\frontend> Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass
\Desafio-tecnico>\codigo\frontend\desafio-front> npm install
\Desafio-tecnico>\codigo\frontend\desafio-front> npm run dev


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

Ou copiar e colar na barra de endereço do seu navegador para ter acesso a tela de *Login*

## Descrição das funcionalidades implementadas.

* ### Tela de Login

<img src="./Imagem README/telaLogin.png"></img>

Para acessar as telas a seguir e as suas funcionalidades, é preciso fazer o login com o **Usuário** "matheus" e **Senha** "123456"

* ### Tela inicial do projeto

<img src="./Imagem README/telaInicialProjeto.png">

A imagem mostra a tela do caso de uso de projeto. Pressionar o botão **Novo Projeto**, onde a seta(vermelha) esta apontando para adicionar um novo projeto no sistema.

* ### Tela inicial do projeto com a funcionalidade para adicionar um novo projeto no sistema

<img src="./Imagem README/telaDeNovoProjeto.png"></img>

A imagem mostra os campos que precisam ser preenchidos. Logo em seguida, caso queira Adiconar o novo projeto no sistema, pressionar O botão **Adicionar** que esta sendo apontado pela seta(vermelha) na imagem, caso queira cancelar a inserção, pressionar o botão **Fechar** que esta sendo apontado pela seta(azul).


* ### Tela inicial com um projeto adicionado

<img src="./Imagem README/telaProjetoBotaoStatus.png"></img>

A imagem o novo projeto adicionado com o seu **nome**, nome do **responsavel**, **status**, **data de termino** e duas ações, Status e Tarefas.

Ao pressionar o botão **Status** que esta sendo apontado pela seta(vermelha) abrirá um modal mostrando as todas as informações do prjeto e uma opção para mudar o status do projeto, como mostra a imagem a seguir:

<img src="./Imagem README/telaProjetoAlterar.png"></img>

Para alterar o status, basta selecionar um dos status e pressionar o botão **Alterar** que esta sendo apontado pela seta(vermelha).

Caso não queira fazer a alteração, basta pressionar o botão **Fechar** apontado pela seta(azul);

* ### Telas para a funcionalidades de **Tarefa**

<img src="./Imagem README/telaProjetoTarefa.png"></img>

Ainda na tela incial de projeto, pressionar o botão **Tarefas** apontado pela seta(vermelha) para ir para as tarefas do determinado projeto.

<img src="./Imagem README/telaInicioTarefa.png"></img>

A imagem mostra uma tela semelhante a tela inicial de **Projeto** so que para as funcionalidades de tarefas.

Pressionado o botão **Adicionar** que a seta(vermelha) esta apontado, abrirá um modal para adiconar uma nova tarefa.

<img src="./Imagem README/telamodalTarefa.png"></img>

A imagem mostra os campos que precisam ser preenchidos para adiconar uma nova tarefa.

Para adiconar a tarefa, após ter preenchidos todos os campos, basta pressionar o botão **Adicionar** apontado pela seta(vermelha). Caso não queira adiconar, pressione o botão **Fechar** apontado pela seta(azul).

#### Tela de tarefas com uma tarefa adicionada

<img src="./Imagem README/telaTarefaAdicionada.png"></img>

A imagem mostra a tela de tarefas logo apos uma tarefa ser adicionada ao sistema. Podemos ver o **título**, nome do **responsavel** e o **status**, e como na tela de projeto ao pressionar o botão **Status** apontado pela seta(vermelha) abrirá um modal mostrando todas as informações da tarefa com a opção de alterar o status.

<img src="./Imagem README/telaAlterarStatus.png"></img>

Para alterar o status, basta selecionar um dos status e pressionar o botão **Alterar** que esta sendo apontado pela seta(vermelha).

Caso não queira fazer a alteração, basta pressionar o botão **Fechar** apontado pela seta(azul);

* ### Tela de Funcionalidade de pesquisa por nome e status do projeto

<img src="./Imagem README/telaPesquisa.png"></img>

No retângulo(azul), mostra um campo de pesquisa para nome do projeto, basta digitar o nome de um projeto, caso esse projeto exista, será exibido na tela.

No retângulo(vermelho), ostra umas opções, **Todos** exibe todos os projetos Independente do seu status, **Planejado** exibe todos os projetos com status *PLANEJADO*, **Em execução** exibe todos os projetos com status *EM EXECUÇÃO*, **Abortado** exibe todos os projetos com o status *ABORTADO* e **Finalizado** que exibirá todos os projetos com status *FINALIZADO*.

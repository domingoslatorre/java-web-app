# Java web app

## Instalação e configuração do Apache Tomcat

1. Fazer download do apache tomcat 10 core zip
https://tomcat.apache.org/download-10.cgi

2. Descompactar no dir home de estudante ~/

3. Abrir no terminal o dir apache-tomcat-10.1.12 e executar o comando:
chmod +x bin/*.sh

4. Executar o comando ./bin/startup.sh para iniciar o serviço do tomcat

5. Acessar localhost:8080

6. Alterar o arquivo /conf/tomcat-users.xml configurando user admin e senha admin
<user username="admin" password="admin" roles="manager-gui"/>

7. Executar o comando ./bin/shutdown.sh para para o serviço do tomcat

8. Executar o comando ./bin/startup.sh para iniciar o serviço do tomcat com as novas configurações

9. Acessar localhost:8080 e em seguida o link server status e manager app

## Clonar repositório de template

10. Fazer o git clone do projeto https://github.com/domingoslatorre/java-web-app

```bash
git clone https://github.com/domingoslatorre/java-web-app
```

11. Entrar no diretório do projeto e apagar a pasta .git
```bash
cd java-web-app
rm -rf .git
```
12. Abrir o projeto no VSCode
```bash
code .
```

## Implantando a aplicação manualmente

13. Executar os comandos
```bash 
export JAVA_TOOL_OPTIONS="-Djava.net.useSystemProxies=true" 
```
Obs: a configuração do proxy é necessária apenas nos laboratórios de informática.

```bash
./mvnw package
```

14. Mover o arquivo .war localizado na pasta target para a pasta /apache-tomcat-10.1.12/webapps do tomcat. 

15. Reiniciar o tomcat 
```bash
./bin/shutdown.sh
```
```bash
./bin/startup.sh
```

16. Refazer os passos 13, 14 e 15 a cada alteração no código

## Implantando a aplicação com uma extensão do VSCode

14. Instalar as extensões Extension Pack for Java e Community Server Connectors

15. Configurar o Apache Tomcat com Server na Community Server Connectors
Create new server -> Download server -> No, use server on disk, selecionar o diretório /apache-tomcat-10.1.12

16. No icone do Tomcat configurado parar o server com Stop Server e em seguida iniciar com Start Server

17. Executar os comandos
```bash 
export JAVA_TOOL_OPTIONS="-Djava.net.useSystemProxies=true" 
```
Obs: a configuração do proxy é necessária apenas nos laboratórios de informática.

```bash
./mvnw package
```

18. No tomcat configurado adicionar uma implantação via Add Deployment, Exploded, selecionar o diretório gerado pelo maven no projeto /apache-tomcat-10.1.12/target/java-web-app-1.0

19. Refazer o passo 17 e a opção Publish Server (Full) cada alteração.

20. No arquivo conf/context.xml do servidor adicionar o seguinte configuração
<Context reloadable="true">

21. Refazer apenas o passo 17 a cada alteração que a extensão irá implantar automaticamente a aplicação no Tomcat.

```bash
./mvnw package
```


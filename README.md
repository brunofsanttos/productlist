<h4 align="center"> 
    :construction:  Projeto em construção  :construction:
</h4>

# Documentation

# Index

* [Descrição do projeto](#descrição-do-projeto)
* [Pre-requisitos](#Pré-requisitos)
* [Executando aplicação localmente](#-rodando-o-aplicação--localmente-)


### Descrição do projeto
<p>API que serve a visão de lista de produtos contratados por cliente.</p>

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [Git](https://git-scm.com)
- [JDK 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html#license-lightbox)
- [Docker](https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe?utm_source=docker&utm_medium=webreferral&utm_campaign=dd-smartbutton&utm_location=module)
- [Maven](https://dlcdn.apache.org/maven/maven-3/3.9.0/binaries/apache-maven-3.9.0-bin.zip)

### 🎲 Rodando o aplicação (localmente)

- `Criação do banco de dados`: Basta rodar o arquivo [docker-compose.yml](docker-compose.yml)
- `Configurando IDE`: É necessario a inclusão de algumas variaveis de ambiente

``
HOSTDB_DEV=jdbc:postgresql://localhost:5432/dblistadeprodutos;USERNAME=postgres;PASSWORD=123
``
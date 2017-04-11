#Sistema de Ponto de Interesse.

##Instruções para Execução
Importar o projeto no Eclipse como projeto Maven.

Configuração de usuário, senha e nome do Banco de Dados está disponível no arquivo **application.properties**

Caso precise alterar a porta do Tomcat, adicionar server.port=porta, ex: server.port=9000

A Url para o banco é spring.datasource.url=jdbc:mysql://localhost:3306/testezup sendo assim, criar o banco com o mesmo nome "testezup", ou alterar o nome no properties

No Eclipse executar a classe Boot.java, dentro do pacote br.com.xyinc.pontointeresse, como Java Applicationp
A tabela será gerada automaticamente.	
Para acessar o sistema digitar no navegador localhost:8080/poi

##Tecnologias usada
A Estrutura base (estrutura Maven, templates e crud basico) foi gerado através do site http://setupmyproject.com/
*Spring Boot
*Jersey
*JPA
*MySql
*Eclipse Neon
*SoapUI

##Testes
Classe PontoInteresseTeste.java dentro do Pacote br.com.xyinc.teste

##Testes HTTP
Classe ServiceController.java dentro do Pacote br.com.xyinc.pontointeresse.controllers

Utilizado o SoapUI para realizar os testes.

Criar um novo projeto REST Url: http://localhost:8080/pontoInteresses

**GET** - Lista todos os pontos de interesses cadastrados

**POST** - Cadastra um novo ponto de Interesse, caso alguma coordenada seja negativa, retorna exception com mensagem "Coordenada Negativa"

{
   "nome": "Novo Ponto",
   "coordenadaX": 10,
   "coordenadaY": 5
}

**PUT** - Altera um ponto

{
   "id": "8",
   "nome": "Ponto Alterado",
   "coordenadaX": 10,
   "coordenadaY": 5
}

**DELETE** - Apaga um ponto passando o ID

http://localhost:8080/pontoInteresses/8


**POST** - http://localhost:8080/pontoInteresses/filtrar Para filtrar os pontos através das coordenadas X, Y e distância Maxima

{
    "x": "20",
	"y": "10",
	"dMax": "10"
}

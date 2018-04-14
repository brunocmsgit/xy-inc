#Sistema de Ponto de Interesse.

##Instruções para Execução
Importar o projeto no Spring Tool Suite (STS) como projeto Maven.

Configuração de usuário, senha e nome do Banco de Dados está disponível no arquivo **application.properties** - pasta /resources

Caso precise alterar a porta do Tomcat, adicionar server.port=porta, ex: server.port=9000

A Url para o banco é spring.datasource.url=jdbc:mysql://localhost:3306/testezup sendo assim, criar o banco com o mesmo nome "testezup", ou alterar o nome no properties

No STS executar a classe Boot.java, dentro do pacote br.com.xyinc.pontointeresse, como Java Application
A tabela será gerada automaticamente.	
Para acessar o sistema (web) digitar no navegador localhost:8080/poi

##Tecnologias usadas
Estrutura base MVC (para a parte web - não foi pedido mas achei interesante colocar) foi gerado através do site http://setupmyproject.com/
*Spring Boot 1.5.12
*Spring Data JPA
*MySql
*Spring Tools Suite 3.9.0
*Postman/SoapUI para testar a api

##Testes
Classe PontoInteresseTeste.java dentro do Pacote br.com.xyinc.teste

##Serviços
Classe PontoInteresseResource.java dentro do Pacote br.com.xyinc.pontointeresse.api

Utilizado o Postman para realizar os testes e executar os serviços.

Formato JSON.

**Listar todos os pontos de interesses**
**GET** - http://localhost:8080/pontoInteresses/ -  cadastrados.

**Para filtrar os pontos através das coordenadas X, Y e distância maxima**
**POST** - http://localhost:8080/pontoInteresses/filtrar
 
Exemplo:
{
    "x": "20",
	"y": "10",
	"dMax": "10"
}

**Cadastrar um novo ponto de interesse, caso alguma coordenada seja negativa ou nome nulo, retorna Erro 400 Bad Request e a mensagem correspondente.**
**POST** - http://localhost:8080/pontoInteresses/

Exemplo:
{
   "nome": "Novo Ponto",
   "coordenadaX": 10,
   "coordenadaY": 5
}

**Altera um ponto de interesse**
**PUT** - http://localhost:8080/pontoInteresses/id

Exemplo: http://localhost:8080/pontoInteresses/8 - Altera ponto de id 8, se não existir o recurso 8, retorna mensagem correspondente.
{
   "nome": "Ponto Alterado",
   "coordenadaX": 10,
   "coordenadaY": 5
}

**Remove um ponto passando o id. Se não existir o recurso 8, retorna mensagem correspondente, se removeu retorna status 200 OK.**
**DELETE**

Exemplo:
http://localhost:8080/pontoInteresses/8

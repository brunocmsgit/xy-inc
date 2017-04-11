<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags/template" prefix="template"%>
<template:admin>
	
	<jsp:body>
	  <div>
	    <div class="container min-container">
	      <h2 class="basic-title">Lista de POIs</h2>
	        <div class="well">
	        
	        
	        <form:form role="form" cssClass="well" commandName="filtro" servletRelativeAction="/poi" method="GET">
			    <fieldset>
			        <legend>Consultar</legend>
			        <table>
			            <tr>
			                <th><form:label path="x">Coordenada X</form:label></th>
			                <th><form:label path="y">Coordenada Y</form:label></th>
			                <th><form:label path="dMax">Distância Máxima </form:label></th>
			                
			            </tr>
			            <tr>
			                <td><form:input path="x" type='number' min="1"/></td>
			                <td><form:input path="y" type='number' min="1"/></td>
			                <td><form:input path="dMax" type='number' min="1"/></td>
			                <td><button id="consultar">Consultar</button></td>
			            </tr>
			            
			            
			            
			        </table>
			        
			    </fieldset>
			    
			</form:form>
	        
	            
	          <table class="table table-condensed table-bordered table-striped table-hover">
	          		  <thead>
		                  <tr>
		                  	<td>ID</td>
		                  	<td>Nome</td>
		                  	<td>Coordenada X</td>
		                  	<td>Coordenada Y</td>
		                  	<td>Remover</td>
						  </tr>
	                  </thead>
	                  <tbody>
	                  <c:forEach items='${listaPois}' var='object'>         		
		                  <tr>
							<td>
								<a href="<c:url value='/poi'/>/${object.id}">${object.id}</a></td>
			                  	<td>${object.nome}</td>
			                  	<td>${object.coordenadaX}</td>
			                  	<td>${object.coordenadaY}</td>
		                    <td>
		                    <a href="<c:url value='/poi/remove'/>/${object.id}">Remover</a></td>
						  </tr>
	                  </c:forEach>
	                  </tbody>
	          </table>
			  
	          <a href="<c:url value='/poi/form'/>" class="btn btn-success">
	          	<span class="glyphicon glyphicon-plus-sign"></span>Novo</a>
	          
	        </div>
	    </div>
	  </div>
	</jsp:body>
	
</template:admin>

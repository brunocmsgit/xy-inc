<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="form-group">
	<label for="nome">Nome</label>
	<div class="input-group">
		<form:input path='nome' type='text' required="required"/>
		<form:errors path='nome' />

	</div>
</div>
<div class="form-group">
	<label for="description">Coordenada X</label>
	<div class="input-group">
		<form:input path='coordenadaX' type='number' required="required" min="1"/>
		<form:errors path='coordenadaX' />
	</div>
</div>
<div class="form-group">
	<label for="description">Coordenada Y</label>
	<div class="input-group">
		<form:input path='coordenadaY' type='number' required="required" min="1"/>
		<form:errors path='coordenadaY' />
	</div>
</div>

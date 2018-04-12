<!DOCTYPE HTML>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<link href="static/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!-- saveRis --><script>
		$(document).ready(function(){
			$('#saveRisUpdate').click(function(){
				var dataRis = {"id":$("#idRisUpdate").val(),"risorsa_type":$("#typeRisUpdate").val(), "limite":$("#limitRisUpdate").val()}
				$.post({
					dataType:"json",
					contentType:"application/json",
					url:"http://localhost:8085/newRisJQ",
					data:JSON.stringify(dataRis),
					success:function(dataRis){
						window.location.href = "http://localhost:8085/all-resources";		
					}
				});
			});
		});
	</script>
<!-- newRis --><script>
	$(document).ready(function(){
		$('#newRis').click(function(){
			var dataRis = {"risorsa_type":$("#tipoRis").val(), "limite":$("#limiteRis").val()}
			$.post({
				dataType:"json",
				contentType:"application/json",
				url:"http://localhost:8085/newRisJQ",
				data:JSON.stringify(dataRis),	
				success:function(dataRis){
					window.location.href = "http://localhost:8085/all-resources";		
				}
			});
		});
	});
</script>
<!-- userUpdate --><script>
		$(document).ready(function(){
			$('#saveUserUpdate').click(function(){
				var dataUser = {"id":$("#idUserUpdate").val(),"nome":$("#nomeUserUpdate").val(),"mail":$("#mailUserUpdate").val(), "password":$("#passwordUserUpdate").val()}
				$.post({
					dataType:"json",
					contentType:"application/json",
					url:"http://localhost:8085/newUserJQ",
					data:JSON.stringify(dataUser),
					success:function(dataUser){
						window.location.href = "http://localhost:8085/j_spring_security_logout";		
					}
				});
			});
		});
	</script>
<!-- signUp --><script>
	$(document).ready(function(){
		$('#buttonSignUp').click(function(){
			var dataUser = {"id":-1, "nome":$("#nomeSignUp").val(),"mail":$("#mailSignUp").val(), "password":$("#passwordSignUp").val()}
			var checkMail = /^([a-zA-Z0-9_])+\@(([a-zA-Z0-9]{2,})+\.)+([a-zA-Z0-9]{2,})+$/ ;
			if(!!$("#nomeSignUp").val() && !!$("#passwordSignUp").val() && checkMail.test($("#mailSignUp").val())){
			$.post({
				dataType:"json",
				contentType:"application/json",
				url:"http://localhost:8085/signUpJQ",
				data:JSON.stringify(dataUser),
				success:function(dataUser){
				window.location.href = "/";
				}
			});
			}else{
				alert("Dati incorretti, reinserirli");
			}
	});
});
</script>
<!-- newRent --> <script> 
	$(document).ready(function(){
		$('#buttonRent').click(function(){
			alert("prova alert buttonRent");
			var dataPreno = {
					"id":-1, 
					"dataInizio":$('#dataInizioNewPren').val(),
					"dataFine":$('#dataFineNewPren').val(),
					"risorsa_id":$('#risorsaIdPren').val(),
					"utente_id":$('#utenteIdPren').val()
					}
			alert(JSON.stringify(dataPreno));
			$.post({
				dataType:"json",
				contentType:"application/json",
				url:"http://localhost:8085/newPrenJQ",
				data:JSON.stringify(dataPreno),
				success:function(dataPreno){
					window.location.href = "/";
				}
			});
	});
});
</script> 
	<title>Ariadn3Specia1 Lending</title>
</head>
<body>
<div role="navigation">
	<div class="navbar navbar-inverse">
		<a href="/" class="navbar-brand">LOGO</a>
		<div class="navbar-collapse">
			<ul class="nav navbar-nav">
			<sec:authorize access="hasRole('ADMIN')">
				<li><a href="all-users">Users</a></li>
				<li><a href="all-resources">Resources</a></li>		
				<li><a href="new-resource">Add risorsa</a></li>	
				<li><a href="new-prenotation">Nuova prenotazione</a></li>			
			</sec:authorize>
			<sec:authorize access="hasRole('USER')">
				<li><a href="update-user?nome=${pageContext.request.userPrincipal.name}">Edit Profile</a></li>
				<li><a href="new-prenotation">Nuova prenotazione</a></li>
			</sec:authorize>
						
			</ul>
			<ul class="nav navbar-nav navbar-right">			
      			<c:choose><c:when test="${pageContext.request.userPrincipal.name == null}">
      				<li><a href="sign-up"><span class="glyphicon glyphicon-user"></span>Sign Up</a></li>
      				<li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      			</c:when>
      			<c:when test="${pageContext.request.userPrincipal.name != null}">     				
      				<li style="color:grey;"><h4>Logged as "${pageContext.request.userPrincipal.name}"</h4></li>
      				<li ><a href="/j_spring_security_logout">LOGOUT</a></li>
      			</c:when>
      			</c:choose>
    		</ul>
		</div> 
	</div>
</div>

<c:choose>
	<c:when test="${mode=='MODE_HOME'}">
		<div class="container" id="homeDiv">
			<div class="jumbotron text-center">Benvenuto nel portale per l'affitto di risorse AriadneSpecial</div>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_FIND_ALL_USERS'}">
		<div class="container text-center" id="usersAllDiv">
			<h3>Utenti</h3>
			<hr>
			<div class="table-responsive">
				<table class="table table-striped table-bordered text-left">
					<thead>
					<tr>
						<th>Mail</th>
						<th>Password</th>
						<th>Nome</th>
						<th>Cancella</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="users" items='${users}'>
					<tr>
						<td>${users.getMail()}</td>
						<td>${users.getPassword()}</td>
						<td>${users.getNome()}</td>
						<td><a href="delete-user?id=${users.getId()}"><span class="glyphicon glyphicon-trash"></span></a></td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_FIND_ALL_RESOURCES'}">
		<div class="container text-center" id="resourcesAllDiv">
			<h3>Risorse</h3>
			<hr>
			<div class="table-responsive">
				<table class="table table-striped table-bordered text-left">
					<thead>
					<tr>
						<th>Id</th>
						<th>Tipo</th>
						<th>Limite</th>
						<th>Modifica</th>
						<th>Elimina</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="resources" items='${resources}'>
					<tr>
						<td>${resources.getId()}</td>
						<td>${resources.getRisorsa_type()}</td>
						<td>${resources.getLimite()}</td>
					    <td><a href="update-resource?id=${resources.getId()}"><span class="glyphicon glyphicon-pencil"></span></a></td>
						<td><a href="delete-resource?id=${resources.getId()}"><span class="glyphicon glyphicon-trash"></span></a></td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_UPDATE_RESOURCE'}">
		<div class="container text-center">
			<h3>Modifica Risorse</h3><hr>
				<input disabled="disabled" id="idRisUpdate" value='${resource.id}'/>
				<div class="from-group">
					<label class="control-label col-md-3">Tipo</label>
					<div class="col-md-7">
						<input type="text" id="typeRisUpdate" class="form-control" name="nome" value='${resource.risorsa_type}' disabled="disabled"/>
					</div>
					<label class="control-label col-md-3">Limite</label>
					<div class="col-md-7">
						<input type="text" id="limitRisUpdate" class="form-control" name="descrizione" value='${resource.limite}'/>
					</div>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" id="saveRisUpdate">Save</button>
					<a href="/"><button class="btn btn-primary">Home</button></a>
				</div>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_UPDATE_USER'}">
		<div class="container text-center">
			<h3></h3><hr>
			<div class="container text-center">
			<h3>Edit Profile Info</h3><hr>
				<input type="hidden" id="idUserUpdate" value='${user.id}'/>
				<div class="from-group">
					<label class="control-label col-md-3">Nome</label>
					<div class="col-md-7">
						<input type="text" id="nomeUserUpdate" class="form-control"  value='${user.nome}' />
					</div>
					<label class="control-label col-md-3">Mail</label>
					<div class="col-md-7">
						<input type="text" id="mailUserUpdate" class="form-control"  value='${user.mail}' />
					</div>
					<label class="control-label col-md-3">Old Password</label>
					<div class="col-md-7">
						<input type="text" id="OldPassword" class="form-control"  value='${user.password}' disabled="disabled"/>
					</div>
					<label class="control-label col-md-3">New Password</label>
					<div class="col-md-7">
						<input type="text" id="passwordUserUpdate" class="form-control"  value='' placeholder='${user.password}' />
					</div>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" id="saveUserUpdate">Save</button>
					<a href="/"><button class="btn btn-primary">Home</button></a>
				</div>
		</div>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_LOGIN'}">
		<div class="container text-center">
			<h3>Login</h3><hr>
			<form action="j_spring_security_check" method="post">
				<div class="from-group">
					<label class="control-label col-md-3">Username</label>
					<div class="col-md-7">
						<input type="text" id="nomeLogin" name="nome" class="form-control"/>
					</div>
					<label class="control-label col-md-3">Password</label>
					<div class="col-md-7">
						<input type="password" id="passwordLogin" name="password" class="form-control"/>
					</div>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" id="" type="submit">Login</button>
					<button class="btn btn-primary" type="reset" >Annulla</button>
				</div>
			</form>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_SIGN_UP'}">
		<div class="container text-center" >
			<h3>Sign Up</h3><hr>
			<div class="form-horizontal">
				<input type="hidden" name="id" value='-1'/>
				<div class="form-group">
					<label class="control-label col-md-3">Mail</label>
					<div class="col-md-7">
						<input type="text" id="mailSignUp" class="form-control" name="mail" required placeholder="mail here"/>
					</div>
						<label class="control-label col-md-3">Password</label>
					<div class="col-md-7">
						<input type="password" id="passwordSignUp" class="form-control" name="password" required placeholder="password here"/>
					</div>
					<label class="control-label col-md-3">Nome</label>
					<div class="col-md-7">
						<input type="text" id="nomeSignUp" class="form-control" name="nome" placeholder="name here" />
					</div>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" value="Registrati" id="buttonSignUp">Registrati</button>
					<a href="/"><button class="btn btn-primary" value="Annulla">Home</button></a>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_NEW_RESOURCE'}">
		<div class="container text-center" >
			<h3>Aggiungi risorsa</h3><hr>
			<div class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-md-3">Tipo</label>
					<div class="col-md-7">
						<select class="form-control form-control-sm" id="tipoRis">
  							<option>Auto</option>
  							<option>Laptop</option>
  							<option>ConferenceRoom</option>
						</select>					
					</div>
					<label class="control-label col-md-3" >Limite</label>
					<div class="col-md-7">
   						<input id="limiteRis" type="number" min="0" step="1"/>
					</div>
				</div>
				<div class="form-group">
					<button class="btn btn-primary" id="newRis">Aggiungi</button>
					<a href="http://localhost:8085/"><button class="btn btn-primary" >Home</button></a>
				</div>
			</div>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_RISORSE_OK'}">
	<div class="container text-center" >
			<h3>Risorse ok</h3>
			<hr>
			<div class="table-responsive">
				<table class="table table-striped table-bordered text-left">
					<thead>
					<tr>
						<th>Id</th>
						<th>Tipo</th>
						<th>Limite</th>
						<th>Prenota</th>
					</tr>
					</thead>
					<tbody>
					<c:forEach var="resources" items='${resources}'>
					<tr>
						<td>${resources.getId()}</td>
						<td>${resources.getRisorsa_type()}</td>
						<td>${resources.getLimite()}</td>
						<td><a href="add-prenotations?idRis=${resources.getId()}&nomeUser=${pageContext.request.userPrincipal.name}&dataStart=${dataInizioPar}&DataEnd=${dataFinePar}"><button class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span>prenota</button></a></td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_NEW_PRENOTATION' }">
	<div class="container text-center" >
			<h3>Nuova Prenotazione</h3><hr>
			<h5>Seleziona tipo e limite desiderati</h5><br>
			<div class="form-horizontal">
				<form action="/checkRisOk" method="post">
				<div class="form-group">
					<label class="control-label col-md-3">Tipo Risorsa</label>
					<div class="col-md-7">
						<select name="tipo" class="form-control form-control-sm" id="tipoRisPrenotation">
  							<option>Auto</option>
  							<option>Laptop</option>
  							<option>ConferenceRoom</option>
						</select>					
					</div>
					<label class="control-label col-md-3" >Limite</label>
					<div class="col-md-7">
   						<input id="limiteRisPrenotation" type="number" name="limite" min="0" step="1"/>
					</div>
					<div class="col-md-10">
					<label class="control-label col-md-3" >Data e Ora Inizio</label>
   						<input id="dateStartPrenotation" name="dataInizio" type="date" />
   						<input id="oraStartPrenotation" name="oraInizio" type="text" placeholder="hh:mm" value="12:00"/>
					</div>
					<div class="col-md-10">
					<label class="control-label col-md-3" >Data e ora Fine</label>
   						<input id="dateEndPrenotation" name="dataFine" type="date"/>
   						<input id="oraEndPrenotation" name="oraFine" type="text" placeholder="hh:mm" value="13:00"/>
					</div>
				</div>
				
				<div class="form-group">
					<button class="btn btn-primary"  id="risO" type="submit">Cerca</button>
					<a href="/"><button class="btn btn-primary" >Home</button></a>
				</div>
				</form>
			</div>
		</div>
	</c:when>
	<c:when test="${mode=='MODE_CONFIRM_PRENOTATION'}">
		<div class="container text-center" >
			<h3>Conferma Prenotazione</h3>
			<hr>
			<input type="hidden" id="risorsaIdPren" value="${choosenRes.getId()}">
			<input type="hidden" id="utenteIdPren" value="${activeUser.getId() }">
			<label class="control-label col-md-3" >Utente</label>
			<div class="col-md-7">
				<input disabled="disabled" id="nomeUtente" type="text" value="${activeUser.getNome()}"/>
			</div>
			<label class="control-label col-md-3" >Tipo</label>
			<div class="col-md-7">
				<input disabled="disabled" id="tipoNewPren" type="text" value="${choosenRes.getRisorsa_type()}"/>
			</div>
			<label class="control-label col-md-3" >Limite</label>
			<div class="col-md-7">
				<input disabled="disabled" id="limiteNewPren" type="text" value="${choosenRes.getLimite()}"/>
			</div>
			<label class="control-label col-md-3" >Data e ora inizio</label>
			<div class="col-md-7">
				<input disabled="disabled" id="dataInizioNewPren" type="text" value="${dataInizioPar}"/>
			</div>
			<label class="control-label col-md-3" >Data e ora fine</label>
			<div class="col-md-7">
				<input disabled="disabled" id="dataFineNewPren" type="text" value="${dataFinePar}"/>
			</div>
			<hr>
			<div class="form-group">
				<button class="btn btn-primary" id="buttonRent">Conferma Prenotazione</button>
				<a href="/"><button class="btn btn-primary">Torna alla home</button></a>
			</div>
		</div>
	</c:when>
</c:choose>



</body>
</html>

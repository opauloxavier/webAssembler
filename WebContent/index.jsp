<%@taglib prefix ="s" uri = "/struts-tags"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>  
    
 <%
String path = request.getContextPath();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src='<%=path%>/dwr/engine.js'></script>	
<script src="<%=application.getContextPath()%>/dwr/interface/AssemblerMips.js"></script>
<script src="<%=application.getContextPath()%>/dwr/interface/UCP.js"></script>
<link rel="icon" type="image/gif" href="image/favicon.png" />
<link rel="shortcut icon" href="image/favicon.png" />
<link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/estilos/interface.css" >
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<link href='http://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
<script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>

<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.0/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript" src="scripts/scripts.js"></script>
<script type="text/javascript" src="scripts/FileSave.js"></script>
<script src="<%=path%>/dwr/interface/AssemblerMips.js"></script>
<script src="<%=path%>/dwr/interface/Buscador.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WebAssembler</title>
</head>
<body>
 <div id="menu">
 			<div id="caixaLogo" title="WebAssembler">
   				<div id="logo"></div>
   			</div>
            <div id="pesquisar">
						<input type="text" name="textoPesquisa" id="caixaPesquisa" placeholder="Pesquisar">
			<div id="logoPesquisar" onclick="preencheModal()"></div>
			</div>
    </div>
<div id="all">
	 		<div id="abrir" onclick="abrirArquivo()" title="ctrl+F1"><span>Abrir</span></div>
           	<div id="salvar" onclick="downloadFile()" title="ctrl+F2"><span>Salvar</span></div>
            <div id="montar" title="ctrl+F3"><span>Montar</span></div>
            <div id="executar" onclick="execAll()" title="ctrl+F6"><span>Executar</span></div>
            <div id="step_step" title="ctrl+F7"><span>Exec. Etapas</span></div>

	    <div id="abas">
	    	<div id="seletorAba1" class="ativa">Editar</div>
	    	<div id="seletorAba2"class="inativa">Executar</div>
	    	
	    	  <div id="aba1">
	    		<div id="caixaAba1">
	    			<textarea name="textoEntrada" id="caixaTextoMontagem"></textarea>
	    			<textarea id="codeLines" disabled="true">1</textarea>
	    		<div id="caixaCodigoBinario">
	    			<table id="tableCodigoBinario">
	    			<tr>
		    				<th>Instrução</th>
		    				<th>Codigo Binario</th>
	    			</tr>
					<tr>
						<td id="instrucao"></td>
						<td id="codigoBin"></td>
					</tr>
	    		</table>	
	    		</div>
	    		<textarea name="textoErro" id="caixaTextoErro" disabled="true" class='textVazia'></textarea>
	    	</div>
	</div>
	    	
	  </div>
	  
	<div id="registradores">
	<form id="openFile">
	<input type="file" id="fileChooser" style="display:none;">
	</form>
			<table>
					<tr>
						<th>Nome</th>
						<th>Valor</th>
					</tr>	
					<tr>
						<td id="regName"></td>
						<td id="regValor"></td>
					</tr>
		</table>
</div>

		<div id="memoria">
<table>
	<tr>
		<th>Endereço Base</th>
		<th>+0</th>
		<th>+1</th>
		<th>+2</th>
		<th>+3</th>
	</tr>	
	<tr>
		<td id="index"></td>
		<td id="byte0"></td>
		<td id="byte1"></td>
		<td id="byte2"></td>
		<td id="byte3"></td>
	</tr>
</table>
	</div>
	</div>
		<div id="searchResult" class="modalOpen" >
			<div id="caixaSR">
				<div id="tituloSR">Add</div>
				<div id="sintaxeSR">rdst, rsrc1,rsrc2</div>
				<div id="descricaoSR">Adiciona dois registros, estende sinal de largura de registro.</div>
			</div>
		</div>
		
		<script>	
		
		$('#caixaPesquisa').keydown(function(e) {
			 if(e.keyCode == 13 ) {
				 preencheModal();
		    }
		});
		
		$(document).keydown(function(e) {
			 if(e.keyCode == 27 )
				 criaModal();
			 
			 if(e.keyCode == 112 && e.ctrlKey )
				abrirArquivo();
			 
			 if(e.keyCode == 113 && e.ctrlKey )
				downloadFile();
			 
			 if(e.keyCode == 114 && e.ctrlKey )
				$('#montar').trigger('click');
			 
			 if(e.keyCode == 117 && e.ctrlKey )
				 execAll();
			 
			 if(e.keyCode == 118 && e.ctrlKey )
				 $('#step_step').trigger('click');
			 
		});
		
		
		
		$(function(){
			   $("#fileChooser").change(function(e){ delegateFileSelect()
				   
				   $('#codeLines').val(count);	
			   
			   });
		});
		
		$('#caixaTextoMontagem').scroll(function(event){ 
			 $('#codeLines').scrollTop($(this).scrollTop());
		});
		
		$('#caixaTextoMontagem').keyup(function(){
			   var lines = $('#caixaTextoMontagem').val().split('\n');
			   var count ="";
			   for (var i = 1; i <= lines.length; i++) {
			    	count += i+'\n';
			}
		 	$('#codeLines').val(count);	
		});
</script>
</body>

</html>
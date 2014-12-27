	var registers= ['$zero', '$at', '$v0', '$v1', '$a0', '$a1', '$a2', '$a3', '$t0', '$t1', '$t2', '$t3', 
	                '$t4', '$t5', '$t6', '$t7', '$s0', '$s1', '$s2', '$s3', '$s4', '$s5', '$s6', '$s7', '$t8',
	                '$t9', '$k0', '$k1', '$gp', '$sp', '$fp', '$ra'];
	
function delegateFileSelect()
{               
    if (!window.File || !window.FileReader || !window.FileList || !window.Blob) {
        alert('The File APIs are not fully supported in this browser.');
        return;
    }   

    input = document.getElementById('fileChooser');
    if (!input) {
      alert("Um, couldn't find the fileinput element.");
   }
   else if (!input.files) {
      alert("This browser doesn't seem to support the `files` property of file inputs.");
   }
   else if (!input.files[0]) {
      alert("Please select a file before clicking 'Load'");               
   }
   else {
      file = input.files[0];
      fr = new FileReader();
      fr.onload = receivedText;
      fr.readAsText(file);
      //fr.readAsDataURL(file);
      console.log(fr);
      console.log(fr.result);
	  receivedText();
   }
}

function receivedText() {           
	var  result = fr.result;
	$('#caixaTextoMontagem').val(result);
}           



$(function() {
    var availableSearchs = [
            "add","addi","addu","sub","subu","addi","addiu","lw", "lh","lb","sw","sh","sb","lui","and","andi","or","ori",
            "xor","xori","nor", "slt", "slti","sll","srl","beq","bne","j","jr","jal","sltiu","sltu"
    ];
    $("#caixaPesquisa").autocomplete({
        source: availableSearchs,
        messages: {
        noResults: 'Nao foram encontrados resultados!',
        results: function()	{}
        }
     });
   
   });  

$(document).ready(function() {
	
	preencheRegistradores();
	preencheMemoria();
	
	$('#seletorAba1').click(function(){
		$('#seletorAba1').removeClass('inativa').addClass('ativa');
		$('#seletorAba2').removeClass('ativa').addClass('inativa');
		$('#caixaTextoMontagem').show();
		$('#codeLines').show();
		$('#caixaCodigoBinario').hide();
	});
	
	$('#seletorAba2').click(function(){
		$('#seletorAba1').removeClass('ativa').addClass('inativa');
		$('#seletorAba2').removeClass('inativa').addClass('ativa');
		$('#caixaCodigoBinario').show();
		$('#caixaTextoMontagem').hide();
		$('#codeLines').hide();
		$('#registradores').show();
		});

	$('#logoPesquisar').click(function(){
    	$('#pesquisa').submit();
    });
	
   /* $('#montar').click(function(){
    		$('#textoMontagem').submit();
     });*/
	
	$('#registradores').dblclick(function(){
    	if($(this).hasClass("normalR")){
    		$(this).switchClass("normalR","expandeR",1000,"easeInOutQuad");
    		$('#textoRegistradores').fadeOut();
    		$('#tableRegistradores').show();
    	}
    	
    	
    	else if ($(this).hasClass("expandeR")){
    		$(this).switchClass("expandeR","normalR",1000,"easeInOutQuad");
    		$('#textoRegistradores').fadeIn();
        	$('#tableRegistradores').hide();
    		}
    });
    	    
    $('#fecharAjuda').click(function(){
    	$('#caixaAjuda').hide();
    });
    
    $('#memoria').dblclick(function(){
    	if($(this).hasClass("normal")){
    		$(this).switchClass("normal","expande",1000,"easeInOutQuad");
    		$('#tituloMemoria').fadeOut();
    		$('#tableMemoria').show();
    	}
    	
    	
    	else if ($(this).hasClass("expande")){
    			$(this).switchClass("expande","normal",1000,"easeInOutQuad");
    			$('#tituloMemoria').fadeIn();
    			$('#tableMemoria').hide();
    		}
    	});
    
	
    $('#editar').click(function(){
    	$('#caixaTextoMontagem').removeClass('abaExecutar').addClass('abaEditar');
		$('#caixaMontagem').show();
		$('#codigoMontado').hide();
		$('#caixaCodigoMontado').hide();
    });	
    	
    $('#montar').click(function (){
    	if($('#caixaTextoMontagem').val()==null || $('#caixaTextoMontagem').val()=="")
    		$('#caixaTextoErro').val("É necessário codigo para montagem! \n");
    	
    	else{
    		AssemblerMips.montarTeste($('#caixaTextoMontagem').val(), delegate);
    		$('#seletorAba2').trigger("click");
    		$('#caixaTextoErro').val("Codigo Montado com sucesso. \n");
    		preencheRegistradores();
    		preencheMemoria();
    		AssemblerMips.montar(true,montaMemoria);
    	}	
    });
    
    $('#step_step').click(function(){
    		UCP.execute(true,regChange);
    });
    
});

function abrirArquivo(){
	var str =$('#fileChooser').trigger("click");
	console.log(str);
	handleFileSelect();
}

function delegate(parametro){
	$('#instrucao').replaceWith("<td id='instrucao'></td>");
	$('#codigoBin').replaceWith("<td id='codigoBin'></td>");

	
	for(var i =0;i<parametro.length;i++){
		var str=parametro[i].split('?');
		$('#instrucao').append("<div id='instr"+i+"'>" +str[0]+ "</div>");
		$('#codigoBin').append("<div id='codBin"+i+"'>" +str[1]+ "</div>");
	}
}


function regChange(parametro){
	$('#regValor').replaceWith("<td id='regValor'></td>");
	
	
	for(var i =0;i<32;i++){
		$('#regValor').append("<div> "+ parametro[i]+ "</div");
	}
}	

function execAll(){
	UCP.executeAll(true,regChange);
}



function preencheRegistradores(){
	$('#regName').replaceWith("<td id='regName'></td>");
	$('#regValor').replaceWith("<td id='regValor'></td>");
	
	
	for(var i =0;i<32;i++){
		$('#regName').append("<div> "+ registers[i]+ "</div");
		$('#regValor').append("<div> 0x00000000 </div>");
	}
}	

function preencheMemoria(){
	$('#index').replaceWith("<td id='index'></td>");
	$('#byte0').replaceWith("<td id='byte0'></td>");
	$('#byte1').replaceWith("<td id='byte1'></td>");
	$('#byte2').replaceWith("<td id='byte2'></td>");
	$('#byte3').replaceWith("<td id='byte3'></td>");
	
	for(var i =0;i<20;i++){
		$('#index').append("<div>" +i*4+ "</div");
		$('#byte0').append("<div id='reg"+i+"'> 00000000 </div>");
		$('#byte1').append("<div id='reg"+i+"'> 00000000 </div>");
		$('#byte2').append("<div id='reg"+i+"'> 00000000 </div>");
		$('#byte3').append("<div id='reg"+i+"'> 00000000 </div>");
	}

}	

function montaMemoria(parametro){

	$('#index').replaceWith("<td id='index'></td>");
	$('#byte0').replaceWith("<td id='byte0'></td>");
	$('#byte1').replaceWith("<td id='byte1'></td>");
	$('#byte2').replaceWith("<td id='byte2'></td>");
	$('#byte3').replaceWith("<td id='byte3'></td>");
	console.log(parametro);
	console.log(parametro.length);
	
	for(var i =0;i<1024;i++){
		var str=parametro[i].split('-');
		$('#index').append("<div>" +str[0]+ "</div");
		$('#byte0').append("<div>" +str[1]+ "</div");
		$('#byte1').append("<div>" +str[2]+ "</div");
		$('#byte2').append("<div>" +str[3]+ "</div");
		$('#byte3').append("<div>" +str[4]+ "</div");
		}
	
	if(parametro.length<256){
		for(var i=parametro.length;i<256;i++){
			$('#index').append("<div>" +i*4+ "</div");
			$('#byte0').append("<div id='reg"+i+"'> 00000000 </div>");
			$('#byte1').append("<div id='reg"+i+"'> 00000000 </div>");
			$('#byte2').append("<div id='reg"+i+"'> 00000000 </div>");
			$('#byte3').append("<div id='reg"+i+"'> 00000000 </div>");
		}
	}		
	
}


function downloadFile(){
	var blob = new Blob([$('#caixaTextoMontagem').val()], {type: "text/plain;charset=utf-8"});
	saveAs(blob, 'mips.asm');
}

function colore(){
/*	var name = $('#instrucao').childNodes;
	var valor = $('#codigoBin').chilNodes;
	var size = $('#codigoBin').childNodes.length;*/
	var div = $('.codigoAtivo');
	
	var index = div[0].id;
	console.log(index+'---');
	index = index.replace('instr','');
	console.log(index);
	$('#instr'+index).toggleClass('codigoAtivo');
	$('#codeBin'+index).toggleClass('codigoAtivo');
	$('#instr'+(index+1)).toggleClass('codigoAtivo');
	$('#codeBin'+(index+1)).toggleClass('codigoAtivo');
}

 function criaModal() {
	  // State changes
	  $("body").toggleClass("dialogIsOpen");
	  $("#menu").toggleClass("dialogIsOpen");
	  $("#searchResult").toggleClass("modalOpen");
	}

 function efetuaPesquisa(){
	 Buscador.pesquisarInstrucao($('#caixaPesquisa').val(),preenchePesquisa);
	 console.log("RODEI O EFETUAR");
 }
 
 function preenchePesquisa(parametro){
	var str=parametro.split('-');
	$('#tituloSR').text(str[0]);
	$('#sintaxeSR').text(str[1]);
	$('#descricaoSR').text(str[2]);
 }

 function preencheModal(){
	 if($("#caixaPesquisa").val()==null ||$("#caixaPesquisa").val()=="")
		 console.log("nada!");
		 
	 else{	 
		 criaModal();
		 efetuaPesquisa();
	 }
 } 


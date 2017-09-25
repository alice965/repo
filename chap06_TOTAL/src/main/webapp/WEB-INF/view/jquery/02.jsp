<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3 id="t">JQuery</h3>

JQuery로 element가 가지고 있는 정보를 얻거나 설정하는 메서드를 제공함.

<ul>
	<li>innerHTML			    --> html()	</li>
	<li>value 					--> val()		</li>
	<li>기본 attribute들 		--> attr()		</li>
	<li>특수 attribute들 		--> prop()	</li> 
		   - true/false : disable, checked, readonly
	<li>style 						--> css()		</li>
</ul>

<input type="text" id="text" class="i" maxlength="8"> 
<input type="text" class="i">
<input type="email"  id="e" class="i" size="40"><br>
<input type="checkbox"  id="cb"  checked> 체크박스

<hr>
<button id="bt1" style="border-radius:5px;">테스트1 val</button>
<button id="bt2" disabled>테스트2 여러개</button>
<button id="bt3">테스트3 attr</button>
<button id="bt4">테스트4 checkbox</button>
<button id="bt5">테스트5 css</button>

<script>
	$("#bt5").click(function(){
		window.alert($("#bt1").css("border-radius"));
		$("button").css("color", "white");
		$("button").css("background", "black");
	});
	$("#bt4").click(function(){
		var pc=$("#cb").prop("checked");
		window.alert(pc + " / " + typeof pc );
		$("#cb").prop("checked", false);
		$("#bt1").prop("disabled", false);
		window.alert($("#bt2").prop("disabled"));
		$("#bt2").prop("disabled", !$("#bt2").prop("disabled"));
	});
	
	$("#bt3").click(function(){
		var t=$("#text").attr("type");
		window.alert(t + " / " + typeof t)
		
		window.alert($("#e").attr("type")); //값 뽑는 형태
		window.alert($("#e").attr("class"));
		window.alert($("#e").attr("size"));
		window.alert($("#text").attr("maxlength"));
		//==============================
		$(".i").attr("type", "checkbox");	//값 설정 형태
	});
	
	
	$("#bt2").click(function(){
		window.alert($(".i").val()); //클래스가 여러개인 경우 값을 뽑으려고 하면 첫번째 값만 나온다.
		$(".i").val("");	 			   // 값 설정은 그룹으로 처리가 됨.
	});
	
	$("#bt1").click(function(){
		var text = $("#text").val();		//html 뽑는 거 val
		//window.alert(text);
		$("#t").html(text);				//html 설정 하는 거
		$("#text").val("");					//인풋박스 비우는 거
	});
</script>
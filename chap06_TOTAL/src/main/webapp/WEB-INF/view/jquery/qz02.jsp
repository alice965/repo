<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>JQuery 연습</h3>
아래 선택된 항목에 따라 총 상품금액을 계산하여 출력하는 script 설정
<hr>
<div>
	<h3>삼성 노트북 코어 i5</h3>
	판매가 : <div id="price"  data="250650">250650</div>
	<hr>
	수량 : 
	<button id="minus">-</button>
	<input type="number" id="ea" style="width:40px;" value="0"> 
	<button  id="plus">+</button>
	<hr>
	옵션 : 
	<ul style="list-style:none; ">
		<li><input type="checkbox"  class="opt" data="24000" />RAM 추가 (+24,000) </li>
		<li><input type="checkbox"  class="opt" data="22000" />외장그래픽카드추가 (+22,000)</li>
		<li><input type="checkbox"  class="opt" data="42000" />OS포함 (+42,000)</li>
	</ul>
</div>
<div>
	전체 상품 총액: <div id="tot"  data="0" ></div> <br>
</div>
<script>
$(".opt").change(function(){
	console.log(typeof $(this).attr("data"));	
	console.log($(this).attr("data") + 100);
	console.log(typeof  parseInt($(this).attr("data"))  );
	console.log($(this).attr("data") + 100);
	console.log(parseInt($(this).attr("data")) + 100 );
});

$("#minus").click(function(){
	var price = parseInt($("#price").attr("data"));
	$("#tot").innerHTML=price;
	if($("#ea").val()<=0){
		console.log("0보다 작음");
		$("#minus").prop("disabled", true);
	}else{
		$("#minus").prop("disabled", false);
		$("#ea").val( parseInt($("#ea").val())-1  );
		console.log("0보다 크다");
	}
});
$("#plus").click(function(){
	$("#ea").val( parseInt($("#ea").val())+1  );

	if($("#ea").val()>0){
	$("#minus").prop("disabled", false);
	}
});

</script>

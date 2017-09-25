<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>JQuery 연습#1</h3>
<hr/>
<input type="checkbox" id="all"/> 전체선택
<hr/>
<c:forEach var="i" begin="10" end="19">
	<input type="checkbox" class="item" value="${i }"/>${i }........<br/>
</c:forEach>
<hr/>
<button id="move">휴지통으로</button>
<script>
	$("#move").click(function(){
		//length propety 이용하는 방식
		//JQuery로 객체들을 선택시 length라는 변수를 갖게 됨.
		for (var i=0; i<$(".item").length; i++) {
			// $(".item")[i] X 
			//get, eq를 사용하면 됨.
			console.log("eq : " + $(".item").eq(i));	//jQuery 객체
			console.log("get : " +$(".item").get(i)); //HTML DOM
			console.log("==============");
			
		/*	자바스크립트 형태
			if($(".item").get(i).checked){
				window.alert($(".item").get(i).value);
			}
		*/
			//이거 이제 됨. 되게 하려면 다시 제이쿼리 객체로
			// 자바스크립트로 뽑은 것을 제이쿼리로 감싸서 출력함.
			if($(".item").get(i).checked){
				window.alert($($(".item").get(i)).val());
			}
			
			//eq로 뽑은 것은 val로 됨..
			//제이쿼리 형태
		/*	if($(".item").eq(i).prop(checked)){
				window.alert($(".item").eq(i).val());
			}
			*/
		}
	});
	
	$("#all").change(function(){
		$(".item").prop("checked", $(this).prop("checked"));
	});
	/*
	$("#move").click(function(){
		//length 프로퍼티 이용하는 방식
		window.alert($("#all").length);
		window.alert($(".item").length);
		window.alert($("button").length);
		
		var ar=[];
		$(".item").each(function(){
			if($(this).prop("checked")) {
				//window.alert($(this).val()+"을 지우시겠습니까");
				ar.push($(this).val());
			}
		});
		window.confirm(JSON.stringify(ar)+"들을 삭제하시겠습니까?");
	});
	
		*/
		

</script>

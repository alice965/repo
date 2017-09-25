<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h3 id="q">JQuery</h3>
each() : 각 엘레멘트마다, 특정수행 작업이 필요할때,
<hr/>
<c:forTokens var="t" delims="," items="봄,여름,가을,겨울">
	<button class="bt">${t }</button>
</c:forTokens>
<hr/>
<c:forEach begin="1" end="4">
	-><input type="text"><br>
</c:forEach>
<button id="sbt">EACH TEST</button>
<script>
$("#sbt").click(function(){
	$("input").css("background", "yellow");
});
	$("#q").click(function() {
		$(".bt").each(function(){
			//this.innerHTML += this.innerHTML +",";
			// this.html("zzzz");
			$(this).prop("disabled", true);
			console.log($(this).html() + " / " +typeof this.innerHTML + " / " + $(this).html().length );
			if($(this).html().length ==2) { + typeof this
				window.alert($(this).html() );
			}
		});
		// 이 작업은 each를 안써도 되는 상황, $(".bt").prop("disabled", true);
	});
</script>



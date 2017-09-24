<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div align="center">	
		<h3>My Info</h3>
			<p>
				<b>NAME</b><br/>
					<c:if test="${map.NAME eq null }">
						<small> 입력된 정보가 없습니다.</small>
				 	</c:if>
					${map.NAME} <br/>
			</p>
			<p>
				<b>GENDER</b><br/>
					<c:if test="${map.GENDER eq null }">
						<small> 입력된 정보가 없습니다.</small>
				 	</c:if>
					${map.GENDER }<br /> 
			</p>
			<p>
				<b>BIRTH</b><br/>
					<c:if test="${map.BIRTH eq null }">
						<small> 입력된 정보가 없습니다.</small>
				 	</c:if>
					${map.BIRTH}<br/> 
			</p>
			<p>
				<b>ADDRESS</b><br/> 
					<c:if test="${map.ADDRESS eq null }">
						<small> 입력된 정보가 없습니다.</small>
				 	</c:if>
				
					${map.ADDRESS}<br /> 
			</p>
			
			<button type="button" id="edit" >정보변경</button>
			<button type="button" id="drop" >회원탈퇴</button>
	</div>
	<script>
		document.getElementById("edit").onclick=function(){
			location.href='/my/edit_info'
		}
		document.getElementById("drop").onclick=function(){
			location.href='/my/dropout'
		}
	
	
	</script>
</div>
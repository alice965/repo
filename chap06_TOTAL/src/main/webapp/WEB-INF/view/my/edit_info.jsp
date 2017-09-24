<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<div align="center">	
		<h3>My Info</h3>
		<form action="/my/edit_info" method="post">
			<p>
				<b>NAME</b><br/>
				<c:choose>
					<c:when test="${map.NAME eq null }">
						<input type="text" name="name"/>
				 	</c:when>
				 	<c:otherwise>
				 		<input type="text" name="name" value="${map.NAME}"/>
				 	</c:otherwise>
				 </c:choose>
					 <br/>
			</p>
			<p>
			
				<b>GENDER</b><br/>
				<c:choose>
					<c:when test="${map.GENDER eq null }">
						<input type="text" name="gender"/>
				 	</c:when>
				 	<c:otherwise>
				 		<input type="text" name="gender" value="${map.GENDER}"/>
				 	</c:otherwise>
				</c:choose>
					 <br/>				
			</p>
			<p>
				<b>BIRTH</b><br/>
				<c:choose>
					<c:when test="${map.BIRTH eq null }">
						<input type="text" name="birth"/>
				 	</c:when>
				 	<c:otherwise>
				 		<input type="text" name="birth" value="${map.BIRTH}"/>
					</c:otherwise>	
				</c:choose>
			</p>
			<p>
				<b>ADDRESS</b><br/> 
					<c:choose>
					<c:when test="${map.ADDRESS eq null }">
						<input type="text" name="address"/>
				 	</c:when>
				 	<c:otherwise>
				 		<input type="text" name="address" value="${map.ADDRESS}"/>
					</c:otherwise>	
				</c:choose>
					 <br/>	
			</p>
			
			<button type="submit" >정보변경</button>
		</form>
	</div>
</div>

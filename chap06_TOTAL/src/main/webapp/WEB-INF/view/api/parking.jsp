<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
th {
	border-bottom: 1px solid;
	text-align: left;
}

th, td {
	padding: 10px;
}

input {
	font-family: 맑은 고딕;
	padding: 3px;
}
button {
	padding: 7px;
}
</style>
<div align="left">
	<h2>주차장 정보 / ${data.list_total_count }</h2>
	<form action ="/api/parking">
		<input type="text" name="location" placeholder="지역명 입력">
		<button type="submit" style="width: 50px;">검색</button>
	</form>
	
	<table style="width: 80%">
		<thead>
			<tr>
				<th style="width: 20%">주차장명</th>
				<th style="width: 20%">주차장위치</th>
				<th style="width: 20%">수용공간</th>
				<th style="width: 20%">주차장종류명</th>
				<th style="width: 10%">분당요금</th>
				<th style="width: 10%">일최고요금</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="obj" items="${data.row }">
				<tr>
					<td>${obj.PARKING_NAME }</td>
					<td>${obj.ADDR }</td>
					<td><fmt:formatNumber value="${obj.CAPACITY }" pattern="#,###" /></td>
					<td>${obj.PARKING_TYPE_NM }</td>
					<td>${obj.RATES }</td>
					<td>${obj.DAY_MAXIMUM }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>



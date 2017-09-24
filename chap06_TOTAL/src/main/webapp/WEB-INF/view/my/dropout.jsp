<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div align="center">
	<h2>회원 탈퇴 신청</h2>
	<form action="/my/dropout" method="post">
		<p> 현재 비밀번호를 입력해주세요 <br><br>
		
		<input type="password" name="pass" required> 
		</p>
		
		<button type="submit">탈퇴신청</button>
		<a href="/my/info"><button type="button">탈퇴취소</button></a>
	
	</form>


</div>
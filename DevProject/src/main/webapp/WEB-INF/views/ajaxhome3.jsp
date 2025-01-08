<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
//모든 html dom객체가 준비가 되었다면 이 함수를 시작해줘.
$(document).ready(function(){
	//putbtn 클릭을하면 이 함수를 작동시켜줘 (서버에 데이타를 전송(ajax)하고, 전송성공유무를 리턴받아서 출력한다.
	$("#getBtn").on("click",function(){
		//사용자가 입력한 데이터를 읽어와서 객체를 만들것
		let boardNo = $("#boardNo");
		//사용자입력한값을 가져온다.
		let boardNoVal = boardNo.val();
		//비동기식방식으로 서버에 전송한다. (4가지 정보를 넘겨줘야한다.)
		$.get("/board/"+boardNoVal, function(result){
				console.log("result="+result);
				const obj = JSON.stringify(result); 
				console.log("parse result="+obj);
				alert("result="+obj);
			}
		); 
	}); 
}); 

</script>
<body>
	<h1>Ajax Home</h1>
	<form>
		boardNo: <input type="text" name="boardNo" value="" id="boardNo"><br>
		title: <input type="text" name="title" value="" id="title"><br>
		content: <input type="text" name="content" value="" id="content"><br>
		writer: <input type="text" name="writer" value="" id="writer"><br>
	</form>
	<div>
		<button id="getBtn">객체요청(get type )</button>
	</div>
</body>
</html>
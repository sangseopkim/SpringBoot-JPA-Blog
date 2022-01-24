<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<div class="container">

	<div class="card m-2">
		<div class="card-body">
			<h4 class="card-title">제목제목</h4>
			<a href="#" class="btn btn-primary">상세보기</a>
		</div>
	</div>

	<div class="card m-2">
		<div class="card-body">
			<h4 class="card-title">제목제목</h4>
			<p class="card-text">내용쓰는 부분</p>
			<a href="#" class="btn btn-primary">상세보기</a>
		</div>
	</div>


	<p>&nbsp;</p>
	<p>Form data를 json 데이터로 변경해서 Ajax로 보내기 / post, put, patch, delete</p>
	<p>
		전송방식 : 
		<select id="send_type">
			<option value="POST">POST</option>
			<option value="PUT">PUT</option>
			<option value="PATCH">PATCH</option>
			<option value="DELETE">DELETE</option>
		</select> &nbsp; &nbsp;
		<input type="button" name="btn" id="btn" value="확인">
	</p>

	<form id="myForm" name="myForm">
		username : <input type="text" name="username" size="50" value="김상섭"><br />
		email &nbsp; &nbsp;&nbsp; &nbsp; : <input type="text" name="email" size="50" value="k99ss@daum.net"><br />
		password : <input type="text" name="password" size="50" value="qwer1234">
	</form>
	<p id="json_data_view">&nbsp;</p>
	<p>&nbsp;</p>


</div>

<%@ include file="../layout/footer.jsp" %>
	<script>

		$(document).ready(function() {
			console.log("index script log >>>");
			$("#btn").on("click", ()=>{
			
				let data_send_type = $("#send_type").val();
				console.log("data_send_type : " + data_send_type);
			
				let frm = $("#myForm");
				let form = frm[0]; // == $("#myForm")[0];
				let username = document.myForm.username.value;
				let params = frm.serializeObject();
				
				console.log(form.username.value + ", "+ username);
				console.log(frm.serialize()); // 결과=> username=김상섭&email=k99ss@daum.net&password=qwer1234
				console.log(frm.serializeArray()); // 결과 => 배열과 객체 방식으로 변경 => 바로 사용하기 힘든 구조
				console.log(params); // 결과 => {"username": "김상섭", "email": "k99ss@daum.net", "password": "qwer1234"} json 객체로 변경
				console.log(JSON.stringify(params)); // json 객체를 텍스트로 변경 / Ajax로 보낼때 사용
				
				$.ajax({
					url: "/blog/dummy/ajax", 
					type: data_send_type,
					data: JSON.stringify(params),
					contentType: 'application/json; charset=UTF-8', // 서버로 보내는 데이터 형식
					dataType: 'html', // 서버에서 받을 데이터 형식(html, json)
					//async: true, // 비동기설정 true(기본값), 동기 설정이 필요하면 false로 설정  
					beforeSend:function() {
						console.clear();
						console.log("##beforeSend()");
						$("#json_data_view").html(JSON.stringify(params));
					},
					success:function(data) {
						console.log("##success()");					
						console.log(data);
					},
					complete:function(c) {
						console.log("##complete()");
					},
					error: function (request,status,error) {
						console.log("##error()");
						console.log("error code: "+request.status+"\n"+"error msg: "+error);
					}
				}); // End Ajax
			
				
			}); // End btn click
		});	// End document.ready
	</script>




</body>
</html>


    
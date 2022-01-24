/*
$(document).ready(function() {
	console.log("user.js script >>>");
	$("#joinFormSaveBtn").on("click", function(){ // 회원가입
		console.log("btn click event >>>");
		let form = $("#joinForm");
		let formJsonData = form.serializeObject();
		console.log(formJsonData);
		
	}); // 가입버튼 클릭 이벤트
});
*/

// 강좌에서 나온 스크립트 내용
let user = {
	init: function() {
		$("#joinFormSaveBtn").on("click", ()=>{
			this.save();
		});
	},
	save: function() { // 회원가입
		let params = $("#joinForm").serializeObject();
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data     : JSON.stringify(params),
            contentType: "application/json",
            //dataType: "json", // 기본값이 json, text, html
            /*beforeSend:function(){
				console.log("보내기 시작...")
			},
			complete:function() {
				console.log("보내기 완료...");
			},
			success:function(json) {
				console.log("수신내용 : ");
				console.log(json)
			},
			error: function (error, errtext) {
				console.log("##error()");
				console.log("error code: "+error.status+"\n"+"error msg: "+errtext);
			}*/
		})
		.done((json, textStatus, xhr)=>{
			console.log("done begin ..................");
			console.log(json);
			console.log("textStatus : " + textStatus);
			console.log("xhr.status : " + xhr.status);
			console.log("done end ..................");
		})
		.fail(function(error){
			console.log("fail begin ..................");
			console.log("error code : "+error.status);
			console.log(error.responseText);
			console.log("fail end ..................");
		}); // ajax 통신을 이용해서 요청
	}, // save
	login: function(){ // 로그인 => 사용안함
		console.log("user.js > login() begin");
		let params = $("#loginForm").serializeObject();
		console.log(params);
		$.ajax({
			type: "POST",
			url: "/auth/loginProc",
			data     : JSON.stringify(params),
            contentType: "application/json",
            dataType: "json",
			success: function(json) {
				console.log("수신내용 : ");
				alert("로그인 완료");
				location.href = "/";
			},
			error: function (error, errMsg) {
				console.error("error code: "+error.status+"\n"+"error msg: "+errMsg);
				console.log(error);
			}
		});
	} // 로그인
}

user.init();


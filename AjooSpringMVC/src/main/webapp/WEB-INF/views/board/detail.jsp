<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 상세</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>
	<h1>게시글 상세</h1>
	<ul>
		<li>
			<label>제목</label>
			<span>${board.boardTitle }</span>
		</li>
		<li>
			<label>작성자</label>
			<span>${board.boardWriter }</span>
		</li>
		<li>
			<label>내용</label>
			<span>${board.boardContent }</span>
		</li>
		<li>
			<label>첨부파일</label>
			<a href="#">${board.boardFilename }</a>
		</li>
	</ul>	
	<div>
		<button>수정하기</button>
		<button>삭제하기</button>
		<button>목록으로</button>
		<button>뒤로가기</button>
	</div>
	

	
	<!-- 댓글 등록 -->
	<hr>
		<!--  Ajax 사욯할 땐 form태그 사용하지 않음 -->
<!-- 	<form action="/reply/add.kh" method="post"> -->
		<input type="hidden" name="refBoardNo" id="refBoardNo" value="${board.boardNo }">
		<table width="500" border="1">
			<tr>
				<td>
					<input type="text" name="replyContent" id="replyContent" size="50">
				</td>
				<td>
<!-- 					<input type="submit" value="완료"> -->
					<button id="rSubmit">등록하기</button>
										
				</td>
			</tr>			
		</table>
		
<!-- 	</form> -->
	<!-- 댓글 목록 -->
	<!-- JSTL(Core)태그를 사용하여 반복문을 생성하는 구문. -->
	<!-- c:forEach: JSTL 태그, 컬렉션 또는 배열과 같은 요소들을 반복하여 처리할 떄 사용. -->
	<!-- items="" 반복하고자 하는 컬렉션 또는 배열을 지정.  -->
	<!-- 는 JSP내에서 사용되는 EL문법.안의 표현식이 평가되어 반복 대상이 됨.  -->
	<!-- var="reply" 각 반복 요소에 대한 이름을 지정. 이 이름은 반복되는 요소를 참조하는데 사용딤. -->
	<!-- 여기서는 각 반복 요소가  라는 이름으로 참조됨.-->
	<!-- 따라서 위의 코드는 에 있는 요소들을 순회하면서 각 요소를 라는 이름으로 참조하여 처리함. -->
	
	<table width="550" border="1" id="replyTable">
		<tbody>
			
		</tbody>
<%-- 		<c:forEach items="${rList }" var="reply">  --%>
<!-- 		<tr> -->
<%-- 			<td>${reply.replyWriter }</td> --%>
<%-- 			<td>${reply.replyContent }</td> --%>
<%-- 			<td>${reply.rCreateDate }</td> --%>
<!-- 			<td> -->
<!-- 				<a href="#">수정</a> -->
<!-- 				<a href="#">삭제</a> -->
<!-- 			</td> -->
<!-- 		</tr> -->
<%-- 		</c:forEach> --%>
	</table>

	
	<script>
	
		// 댓글 목록
		getReplyList(); // 페이지가 로드될 때 댓글 목록을 가져오는 함수 호출
		function getReplyList(){ // (...) 댓글 목록을 가져오는 함수 정의
			var refBoardNo = $("#refBoardNo").val(); // "refBoardNo" id를 가진 요소의 값을 가져와서 변수 refBoardNo에 저장.(게시글 번호)
			$.ajax({ // jQuery의 Ajax 함수를 사용하여 서버에 비동기적으로 요청을 보냄.
				url : "/reply/list.kh", // 요청을 보낼 URL 지정. /reply/list.kh -> 댓글 목록을 가져오는 기능을 처리하는 서버의 URL을 나타냄
				data : { "refBoardNo" : refBoardNo }, // 서버에 전달할 데이터 지정. refBoardNo라는 이름으로 게시글 번호 전달.
				type : "GET", // 요청의 HTTP 메소드 지정. GET을 사용하여 데이터를 서버로 요청
				success : function(result){ // 요청이 성공했을 때 실행할 콜백 함수 지정. 여기서는 서버로부터 받은 데이터를 이용하여 댓글 목록을 생성하고 화면에 표시
					var tableBody = $("#replyTable tbody");
	//					tableBody.empty(); // * empty(); 제이쿼리 메소드로 삭제한 댓글들 남아있는 오류 해결
					tableBody.html(""); // * empty()가 아니더라도 html("")를 이용해서 비워줄 수 있음
					var tr;
					var replyWriter;
					var replyContent;
					var rCreateDate;
					var btnArea;
					if(result.length > 0){
						for(var i in result){	
							var replyWriterVal = result[i].replyWriter;
							var replyContentVal = result[i].replyContent;
							var rCreateDateVal = result[i].rCreateDate;
							var replyNoVal = result[i].replyNo;							
							tr = $("<tr>") // <tr><tr>
							//replyWriter = $("<td>").text(result[i].replyWriter); // <td><td>
							replyWriter = $("<td>").text(replyWriterVal); // <td><td>
							replyContent = $("<td>").text(replyContentVal);
							rCreateDate = $("<td width='100'>").text(rCreateDateVal);
							btnArea = $("<td width='90'>")
								// ** JS에서의 this는, 이벤트가 발생한 태그를 알려주는 키워드 ! (요소 탐색, 요소 조작 가능)
								.append("<a href='javascript:void(0)' onclick='modifyViewReply(this, " + replyNoVal + ", \"" + replyContentVal + "\");'>수정</a>")
								//.append("<a href='javascript:void(0)' onclick='modifyViewReply(this, "replyNoVal+", \"" + replyContentVal + "\");'>수정</a>") // *문자열이기 때문에 replyContent 앞뒤에 '' + 이미 사용중이기 때문에 escape문자(\) 사용해주기
	//							.append("<a href='javascript:void(0)' onclick='modifyViewReply(this, \"" + replyContentVal + "\");'>수정</a>")
	
	
								.append("<a href='javascript:void(0)' onclick='removeReply(" + replyNoVal + ");'>삭제</a>")								
							tr.append(replyWriter);
							tr.append(replyContent);
							tr.append(rCreateDate);
							tr.append(btnArea);
							tableBody.append(tr);
						}
					}					
				},
				error : function(){
					alert("ajax 통신 실패, 관리자에게 문의해주세요");
				}
			});
		}
	
		// 댓글 등록
		$("#rSubmit").on("click", function(){ // rSubmit id를 가진 요소에 클릭 이벤트 핸들러 등록.(댓글 등록버튼)
			// var refBoardNo = ${board.boardNo };
			var refBoardNo = $("#refBoardNo").val(); // refBoardNo id를 가진 요소의 값을 가져와 변수 refBoardNo에 저장.(게시글 번호)
			var replyContent = $("#replyContent").val(); // replyContent id -> 변수 replyContent에 저장. (사용자가 입력한 댓글 내용) 
			$.ajax({ // jQuery의 Ajax 함수를 사용하여 서버에 비동기적으로 요청을 보냄.
				url : "/reply/add.kh", // 요청을 보낼 url을 지정. reply/add.kh -> 댓글을 추가하는 기능을 처리하는 서버의 URL.
				data : {"refBoardNo" : refBoardNo // 서버에 전달할 데이터를 지정. refBoardNo와 replyContent라는 이름으로 게시글 번호와 댓글 내용을 전달함.
						, "replyContent" : replyContent},
				type : "POST", // POST 메소드를 사용하여 데이터를 서버로 전송함.
				success : function(result){ // 요청이 성공했을 때 실행할 콜백 함수를 지정.
					alert("댓글이 등록되었습니다."); // 등록 성공 알림창 표시
// 					location.href = "/board/detail.kh?boardNo=" + refBoardNo; // 페이지 새로고침 발생
					getReplyList(); // 페이지 새로고침 X (댓글 목록을 다시 가져와서 화면에 표시)
					$("#replyContent").val(""); // 댓글 등록 후 댓글 입력란 비우기
				},
				error : function(){
					alert("Ajax 통신 실패! 관리자에게 문의바랍니다.");
				}
			});
		});
		
		// 댓글 삭제
		function removeReply(replyNo){
// 			var replyNo = 2; // 변수선언 누락주의
			$.ajax({
				url : "/reply/remove.kh",
				data : { "replyNo" : replyNo },
				type : "POST",
				success : function(result){
					alert("댓글이 삭제되었습니다.");
					getReplyList(); // 댓글 삭제 후 목록 다시 불러오기
				},
				error : function(){
					alert("ajax 통신 실패, 관리자에게 문의해주세요")
				}
			});		
		}		
		
		// 댓글 수정
		function modifyViewReply(obj, rNo, rContent){		
			var tr = $("<tr>");
			tr.append("<td colspan='3'><input type='text' size='60' value='" + rContent + "'></td>");
			tr.append("<td><button type='button' onclick='modifyReply("+rNo+", this);'>수정완료</button></td>");
			$(obj).parent().parent().after(tr);		// $(obj) = 수정 , $(obj).parent() , $(obj).parent().parent() = tr , after = 여기에 댓글창 설치
													// 수정창은 버튼을 눌렀던 행 바로 밑에 붙어야 함.
//			$("#replyTable tbody").append(tr);
//			$("<td>").append("<input type='text'>");
		}
		
		function modifyReply(replyNo, obj){
			var inputTag = $(obj).parent().prev().children(); // 버튼을 감싸고 있는 td태그
			var replyContent = inputTag.val();
			$.ajax({
				url : "/reply/update.kh",
				data : { "replyNo" : replyNo, "replyContent" : replyContent },
				type : "POST",
				success : function(data){
					alert("서비스 결과 : " + data);
					getReplyList();
				},
				error : function(){
					alert("Ajax 통신 실패! 관리자에게 문의해주세요.");
				}
			});
		}
		
		
		
	
		
	</script>
</body>
</html>
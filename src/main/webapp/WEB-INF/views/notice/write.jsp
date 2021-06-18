<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page trimDirectiveWhitespaces="true" %>

<c:set var = 'root' value='${pageConexte.request.contextPath }/' /> 
<!DOCTYPE html>
<html>
<head>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		
		$(".cancel_btn").on("click", function(){
			event.preventDefault();
			location.href = "/home";
		})
		
// 		$(".write_btn").on("click", function(){
// 			if($.trim($('#ttl').val()) ==""){
// 				alert("제목을 입력해주세요");
// 				return false;
// 			}
// 			if($.trim($('#cont').val()) ==""){
// 				alert("내용을 입력해주세요");
// 				return false;
// 			}
// 			if($.trim($('#rgstrNm').val()) ==""){
// 				alert("작성자를 입력해주세요");
// 				return false;
// 			}
			
// 		});
					
	});

	</script>

<body>
<div class="container">

	<section id="container">
		<form:form role="form" modelAttribute="notice" name="writeForm" method="post" action="/write" enctype="multipart/form-data">
			<div class="form-group">
				<label for="ttl" class="col-sm-2 control-label">제목</label>
				<input type="text" class="form-control" id="ttl" name="ttl" class="chk" title="제목을 입력해주세요" />
				<form:errors path="ttl" />
			</div>		
			<div class="form-group">
				<label for="cont" class="col-sm-2 control-label">내용</label>
				<textarea id="cont" class="form-control" name="cont" class="chk" title="내용을 입력해주세요" /></textarea>
				<form:errors path="cont" />
			</div>
			<div class="form-group">
				<label for="rgstrNm" class="col-sm-2 control-label">작성자</label>
				<input type="text" class="form-control" id="rgstrNm" name="rgstrNm" class="chk" title="작성자을 입력해주세요" />
				<form:errors path="rgstrNm" />
			</div>			
			<div class="form-group">
				<label for="file" class="col-sm-2 control-label">파일</label>
				<input type="file" path="uploadFile" name="uploadFile" class="btn btn-default btn-xs" />
			</div>
			<div>
				<button type="submit" class="write_btn btn btn-primary" id="btnSave" >작성</button>
				<button type="button" class="cancel_btn btn btn-danger">목록</button>
				
			</div>
			
		</form:form>
	</section>
</div>
</body>
</html>
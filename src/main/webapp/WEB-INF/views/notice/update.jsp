<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			
			var formObj = $("form[name='updateForm']");
			
			$(".cancel_btn").on("click", function(){
				event.preventDefault();
				location.href = "/home?page=${page}";
			})
			
			$(".update_btn").on("click", function(){
				if(fn_valiChk()){
					return false;
				}
				formObj.attr("action", "/update");
				formObj.attr("method", "post");
				formObj.submit();
			})
		})
		
	</script> 

<body>
<div class="container">

			<section id="container">
				<form name="updateForm" role="form" method="post" action="/update">
					<input type="hidden" name="seq" value="${update.seq}" readonly="readonly"/>

					<div class="form-group">
						<label for="ttl" class="col-sm-2 control-label">제목</label>
						<input type="text" class="form-control" id="ttl" name="ttl" value="${update.ttl}" class="chk" title="제목을 입력하세요."/>
					</div>				
					<div class="form-group">
						<label for="cont" class="col-sm-2 control-label">내용</label>
						<textarea class="form-control" id="cont" name="cont" class="chk" title="내용을 입력하세요"><c:out value="${update.cont}" /></textarea>
					</div>
					<div class="form-group">
						<label for="rgstrNm" class="col-sm-2 control-label">작성자</label>
						<input type="text" class="form-control" id="rgstrNm" name="rgstrNm" value="${update.rgstrNm}" readonly="readonly"/>
					</div>
					<div class="form-group" class="col-sm-2 control-label">		
						<label for="regdate">작성날짜</label>
 		                <td>${update.getNotiYmd()}</td>
					</div>	
					<div>
						<button type="submit" class="update_btn btn btn-success">저장</button>
						<button type="button" class="cancel_btn btn btn-danger">취소</button>
					</div>
				</form>
			</section>
			<hr />
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>객체 탐지</title>
		<script src="<c:url value='js/jquery-3.6.0.min.js'/>"></script>
		<!-- <script src="<c:url value='js/object.js'/>"></script> -->
		<script type="text/javascript">
			$(function(){
				$('#objectForm').on('submit', function(event){
					event.preventDefault(); //submit 후에  reload 안 되게
					//alert($('#uploadFile').val());	//c:/fakepath/run.jpg	
					var src = $('#uploadFile').val().split("\\").pop(); //run.jpg
					alert(src);
					var formData = new FormData($('#objectForm')[0]);
					
					$.ajax({
						type:"post",
						enctype:"multipart/form-data",
						url:"objectDetect",
						data:formData,
						processData:false, //필수
						contentType:false, //필수
						success:function(result){
							alert(result);
							data = JSON.parse(result);
							alert(data.predictions[0].detection_names[0]);
							//alert('in');
							//drawCanvas(result, src);
							alert('out');
						},
						error:function(e){
							alert("에러 발생 : " + e);
						}			
					});
					/**/
				});
			});
			
			function drawCanvas(result, src){
				var canvas = document.getElementById("objectCanvas");
				var context = canvas.getContext("2d");
				
				var objectImage = new Image();
				objectImage.src = "/ai/" + src;
				objectImage.width = canvas.width;
				objectImage.height = canvas.height;
				
				objectImage.onload = function(){
					context.drawImage(objectImage, 0, 0, objectImage.width, objectImage.height);	
					
					$.each(result, function(i){
						var x1 = this.x1 * objectImage.width;
						var y1 = this.y1 * objectImage.height;
						var x2 = this.x2 * objectImage.width;
						var y2 = this.y2 * objectImage.height;	
						
						context.font = '15px batang';
						context.fillStyle = "rgb(255, 0, 255)";
						//context.fillText(this.name, x1, y1);
						
						context.strokeStyle = "red";
						context.lineWidth = 2; 
						//context.strokeRect(x1, y1, x2-x1, y2-y1);	
								
						context.fillText(this.name, y1, x1);
						context.strokeRect(y1, x1, y2 - y1, x2 - x1);	
					});			
				};
			}
			
			
		</script>
	</head> 
	<body>
		<h2>객체 탐지</h2>
		
		<form id="objectForm"  method="post" enctype="multipart/form-data">	         
         	파일 :  <input type="file" id="uploadFile" name="uploadFile"> 
           <input type="submit" value="결과 확인">
		</form><br><br>		
				
		<h2>객체 탐지 결과를 이미지에 박스로 표시</h2>
		<canvas id="objectCanvas" width="600" height="600"></canvas>		
		
		<div id="resultDiv"></div>
		
		<br><br>
		<a href="/">index 페이지로 이동</a>
	</body>
</html>
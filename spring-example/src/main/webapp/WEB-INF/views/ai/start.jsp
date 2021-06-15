<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Start</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="resources/js/nmt.js"></script>
<script type="text/javascript">

    function testResult(){
    	var obj = {"message":{"@type":"response","@service":"naverservice.nmt.proxy","@version":"1.0.0","result":{"srcLangType":"ko","tarLangType":"en","translatedText":"It's a pleasure to meet you."}}};
		alert(obj.message['@type']);
		var msg = obj.message.result.translatedText;
		alert(msg);
		 $('#message').html("<h1>"+msg+"</h1>");
    }

</script>
</head>
<body>
	<form action="" method="">
		<input type="text" name="userId" id="userId" value="안녕">
		<input type="button" value="확인" onclick="checkID()"><br>
		<input type="button" value="확인2" onclick="testResult()"><br>
		<span id="message"></span><br>
	</form>
</body>
</html>
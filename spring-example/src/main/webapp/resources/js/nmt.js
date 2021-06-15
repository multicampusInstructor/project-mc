/**
 * 
 */
function checkID(){
	alert('check '+$("#userId").val());
	$.ajax(
		{
         type:"get",
         url:"/mc-service/team1",
         contentType: "application/json",
         data :{"userId":$("#userId").val()},
	     success:function (data,textStatus){
	    	  alert(data);
	    	  var obj = JSON.parse(data);
	    	  alert(obj.message.result.translatedText);	
	    	  $('#message').text(obj.message.result.translatedText);
	     },
	     error:function(data,textStatus){
	        alert("에러가 발생했습니다.");
	     },
	     complete:function(data,textStatus){
	    	 
	     }
	  }	
	);
}
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Test</title>
<script>
	function test(){
		temp = "{\"predictions\": [{\"num_detections\": 4, \"detection_classes\": [17.0, 17.0, 17.0, 17.0], \"detection_names\": [\"cat\", \"cat\", \"cat\", \"cat\"], \"detection_scores\": [0.957558, 0.855716, 0.687029, 0.466183], \"detection_boxes\": [[0.0755033, 0.283284, 0.901218, 0.788447], [0.14067, 0.115573, 0.901519, 0.711627], [0.108265, 0.323811, 0.874295, 0.645661], [0.0637712, 0.130355, 0.728322, 0.865261]]}]}";
		data = JSON.parse(temp);
		console.log(data.predictions[0].detection_names[0]);
	}
	test();
</script>
</head>
<body>

</body>
</html>
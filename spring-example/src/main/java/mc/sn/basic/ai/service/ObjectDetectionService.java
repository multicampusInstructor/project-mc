package mc.sn.basic.ai.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import mc.sn.basic.ai.vo.ObjectVO;

@Service
public class ObjectDetectionService {
	public /*ArrayList<ObjectVO>*/ String objectDetect(String filePathName) {
		StringBuffer reqStr = new StringBuffer();
        String clientId = "b7f438k0mj";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "ZNiLwCBM2JNGvwLn6MRaITtNnnfJLz8QPS7xRxKM";//애플리케이션 클라이언트 시크릿값";
        ArrayList<ObjectVO> objectList = new ArrayList<ObjectVO>();
        String result = null;
        try {
            String paramName = "image"; // 파라미터명은 image로 지정
            String imgFile = filePathName;
            File uploadFile = new File(imgFile);
            String apiURL = "https://naveropenapi.apigw.ntruss.com/vision-obj/v1/detect"; // 객체 인식
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            // multipart request
            String boundary = "---" + System.currentTimeMillis() + "---";
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
            con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
            OutputStream outputStream = con.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"), true);
            String LINE_FEED = "\r\n";
            // file 추가
            String fileName = uploadFile.getName();
            writer.append("--" + boundary).append(LINE_FEED);
            writer.append("Content-Disposition: form-data; name=\"" + paramName + "\"; filename=\"" + fileName + "\"").append(LINE_FEED);
            writer.append("Content-Type: "  + URLConnection.guessContentTypeFromName(fileName)).append(LINE_FEED);
            writer.append(LINE_FEED);
            writer.flush();
            FileInputStream inputStream = new FileInputStream(uploadFile);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
            inputStream.close();
            writer.append(LINE_FEED).flush();
            writer.append("--" + boundary + "--").append(LINE_FEED);
            writer.close();
            BufferedReader br = null;
            int responseCode = con.getResponseCode();
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 오류 발생
                System.out.println("error!!!!!!! responseCode= " + responseCode);
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }
            String inputLine;
            if(br != null) {
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                result = response.toString();
                System.out.println(response.toString());  // 결과 출력
                objectList = jsonToVoList(response.toString());       
                System.out.println(objectList.toString());
            } else {
                System.out.println("error !!!");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return /*objectList*/result;
	}
	
	public ArrayList<ObjectVO> jsonToVoList(String jsonStr){
		 ArrayList<ObjectVO> objectList = new ArrayList<ObjectVO>();
		 
		 try {
			JSONParser jsonParser = new JSONParser();
			 JSONObject jsonObj = (JSONObject)jsonParser.parse(jsonStr);
			 JSONArray predictArray = (JSONArray)jsonObj.get("predictions");
			 JSONObject prdict0 = (JSONObject) predictArray.get(0);
			 
			 JSONArray namesArray = (JSONArray)prdict0.get("detection_names");
			 JSONArray boxArray = (JSONArray)prdict0.get("detection_boxes");
			 
			 for(int i=0; i<namesArray.size(); i++) {
				 String name = (String) namesArray.get(i);
				 
				 JSONArray box = (JSONArray)boxArray.get(i) ;
				 double x1 = (Double) box.get(0);
				 double y1 = (Double) box.get(1);
				 double x2 = (Double) box.get(2);
				 double y2 = (Double) box.get(3);
				 
				 ObjectVO vo = new ObjectVO();
				 vo.setName(name);
				 vo.setX1(x1);
				 vo.setY1(y1);
				 vo.setX2(x2);
				 vo.setY2(y2);
				 
				 objectList.add(vo);
			 }			 
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
		 
		 return objectList;
	}
	
	
}

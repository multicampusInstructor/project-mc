package mc.sn.basic.ai.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import mc.sn.basic.ai.service.OCRService;
import mc.sn.basic.ai.service.ObjectDetectionService;
import mc.sn.basic.ai.service.STTService;

@RestController
public class AIRestController {
	@Autowired
	private OCRService ocrService;
	
//	@Autowired
//	private PoseEstimationService poseService;
	
	@Autowired
	private ObjectDetectionService objService;
	
	@Autowired
	private STTService sttService;
	
//	@Autowired
//	private TTSService ttsService;
	
	@RequestMapping("/clovaOCR")
	public String clovaOCR(@RequestParam("uploadFile") MultipartFile file) {
		String result = "";
		
		try {
			//1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
			  String uploadPath =  "c:/ai/";
			  
			  //2.원본 파일 이름
			  String originalFileName = file.getOriginalFilename();  
			  
			  //3. 파일 생성 
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  
			  //4. 서버로 전송
			  file.transferTo(file1);
			  
			  result = ocrService.clovaOCRService(filePathName);
			  System.out.println(result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	
//	@RequestMapping("/poseDetect")
//	public ArrayList<PoseVO> poseDetect(@RequestParam("uploadFile") MultipartFile file)
//										throws IOException{
//		
//			//1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
//			  String uploadPath =  "c:/ai/";
//			  
//			  //2.원본 파일 이름
//			  String originalFileName = file.getOriginalFilename();  
//			  
//			  //3. 파일 생성 
//			  String filePathName = uploadPath + originalFileName;
//			  File file1 = new File(filePathName);
//			  
//			  //4. 서버로 전송
//			  file.transferTo(file1);			  
//			  
//			  ArrayList<PoseVO> poseList = poseService.poseEstimate(filePathName);
//		
//		return poseList;
//	}
	
	@RequestMapping("/objectDetect")
	public /*ArrayList<ObjectVO>*/String objectDetect(@RequestParam("uploadFile") MultipartFile file)
										throws IOException{
		
			//1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
			  String uploadPath =  "c:/ai/";
			  
			  //2.원본 파일 이름
			  String originalFileName = file.getOriginalFilename();  
			  
			  //3. 파일 생성 
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  
			  //4. 서버로 전송
			  file.transferTo(file1);			  
			  
			  /*ArrayList<ObjectVO>*/ String objectList = objService.objectDetect(filePathName);
		
		return objectList;
	}
	
	@RequestMapping("/clovaSTT")
	public String STT(@RequestParam("uploadFile") MultipartFile file,
								@RequestParam("language") String language) {
		String result = "";
		
		try {
			//1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
			  String uploadPath =  "c:/ai/";
			  
			  //2.원본 파일 이름
			  String originalFileName = file.getOriginalFilename();  
			  
			  //3. 파일 생성 
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  
			  //4. 서버로 전송
			  file.transferTo(file1);
			  
			  result = sttService.clovaSpeechToText(filePathName, language);
			  System.out.println(result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 챗봇 : 음성 메시지를 텍스트로 변환
	@RequestMapping("/clovaSTT2")
	public String STT2(@RequestParam("uploadFile") MultipartFile file) {
		String result = "";
		
		try {
			//1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
			  String uploadPath =  "c:/ai/";
			  
			  //2.원본 파일 이름
			  String originalFileName = file.getOriginalFilename();  
			  
			  //3. 파일 생성 
			  String filePathName = uploadPath + originalFileName;
			  File file1 = new File(filePathName);
			  
			  //4. 서버로 전송
			  file.transferTo(file1);
			  
			  result = sttService.clovaSpeechToText2(filePathName);
			  System.out.println(result);
			  
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		return result;
	}
	
//	@RequestMapping("/chatbotTTS")
//	public String chatbotTTS(@RequestParam("message") String message) {
//		String result = "";
//		
//		result = ttsService.chatbotTextToSpeech(message);	
//
//		return result;
//	}
	
//	@RequestMapping("/chatbotOnlyVoice")
//	public String clovaSTT4(@RequestParam("uploadFile") MultipartFile file){
//		String result = "";
//
//		try {
//			if (file == null) {
//				//웰컴 메시지 받기 위해 질문 내용 빈 값으로 설정
//				result = "";		
//			} else {
//				String uploadPath = "c:/ai/";
//				String originalFilename = file.getOriginalFilename();
//				// 3. 파일 생성
//				String filePathName = uploadPath + originalFilename;
//				File file1 = new File(filePathName);
//				file.transferTo(file1);
//				
//				// 음성 파일을 받아서 텍스트로 변환
//				result = sttService.clovaSpeechToText2(filePathName); // 텍스트 받음						
//			}
//			// 텍스트 질문을 챗봇에게 보내 답변 받음
//			result = ChatbotService.main(result); // 텍스트 받음		
//			// 챗봇으로부터 받은 텍스트 답변을 음성으로 변환 변환
//			result = ttsService.chatbotTextToSpeech(result); // 음성 파일명 받음
//		}  catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		System.out.println(result); // 음성 파일명 반환
//		return result;
//	}
//	
//	
//	@RequestMapping("/clovaTTS")
//	public String TTS(@RequestParam("uploadFile") MultipartFile file,
//								@RequestParam("language") String language) {
//		String result = "";
//		
//		try {
//			//1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
//			  String uploadPath =  "c:/ai/";
//			  
//			  //2.원본 파일 이름
//			  String originalFileName = file.getOriginalFilename();  
//			  
//			  //3. 파일 생성 
//			  String filePathName = uploadPath + originalFileName;
//			  File file1 = new File(filePathName);
//			  
//			  //4. 서버로 전송
//			  file.transferTo(file1);
//			  
//			  result = ttsService.clovaTextToSpeech(filePathName, language);
//			  System.out.println(result);
//			  
//		}catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return result;  //음성 일 이름 반환
//	}
//	
//	@RequestMapping("/chatbotCall")
//	public String chatbot(@RequestParam("message") String message) {
//		String result = ChatbotService.main(message);			
//		return result;  
//	}
//	
//	@RequestMapping("/chatbotCallJSON")
//	public String chatbotCallJSON(@RequestParam("message") String message) {
//		String result = ChatbotService.mainJSON(message);			
//		return result;  //JSON 형식 그대로 반환
//	}
}








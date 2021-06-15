package mc.sn.basic.ai.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import mc.sn.basic.ai.service.CFRService;
import mc.sn.basic.ai.service.OCRService;
import mc.sn.basic.ai.service.ObjectDetectionService;
import mc.sn.basic.ai.service.STTService;
import mc.sn.basic.ai.vo.FaceVO;

@Controller
public class APIController {
//	@Autowired
//	private CFRCelebrityService cfrServiceCel;
	
	@Autowired
	private CFRService cfrService;
	
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
	
	@RequestMapping("/test")
	public String test() {
		return "ai/test";
	}
	
	@RequestMapping("/index.do")
	public String index() {
		return "index";
	}
	
	@RequestMapping("/fileuploadCel")
	public String fileuploadCel() {
		return "ai/celebrityResult";
	}
	
	@RequestMapping("/clovaFaceCel")
	public String cfrCelebrity(@RequestParam("uploadFile") MultipartFile file,
												Model model) throws IOException {
		
		//파일 업로드 기능
		//1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
		  String uploadPath =  "c:/ai/";
		  
		  //2.원본 파일 이름
		  String originalFileName = file.getOriginalFilename();  
		  
		  //3. 파일 생성 
		  String filePathName = uploadPath + originalFileName;
		  File file1 = new File(filePathName);
		  
		  //4. 서버로 전송
		  file.transferTo(file1);
		
//		ArrayList<CelebrityVO> celList = new  ArrayList<CelebrityVO>();		
//		celList = cfrServiceCel.clovaFaceRecogCel(filePathName);
//		//Model에 VO 리스트 저장 -> view 페이지로 전달
//		model.addAttribute("celList", celList);
		
		return "ai/celebrityResult";
	}
	
	@RequestMapping("/fileuploadFace")
	public String fileuploadFace() {
		return "ai/faceRecogResult";
	}
	
	@RequestMapping("/clovaFace")
	public String clovaFaceRecog(@RequestParam("uploadFile") MultipartFile file,
												   Model model) throws IOException  {
		
		// 파일 업로드 기능
		// 1. 파일 저장 경로 설정 : 실제 서비스 되는 위치 (프로젝트 외부에 저장)
		String uploadPath = "c:/ai/";

		// 2.원본 파일 이름
		String originalFileName = file.getOriginalFilename();

		// 3. 파일 생성
		String filePathName = uploadPath + originalFileName;
		File file1 = new File(filePathName);

		// 4. 서버로 전송
		file.transferTo(file1);
		
		ArrayList<FaceVO> faceList = cfrService.clovaFaceRecog(filePathName);	
		
		//Model에 VO 리스트 저장 -> view 페이지로 전달
		model.addAttribute("faceList", faceList);
		
		return "ai/faceRecogResult";
	}
	
	/*
	 * @RequestMapping("/clovaOCR") public void clovaOCR() {
	 * ocrService.clovaOCRService(); }
	 */
	
	@RequestMapping("/ocr")
	public String clovaOCR() {
		return "ai/ocrResult";  // 뷰페이지 이름
	}
	
	@RequestMapping("/pose")
	public String poseDetect() {
		return "ai/poseResult";
	}
	
	@RequestMapping("/object")
	public String objectDetect() {
		return "ai/objectResult";
	}
	
	@RequestMapping("/stt")
	public String STT() {
		return "ai/sttResult";
	}
	
	@RequestMapping("/tts")
	public String TTS() {
		return "ai/ttsResult";
	}
	
	
	@RequestMapping("/chatbot")
	public String chatbot() {
		return "ai/chatForm"; //chatForm.jsp
	}
	
	@RequestMapping("/chatbot2")
	public String chatbot2() {
		return "ai/chatForm2"; //chatForm.jsp
	}
	
	@RequestMapping("/voiceRecord")
	public String voiceRecord() {
		return "ai/voiceRecord"; //voiceRecord.jsp
	}
	
	@RequestMapping("/chatbotVoice")
	public String chatbotVoice() {
		return "ai/chatbotVoice"; //voiceRecord.jsp
	}
	
}






















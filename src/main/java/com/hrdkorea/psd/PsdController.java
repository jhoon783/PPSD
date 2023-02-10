package com.hrdkorea.psd;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PsdController {
	
	@Autowired
	private PsdServiceImpl service; //클래스를 가져와야함

	@GetMapping("/form.do")
	String form() {
		return "form.html";
	}
	
	@PostMapping("/formOK.do")
	String formOK(PsdVO vo, HttpServletRequest request) throws IllegalStateException, IOException {
		String path = request.getSession().getServletContext().getRealPath("/img/");
		System.out.println("path : " + path);
		
		long time = System.currentTimeMillis();
		SimpleDateFormat daytime = new SimpleDateFormat("HHmmss");
		String timeStr = daytime.format(time);

		MultipartFile updateFile = vo.getUpdateFile(); // 넘어온 실제 파일
		String fileName = updateFile.getOriginalFilename(); // 넘어온 실제 파일명
		File f = new File(path + fileName);
		
		if(!updateFile.isEmpty()) {
			if(f.exists()) {
				String onlyFileName  = fileName.substring(0,fileName.lastIndexOf(".")); // good.fi
				String extension  = fileName.substring(fileName.lastIndexOf(".")); // .gif
				fileName = onlyFileName + "_"+timeStr+extension;
				updateFile.transferTo(new File(path + fileName)); //저장
			} else {
				updateFile.transferTo(new File(path + fileName)); //저장
			}
				
		}
		vo.setImg(fileName);
		if (fileName == null || fileName.equals("")) {
			vo.setImg("space.PNG");
		}
		service.formInsert(vo);
		
		return "redirect:formList.do";
	}
	
	@GetMapping("/formList.do")
	String formList(PsdVO vo, Model model) {
		model.addAttribute("li", service.formList(vo));
		return "list.html";
	}
	
	@GetMapping("/content.do")
	String content(PsdVO vo, Model model) {
		model.addAttribute("m", service.content(vo));
		return "content.html";
	}
	
	// 업데이트
	@PostMapping("/formUpdate.do")
	String formUpdate(PsdVO vo, HttpServletRequest request) throws IllegalStateException, IOException {
		
		String path = request.getSession().getServletContext().getRealPath("/img/");
		System.out.println("path : " + path);
		
		long time = System.currentTimeMillis();
		SimpleDateFormat daytime = new SimpleDateFormat("HHmmss");
		String timeStr = daytime.format(time);

		MultipartFile updateFile = vo.getUpdateFile(); // 넘어온 실제 파일
		String fileName = updateFile.getOriginalFilename(); // 넘어온 실제 파일명
	
		if(!updateFile.isEmpty()) {
			
			// 레코드에 파일이 있으면 파일 찾아서 삭제하기
			// 넘어온 이미지 파일과 같은게 있으면 삭제 후 추가
			PsdVO oldImg = service.content(vo);
			String delOldFileName = oldImg.getImg();
			File delf = new File(path + delOldFileName);
			
			if (oldImg.getImg() != null ) {
		        if (!oldImg.getImg().equals("space.png")) {
		        		if(delf.exists()) {
		        		   delf.delete();
		   			 }
	        	}  
			
			File newf = new File(path + fileName);
			if(newf.exists()) { 
				// 새롭게 추가한 파일명이 기존 파일명과 중복이 되면..
				String onlyFileName  = fileName.substring(0,fileName.lastIndexOf(".")); // good.fi
				String extension  = fileName.substring(fileName.lastIndexOf(".")); // .gif
				fileName = onlyFileName + "_"+timeStr+extension;
				updateFile.transferTo(new File(path + fileName)); //저장
			} else {
				// 기존 파일과 중복이 되지 않는다면..
				updateFile.transferTo(new File(path + fileName)); //저장
			}
				
		}
		vo.setImg(fileName);
		service.formUpdate1(vo);
			
		} else {
			service.formUpdate2(vo);
		}
		
		return "redirect:formList.do";
	}
	
	/*if(첨부파일이 있으면) {
		수정1(이미지첨부)
		기본의 레코드를 확인해서 이미지가 있으면 
		이미지 삭제 후
		파일 첨부
		} else {
		수정2(이미지 첨부 X)
		파일에 대한것은 신경쓰지 않아도 된다.
		}*/
	
	@GetMapping("/formDelete.do")
	String formDelete(PsdVO vo, HttpServletRequest request) {
		
		String path = request.getSession().getServletContext().getRealPath("/img/");
		
		vo = service.content(vo);
		String delFileName=vo.getImg();
		File f = new File(path + delFileName);
		
		if(delFileName != null || !delFileName.equals("space.png")) {
			if(f.exists()) {
				f.delete();
			}
		}
		
		service.formDelete(vo);
		return "redirect:formList.do";
	}
}

package com.hrdkorea.psd;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PsdVO {

	 private int idx;
	 private String sname;
	 private MultipartFile updateFile; //실제파일
	 private String img;
	 private Date today = new Date(); // 날짜를 받아서 초기화
}

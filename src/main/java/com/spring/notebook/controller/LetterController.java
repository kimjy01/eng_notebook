package com.spring.notebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.notebook.entity.Letters;
//import com.spring.notebook.entity.Letters;
import com.spring.notebook.service.LetterService;

@Controller
public class LetterController {
	
	@Autowired
//	private LetterService letterService;
	
	// 작성 전 모든 스티커 조회 페이지
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// 해당 편지지 화면으로 이동
//	@RequestMapping("/letter/{stickerNumber}")
//	public String stickerPage(@PathVariable int stickerNumber, Model model) {
//		Letters letter = letterService.getStickerInfoByLetterId(stickerNumber);
//	}
	
	// 편지 작성 메서드
//	@PostMapping("/letter/write")
//	public String writeLetter(Letters letter) {
//		letterService.write(letter);
//		return "letterWrite";
//	}
}

package com.spring.notebook.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.notebook.entity.Letters;
import com.spring.notebook.service.LetterService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class LetterController {
	
	@Autowired
	private LetterService letterService;

	// 작성 전 모든 스티커 조회 페이지
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	// 해당 편지지 화면으로 작성하러 감
	@GetMapping("/letter/write/{stickerDesign}")
	public String stickerPage(@PathVariable String stickerDesign, Model model) {
		model.addAttribute("stickerDesign", stickerDesign);
		log.info(stickerDesign);
		return "letterWrite";
	}
	
	
	 // 편지 작성 후 목록으로 이동함
	@PostMapping("/letter/list")
	public String writeLetter(HttpServletRequest request, RedirectAttributes attr, Letters letters, Model model) {
		
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		String stickerDesign = request.getParameter("stickerDesign");
		
		// openDate 기본값 -> 2023-12-25로 default
		LocalDate defaultOpenDate = LocalDate.of(2023, 12, 25);
		
		Letters letter = Letters.builder()
                .content(content)
                .nickname(nickname)
                .stickerDesign(stickerDesign)
                .openDate(defaultOpenDate)
                .build();
		
		letterService.save(letter);
		
		attr.addAttribute("letter", letter);
		
		return "redirect:/letter/content";
	}
	
	// 편지 내용 조회 메서드
	@GetMapping("/letter/content")
	public String viewLetterContent(@RequestParam("letter") Long letter , Model model) {
		
		Letters letters = letterService.getStickerInfoByLetterId(letter);
		
		System.out.println(letters.getContent());
		
		return "letterList";
	}
}

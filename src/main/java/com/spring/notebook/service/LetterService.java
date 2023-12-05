package com.spring.notebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.notebook.entity.Letters;
import com.spring.notebook.repository.LetterRepository;

@Service
public class LetterService {
	
	private LetterRepository letterRepository;
	
	@Autowired
	public LetterService(LetterRepository letterRepository) {
		this.letterRepository = letterRepository;
	}
	
	public Letters getStickerInfoByLetterId(Long letterId) {
		// LetterId로 Letter를 조회해 반환
		return letterRepository.findByletterId(letterId)
				.orElseThrow(() -> new RuntimeException("해당하는 편지가 없어요 (id : " + letterId + ")"));
	}
	
	// 편지 저장
	public void save(Letters letter) {
		letterRepository.save(letter);
	}
	
	// 편지 작성
	public void write(Letters letter) {
		letterRepository.save(letter);
	}
	
	// 편지 리스트
	public List<Letters> letterList() {
		return letterRepository.findAll();
	}

}

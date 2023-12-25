package com.spring.notebook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteDesign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "noteId", updatable = false)
	private Long noteId;
	
	// 노트북 브랜드 이름
	@Column(name = "noteBrand")
	private String noteBrand;
	
	// 노트북 색상
	@Column(name = "noteColor")
	private String noteColor;
	
	// 노트북 불러오는 사진 경로
	@Column(name = "notePath")
	private String notePath;
}

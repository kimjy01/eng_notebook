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
public class LetterDesign {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false)
	private Long id;
	
	// 스티커 이름
	@Column(name = "letterBrand")
	private String letterBrand;
	
	// 편지지 색상
	@Column(name = "letterColor")
	private String letterColor;
	
	// 스티커 불러오는 사진 경로
	@Column(name = "letterPath")
	private String letterPath;
}

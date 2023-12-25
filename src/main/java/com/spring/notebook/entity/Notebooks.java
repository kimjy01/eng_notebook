package com.spring.notebook.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notebooks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "noteId", updatable = false)
	private Long noteId;
	
	@Column(name = "messageCnt")
	private Long messageCnt;
	
	// noteDesign 참조 (단방향이므로 noteDesign에서는 참조 불가)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "noteDesignId")
	private NoteDesign noteDesign;
	
	// users와 일대일 매핑 - 양방향, FK가 있는 테이블이 주인.
	@OneToOne
	@JoinColumn(name = "userId")
	private Users users;
	
	// letter와 일대다 매핑 - 양방향
	@OneToMany(mappedBy = "notebooks")
    private List<Letters> letters = new ArrayList<Letters>();

}

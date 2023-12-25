package com.spring.notebook.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Letters {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "letterId", updatable = false)
	private Long letterId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "openDate")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate openDate;
	
	@Column(name = "sendDate")
	@CreationTimestamp
	private LocalDateTime sendDate;
	
	@Column(name = "status")
	private Long status;
	
	@Column(name = "nickname")
	private String nickname;
	
	@Column(name = "stickerDesign")
	private String stickerDesign;
	
	// letterDesign 참조 (단방향이므로 letterDesign에서는 참조 불가)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "letterDesignId")
	private LetterDesign letterDesign;

	// user와 일대다 관계 (양방향)
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users users;
    
    // notebook과 일대다 관계 (양방향)
 	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "noteId")
	private Notebooks notebooks;
 	
 	// 연관 관계 메서드
 	public void setUsers(Users users) {
        this.users = users;
    }
	
	public void setNotebooks(Notebooks notebooks) {
	    this.notebooks = notebooks;
	}

	
	// 황이 추가 코드
	public void setLetterId(long letterId2) {
		// TODO Auto-generated method stub
		
	}
	
}

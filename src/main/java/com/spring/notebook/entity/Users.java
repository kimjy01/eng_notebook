package com.spring.notebook.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId", updatable = false)
	private Long userId;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "nickname", nullable = false)
	private String nickname;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "userUrl", unique = true)
	private String userUrl;
	
	@Enumerated(EnumType.STRING)
    private Roles roles;
	
	// 노트북과 1대1관계 (양방향)
	@OneToOne(mappedBy = "users")
	private Notebooks notebooks;
	
	// letter와 일대다 (양방향)
	@OneToMany(mappedBy = "users")
    private List<Letters> letters = new ArrayList<Letters>();
	
}

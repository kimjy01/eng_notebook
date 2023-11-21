package com.spring.notebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.notebook.entity.Letters;

public interface LetterRepository extends JpaRepository<Letters, Long> {

}

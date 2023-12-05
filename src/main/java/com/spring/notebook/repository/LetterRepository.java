package com.spring.notebook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.notebook.entity.Letters;

@Repository
public interface LetterRepository extends JpaRepository<Letters, Long> {
	Optional<Letters> findByletterId(Long letterId);
}

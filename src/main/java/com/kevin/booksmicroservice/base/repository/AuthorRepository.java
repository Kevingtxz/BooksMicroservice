package com.kevin.booksmicroservice.base.repository;

import com.kevin.booksmicroservice.base.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {}

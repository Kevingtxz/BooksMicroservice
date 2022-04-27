package com.kevin.booksmicroservice.base.repository;

import com.kevin.booksmicroservice.base.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional(readOnly=true)
    @Query("SELECT obj FROM Book obj WHERE obj.author.id = :authorId")
    List<Book> findBooksByAuthor(@Param("authorId") Long author_id);
}

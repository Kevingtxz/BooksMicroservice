package com.kevin.booksmicroservice.config.service;

import com.kevin.booksmicroservice.base.domain.Author;
import com.kevin.booksmicroservice.base.domain.Book;
import com.kevin.booksmicroservice.base.dto.AuthorNewDTO;
import com.kevin.booksmicroservice.base.dto.BookDTO;
import com.kevin.booksmicroservice.base.repository.AuthorRepository;
import com.kevin.booksmicroservice.base.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public void instantiateTestDatabase() {

        List<Author> authors = new ArrayList<>(){{
            add(new Author(new AuthorNewDTO("João Gomez")));
            add(new Author(new AuthorNewDTO("Fernando Dize")));
            add(new Author(new AuthorNewDTO("Santiago")));
        }};

        authorRepository.saveAll(authors);

        List<Book> books = new ArrayList<>() {{
            add(new Book(new BookDTO("Vida Longa", "Vida de tartaruga se o humano não matar", "Não mate tartaruga", "21/12/1995", 150, authors.get(0).getId()), authors.get(0)));
            add(new Book(new BookDTO("Sai Dessa", "Nunca", "...", "01/11/2000", 423, authors.get(0).getId()), authors.get(0)));
            add(new Book(new BookDTO("Lei do Amanhã", "Legislação futurista", "Nada a declarar", "21/09/2010", 235, authors.get(1).getId()), authors.get(1)));
            add(new Book(new BookDTO("Ratata", "Melhor Pokemon", "Ratão de esgoto", "10/10/2020", 165, authors.get(2).getId()), authors.get(2)));
        }};

        bookRepository.saveAll(books);
    }
}

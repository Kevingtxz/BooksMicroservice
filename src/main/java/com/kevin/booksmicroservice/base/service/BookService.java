package com.kevin.booksmicroservice.base.service;

import com.kevin.booksmicroservice.base.domain.Author;
import com.kevin.booksmicroservice.base.domain.Book;
import com.kevin.booksmicroservice.base.dto.BookDTO;
import com.kevin.booksmicroservice.base.repository.AuthorRepository;
import com.kevin.booksmicroservice.base.repository.BookRepository;
import com.kevin.booksmicroservice.base.service.exceptions.DataIntegrityException;
import com.kevin.booksmicroservice.base.service.exceptions.ObjectNotFoundException;
import com.kevin.booksmicroservice.base.utils.ConvertToDateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repo;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;


    public BookDTO findDto(Long id) {
        return new BookDTO(this.find(id));
    }

    public List<Book> findAll() {
        return repo.findAll();
    }

    public Book find(Long id) {
        Optional<Book> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Object not find! Id: " + id + ", Type: "
                        + Book.class.getName()));
    }

    public Page<Book> findPage(
            Integer page,
            Integer linesPerPage,
            String orderBy,
            String direction){
        PageRequest pageRequest =
                PageRequest.of(page, linesPerPage,
                        Sort.Direction.valueOf(direction), orderBy);
        return repo.findAll(pageRequest);
    }

    public Book insert(Book obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Book update(Long id, BookDTO objDto) {
        Book obj = find(id);
        updateData(obj, objDto);
        return repo.save(obj);
    }

    public void updateTitle(String title, Long id) {
        Book book = repo.getById(id);
        book.setTitle(title);
        repo.save(book);
    }

    public void delete(Long id) {
        find(id);
        repo.deleteById(id);
    }

    public Book fromDTO(BookDTO objDto) {
        Author author = authorRepository
                .findById(objDto.getAuthorId())
                .orElseThrow();
        return new Book(objDto, author);
    }

    public void updateData(Book obj, BookDTO objDto) {
        obj.setTitle(objDto.getTitle());
        obj.setDescription(objDto.getDescription());
        obj.setPagesNumber(objDto.getPagesNumber());
        obj.setPublicationDate(
                ConvertToDateHelper
                        .fromStringToLocalDate(
                                objDto.getPublicationDate()));
    }
}

package com.kevin.booksmicroservice.base.service;

import com.kevin.booksmicroservice.base.domain.Author;
import com.kevin.booksmicroservice.base.domain.Book;
import com.kevin.booksmicroservice.base.dto.AuthorDTO;
import com.kevin.booksmicroservice.base.dto.AuthorNewDTO;
import com.kevin.booksmicroservice.base.repository.AuthorRepository;
import com.kevin.booksmicroservice.base.repository.BookRepository;
import com.kevin.booksmicroservice.base.service.exceptions.DataIntegrityException;
import com.kevin.booksmicroservice.base.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repo;
    @Autowired
    private BookRepository bookRepository;


    public List<Book> findBooksByAuthorId(Long id) {
        return bookRepository.findBooksByAuthor(id);
    }

    public List<Author> findAll() {
        return repo.findAll();
    }

    public List<AuthorDTO> findAllDto() {
        List<AuthorDTO> listDto = findAll()
                .stream()
                .map(obj -> new AuthorDTO(obj))
                .collect(Collectors.toList());
        return listDto;
    }

    public Author find(Long id) {
        Author obj = repo.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                "Object not find! Id: " + id + ", Type: " + Author.class.getName()));
        return obj;
    }

    public Author insert(Author obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public void delete(Long id) {
        find(id);
        repo.deleteById(id);
    }

    public Author fromNewDto(AuthorNewDTO objNewDto) {
        return new Author(objNewDto);
    }
}

package com.kevin.booksmicroservice.base.resource;

import com.kevin.booksmicroservice.base.domain.Author;
import com.kevin.booksmicroservice.base.domain.Book;
import com.kevin.booksmicroservice.base.dto.AuthorDTO;
import com.kevin.booksmicroservice.base.dto.AuthorNewDTO;
import com.kevin.booksmicroservice.base.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/authors")
public class AuthorResource {

    @Autowired
    private AuthorService service;


    @RequestMapping(value="/{id}/books", method= RequestMethod.GET)
    public ResponseEntity<List<Book>> findBooksByAuthor(@PathVariable Long id) {
        List<Book> list = service.findBooksByAuthorId(id);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<AuthorDTO> find(@PathVariable Long id) {
        Author obj = service.find(id);
        AuthorDTO objDto = new AuthorDTO(obj);
        return ResponseEntity.ok().body(objDto);
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<AuthorDTO>> findAll() {
        List<AuthorDTO> listDto = service.findAllDto();
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody AuthorNewDTO objNewDto){
        Author obj = service.fromNewDto(objNewDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

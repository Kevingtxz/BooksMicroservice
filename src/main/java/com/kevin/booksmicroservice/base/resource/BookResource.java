package com.kevin.booksmicroservice.base.resource;

import com.kevin.booksmicroservice.base.domain.Book;
import com.kevin.booksmicroservice.base.dto.BookDTO;
import com.kevin.booksmicroservice.base.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/books")
public class BookResource {

    @Autowired
    private BookService service;


    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<BookDTO> find(@PathVariable Long id) {
        BookDTO objDto = service.findDto(id);
        return ResponseEntity.ok().body(objDto);
    }

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<BookDTO>> findAll() {
        List<Book> list = service.findAll();
        List<BookDTO> listDto = list.stream()
                .map(obj -> new BookDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/page", method=RequestMethod.GET)
    public ResponseEntity<Page<BookDTO>> findPage(
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="name") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        Page<Book> list = service.findPage(page, linesPerPage, orderBy, direction);
        Page<BookDTO> listDto = list.map(obj -> new BookDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody BookDTO objDto){
        Book obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody BookDTO objDto, @PathVariable Long id){
        service.update(id, objDto);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @RequestMapping(value="/{id}/title", method=RequestMethod.PATCH)
    public ResponseEntity<Void> update(@Valid @RequestBody String title, @PathVariable Long id){
        service.updateTitle(title, id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

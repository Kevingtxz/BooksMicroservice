package com.kevin.booksmicroservice.base.dto;

import com.kevin.booksmicroservice.base.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String title;
    private String description;
    private String etc;
    private String publicationDate;
    private int pagesNumber;
    private Long authorId;

    public BookDTO(Book book) {
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.etc = book.getEtc();
        this.publicationDate = book.getPublicationDate().toString();
        this.pagesNumber = book.getPagesNumber();
        this.authorId = book.getAuthor().getId();
    }
}

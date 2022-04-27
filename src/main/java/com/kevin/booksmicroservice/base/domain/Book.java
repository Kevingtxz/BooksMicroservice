package com.kevin.booksmicroservice.base.domain;

import com.kevin.booksmicroservice.base.dto.BookDTO;
import com.kevin.booksmicroservice.base.utils.ConvertToDateHelper;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@NoArgsConstructor
@Entity
public class Book implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String etc;
    private int pagesNumber;
    private LocalDate publicationDate;
    private final LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("GMT-03:00"));

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    public Book(BookDTO objDto, Author author) {
        this.title = objDto.getTitle();
        this.description = objDto.getDescription();
        this.etc = objDto.getEtc();
        this.pagesNumber = objDto.getPagesNumber();
        this.publicationDate = ConvertToDateHelper
                .fromStringToLocalDate(objDto.getPublicationDate());
        this.author = author;
    }

    // TODO add equals + hash code since you are using a set to store books
}



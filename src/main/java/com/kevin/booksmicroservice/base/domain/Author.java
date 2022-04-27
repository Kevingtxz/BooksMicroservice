package com.kevin.booksmicroservice.base.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kevin.booksmicroservice.base.dto.AuthorNewDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String name;
    private final LocalDateTime createdAt = LocalDateTime.now(ZoneId.of("GMT-03:00"));

    @JsonIgnore
    @OneToMany(mappedBy="author", cascade=CascadeType.ALL)
    private final Set<Book> books = new HashSet<>();


    public Author(AuthorNewDTO objNewDto) {
        this.name = objNewDto.getName();
    }
}

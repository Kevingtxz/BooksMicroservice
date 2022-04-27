package com.kevin.booksmicroservice.base.dto;

import com.kevin.booksmicroservice.base.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private String name;

    public AuthorDTO(Author author) {
        this.name = author.getName();
    }
}

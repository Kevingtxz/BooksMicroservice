package com.kevin.booksmicroservice.base.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorNewDTO {
    @NotNull
    @Length(min = 10, max = 300)
    private String name;
}

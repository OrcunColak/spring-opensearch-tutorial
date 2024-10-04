package com.colak.springtutorial.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "books")
@Getter
@Setter
public class Book {

    @Id
    private String id;

    private String title;

    private int publicationYear;

    private String authorName;

    private String isbn;
}

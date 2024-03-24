package com.java.aws.apirestsqs.model;

public class Book {

    public Integer Id;

    public String Name;

    public String Author;

    public Book() {
    }

    public Book(Integer id, String name, String author) {
        Id = id;
        Name = name;
        Author = author;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}

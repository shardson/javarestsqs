package com.java.aws.apirestsqs.controller;

import com.java.aws.apirestsqs.model.Book;
import com.java.aws.apirestsqs.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;
    @RequestMapping(method = RequestMethod.GET, value = "/books")
    public String getBooks(){
        return "Hellow Books";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addbook")
    public void addBook(@RequestBody Book book){
        bookService.SendMessage(book);
    }


}

package ru.controllers;

import ru.dao.BooksDAO;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.models.Book;


@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    BooksDAO bookDAO;

    @GetMapping("/getbooks")
    public @ResponseBody String getBooks(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(bookDAO.getBooks());
        }catch (Exception e){
            return "lol";
        }
    }

    @GetMapping("/getbooksbytype")
    public @ResponseBody String getBooksByType(@RequestParam("type") String type){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(bookDAO.getBooksbyType(type));
        }catch (Exception e){
            return "lol";
        }
    }

    @GetMapping("/all")
    public String showBooks(){

        return "books/books.html";
    }

    @PostMapping("/add")
    @ResponseStatus (value = HttpStatus.OK)
    public void addBook(  @RequestParam("authorName")   String authorName,
                          @RequestParam("bookName")     String bookName,
                          @RequestParam("type")         String type
    ){
        Book book = new Book(0, authorName, bookName, type);
        bookDAO.addBook(book);
        //return "redirect:all";
    }

    @PostMapping("/delete")
    @ResponseStatus (value = HttpStatus.OK)
    public void deleteBook(@RequestParam("idsToDelete") String idsToDelete){

        String[] ids = idsToDelete.split(" ");

        for (String id : ids) {
            bookDAO.deleteBook(id);
        }

    }



    public BooksController(){
        System.out.println("lol");
    }




}
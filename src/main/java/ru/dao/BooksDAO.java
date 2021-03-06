package ru.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.models.Book;

import java.util.List;

@Component
public class BooksDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BooksDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    public static final String USER = "postgres";
    public static final String PASSWORD ="postgres";




    public List <Book> getBooks(){
        return jdbcTemplate.query("SELECT * FROM test", new BeanPropertyRowMapper<Book>(Book.class));
    }

    public void deleteBook(String id){
        jdbcTemplate.update("DELETE FROM test WHERE id = ?", Integer.parseInt(id));
    }

    public void addBook(Book book){
        jdbcTemplate.update("INSERT INTO test(authorname, bookname, type) VALUES (?, ?, ?)", book.getAuthorName(), book.getBookName(), book.getType());
    }

    public List<Book> getBooksbyType(String type){
        return jdbcTemplate.query("SELECT * from test WHERE type = ?", new Object[]{type}, new BeanPropertyRowMapper<Book>(Book.class));
    }


}
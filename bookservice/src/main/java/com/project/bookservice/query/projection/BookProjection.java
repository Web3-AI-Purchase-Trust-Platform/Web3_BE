package com.project.bookservice.query.projection;

import com.project.bookservice.command.data.Book;
import com.project.bookservice.command.data.BookRepository;
import com.project.bookservice.query.model.BookResponseModel;
import com.project.bookservice.query.queries.GetAllBooksQuery;
import com.project.bookservice.query.queries.GetBookQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookProjection {
    @Autowired
    private BookRepository bookRepository;

    @QueryHandler
    public BookResponseModel handle(GetBookQuery getBookQuery) {
        BookResponseModel model = new BookResponseModel();
        Book book = bookRepository.getById(getBookQuery.getBookId());
        BeanUtils.copyProperties(book, model);
        return model;
    }

    @QueryHandler
    public List<BookResponseModel> handle(GetAllBooksQuery getAllBooksQuery) {
        List<Book> listEntity = bookRepository.findAll();
        List<BookResponseModel> listBook = new ArrayList<>();
        listEntity.forEach(s -> {
            BookResponseModel model = new BookResponseModel();
            BeanUtils.copyProperties(s, model);
            listBook.add(model);
        });

        return listBook;
    }
}

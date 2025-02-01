package com.project.bookservice.query.controller;

import com.project.bookservice.query.model.BookResponseModel;
import com.project.bookservice.query.queries.GetAllBooksQuery;
import com.project.bookservice.query.queries.GetBookQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookQueryController {
    @Autowired
    private QueryGateway queryGateway;

    @GetMapping
    public BookResponseModel getBookDetail(@RequestParam String id) {
        GetBookQuery getBookQuery = new GetBookQuery();
        getBookQuery.setBookId(id);

        BookResponseModel bookResponseModel =
                queryGateway.query(getBookQuery,
                        ResponseTypes.instanceOf(BookResponseModel.class))
                        .join();

        return bookResponseModel;
    }

    @GetMapping("/all")
    public List<BookResponseModel> getAllBooks() {
        GetAllBooksQuery getAllBooksQuery = new GetAllBooksQuery();
        List<BookResponseModel> list =
                queryGateway.query(getAllBooksQuery,
                        ResponseTypes.multipleInstancesOf(BookResponseModel.class))
                        .join();
        return list;
    }
}

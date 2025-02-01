package com.project.bookservice.command.event;

import com.project.bookservice.command.data.Book;
import com.project.bookservice.command.data.BookRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookEventsHandler {
    @Autowired
    private BookRepository bookRepository;

    @EventHandler
    public void on(BookCreatedEvent event) {
        Book book = new Book();
        BeanUtils.copyProperties(event, book);
        bookRepository.save(book);
    }

    @EventHandler
    public void on(BookUpdatedEvent event) {
        Optional<Book> book = bookRepository.findById(event.getBookId());
        if (book.isPresent()) {
            Book updatedBook = book.get();
            BeanUtils.copyProperties(event, updatedBook);
            bookRepository.save(updatedBook);
        }

    }

    @EventHandler
    public void on(BookDeletedEvent event) {
        Book book = bookRepository.getById(event.getBookId());
        bookRepository.delete(book);
    }

}

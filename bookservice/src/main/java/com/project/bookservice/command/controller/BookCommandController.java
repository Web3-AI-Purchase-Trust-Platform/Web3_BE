package com.project.bookservice.command.controller;

import com.project.bookservice.command.command.CreateBookCommand;
import com.project.bookservice.command.command.DeleteBookCommand;
import com.project.bookservice.command.command.UpdateBookCommand;
import com.project.bookservice.command.model.BookRequestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
public class BookCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String createBook(@RequestBody BookRequestModel model) {
        CreateBookCommand command =
                new CreateBookCommand(UUID.randomUUID().toString(),model.getName(),model.getAuthor(), true);
        commandGateway.sendAndWait(command);
        return "Book created";
    }

    @PutMapping
    public String updateBook(@RequestBody BookRequestModel model) {
        UpdateBookCommand command =
                new UpdateBookCommand(model.getBookId(), model.getName(),model.getAuthor(), model.getIsReady());
        commandGateway.sendAndWait(command);
        return "Book updated";
    }

    @DeleteMapping
    public String deleteBook(@RequestParam String id) {
        DeleteBookCommand command =
                new DeleteBookCommand(id);
        commandGateway.sendAndWait(command);
        return "Book deleted";
    }

}

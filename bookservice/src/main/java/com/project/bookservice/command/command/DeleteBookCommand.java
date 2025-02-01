package com.project.bookservice.command.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@NoArgsConstructor
public class DeleteBookCommand {
    @TargetAggregateIdentifier
    private String bookId;

    public DeleteBookCommand(String bookId) {
        super();
        this.bookId = bookId;
    }
}

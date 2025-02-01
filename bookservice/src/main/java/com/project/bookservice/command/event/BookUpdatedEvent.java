package com.project.bookservice.command.event;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdatedEvent {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}

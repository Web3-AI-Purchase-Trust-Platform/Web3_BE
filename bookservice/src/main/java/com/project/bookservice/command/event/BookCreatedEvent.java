package com.project.bookservice.command.event;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BookCreatedEvent {
    private String bookId;
    private String name;
    private String author;
    private Boolean isReady;
}

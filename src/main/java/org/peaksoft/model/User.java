package org.peaksoft.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)

public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;
    private Long cardId;

    public User(String name, String lastName, Byte age, Long cardId) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.cardId = cardId;
    }
}
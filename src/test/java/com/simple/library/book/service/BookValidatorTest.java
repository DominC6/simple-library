package com.simple.library.book.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class BookValidatorTest {

    @Test
    void shouldValidateAuthorForOneElement() {
        //given
        String author = "Adam";

        //when + then
        assertDoesNotThrow(() -> BookValidator.validateAuthor(author));
    }

    @Test
    void shouldValidateAuthorWhenOnlyForenameStartsWithA() {
        //given
        String author = "Adam Kowalski";

        //when + then
        assertDoesNotThrow(() -> BookValidator.validateAuthor(author));

    }

    @Test
    void shouldValidateAuthorWhenOnlySurnameStartsWithA() {
        //given
        String author = "Jan Adamczyk";

        //when + then
        assertDoesNotThrow(() -> BookValidator.validateAuthor(author));
    }

    @Test
    void shouldValidateAuthorWhenForenameSurnameStartsWithA() {
        //given
        String author = "Adam Adamczyk";

        //when + then
        assertDoesNotThrow(() -> BookValidator.validateAuthor(author));
    }

    @Test
    void shouldThrowWhenAuthorDoesNotStartWithA() {
        //given
        String author = "Jan";

        //when
        ResponseStatusException result = assertThrows(ResponseStatusException.class, () -> BookValidator.validateAuthor(author));

        //then
        assertEquals(400, result.getRawStatusCode());
    }

}

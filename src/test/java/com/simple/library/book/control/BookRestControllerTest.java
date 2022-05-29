package com.simple.library.book.control;

import com.simple.library.book.entity.BookDTO;
import com.simple.library.book.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.simple.library.book.entity.BookTestDataProducer.createDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookRestControllerTest {

    @InjectMocks
    private BookRestController bookRestController;

    @Mock
    private BookService bookService;

    @Test
    void shouldGetAllBooksWithStatusOk() {
        //given
        List<BookDTO> books = List.of(createDto(1L), createDto(2L), createDto(3L));

        //and mocked bookService
        when(bookService.getAllBooks()).thenReturn(books);

        //when
        ResponseEntity<List<BookDTO>> result = bookRestController.getAllBooks();

        //then
        assertNotNull(result);
        assertEquals(books, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void shouldAddBookWithStatusCreated() {
        //given
        BookDTO book = createDto(1L);

        //and mocked bookService
        when(bookService.addNewBook(eq(book))).thenReturn(book);

        //when
        ResponseEntity<BookDTO> result = bookRestController.addBook(book);

        //then
        assertNotNull(result);
        assertEquals(book, result.getBody());
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
    }
}

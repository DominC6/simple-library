package com.simple.library.book.service;

import com.simple.library.book.entity.BookDTO;
import com.simple.library.book.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    BookRepository bookRepository;

    @Test
    void shouldGetAllEntries() {
        // given
        List<BookEntity> entriesList = List.of(createEntry(1L), createEntry(2L), createEntry(3L));

        // and mocked bookRepository
        when(bookRepository.findAll()).thenReturn(entriesList);

        //when
        List<BookDTO> result = bookService.getAllBooks();

        //then
        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(3, result.stream().map(BookDTO::getId).distinct().count());
    }

    @Test
    void shouldReturnEmptyListWhenNoEntryInDatabase() {
        //given mocked bookRepository
        when(bookRepository.findAll()).thenReturn(Collections.emptyList());

        //when
        List<BookDTO> result = bookService.getAllBooks();

        //then
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void shouldPersistBook() {
        //given
        BookDTO bookDTO = createDto();

        //and mocked bookRepository
        when(bookRepository.save(any(BookEntity.class))).thenReturn(new BookEntity(bookDTO));

        //when
        BookDTO result = bookService.addNewBook(bookDTO);

        //then
        assertNotNull(result);
        assertEquals(bookDTO, result);
    }

    @Test
    void shouldThrowWhenBookDtoIsNull() {
        //given
        BookDTO bookDTO = null;

        //when + then
        assertThrows(IllegalArgumentException.class, () -> bookService.addNewBook(bookDTO));
    }

    private BookDTO createDto() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setIsbn("ISBN");
        bookDTO.setAuthor("Author");
        bookDTO.setTitle("Title");
        return bookDTO;
    }

    private BookEntity createEntry(Long id) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(id);
        bookEntity.setAuthor("Author");
        bookEntity.setTitle("Title");
        bookEntity.setIsbn("Isbn");
        return bookEntity;
    }
}


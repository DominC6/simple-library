package com.simple.library.book.control;

import com.simple.library.book.entity.BookDTO;
import com.simple.library.book.service.BookService;
import com.simple.library.book.service.BookValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest controller containing methods:
 * GET for receiving all books
 * POST for creating new book
 */
@Api(value = "BookRestController")
@SwaggerDefinition(info = @Info(version = "1.0.0", title = "Library API", description = "Handles books"))
@RestController
@RequestMapping("/book-api/v1")
public class BookRestController {

    private static final String RECEIVED_GET_REQUEST = "Received new request for BookRestController.getAllBooks";
    private static final String RECEIVED_POST_REQUEST = "Received new request for BookRestController.createNewBook";

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRestController.class);

    private final BookService bookService;

    BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Get method for listing all of books available in the database.
     *
     * @return List of {@link BookDTO}
     */
    @GetMapping("/books")
    @ApiOperation(value = "Gets list of books")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "List of books"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        LOGGER.info(RECEIVED_GET_REQUEST);
        List<BookDTO> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Post method for creating new book record in the database.
     *
     * @param bookDTO book data
     * @return {@link BookDTO} created book entry
     */
    @PostMapping("/books")
    @ApiOperation(value = "Creates new book and puts it into database")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Successfully created book"),
            @ApiResponse(code = 400, message = "Wrong book data"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<BookDTO> addBook(@ApiParam(value = "Data for book insertion")
                                                 @RequestBody BookDTO bookDTO) {
        LOGGER.info(RECEIVED_POST_REQUEST);
        if (bookDTO.getAuthor() != null) {
          BookValidator.validateAuthor(bookDTO.getAuthor());
        }
        BookDTO createdEntry = bookService.addNewBook(bookDTO);
        return new ResponseEntity<>(createdEntry, HttpStatus.CREATED);
    }
}

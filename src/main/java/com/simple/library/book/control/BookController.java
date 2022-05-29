package com.simple.library.book.control;

import com.simple.library.book.entity.BookDTO;
import com.simple.library.book.service.BookService;
import com.simple.library.book.service.BookValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Controller class for book
 */
@Controller
public class BookController {

    private final BookService bookService;

    BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Method for returning books list.
     * If no record, page with no records shown,
     * else page with table shown
     *
     * @param model the model
     * @return the template with records
     */
    @GetMapping("/")
    public String getBooks(Model model) {
        List<BookDTO> listOfBooks = bookService.getAllBooks();
        model.addAttribute("bookRecords", bookService.getAllBooks());
        return listOfBooks.isEmpty() ? "no-records" : "list";
    }

    /**
     * Shows form for adding new record.
     *
     * @param model the model
     * @return template form for adding new record
     */
    @GetMapping("/addBook")
    public String showAddForm(Model model) {
        model.addAttribute("book", new BookDTO());
        return "add-record";
    }

    /**
     * Adds new book using form.
     * Validates if authors forename or surname starts with A
     *
     * @param bookDTO the book data from the form
     * @param bindingResult the binding results
     * @return redirects to book list if no error, otherwise stays and shows error
     */
    @PostMapping(value = "/addBook")
    public String addBook(@ModelAttribute("book") BookDTO bookDTO, BindingResult bindingResult) {
        try {
            BookValidator.validateAuthor(bookDTO.getAuthor());
        } catch (ResponseStatusException rse) {
            bindingResult.rejectValue("author", "author.input.error");
            return "add-record";
        }
        bookService.addNewBook(bookDTO);
        return "redirect:/";
    }
}

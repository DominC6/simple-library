package com.simple.library.book.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Class contains validations for Books.
 */
@Service
public class BookValidator {

    private static final String ERROR_MESSAGE = "Validation of %s went wrong. Name or surname must start with letter A";

    private static final Logger LOGGER = LoggerFactory.getLogger(BookValidator.class);

    /**
     * Validates author.
     * Checks if forename or surname starts with letter A
     *
     * @param author the author
     */
    public static void validateAuthor(String author) {
        boolean doesForeNameOrSurnameStartWithA = splitForenameAndSurname(author).stream()
                .anyMatch(name -> name.matches("A.*"));

        if (!doesForeNameOrSurnameStartWithA) {
            LOGGER.error(String.format(ERROR_MESSAGE, author));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format(ERROR_MESSAGE, author));
        }
    }

    /**
     * Splits author with space delimiter
     *
     * @param author the author
     * @return list of forename and surname
     */
    private static List<String> splitForenameAndSurname(String author) {
        return List.of(author.split(" "));
    }
}

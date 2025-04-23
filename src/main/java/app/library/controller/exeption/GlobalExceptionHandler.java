package app.library.controller.exeption;

import app.library.exception.AuthorRepositoryException;
import app.library.exception.BookRepositoryException;
import app.library.exception.JournalRepositoryException;
import app.library.exception.UserRepositoryException;
import app.library.model.Author;
import app.library.model.Book;
import app.library.model.Journal;
import app.library.model.UserTable;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(AuthorRepositoryException.class)
    public ModelAndView handleAuthorException(AuthorRepositoryException e, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView(getView(request));
        logger.error("Error during author creation", e);
        mav.addObject("author", new Author());
        mav.addObject("error", String.format("An error occurred while adding the author.\n %s. Please try again.", e.getMessage()));
        return mav;
    }

    @ExceptionHandler(JournalRepositoryException.class)
    public ModelAndView handleJournalException(JournalRepositoryException e, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(getView(request));
        logger.error("Error during journal creation", e);
        mav.addObject("journal", new Journal());
        mav.addObject("error", String.format("An error occurred while adding the journal.\n %s. Please try again.", e.getMessage()));
        return mav;
    }

    @ExceptionHandler(BookRepositoryException.class)
    public ModelAndView handleBookException(BookRepositoryException e, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(getView(request));
        logger.error("Error during author creation", e);
        mav.addObject("book", new Book());
        mav.addObject("error", String.format("An error occurred while adding the book.\n %s. Please try again.", e.getMessage()));
        return mav;
    }

    @ExceptionHandler(UserRepositoryException.class)
    public ModelAndView handleUserException(UserRepositoryException e, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(getView(request));
        logger.error("Error during user creation", e);
        mav.addObject("user", new UserTable());
        mav.addObject("error", String.format("An error occurred while adding the user.\n %s. Please try again.", e.getMessage()));
        return mav;
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeExceptions(RuntimeException e, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(getView(request));
        logger.error("Unexpected Error happened", e);
        mav.addObject("error", "Unexpected Error happened. Please try again.");
        return mav;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleExceptions(Exception e, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(getView(request));
        logger.error("Unexpected Error happened", e);
        mav.addObject("error", "Unexpected Error happened. Please try again.");
        return mav;
    }

    @ExceptionHandler(BindException.class)
    public ModelAndView handleTypeMismatchException(BindException e, HttpServletRequest request) {
        String view = getView(request);
        ModelAndView mav = new ModelAndView(view);
        logger.error("Mismatch error happened", e);
        mav.addObject("book", new Book());
        mav.addObject("author", new Author());
        mav.addObject("journal", new Journal());
        mav.addObject("error", "Mismatch Error happened. You entered wrong value to the field. Please try again with correct number.");
        return mav;
    }

    private String getView(HttpServletRequest request) {
        String view = (String) request.getAttribute("viewName");
        if (view == null) {
            view = "error";
        }
        logger.info("User view: {}", view);
        return view;
    }
}

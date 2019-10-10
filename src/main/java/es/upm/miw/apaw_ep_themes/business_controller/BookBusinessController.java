package es.upm.miw.apaw_ep_themes.business_controller;

import es.upm.miw.apaw_ep_themes.daos.BookDao;
import es.upm.miw.apaw_ep_themes.documents.Book;
import es.upm.miw.apaw_ep_themes.dtos.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookBusinessController {

    private BookDao bookDao;

    @Autowired
    public BookBusinessController(BookDao bookDao){
        this.bookDao = bookDao;
    }

    public BookDto create(BookDto bookDto){
        Book book = new Book(bookDto.getTitle(), bookDto.getAuthor());
        this.bookDao.save(book);
        return new BookDto(book);
    }
}

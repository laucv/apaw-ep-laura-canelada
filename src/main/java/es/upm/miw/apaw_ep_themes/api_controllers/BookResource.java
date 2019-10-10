package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.business_controller.BookBusinessController;
import es.upm.miw.apaw_ep_themes.dtos.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookResource.BOOKS)
public class BookResource {

    static final String BOOKS = "/books";

    private BookBusinessController bookBusinessController;

    @Autowired
    public BookResource(BookBusinessController bookBusinessController) {
        this.bookBusinessController = bookBusinessController;
    }

    @PostMapping
    public BookDto create(@RequestBody BookDto bookDto){
        bookDto.validate();
        return this.bookBusinessController.create(bookDto);
    }
}

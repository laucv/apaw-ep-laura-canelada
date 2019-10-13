package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.business_controller.BookBusinessController;
import es.upm.miw.apaw_ep_themes.dtos.BookDto;
import es.upm.miw.apaw_ep_themes.dtos.BookPatchDto;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(BookResource.BOOKS)
public class BookResource {

    static final String BOOKS = "/books";
    static final String SEARCH = "/search";
    static final String TITLE = "/title";
    static final String ID_ID = "/{id}";

    private BookBusinessController bookBusinessController;

    @Autowired
    public BookResource(BookBusinessController bookBusinessController) {
        this.bookBusinessController = bookBusinessController;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public BookDto create(@RequestBody BookDto bookDto) {
        bookDto.validate();
        return this.bookBusinessController.create(bookDto);
    }

    @GetMapping(value = SEARCH)
    public List<BookDto> find(@RequestParam String q) {
        if (!"author".equals(q.split(":")[0])) {
            throw new BadRequestException("query param q is incorrect, missing 'author:'");
        }
        return this.bookBusinessController.findByAuthor(q.split(":")[1]);
    }

    @PutMapping(value = ID_ID + TITLE)
    public void updateTitle(@PathVariable String id, @RequestBody BookDto bookDto) {
        bookDto.validate();
        this.bookBusinessController.updateTitle(id, bookDto.getTitle());
    }

    @PatchMapping(value = ID_ID)
    public void patch(@PathVariable String id, @RequestBody BookPatchDto bookPatchDto) {
        bookPatchDto.validate();
        this.bookBusinessController.patch(id, bookPatchDto);
    }
}

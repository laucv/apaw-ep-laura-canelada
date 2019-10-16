package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.business_controller.BookProposalBusinessController;
import es.upm.miw.apaw_ep_themes.dtos.BookProposalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(BookProposalResource.BOOKS_PROPOSAL)
public class BookProposalResource {

    static final String BOOKS_PROPOSAL = "/books-proposal";

    private BookProposalBusinessController bookProposalBusinessController;

    @Autowired
    public BookProposalResource(BookProposalBusinessController bookProposalBusinessController) {
        this.bookProposalBusinessController = bookProposalBusinessController;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public BookProposalDto create(@RequestBody BookProposalDto bookProposalDto) {
        bookProposalDto.validate();
        return this.bookProposalBusinessController.create(bookProposalDto);
    }
}

package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.business_controller.BookProposalBusinessController;
import es.upm.miw.apaw_ep_themes.dtos.BookProposalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BookProposalResource.BOOKS_PROPOSAL)
public class BookProposalResource {

    static final String BOOKS_PROPOSAL = "/books-proposal";

    private BookProposalBusinessController bookProposalBusinessController;

    @Autowired
    public BookProposalResource(BookProposalBusinessController bookProposalBusinessController) {
        this.bookProposalBusinessController = bookProposalBusinessController;
    }

    @PostMapping
    public BookProposalDto create(@RequestBody BookProposalDto bookProposalDto) {
        bookProposalDto.validate();
        return this.bookProposalBusinessController.create(bookProposalDto);
    }
}

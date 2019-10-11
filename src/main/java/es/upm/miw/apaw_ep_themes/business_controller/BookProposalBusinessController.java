package es.upm.miw.apaw_ep_themes.business_controller;

import es.upm.miw.apaw_ep_themes.daos.BookProposalDao;
import es.upm.miw.apaw_ep_themes.documents.BookProposal;
import es.upm.miw.apaw_ep_themes.dtos.BookProposalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookProposalBusinessController {

    private BookProposalDao bookProposalDao;

    @Autowired
    public BookProposalBusinessController(BookProposalDao bookProposalDao) {
        this.bookProposalDao = bookProposalDao;
    }

    public BookProposalDto create(BookProposalDto bookProposalDto) {
        BookProposal bookProposal = new BookProposal(bookProposalDto.getTitle(), bookProposalDto.getAuthor());
        this.bookProposalDao.save(bookProposal);
        return new BookProposalDto(bookProposal);
    }
}
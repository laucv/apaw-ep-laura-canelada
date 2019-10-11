package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.ApiTestConfig;
import es.upm.miw.apaw_ep_themes.dtos.BookProposalDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApiTestConfig
public class BookProposalResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateBookProposal() {
        BookProposalDto bookProposalDto = this.webTestClient
                .post().uri(BookProposalResource.BOOKS_PROPOSAL)
                .body(BodyInserters.fromObject(new BookProposalDto("Si no despierto", "Lauren Oliver")))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookProposalDto.class).returnResult().getResponseBody();
        assertNotNull(bookProposalDto);
        assertEquals("Si no despierto", bookProposalDto.getTitle());
        assertEquals("Lauren Oliver", bookProposalDto.getAuthor());
    }

    @Test
    void testCreateBookProposalException() {
        BookProposalDto bookProposalDto = new BookProposalDto();
        this.webTestClient
                .post().uri(BookProposalResource.BOOKS_PROPOSAL)
                .body(BodyInserters.fromObject(bookProposalDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}

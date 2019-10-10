package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.ApiTestConfig;
import es.upm.miw.apaw_ep_themes.dtos.BookDto;
import es.upm.miw.apaw_ep_themes.dtos.LibraryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApiTestConfig
public class BookResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        LibraryDto libraryDto = new LibraryDto("Biblioteca");
        String libraryId = this.webTestClient
                .post().uri(LibraryResource.LIBRARIES)
                .body(BodyInserters.fromObject(libraryDto))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .returnResult().getResponseBody().getId();
        BookDto bookDto = this.webTestClient
                .post().uri(BookResource.BOOKS)
                .body(BodyInserters.fromObject(new BookDto("El ladrón del rayo", "Rick Riordan", libraryId)))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class).returnResult().getResponseBody();
        assertNotNull(bookDto);
        assertEquals("El ladrón del rayo", bookDto.getTitle());
        assertEquals("Rick Riordan", bookDto.getAuthor());
        assertEquals(libraryId, bookDto.getLibraryId());
    }

    @Test
    void testCreateBookException() {
        BookDto genreDto = new BookDto();
        this.webTestClient
                .post().uri(BookResource.BOOKS)
                .body(BodyInserters.fromObject(genreDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testCreateLibraryIdException() {
        BookDto bookDto = new BookDto("El ladrón del rayo", "Rick Riordan", "no");
        this.webTestClient
                .post().uri(BookResource.BOOKS)
                .body(BodyInserters.fromObject(bookDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }
}

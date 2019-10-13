package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.ApiTestConfig;
import es.upm.miw.apaw_ep_themes.dtos.BookDto;
import es.upm.miw.apaw_ep_themes.dtos.BookPatchDto;
import es.upm.miw.apaw_ep_themes.dtos.LibraryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
public class BookResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreateBook() {
        this.createBook("El ladrón del rayo", "Rick Riordan", this.createLibrary("Biblioteca"));
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

    BookDto createBook(String title, String author, String libraryId) {
        return this.webTestClient
                .post().uri(BookResource.BOOKS)
                .body(BodyInserters.fromObject(new BookDto(title, author, libraryId)))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class).returnResult().getResponseBody();
    }

    String createLibrary(String name) {
        return this.webTestClient
                .post().uri(LibraryResource.LIBRARIES)
                .body(BodyInserters.fromObject(new LibraryDto(name)))
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .returnResult().getResponseBody().getId();
    }

    @Test
    void testSearchByAuthor() {
        String libraryId = this.createLibrary("Biblioteca");
        this.createBook("El ladrón del rayo", "Rick Riordan", libraryId);
        this.createBook("Reinos de Cristal", "Iria G. Parente, Selene M. Pascual", libraryId);
        this.createBook("La batalla del laberinto", "Rick Riordan", libraryId);
        this.createBook("La casa de Hades", "Rick Riordan", libraryId);
        List<BookDto> themes = this.webTestClient
                .get().uri(uriBuilder ->
                        uriBuilder.path(BookResource.BOOKS + BookResource.SEARCH)
                                .queryParam("q", "author:RickRiordan")
                                .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class)
                .returnResult().getResponseBody();
        assertFalse(themes.isEmpty());
    }

    @Test
    void testUpdateTitle() {
        String libraryId = this.createLibrary("Biblioteca");
        String bookId = this.createBook("El ladrón de manzanas", "Rick Riordan", libraryId).getId();
        BookDto bookDto = new BookDto("El ladrón de manzanas", "Rick Riordan", libraryId);
        bookDto.setTitle("El ladrón del rayo");
        this.webTestClient
                .put().uri(BookResource.BOOKS + BookResource.ID_ID + BookResource.TITLE, bookId)
                .body(BodyInserters.fromObject(bookDto))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testPutTitleNotFoundException() {
        BookDto bookDto = new BookDto("aaaaaa", "Rick Riordan", this.createLibrary("Biblioteca"));
        bookDto.setId("no");
        bookDto.setTitle("El ladrón del rayo");
        this.webTestClient
                .put().uri(BookResource.BOOKS + BookResource.ID_ID + BookResource.TITLE, bookDto.getId())
                .body(BodyInserters.fromObject(bookDto))
                .exchange()
                    .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testPutTitleBadRequestException() {
        BookDto bookDto = new BookDto();
        bookDto.setId("no");
        this.webTestClient
                .put().uri(BookResource.BOOKS + BookResource.ID_ID + BookResource.TITLE, bookDto.getId())
                .body(BodyInserters.fromObject(bookDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testBookUpdateBookPatchDtoException() {
        String bookId = this.createBook("El ladrón del rayo", "Rick Riordan", this.createLibrary("Biblioteca")).getId();
        this.webTestClient
                .patch().uri(BookResource.BOOKS + BookResource.ID_ID, bookId)
                .body(BodyInserters.fromObject(new BookPatchDto()))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testBookUpdateIdException() {
        this.webTestClient
                .patch().uri(BookResource.BOOKS + BookResource.ID_ID, "no")
                .body(BodyInserters.fromObject(new BookPatchDto("title", "other")))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void testBookUpdate() {
        String bookId = this.createBook("El ladrón de manzanas", "Rick Riordan", this.createLibrary("Biblioteca")).getId();
        this.webTestClient
                .patch().uri(BookResource.BOOKS + BookResource.ID_ID, bookId)
                .body(BodyInserters.fromObject(new BookPatchDto("title", "El ladrón del rayo")))
                .exchange()
                .expectStatus().isOk();
    }
}
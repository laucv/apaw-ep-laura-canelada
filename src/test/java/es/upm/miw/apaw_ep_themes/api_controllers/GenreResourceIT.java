package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.ApiTestConfig;
import es.upm.miw.apaw_ep_themes.dtos.GenreDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApiTestConfig
public class GenreResourceIT {


    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        GenreDto genreDto = this.webTestClient
                .post().uri(GenreResource.GENRES)
                .body(BodyInserters.fromObject(new GenreDto("Ciencia ficción")))
                .exchange()
                .expectStatus().isOk()
                .expectBody(GenreDto.class).returnResult().getResponseBody();
        assertNotNull(genreDto);
        assertEquals("Ciencia ficción", genreDto.getGenreName());
    }

    @Test
    void testCreateLibraryException() {
        GenreDto genreDto = new GenreDto();
        this.webTestClient
                .post().uri(GenreResource.GENRES)
                .body(BodyInserters.fromObject(genreDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}

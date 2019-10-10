package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.ApiTestConfig;
import es.upm.miw.apaw_ep_themes.dtos.LibraryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.*;

@ApiTestConfig
public class LibraryResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testCreate() {
        LibraryDto libraryDto = this.webTestClient
                .post().uri(LibraryResource.LIBRARIES)
                .body(BodyInserters.fromObject(new LibraryDto("Biblioteca Municipal de Coslada")))
                .exchange()
                .expectStatus().isOk()
                .expectBody(LibraryDto.class).returnResult().getResponseBody();
        assertNotNull(libraryDto);
        assertEquals("Biblioteca Municipal de Coslada", libraryDto.getName());
    }

    @Test
    void testCreateLibraryException() {
        LibraryDto libraryDto = new LibraryDto((String) null);
        this.webTestClient
                .post().uri(LibraryResource.LIBRARIES)
                .body(BodyInserters.fromObject(libraryDto))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}

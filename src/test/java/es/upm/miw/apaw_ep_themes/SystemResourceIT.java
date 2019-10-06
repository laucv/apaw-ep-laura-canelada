package es.upm.miw.apaw_ep_themes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ApiTestConfig
class SystemResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testReadVersionBadge() {
        String badge = new String(
                this.webTestClient
                        .get().uri(SystemResource.SYSTEM + SystemResource.VERSION_BADGE)
                        .exchange()
                        .expectStatus().isOk()
                        .expectBody(byte[].class)
                        .returnResult().getResponseBody()
        );
        assertNotNull(badge);
        assertEquals("<svg", badge.substring(0, 4));
    }

    @Test
    void testReadVersion() {
        VersionDto versionDto = this.webTestClient
                .get().uri(SystemResource.SYSTEM + SystemResource.VERSION)
                .exchange()
                .expectStatus().isOk()
                .expectBody(VersionDto.class)
                .returnResult().getResponseBody();
        assertNotNull(versionDto.getVersion());
        assertEquals("VersionDto{",versionDto.toString().substring(0,11));

    }

    @Test
    void testException(){
        this.webTestClient.post().uri(SystemResource.SYSTEM + SystemResource.VERSION_BADGE)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_REQUEST);
    }
}

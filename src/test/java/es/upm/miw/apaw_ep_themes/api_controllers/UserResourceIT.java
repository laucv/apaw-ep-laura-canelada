package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.ApiTestConfig;
import es.upm.miw.apaw_ep_themes.daos.UserDao;
import es.upm.miw.apaw_ep_themes.documents.User;
import es.upm.miw.apaw_ep_themes.dtos.UserDeleteDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

@ApiTestConfig
public class UserResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserDao userDao;

    @Test
    void testDeleteUser() {
        User user = new User("user1");
        userDao.save(user);
        UserDeleteDto userDeleteDto = new UserDeleteDto(user);
        this.webTestClient
                .delete().uri(UserResource.USERS + UserResource.ID_ID, userDeleteDto.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDeleteUserNotFound() {
        UserDeleteDto userDeleteDto = new UserDeleteDto(new User("user1"));
        this.webTestClient
                .delete().uri(UserResource.USERS + UserResource.ID_ID, userDeleteDto.getId())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

}

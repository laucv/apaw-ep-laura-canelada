package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.business_controller.UserBusinessController;
import es.upm.miw.apaw_ep_themes.dtos.UserDeleteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {

    static final String USERS = "/users";
    static final String ID_ID = "/{id}";

    private UserBusinessController userBusinessController;

    @Autowired
    public UserResource(UserBusinessController userBusinessController) {
        this.userBusinessController = userBusinessController;
    }


    @DeleteMapping(value = ID_ID)
    public void delete(@PathVariable String id){
        this.userBusinessController.delete(id);
    }
}

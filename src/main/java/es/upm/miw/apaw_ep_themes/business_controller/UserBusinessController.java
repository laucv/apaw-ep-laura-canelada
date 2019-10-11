package es.upm.miw.apaw_ep_themes.business_controller;

import es.upm.miw.apaw_ep_themes.daos.UserDao;
import es.upm.miw.apaw_ep_themes.documents.User;
import es.upm.miw.apaw_ep_themes.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserBusinessController {

    private UserDao userDao;

    @Autowired
    public UserBusinessController(UserDao userDao) {
        this.userDao = userDao;
    }


    public void delete(String id) {
        User user = this.userDao.findById(id).orElseThrow(() -> new NotFoundException("User id: " + id));
        this.userDao.delete(user);
    }
}

package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.documents.User;

public class UserDeleteDto {

    private String id;

    public UserDeleteDto() {
        //empty for framework
    }

    public UserDeleteDto(User user) {
        this.id = user.getId();
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserDeleteDto{" +
                "id='" + id + '\'' +
                '}';
    }
}

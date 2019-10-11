package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.documents.User;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class UserDeleteDto {

    private String id;

    private String userName;

    public UserDeleteDto(){
        //empty for framework
    }

    public UserDeleteDto(String userName) {
        this.userName = userName;
    }

    public UserDeleteDto(User user){
        this(user.getUserName());
        this.id = user.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void validate(){
        if(userName == null || userName.isEmpty()){
            throw new BadRequestException("Incomplete UserDeleteDto");
        }
    }

    @Override
    public String toString() {
        return "UserDeleteDto{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

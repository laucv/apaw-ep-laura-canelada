package es.upm.miw.apaw_ep_themes.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class User {

    @Id
    private String id;

    private String userName;

    private LocalDateTime registerDate;

    @DBRef
    private List<Book> bookList;

    public User(String userName) {
        this.userName = userName;
        this.registerDate = LocalDateTime.now();
        this.bookList = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }
}

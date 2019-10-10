package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.documents.Book;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class BookDto {

    private String id;

    private String title;

    private String author;

    private boolean borrow;

    public BookDto(){
        //empty for framework
    }

    public BookDto(String title, String author) {
        this.title = title;
        this.author = author;
        this.borrow = false;
    }

    public BookDto(Book book){
        this(book.getTitle(), book.getAuthor());
        this.id = book.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrow() {
        return borrow;
    }

    public void setBorrow(boolean borrow) {
        this.borrow = borrow;
    }

    public void validate(){
        if(title == null || title.isEmpty() || author==null || author.isEmpty()){
            throw new BadRequestException("Incomplete BookDao. ");
        }
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", borrow=" + borrow +
                '}';
    }
}

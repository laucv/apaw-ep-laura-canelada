package es.upm.miw.apaw_ep_themes.documents;

public class BookBuilder {

    private Book book;

    public BookBuilder(String title, String author, Library library){
        this.book = new Book(title, author, library);
    }

    public Book build(){
        return this.book;
    }
}

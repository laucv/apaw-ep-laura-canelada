package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.documents.Book;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class BookDto {

    private String id;

    private String title;

    private String author;

    private String libraryId;

    public BookDto() {
        //empty for framework
    }

    public BookDto(String title, String author, String libraryId) {
        this.title = title;
        this.author = author;
        this.libraryId = libraryId;
    }

    public BookDto(Book book) {
        this(book.getTitle(), book.getAuthor(), book.getLibrary().getId());
        this.id = book.getId();
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void validate() {
        if (title == null || title.isEmpty() || author == null || author.isEmpty() || libraryId == null || libraryId.isEmpty()) {
            throw new BadRequestException("Incomplete BookDto. ");
        }
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", libraryId='" + libraryId + '\'' +
                '}';
    }
}

package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.documents.BookProposal;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class BookProposalDto {

    private String id;

    private String title;

    private String author;

    public BookProposalDto() {
        //empty for framework
    }

    public BookProposalDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public BookProposalDto(BookProposal bookProposal) {
        this(bookProposal.getTitle(), bookProposal.getAuthor());
        this.id = bookProposal.getId();
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

    public void validate() {
        if (title == null || title.isEmpty() || author == null || author.isEmpty()) {
            throw new BadRequestException("Incomplete BookProposalDao. ");
        }
    }

    @Override
    public String toString() {
        return "BookProposalDao{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
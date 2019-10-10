package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.documents.Genre;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class GenreDto {

    private String id;

    private String genreName;

    public GenreDto() {
        //empty for framework
    }

    public GenreDto(String genreName) {
        this.genreName = genreName;
    }

    public GenreDto(Genre genre) {
        this(genre.getGenreName());
        this.id = genre.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public void validate() {
        if (genreName == null || genreName.isEmpty()) {
            throw new BadRequestException("Incomplete GenreDto. ");
        }
    }

    @Override
    public String toString() {
        return "GenreDto{" +
                "id='" + id + '\'' +
                ", genreName='" + genreName + '\'' +
                '}';
    }
}

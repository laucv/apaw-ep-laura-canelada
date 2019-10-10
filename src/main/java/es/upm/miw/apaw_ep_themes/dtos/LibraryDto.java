package es.upm.miw.apaw_ep_themes.dtos;

import es.upm.miw.apaw_ep_themes.documents.Library;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;

public class LibraryDto {

    private String id;

    private String name;

    public LibraryDto() {
        //empty for framework
    }

    public LibraryDto(String name) {
        this.name = name;
    }

    public LibraryDto(Library library) {
        this.id = library.getId();
        this.name = library.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void validate() {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Incomplete LibraryDto. ");
        }
    }

    @Override
    public String toString() {
        return "LibraryDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

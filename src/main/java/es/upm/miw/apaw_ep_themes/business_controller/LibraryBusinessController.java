package es.upm.miw.apaw_ep_themes.business_controller;

import es.upm.miw.apaw_ep_themes.daos.LibraryDao;
import es.upm.miw.apaw_ep_themes.documents.Library;
import es.upm.miw.apaw_ep_themes.dtos.LibraryDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LibraryBusinessController {

    private LibraryDao libraryDao;

    @Autowired
    public LibraryBusinessController(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }

    public LibraryDto create(LibraryDto libraryDto) {
        Library library = new Library(libraryDto.getName());
        this.libraryDao.save(library);
        return new LibraryDto(library);
    }
}

package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.business_controller.LibraryBusinessController;
import es.upm.miw.apaw_ep_themes.dtos.LibraryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(LibraryResource.LIBRARIES)
public class LibraryResource {

    static final String LIBRARIES = "/libraries";

    private LibraryBusinessController libraryBusinessController;

    @Autowired
    public LibraryResource(LibraryBusinessController libraryBusinessController) {
        this.libraryBusinessController = libraryBusinessController;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public LibraryDto create(@RequestBody LibraryDto libraryDto) {
        libraryDto.validate();
        return this.libraryBusinessController.create(libraryDto);
    }
}
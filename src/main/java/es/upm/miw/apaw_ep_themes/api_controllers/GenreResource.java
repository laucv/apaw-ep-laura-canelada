package es.upm.miw.apaw_ep_themes.api_controllers;

import es.upm.miw.apaw_ep_themes.business_controller.GenreBusinessController;
import es.upm.miw.apaw_ep_themes.dtos.GenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(GenreResource.GENRES)
public class GenreResource {

    static final String GENRES = "/genres";

    private GenreBusinessController genreBusinessController;

    @Autowired
    public GenreResource(GenreBusinessController genreBusinessController) {
        this.genreBusinessController = genreBusinessController;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public GenreDto create(@RequestBody GenreDto genreDto) {
        genreDto.validate();
        return this.genreBusinessController.create(genreDto);
    }
}

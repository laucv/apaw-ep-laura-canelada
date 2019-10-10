package es.upm.miw.apaw_ep_themes.business_controller;

import es.upm.miw.apaw_ep_themes.daos.GenreDao;
import es.upm.miw.apaw_ep_themes.documents.Genre;
import es.upm.miw.apaw_ep_themes.dtos.GenreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GenreBusinessController {

    private GenreDao genreDao;

    @Autowired
    public GenreBusinessController(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    public GenreDto create(GenreDto genreDto) {
        Genre genre = new Genre(genreDto.getGenreName());
        this.genreDao.save(genre);
        return new GenreDto(genre);
    }
}

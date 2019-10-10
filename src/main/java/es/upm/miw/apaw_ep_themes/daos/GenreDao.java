package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.documents.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreDao extends MongoRepository<Genre, String> {
}

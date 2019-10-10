package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.documents.Library;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LibraryDao extends MongoRepository<Library, String> {
}

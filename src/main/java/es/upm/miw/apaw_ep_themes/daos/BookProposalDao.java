package es.upm.miw.apaw_ep_themes.daos;

import es.upm.miw.apaw_ep_themes.documents.BookProposal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookProposalDao extends MongoRepository<BookProposal, String> {
}

package es.upm.miw.apaw_ep_themes.business_controller;

import es.upm.miw.apaw_ep_themes.daos.BookDao;
import es.upm.miw.apaw_ep_themes.daos.LibraryDao;
import es.upm.miw.apaw_ep_themes.documents.Book;
import es.upm.miw.apaw_ep_themes.documents.BookBuilder;
import es.upm.miw.apaw_ep_themes.documents.Library;
import es.upm.miw.apaw_ep_themes.dtos.BookDto;
import es.upm.miw.apaw_ep_themes.dtos.BookPatchDto;
import es.upm.miw.apaw_ep_themes.exceptions.BadRequestException;
import es.upm.miw.apaw_ep_themes.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookBusinessController {

    private BookDao bookDao;

    private LibraryDao libraryDao;

    @Autowired
    public BookBusinessController(BookDao bookDao, LibraryDao libraryDao) {
        this.bookDao = bookDao;
        this.libraryDao = libraryDao;
    }

    public BookDto create(BookDto bookDto) {
        Library library = this.libraryDao.findById(bookDto.getLibraryId())
                .orElseThrow(() -> new NotFoundException("User id: " + bookDto.getLibraryId()));
        Book book = new BookBuilder(bookDto.getTitle(), bookDto.getAuthor(), library).build();
        this.bookDao.save(book);
        return new BookDto(book);
    }

    public List<BookDto> findByAuthor(String author) {
        return this.bookDao.findAll().stream()
                .filter(book -> book.getAuthor().replaceAll("\\s", "").equals(author))
                .map(BookDto::new)
                .collect(Collectors.toList());
    }

    public Book findBookByIdAssured(String id) {
        return this.bookDao.findById(id).orElseThrow(() -> new NotFoundException("Book id: " + id));
    }

    public void updateTitle(String id, String title) {
        Book book = this.findBookByIdAssured(id);
        book.setTitle(title);
        this.bookDao.save(book);
    }

    public void patch(String id, BookPatchDto bookPatchDto) {
        Book book = this.findBookByIdAssured(id);
        switch (bookPatchDto.getPath()) {
            case "title":
                book.setTitle(bookPatchDto.getNewValue());
                break;
            case "author":
                book.setAuthor(bookPatchDto.getNewValue());
                break;
            case "libraryId":
                book.getLibrary().setId(bookPatchDto.getNewValue());
                break;
            default:
                throw new BadRequestException("BookPatchDto is invalid");
        }
        this.bookDao.save(book);
    }
}

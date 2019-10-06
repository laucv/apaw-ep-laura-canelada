package es.upm.miw.apaw_ep_themes.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestExceptions {

    @Test
    void testBadRequestException(){
        assertEquals("Bad", new BadRequestException("test").getMessage().substring(0,3));
    }

    @Test
    void testConflictException(){
        assertEquals("Conflict", new ConflictException("test").getMessage().substring(0,8));
    }

    @Test
    void testNotFoundException(){
        assertEquals("Not", new NotFoundException("test").getMessage().substring(0,3));
    }

    @Test
    void testErrorMessage(){
        assertEquals("ErrorMessage",new ErrorMessage(new BadRequestException("test"),"test").toString().substring(0,12));
    }
}

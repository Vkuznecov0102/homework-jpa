package ru.itsjava.dao.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Email;
import ru.itsjava.services.EmailService;
import ru.itsjava.services.EmailServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(EmailServiceImpl.class)
public class EmailServiceImplTest {

    @Autowired
    private EmailService service;

    private final Optional<Email> email = Optional.of(new Email(1L, "alexandro@protonmail.com"));
    private final Optional<Email> emailSecond = Optional.of(new Email(2L, "nikolas@mail.ru"));
    private final Optional<Email> emailThird = Optional.of(new Email(3L, "meggy@yandex.ru"));
    private final Optional<Email> emailFourth = Optional.of(new Email(4L, "javelin@yandex.ru"));
    private final Email objMail = email.get();
    private final Email objMailSecond= emailSecond.get();
    private final Email objMailThird=emailThird.get();
    private final Email objMailFourth=emailFourth.get();

    @Test
    public void shouldHaveCorrectCount(){
        service.insertEmail(objMail);
        assertAll(
                ()-> assertEquals(1, service.countEmailByAddress("alexandro@protonmail.com")),
                ()-> assertEquals(0, service.countEmailByAddress("alexandro@gmail.com"))
        );
    }

    @Test
    public void shouldHaveCorrectInsert(){
        service.insertEmail(objMail);
        assertEquals(service.getEmailById(1L),email);
    }

    @Test
    public void shouldHaveCorrectUpdate(){
        service.insertEmail(objMail);
        assertEquals(email, service.getEmailById(1L));
        service.updateEmail(1L,"alexandro@gmail.com");
        assertAll(
                ()-> assertEquals(0, service.countEmailByAddress("alexandro@protonmail.com")),
                ()-> assertEquals(1, service.countEmailByAddress("alexandro@gmail.com"))
        );
    }

    @Test
    public void shouldHaveCorrectDelete(){
        service.insertEmail(objMail);
        assertAll(
                ()-> assertEquals(service.getEmailById(1L),email),
                ()->assertNotNull(service.getEmailById(1L))
        );
        service.deleteEmail(1L);
        assertEquals(Optional.empty(), service.getEmailById(1L));
    }

    @Test
    public void shouldHaveCorrectFindAll(){
        List<Email> mails= new ArrayList<>(List.of(objMail, objMailSecond,objMailThird,objMailFourth));

        service.insertEmail(objMail);
        service.insertEmail(objMailSecond);
        service.insertEmail(objMailThird);
        service.insertEmail(objMailFourth);

        assertEquals(mails,service.findAll());
    }
}

package ru.itsjava.dao.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domains.Email;
import ru.itsjava.domains.Pet;
import ru.itsjava.domains.User;
import ru.itsjava.services.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({EmailServiceImpl.class, PetServiceImpl.class, UserServiceImpl.class})
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PetService petService;

    private final Optional<Pet> pet=Optional.of(new Pet(1L,"cat","Мурзик"));
    private final Optional<Email> email = Optional.of(new Email(1L, "alexandro@protonmail.com"));

    private final Pet objPet=pet.get();
    private final Email mail=email.get();

    private final Optional<User> user= Optional.of(new User(1L,"Иванов ОА",new Email(mail.getId(),mail.getAddress()),new Pet(objPet.getId(), objPet.getType(), objPet.getName())));
    private final Optional<User> userSecond= Optional.of(new User(2L,"Максимов НН",new Email(mail.getId(),mail.getAddress()),new Pet(objPet.getId(), objPet.getType(), objPet.getName())));
    private final Optional<User> userThird= Optional.of(new User(3L,"Жигунов ОА",new Email(mail.getId(),mail.getAddress()),new Pet(objPet.getId(), objPet.getType(), objPet.getName())));
    private final Optional<User> userFourth= Optional.of(new User(4L,"Алексеев СС",new Email(mail.getId(),mail.getAddress()),new Pet(objPet.getId(), objPet.getType(), objPet.getName())));
    private final User objUser=user.get();
    private final User objSecond=userSecond.get();
    private final User objThird=userThird.get();
    private final User objFourth=userFourth.get();

    @Test
    public void shouldHaveCorrectCount(){
        petService.insertPet(objPet);
        emailService.insertEmail(mail);
        userService.insertUser(objUser);
        assertAll(
                ()-> assertEquals(userService.countUserByName("Иванов ОА"),1),
                ()-> assertEquals(userService.countUserByName("Кирьяков ОА"),0)
        );
    }

    @Test
    public void shouldHaveCorrectInsert(){
        petService.insertPet(objPet);
        emailService.insertEmail(mail);
        userService.insertUser(objUser);
        assertAll(
                ()-> assertEquals(petService.getPetById(1L),pet),
                ()-> assertEquals(emailService.getEmailById(1L),email),
                ()-> assertEquals(userService.getUserById(1L),user)
        );
    }

    @Test
    public void shouldHaveCorrectUpdate(){
        petService.insertPet(objPet);
        emailService.insertEmail(mail);
        userService.insertUser(objUser);
        userService.updateUser(1L,"Кирьяков ОА");
        assertAll(
                ()-> assertEquals(userService.countUserByName("Иванов ОА"),0),
                ()-> assertEquals(userService.countUserByName("Кирьяков ОА"),1)
        );
    }

    @Test
    public void shouldHaveCorrectDelete(){
        petService.insertPet(objPet);
        emailService.insertEmail(mail);
        userService.insertUser(objUser);
        assertAll(
                ()->        assertEquals(userService.getUserById(1L),user),
                ()->        assertNotNull(userService.getUserById(1L))
        );
        userService.deleteUser(1L);
        assertEquals(Optional.empty(), userService.getUserById(1L));
    }

    @Test
    public void shouldHaveCorrectFindAll(){
        List<User> users= new ArrayList<>(List.of(objUser,objSecond,objThird,objFourth));
        userService.insertUser(objUser);
        userService.insertUser(objSecond);
        userService.insertUser(objThird);
        userService.insertUser(objFourth);

        assertEquals(users,userService.findAll());

    }
}
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

public class RegistrationTestsViaJenkins extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @Tag("registrationForm")
    @DisplayName("Заполнение формы и проверка, что все данные успешно отправились")
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Ivanna")
                .setLastName("Ivanova")
                .setEmail("ivanova@mail.com")
                .setGender("Female")
                .setUserNumber("7989898890")
                .setDateOfBirth("21", "January", "1980")
                .setSubjects("English")
                .setHobbies("Reading")
                .setPicture("image.jpeg")
                .setAddress("Some address")
                .setState("Uttar Pradesh")
                .setCity("Lucknow")
                .submitForm()
                .checkSuccessSubmit()
                .checkResult("Student Name", "Ivanna Ivanova")
                .checkResult("Student Email", "ivanova@mail.com")
                .checkResult("Gender", "Female")
                .checkResult("Mobile", "7989898890")
                .checkResult("Subjects", "English")
                .checkResult("Hobbies", "Reading")
                .checkResult("Address", "Some address");
    }

    @Test
    @DisplayName("Успешная отправка формы с минимальным количеством данным")
    void registrationWithMinimumDataTest() {
        registrationPage.openPage()
                .setFirstName("John")
                .setLastName("Doe")
                .setGender("Male")
                .setUserNumber("7989898890")
                .submitForm()
                .checkSuccessSubmit()
                .checkResult("Student Name", "John Doe")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "7989898890");
    }

    @Test
    @DisplayName("Проверка, что форма не отправляется без заполнения обязательных полей")
    void unsuccessfulRegistrationTest() {
        registrationPage.openPage()
                .setEmail("test@test.com")
                .submitForm()
                .checkNoSubmit();
    }
}

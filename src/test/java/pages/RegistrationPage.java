package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderSection = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            hobbiesSection = $("#hobbiesWrapper"),
            pictureUploadInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateCitySection = $("#stateCity-wrapper"),
            stateSelect = $("#state"),
            citySelect = $("#city"),
            submitButton = $("#submit"),
            modalPopup = $(".modal-dialog"),
            modalPopupHeader = $(".modal-title"),
            resultsTable = $(".table-responsive");

    CalendarComponent calendarComponent = new CalendarComponent();
    ResultTableComponent resultTableComponent = new ResultTableComponent();

    @Step("Открыть страницу с формой")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    @Step("Заполнить поле 'Имя'")
    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Заполнить поле 'Фамилия'")
    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Заполнить поле 'Email'")
    public RegistrationPage setEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Выбрать гендерную принадлежность")
    public RegistrationPage setGender(String value) {
        genderSection.$(byText(value)).click();

        return this;
    }

    @Step("Заполнить поле 'Номер телефона'")
    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Указать дату рождения")
    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    @Step("Заполнить поле 'Учебные предметы'")
    public RegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Заполнить поле 'Хобби'")
    public RegistrationPage setHobbies(String value) {
        hobbiesSection.$(byText(value)).click();

        return this;
    }

    @Step("Загрузить аватарку'")
    public RegistrationPage setPicture(String value) {
        pictureUploadInput.uploadFromClasspath(value);

        return this;
    }

    @Step("Заполнить поле 'Адрес'")
    public RegistrationPage setAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    @Step("Выбрать регион проживания")
    public RegistrationPage setState(String value) {
        stateSelect.click();
        stateCitySection.$(byText(value)).click();

        return this;
    }

    @Step("Выбрать город проживания")
    public RegistrationPage setCity(String value) {
        citySelect.click();
        stateCitySection.$(byText(value)).click();

        return this;
    }

    @Step("Отправить форму")
    public RegistrationPage submitForm() {
        submitButton.click();

        return this;
    }

    @Step("Проверить, что появился баннер об успешной отправке формы")
    public RegistrationPage checkSuccessSubmit() {
        modalPopup.should(appear);
        modalPopupHeader.shouldHave(text("Thanks for submitting the form"));

        return this;
    }

    @Step("Проверить, что баннер об отправке формы не появился")
    public void checkNoSubmit() {
        modalPopup.shouldNot(appear);
    }

    @Step("Сверить отправленные данные")
    public RegistrationPage checkResult(String key, String value) {
        resultTableComponent.checkResult(key, value);

        return this;
    }
}

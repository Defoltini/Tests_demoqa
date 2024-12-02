import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DemoqaTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize= "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
     void successfulSearchTest() {
        // Открыть страницу
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        // Заполнить форму
        $("[id=firstName]").setValue("Anastasia");
        $("[id=lastName]").setValue("Lyuleva");
        $("[id=userEmail]").setValue("nesti-1998@mail.ru");
        $("[id=userNumber]").setValue("8920012784");

        // Выбрать пол
        $(".custom-radio:nth-of-type(2) .custom-control-label").click(); // Изменен селектор на :nth-of-type(2)

        // Выбрать дату рождения
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("2006");
        $(".react-datepicker__day.react-datepicker__day--014").click();

        // Выборать хобби
        $("#hobbiesWrapper").$(byText("Sports")).click();

        // Загрузить файл
        $("#uploadPicture").uploadFromClasspath("Енот.jpg"); // Путь к файлу
        $("#currentAddress").setValue("г.Нижний Новгород"); //написание адреса
        //выбор State and City
        $("#state").click();
        $("#state").$(byText("NCR")).click();
        $("#city").click();
        $("#city").$(byText("Delhi")).click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        //Отправка формы
        $("#submit").click();

        //Проверки формы
        $(".table-responsive").$(byText("Student Name")).sibling(0).shouldHave(text("Anastasia Lyuleva"));
        $(".table-responsive").$(byText("Student Email")).sibling(0).shouldHave(text("nesti-1998@mail.ru"));
        $(".table-responsive").$(byText("Gender")).sibling(0).shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).sibling(0).shouldHave(text("8920012784"));
        $(".table-responsive").$(byText("Date of Birth")).sibling(0).shouldHave(text("14 February,2006"));
        $(".table-responsive").$(byText("Subjects")).sibling(0).shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).sibling(0).shouldHave(text("Sports"));
        $(".table-responsive").$(byText("Picture")).sibling(0).shouldHave(text("Енот.jpg"));
        $(".table-responsive").$(byText("Address")).sibling(0).shouldHave(text("г.Нижний Новгород"));
        $(".table-responsive").$(byText("State and City")).sibling(0).shouldHave(text("NCR Delhi"));
    }

}

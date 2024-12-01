import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DemoqaTests {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize= "1920x1080";
        Configuration.holdBrowserOpen = true;
    }
    @Test
     void successfulSearchTest() {
        // Открыть страницу
        open("https://demoqa.com/automation-practice-form");

        // Заполнить форму
        $("[id=firstName]").setValue("Anastasia");
        $("[id=lastName]").setValue("Lyuleva");
        $("[id=userEmail]").setValue("nesti-1998@mail.ru");
        $("[id=userNumber]").setValue("89200127845");

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
        File fileUpload = new File("E:\\Загрузки\\Енот.jpg"); // Путь к файлу
        $("[id=uploadPicture]").uploadFile(fileUpload); // Использование переменной fileUpload
        $("#currentAddress").setValue("г.Нижний Новгород"); //написание адреса
        //выбор State and City
        $("#state").click();
        $("#state").$(byText("NCR")).click();
        $("#city").click();
        $("#city").$(byText("Delhi")).click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        Configuration.timeout = Long.parseLong("2000");
        $("#submit").click();
    }}

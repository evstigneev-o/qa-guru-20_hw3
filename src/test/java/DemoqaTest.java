import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class DemoqaTest {
    File studentImage = new File("src/test/resources/penny.jpeg");
    String firstName = "Test";
    String lastName = "Testov";
    String email = "test@testov.com";
    String gender = "Male";
    String dayOfBirth = "17";
    String monthOfBirth = "July";
    String yearOfBirth = "1995";
    String mobile = "8005553535";
    String subjects = "Computer Science";
    String hobby = "Sports";
    String currentAddress = "Moscow, 16 Pushkina street";
    String state = "NCR";
    String city = "Delhi";
    String submittingText = "Thanks for submitting the form";



    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.headless = true;
    }

    @Test
    public void checkStudentRegistrationForm() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirth-wrapper").$("#dateOfBirthInput").click();
        $("#dateOfBirth-wrapper").$(".react-datepicker__month-select").click();
        $("#dateOfBirth-wrapper").$(byText(monthOfBirth)).click();
        $("#dateOfBirth-wrapper").$(".react-datepicker__year-select").click();
        $("#dateOfBirth-wrapper").$(byText(yearOfBirth)).click();
        $("#dateOfBirth-wrapper").$(".react-datepicker__day--0" + dayOfBirth).click();
        $("#subjectsInput").setValue(subjects).press(Keys.DOWN).press(Keys.ENTER);
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#uploadPicture").uploadFile(studentImage);
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        $(byText(submittingText)).shouldBe(Condition.visible);
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(String.format("%s %s",firstName,lastName)));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(mobile));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(String.format("%s %s,%s",dayOfBirth,monthOfBirth,yearOfBirth)));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subjects));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobby));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(studentImage.getName()));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(String.format("%s %s", state,city)));
    }

}

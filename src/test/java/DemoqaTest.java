import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;


public class DemoqaTest {
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
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    public void checkStudentRegistrationForm() {
        open("https://demoqa.com/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText(monthOfBirth)).click();
        $(".react-datepicker__year-select").click();
        $(byText(yearOfBirth)).click();
        $(".react-datepicker__day--0" + dayOfBirth).click();
        $("#subjectsInput").setValue(subjects).press(Keys.DOWN).press(Keys.ENTER);
        $(byText(hobby)).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/penny.jpeg"));
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();

        $(byText(submittingText)).shouldBe(Condition.visible);
        $(byText(String.format("%s %s",firstName,lastName))).shouldBe(Condition.visible);
        $(byText(email)).shouldBe(Condition.visible);
        $(byText(gender)).shouldBe(Condition.visible);
        $(byText(mobile)).shouldBe(Condition.visible);
        $(byText(String.format("%s %s,%s",dayOfBirth,monthOfBirth,yearOfBirth))).shouldBe(Condition.visible);
        $(byText(subjects)).shouldBe(Condition.visible);
        $(byText(hobby)).shouldBe(Condition.visible);
        $(byText("penny.jpeg")).shouldBe(Condition.visible);
        $(byText(currentAddress)).shouldBe(Condition.visible);
        $(byText(String.format("%s %s", state,city))).shouldBe(Condition.visible);
    }

}

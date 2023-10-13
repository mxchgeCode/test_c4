import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


public class AllureSimpleTests {
    private static final String REPOSITORY = "https://github.com/allure-framework/allure2";

    private static final String STORY = "Поиск в репозитории Allure";
    private static final String FEATURE ="Поиск в репозитории";

    private static final String URL2 = "https://github.com/search?q=allure-framework&type=wikis";
    private static final String ISSUES = "How to add a new column to Allure CSV metadata?";

    @BeforeEach
    void enableAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @Feature(FEATURE)
    @Story(STORY)
    @Link(value = "Allure Wiki", url = URL2)


    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Проверка Issue в репозитории Allure с Listener")
    public void checkNameIssueTest() {
        open(REPOSITORY);
        $("#issues-tab").click();
        System.out.println(linkText(REPOSITORY));
        $(withText(ISSUES)).should(Condition.exist);
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @DisplayName("Проверка Issue в репозитории с лямбда шагами через step")
    public void checkNameIssueLambdaTest() {
        step("Открываем репозиторий", () -> open(REPOSITORY));
        step("Открываем таб Issues", () -> $("#issues-tab").click());
        step("Проверяем наличие на странице Issues: " + ISSUES, () -> $(withText(ISSUES)).should(Condition.exist));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Проверка Issue в репозитории Allure шагами с аннотацией Step")
    public void checkNameIssueWithAnnotatedStepTest() {
        Steps steps = new Steps();
        steps.openMainPage(REPOSITORY);
        steps.openTabIssues();
        steps.checkNameIssues(ISSUES);
    }
}
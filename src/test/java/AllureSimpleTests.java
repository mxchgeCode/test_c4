import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class AllureSimpleTests {
    private static final String REPOSITORY = "junit-team/junit5";
    private static final String URL = "https://github.com/";
    private static final String LOCATOR = ".search-input-container";
    private static final String SEARCH = "#query-builder-test";
    private static final String ISSUES = "Remove or relax the new rule for strict String to Boolean conversion";

    @BeforeEach
    void enableAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @Feature("Поиск в репозитории")
    @Story("Поиск в репозитории junit5")
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "Wiki", url = "https://github.com/junit-team/junit5/wiki")
    @DisplayName("Проверка Issue в репозитории junit5 с Listener")
    public void checkNameIssue() {
        open(URL);
        $(LOCATOR).click();
        $(SEARCH).setValue(REPOSITORY).pressEnter();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $(withText(ISSUES)).should(Condition.exist);
    }

    @Test
    @Feature("Поиск в репозитории")
    @Story("Поиск в репозитории junit5")
    @Severity(SeverityLevel.MINOR)
    @Link(value = "Wiki", url = "https://github.com/junit-team/junit5/wiki")
    @DisplayName("Проверка Issue в репозитории " + REPOSITORY + " с лямбда шагами через step")
    public void checkNameIssueLambda() {
        step("Открываем главную страницу", () -> open(URL));
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(LOCATOR).click();
            $(SEARCH).setValue(REPOSITORY).pressEnter();
        });
        step("Открываем репозиторий " + REPOSITORY, () -> $(linkText(REPOSITORY)).click());
        step("Открываем таб Issues", () -> $("#issues-tab").click());
        step("Проверяем наличие на странице Issues: " + ISSUES, () -> $(withText(ISSUES)).should(Condition.exist));
    }

    @Test
    @Feature("Поиск в репозитории")
    @Story("Поиск в репозитории junit5")
    @Severity(SeverityLevel.NORMAL)
    @Link(value = "Wiki", url = "https://github.com/junit-team/junit5/wiki")
    @DisplayName("Проверка Issue в репозитории junit5 шагами с аннотацией @Step")
    public void checkNameIssueWithAnnotatedStep() {
        Steps steps = new Steps();
        steps.openMainPage();
        steps.searchRepository(REPOSITORY, LOCATOR, SEARCH);
        steps.openRepository(REPOSITORY);
        steps.openTabIssues();
        steps.checkNameIssues(ISSUES);
    }

}
import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class Steps {

    @Step("Открываем репозиторий")
    public void openMainPage(String repo) {
        open(repo);
    }

    @Step("Открываем таб Issues")
    public void openTabIssues() {
        $("#issues-tab").click();
    }

    @Step("Проверяем наличие на странице Issues: {issue}")
    public void checkNameIssues(String issue) {
        $(withText(issue)).should(Condition.exist);
    }
}
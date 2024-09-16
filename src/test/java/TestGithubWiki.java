import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class TestGithubWiki {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 5000;
//        Configuration.holdBrowserOpen = true;
    }

    @Test
    void CheckSoftAssertions() {
        open("/selenide/selenide");

        String junitFiveCodeExample = """
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }""";

        $("#wiki-tab").click();
        $(byTagAndText("button", "Show 3 more pages…")).click();
        $(byTagAndText("a", "SoftAssertions")).shouldBe(visible);
        $(byTagAndText("a", "SoftAssertions")).click();
        $(byTagAndText("h1", "SoftAssertions")).shouldBe(visible);
        $$(".highlight-source-java").shouldHave(itemWithText(junitFiveCodeExample));
    }
}

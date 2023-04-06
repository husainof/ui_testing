package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigProvider;

import java.util.ArrayList;
import java.util.List;

public class CustomersPage {

    private WebDriver driver;

    @FindBy(xpath = "//input[contains(@ng-model, 'searchCustomer')]")
    private WebElement searchField;

    @FindBy(xpath = "//a[contains(@ng-click, 'fName')]")
    private WebElement firstNameFilter;

    @FindBy(xpath = "//td[@class=\"ng-binding\"][1]")
    private WebElement firstName;

    @FindBy(xpath = "//td[@class=\"ng-binding\"][2]")
    private WebElement lastName;

    @FindBy(xpath = "//td[@class=\"ng-binding\"][3]")
    private WebElement postCode;

    @FindBy(xpath = "//td[@class=\"ng-binding\"][1]")
    private List<WebElement> firstNamesElements;

    public CustomersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод post code в инпут для поиска пользователя")
    public CustomersPage findCustomerByPostCode(String postCode) {
        searchField.sendKeys(postCode);
        return this;
    }

    public String getPostCode() {
        return postCode.getText();
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public List<String> getListOfFirstNames() {
        List<String> firstNamesList = new ArrayList<String>();

        for (WebElement firstNamesElement: firstNamesElements) {
            firstNamesList.add(firstNamesElement.getText());
        }
        return firstNamesList;
    }

    @Step("Кликнуть на кнопку сортировки")
    public CustomersPage clickToNameSortFilter() {
        firstNameFilter.click();
        return this;
    }

    // Кликаем 2 раза по лейблу для того, чтобы включилась сортировка по возрастанию
    public CustomersPage setAscendingSort() {
        clickToNameSortFilter();
        clickToNameSortFilter();
        return this;
    }

    @Step("Открыть страницу Customers")
    public CustomersPage openPage() {
        driver.get(ConfigProvider.readConfig().getString("urls.customersPage"));
        return this;
    }
}

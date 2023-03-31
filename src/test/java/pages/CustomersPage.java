package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigProvider;

import java.util.ArrayList;
import java.util.List;

public class CustomersPage extends BasePage {
    @FindBy(xpath = "//input[contains(@ng-model, 'searchCustomer')]")
    private WebElement searchField;
    @FindBy(xpath = "//a[contains(@ng-click, 'fName')]")
    private WebElement firstNameFilter;
    private WebElement firstName;
    private WebElement lastName;
    private WebElement postCode;
    public CustomersPage() {
        PageFactory.initElements(driver, this);
    }
    @Step("Ввод данные в строку поиска")
    public CustomersPage findCustomerByPostCode(String postCode) {
        searchField.sendKeys(postCode);
        this.firstName = driver.findElement(By.xpath("//td[@class=\"ng-binding\"][1]"));
        this.lastName = driver.findElement(By.xpath("//td[@class=\"ng-binding\"][2]"));
        this.postCode = driver.findElement(By.xpath("//td[@class=\"ng-binding\"][3]"));

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
        List<WebElement> webElements = driver.findElements(By.xpath("//td[@class=\"ng-binding\"][1]"));
        List<String> firstNamesList = new ArrayList<String>();
        for (WebElement el: webElements
             ) {
            firstNamesList.add(el.getText());
        }
        return firstNamesList;

    }
    @Step("Кликнуть на кнопку сортировки")
    public CustomersPage clickToNameSortFilter() {
        firstNameFilter.click();
        return this;
    }
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

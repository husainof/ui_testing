package pages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigProvider;

public class AddCustomerPage extends BasePage {
    @FindBy(xpath = "//input[contains(@ng-model, 'fName')]")
    private WebElement firstNameField;
    @FindBy(xpath = "//input[contains(@ng-model, 'lName')]")
    private WebElement lastNameField;
    @FindBy(xpath = "//input[contains(@ng-model, 'postCd')]")
    private WebElement postCodeField;
    @FindBy(xpath = "//button[text() = 'Add Customer' and contains(@type, 'submit')]")
    private WebElement btnAddCustomer;
    @FindBy(xpath = "//button[contains(text(), 'Customers')]")
    private WebElement customersBtn;
    public AddCustomerPage() {
        PageFactory.initElements(driver, this);
    }
    @Step("Открыть страницу AddCustomer")
    public AddCustomerPage openPage() {
        driver.get(ConfigProvider.readConfig().getString("urls.addCustomerPage"));
        return this;
    }
    @Step("Заполнить поле first name")
    public void fillFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }
    @Step("Заполнить поле last name")
    public void fillLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }
    @Step("Заполнить поле  post code")
    public void fillPostCode(String postCode) {
        postCodeField.sendKeys(postCode);
    }
    @Step("Кликнуть на кнопку создания клиента")
    public void clickAddCustomer() {
        btnAddCustomer.click();
    }
    public AddCustomerPage addCustomer(String firstName, String lastName, String postCode) {
        fillFirstName(firstName);
        fillLastName(lastName);
        fillPostCode(postCode);
        clickAddCustomer();
        driver.switchTo().alert().accept();
        return this;
    }
    @Step("Переход на страницу Customers")
    public CustomersPage openCustomersPage() {
        customersBtn.click();
        return new CustomersPage();
    }

}

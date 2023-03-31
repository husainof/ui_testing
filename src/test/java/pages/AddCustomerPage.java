package pages;

import core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigProvider;

public class AddCustomerPage extends BasePage {
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/form/div[1]/input")
    private WebElement fieldFirstName;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/form/div[2]/input")
    private WebElement fieldLastName;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/form/div[3]/input")
    private WebElement fieldPostCode;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/form/button")
    private WebElement btnAddCustomer;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[1]/button[3]")
    private WebElement customersBtn;
    public AddCustomerPage() {
        PageFactory.initElements(driver, this);
    }
    public AddCustomerPage openPage() {
        driver.get(ConfigProvider.readConfig().getString("urls.addCustomerPage"));
        return this;
    }
    public void fillFirstName(String firstName) {
        fieldFirstName.sendKeys(firstName);
    }
    public void fillLastName(String lastName) {
        fieldLastName.sendKeys(lastName);
    }
    public void fillPostCode(String postCode) {
        fieldPostCode.sendKeys(postCode);
    }
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
    public CustomersPage openCustomersPage() {
        customersBtn.click();
        return new CustomersPage();
    }

}

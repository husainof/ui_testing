package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ConfigProvider;

import java.util.ArrayList;
import java.util.List;

public class CustomersPage extends BasePage {
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/form/div/div/input")
    private WebElement searchField;
    @FindBy(xpath = "/html/body/div/div/div[2]/div/div[2]/div/div/table/thead/tr/td[1]/a")
    private WebElement firstNameFilter;
    private WebElement firstName;
    private WebElement lastName;
    private WebElement postCode;
    public CustomersPage() {
        PageFactory.initElements(driver, this);
    }
    public CustomersPage findCustomerByPostCode(String postCode) {
        searchField.sendKeys(postCode);
        this.firstName = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]"));
        this.lastName = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[2]"));
        this.postCode = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[3]"));

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
        List<WebElement> webElements = driver.findElements(By.xpath("/html/body/div/div/div[2]/div/div[2]/div/div/table/tbody/tr/td[1]"));
        List<String> firstNamesList = new ArrayList<String>();
        for (WebElement el: webElements
             ) {
            firstNamesList.add(el.getText());
        }
        return firstNamesList;

    }
    public CustomersPage clickToNameSortFilter() {
        firstNameFilter.click();
        return this;
    }
    public CustomersPage setAscendingSort() {
        clickToNameSortFilter();
        clickToNameSortFilter();
        return this;
    }
    public CustomersPage openPage() {
        driver.get(ConfigProvider.readConfig().getString("urls.customersPage"));
        return this;
    }
}

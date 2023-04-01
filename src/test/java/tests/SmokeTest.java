package tests;

import core.BaseParallelTest;
import core.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AddCustomerPage;
import pages.CustomersPage;
import utils.ConfigProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Epic("Smoke test")
public class SmokeTest extends BaseParallelTest {
    @Test
    @Description("Проверка создания клиента")
    public void checkCreatingCustomer() {
        String testFirstName = ConfigProvider.readConfig().getString("testCustomer.firstName");
        String testLastName = ConfigProvider.readConfig().getString("testCustomer.lastName");
        String testPostCode = ConfigProvider.readConfig().getString("testCustomer.postCode");

        CustomersPage customersPage =  new AddCustomerPage(getDriver())
                .openPage()
                .addCustomer(testFirstName, testLastName, testPostCode)
                .openCustomersPage()
                .findCustomerByPostCode(testPostCode);

        Assert.assertEquals(customersPage.getFirstName(), testFirstName);
        Assert.assertEquals(customersPage.getLastName(), testLastName);
        Assert.assertEquals(customersPage.getPostCode(), testPostCode);
    }

    @Test
    @Description("Проверка сортировки клиентов по имени")
    public void checkSortingByFirstName() {
        List<String> firstNames =  new CustomersPage(getDriver())
                .openPage()
                .setAscendingSort()
                .getListOfFirstNames();
        List<String> firstNamesSorted =  new ArrayList<String>();
        for(String el : firstNames) {
            firstNamesSorted.add(el);
        }
        Collections.sort(firstNamesSorted);

        Assert.assertEquals(firstNames, firstNamesSorted, "Список имён клиентов не отсортирован");
    }
}

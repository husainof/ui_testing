package tests;

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

@Epic("Smoke tests")
public class SmokeTest extends BaseTest {
    @Test
    @Description("Проверка создания клиента")
    public void createCustomer() {
        String testFirstName = ConfigProvider.readConfig().getString("testCustomer.firstName");
        String testLastName = ConfigProvider.readConfig().getString("testCustomer.lastName");
        String testPostCode = ConfigProvider.readConfig().getString("testCustomer.postCode");

        CustomersPage customersPage =  new AddCustomerPage()
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
    public void sortByFirstName() {
        List<String> firstNames =  new CustomersPage()
                .openPage()
                .setAscendingSort()
                .getListOfFirstNames();
        List<String> firstNamesSorted =  new ArrayList<String>();
        for(String el : firstNames) {
            firstNamesSorted.add(el);
        }
        Collections.sort(firstNamesSorted);

        Assert.assertEquals(firstNames, firstNamesSorted);
    }
}

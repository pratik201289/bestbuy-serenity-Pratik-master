package com.bestbuy.crudtests;

import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresCRUDTest extends TestBase {
    static String name = "Ambala" + TestUtils.getRandomValue();
    static String type = "HG" + TestUtils.getRandomValue();
    static String address = "1065 London Road";
    static String address2 = "Croydon, UK";
    static String city = "Croydon";
    static String state = "Surrey";
    static String zip = "CR7 6JG";
    static double lat = 44.969658;
    static double lng = -93.449539;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9;Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeID;
    @Steps
    StoresSteps storesSteps;

    @Title("This test will create a new Store")
    @Test
    public void test001(){
        HashMap<Object, Object> servicesData = new HashMap<>();
        ValidatableResponse response = storesSteps.createStore(name,type,address,address2,city,state,zip,lat,lng,hours,servicesData);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
    }

    @Title("This test will verify Store is added")
    @Test
    public void test002(){
        HashMap<String, ?> storeMap = storesSteps.getStoreInfoById(storeID);
        Assert.assertThat(storeMap, hasValue(name));
        System.out.println(storeMap);
    }

    @Title("This test will Update the stores information")
    @Test
    public void test003() {
        name = name + "_updated";
        storesSteps.updateStore(storeID,name);
        HashMap<String, ?> storesList = storesSteps.getStoreInfoById(storeID);
        Assert.assertThat(storesList, hasValue(name));
        System.out.println(storesList);
    }

    @Title("This test will Delete the stores by ID")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeID).statusCode(200).log().all();
        storesSteps.getStoreByID(storeID).statusCode(404).log().all();
    }

}

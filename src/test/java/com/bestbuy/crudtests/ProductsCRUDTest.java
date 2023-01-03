package com.bestbuy.crudtests;

import com.bestbuy.steps.ProductsSteps;
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
public class ProductsCRUDTest extends TestBase {

    static String name = "Durallcell_Batteries" + TestUtils.getRandomValue();
    static String type = "Dura";
    static int price = 4;
    static Integer shipping = 12;
    static String upc = "041333424019";
    static String description = "Long lasting energy";
    static String manufacturer = "DualcellBattery";
    static String model = "MAKE2022";
    static String url = "durallcell.com";
    static String image = "https://media.digikey.com/photos/Duracell-Industrial-Operations/MFG_AAA-MN2400.jpg";
    static int productID;

    @Steps
    ProductsSteps productsSteps;

    @Title("This test will Create a new Product")
    @Test
    public void test001(){
        ValidatableResponse response = productsSteps.createANewProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productID = response.log().all().extract().path("id");
    }

    @Title("This test will verify product was added")
    @Test
    public void test002(){
        HashMap<String, Object> productMap =  productsSteps.getProductInfoById(productID);
        Assert.assertThat(productMap, hasValue(name));
    }

    @Title("This test will Update the product information")
    @Test
    public void test003() {
        name = name + "_updated";
        productsSteps.updateProduct(productID,name).statusCode(200).log().all();
        HashMap<String,Object>  productMapData = productsSteps.getProductInfoById(productID);
        Assert.assertThat(productMapData,hasValue(name));
    }

    @Title("This test will Delete product details")
    @Test
    public void test004() {
        productsSteps.deleteSingleProduct(productID).statusCode(200).log().all();

    }

}

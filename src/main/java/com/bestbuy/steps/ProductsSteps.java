package com.bestbuy.steps;

import com.bestbuy.constants.Endpoints;
import com.bestbuy.model.ProductsPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class ProductsSteps {

    @Step
    public ValidatableResponse getAllProducts() {
        return SerenityRest.given().log().all()
                .when()
                .get(Endpoints.GET_ALL_PRODUCTS)
                .then();
    }

    @Step("Creating product with name :{0}, type : {1}, price :{2}, shipping:{3},upc:{4},description:{5},manufacturer:{6},model:{7},url:{8},image{9}")
    public ValidatableResponse createANewProduct(String name, String type, int price, int shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        ProductsPojo productPojo = new ProductsPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post(Endpoints.CREATE_PRODUCT)
                .then();
    }

    @Step("Get product details of id : {0}")
    public HashMap<String, Object> getProductInfoById(int productID){
        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .when()
                .pathParams("productID", productID)
                .get(Endpoints.GET_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return productMap;

    }

    @Step("Update product details of id: {0}")
    public ValidatableResponse updateProduct(int productID, String name) {
        ProductsPojo productPojo = new ProductsPojo();
        productPojo.setName(name);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .pathParam("productID", productID)
                .when()
                .patch(Endpoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }

    @Step("Delete product details by id: {0}")
    public ValidatableResponse deleteSingleProduct(int productID) {
        return SerenityRest.given().log().all()
                .pathParam("productID", productID)
                .when()
                .delete(Endpoints.GET_PRODUCT_BY_ID)
                .then();
    }

    @Step("Deleting Store with ID {0}")
    public ValidatableResponse deleteStore(int storeID){
        return SerenityRest.given().log().all()
                .pathParam("storeID", storeID)
                .when()
                .delete(Endpoints.DELETE_SINGLE_STORES_BY_ID)
                .then();
    }
}



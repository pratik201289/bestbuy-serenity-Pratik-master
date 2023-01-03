package com.bestbuy.constants;

public class Endpoints {

    /**
     * This is Endpoints of Products api
     */

    public static final String GET_ALL_PRODUCTS = "/products";
    public static final String CREATE_PRODUCT = "/products";
    public static final String GET_PRODUCT_BY_ID = "/products/{productID}";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productID}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{productID}";

    /**
     * This is Endpoints of  Stores api
     */

    public static final String GET_ALL_STORES = "/stores";
    public static final String CREATE_STORES = "/stores";
    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{storeID}";
    public static final String UPDATE_SINGLE_STORES_BY_ID = "/stores/{storeID}";
    public static final String DELETE_SINGLE_STORES_BY_ID = "/stores/{storeID}";

}

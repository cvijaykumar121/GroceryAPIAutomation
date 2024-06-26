package com.api.Endpoints;

public class Routes {

    public static String BASE_URL  = "https://simple-grocery-store-api.glitch.me";

    public static String CREATE_TOKEN = "/auth";

    //API status endpoints
    public static String API_STATUS = "/status";

    //Product endpoints
    public static String GET_ALL_PRODUCTS = "/products";
    public static String GET_SINGLE_PRODUCT = "/products/{productId}";

    //Cart endpoints
    public static String CREATE_NEW_CART = "/carts";
    public static String GET_A_CART = "/carts/{cartId}";
    public static String ADD_ITEM_TO_CART = "/carts/{cartId}/items";
    public static String GET_CART_ITEMS = "/carts/{cartId}/items";
    public static String MODIFY_ITEM_IN_CART = "/carts/{cartId}/items/{itemId}";
    public static String REPLACE_PRODUCT_IN_CART = "/carts/{cartId}/items/{itemId}";

    //Order endpoints
    public static String CREATE_ORDER = "/orders";
    public static String GET_ALL_ORDERS = "/orders";
    public static String GET_SINGLE_ORDER = "/orders/{orderId}";
    public static String UPDATE_ORDER = "/orders/{orderId}";
    public static String DELETE_ORDER = "/orders/{orderId}";
}

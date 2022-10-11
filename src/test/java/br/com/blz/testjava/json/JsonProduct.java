package br.com.blz.testjava.json;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonProduct {

    public static final JSONObject ok;
    public static final JSONObject notFound;
    public static final JSONObject alreadyExist;
    public static final JSONObject update;

    static {
        try {
            notFound = new JSONObject("{\"s\":1,\"name\":\"L'oreal\",\"isMarketable\":true,\"inventory\":{\"quantity\":3,\"warehouses\":[{\"locality\":\"SP\",\"quantity\":2,\"type\":\"ECOMMERCE\"},{\"locality\":\"SP\",\"quantity\":1,\"type\":\"PHYSICAL_STORE\"}]}}");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            ok = new JSONObject("{\"sku\":1560,\"name\":\"L'oreal\",\"isMarketable\":true,\"inventory\":{\"quantity\":3,\"warehouses\":[{\"locality\":\"SP\",\"quantity\":2,\"type\":\"ECOMMERCE\"},{\"locality\":\"SP\",\"quantity\":1,\"type\":\"PHYSICAL_STORE\"}]}}");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            alreadyExist = new JSONObject("{\"sku\":1564,\"name\":\"L'oreal\",\"isMarketable\":true,\"inventory\":{\"quantity\":3,\"warehouses\":[{\"locality\":\"SP\",\"quantity\":2,\"type\":\"ECOMMERCE\"},{\"locality\":\"SP\",\"quantity\":1,\"type\":\"PHYSICAL_STORE\"}]}}");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        try {
            update = new JSONObject("{\"sku\":1564,\"name\":\"Pantene\",\"isMarketable\":true,\"inventory\":{\"quantity\":3,\"warehouses\":[{\"locality\":\"SP\",\"quantity\":2,\"type\":\"ECOMMERCE\"},{\"locality\":\"SP\",\"quantity\":1,\"type\":\"PHYSICAL_STORE\"}]}}");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}

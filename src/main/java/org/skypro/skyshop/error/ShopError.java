package org.skypro.skyshop.error;

public final class ShopError {
    private String code;
    private String message;

    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }



    public String getCode() {
        return code;
    }

}

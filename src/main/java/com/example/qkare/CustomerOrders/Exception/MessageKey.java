package com.example.qkare.CustomerOrders.Exception;

public enum MessageKey {

    USER_NOT_FOUND("error.resource.not.found.user"),
    USER_ALREADY_EXISTS("error.already.exists.user"),
    ORDER_NOT_FOUND("error.resource.not.found.order"),
    PRODUCT_NOT_FOUND("error.resource.not.found.product"),
    ORDER_ALREADY_CANCELED("error.already.already.canceled"),
    NOT_ENOUGH_STOCK("error.resource.not.enough.stock"),
    INVALID_ATTRIBUTE_FOR_CATEGORY("error.resource.invalid.attribute.for.category"),
    UNKNOWN_ATTRIBUTE("error.resource.unknown.attribute"),
    ROLE_ALREADY_EXISTS("error.resource.already.add.role"),
    ROLE_NOT_FOUND("error.resource.not.found.role"),
    EMAIL_REGEX_EXCEPTION("error.resource.email.regex.exception"),
    FAV_LIST_COULD_NOT_CREATE("error.resource.favlist.could.not.create");

    private final String key;
    MessageKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}

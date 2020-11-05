package by.yurovski.enums;

public enum UserPermisson {

    DELETE_PRODUCT("product:delete"),
    ADD_PRODUCT("product:add"),
    EDIT_PRODUCT("product:edit"),
    READ_PRODUCT_LIST("product:read");

    private final String permission;

    UserPermisson(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

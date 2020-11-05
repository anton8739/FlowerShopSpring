package by.yurovski.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    ADMIN(Set.of(UserPermisson.ADD_PRODUCT,UserPermisson.DELETE_PRODUCT,UserPermisson.EDIT_PRODUCT,UserPermisson.READ_PRODUCT_LIST)),
    MODERATOR(Set.of(UserPermisson.READ_PRODUCT_LIST)),

    CLIENT(Set.of(UserPermisson.READ_PRODUCT_LIST));

    private final Set<UserPermisson> permissions;

    UserRole(Set<UserPermisson> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermisson> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}

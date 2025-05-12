package com.accounting_system.accounting_system_backend.config;

import com.accounting_system.accounting_system_backend.entities.User;

public class SecurityContextHolder {
    private static final ThreadLocal<User> currentUser = new ThreadLocal<>();

    public static User getCurrentUser() {
        return currentUser.get();
    }

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }

    public static void clear() {
        currentUser.remove();
    }
}

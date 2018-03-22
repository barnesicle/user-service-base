package com.barnesicle.user.request;

import javax.validation.constraints.NotNull;

public class AccountPasswordChange {

    @NotNull
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountPasswordChange{" +
                "password='" + password + '\'' +
                '}';
    }
}

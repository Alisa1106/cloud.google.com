package org.cloud_google.utils;

public enum SupportedEnv {

    PROD("prod"),
    QA("qa");

    String name;

    SupportedEnv(String name) {
        this.name = name;
    }
}
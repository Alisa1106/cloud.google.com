package org.cloud_google.utils;

public class EnvironmentHandler {

    private static final String MY_ENV = "MY_ENV";
    public static final String DEFAULT_VALUE = "prod";

    public static String getEnv() {
        String env = System.getenv().getOrDefault(MY_ENV, DEFAULT_VALUE);
        switch (env) {
            case "qa":
                return SupportedEnv.QA.name();
            default:
                return SupportedEnv.PROD.name();
        }
    }
}
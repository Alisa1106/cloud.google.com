package org.cloud_google.utils;

import lombok.Data;

@Data
public class Properties {

    private String browser;
    private String numberOfInstances;
    private String operatingSystem;
    private String virtualMachineClass;
    private String series;
    private String instanceType;
    private String numberOfGpu;
    private String gpuType;
    private String localSsd;
    private String datacenterLocation;
    private String commitedUsage;
    String env = EnvironmentHandler.getEnv();

    public Properties() {
        java.util.Properties commonProps = PropertyReader.readProperties("common");
        java.util.Properties computeEngineProperties = PropertyReader.readProperties(env);
        browser = System.getProperty("browser", commonProps.getProperty("browser"));
        numberOfInstances = computeEngineProperties.getProperty("number.of.instances");
        operatingSystem = computeEngineProperties.getProperty("operating.system");
        virtualMachineClass = computeEngineProperties.getProperty("virtual.machine.class");
        series = computeEngineProperties.getProperty("series");
        instanceType = computeEngineProperties.getProperty("instance.type");
        numberOfGpu = computeEngineProperties.getProperty("number.of.gpu");
        gpuType = computeEngineProperties.getProperty("gpu.type");
        localSsd = computeEngineProperties.getProperty("local.ssd");
        datacenterLocation = computeEngineProperties.getProperty("datacenter.location");
        commitedUsage = computeEngineProperties.getProperty("committed.usage");
    }
}
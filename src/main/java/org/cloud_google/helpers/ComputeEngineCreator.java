package org.cloud_google.helpers;

import org.cloud_google.models.ComputeEngine;
import org.cloud_google.utils.EnvironmentHandler;
import org.cloud_google.utils.PropertyReader;

import java.util.Properties;

public class ComputeEngineCreator {

    String env = EnvironmentHandler.getEnv();
    Properties computeEngineProperties = PropertyReader.readProperties(env);

    public ComputeEngine create() {
        return ComputeEngine.builder()
                .numberOfInstances(computeEngineProperties.getProperty("number.of.instances"))
                .operatingSystem(computeEngineProperties.getProperty("operating.system"))
                .virtualMachineClass(computeEngineProperties.getProperty("virtual.machine.class"))
                .series(computeEngineProperties.getProperty("series"))
                .instanceType(computeEngineProperties.getProperty("instance.type"))
                .numberOfGpu(computeEngineProperties.getProperty("number.of.gpu"))
                .gpuType(computeEngineProperties.getProperty("gpu.type"))
                .localSsd(computeEngineProperties.getProperty("local.ssd"))
                .datacenterLocation(computeEngineProperties.getProperty("datacenter.location"))
                .committedUsage(computeEngineProperties.getProperty("committed.usage"))
                .build();
    }
}
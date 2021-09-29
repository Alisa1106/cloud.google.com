package org.cloud_google.helpers;

import org.cloud_google.models.ComputeEngine;
import org.cloud_google.utils.Properties;

public class ComputeEngineCreator {

    static Properties properties = new org.cloud_google.utils.Properties();

    public ComputeEngine create() {
        return ComputeEngine.builder()
                .numberOfInstances(properties.getNumberOfInstances())
                .operatingSystem(properties.getOperatingSystem())
                .virtualMachineClass(properties.getVirtualMachineClass())
                .series(properties.getSeries())
                .instanceType(properties.getInstanceType())
                .numberOfGpu(properties.getNumberOfGpu())
                .gpuType(properties.getGpuType())
                .localSsd(properties.getLocalSsd())
                .datacenterLocation(properties.getDatacenterLocation())
                .committedUsage(properties.getCommitedUsage())
                .build();
    }
}
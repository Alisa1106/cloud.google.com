package org.cloud_google.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ComputeEngine {
    private String numberOfInstances;
    private String operatingSystem;
    private String virtualMachineClass;
    private String series;
    private String instanceType;
    private String numberOfGpu;
    private String gpuType;
    private String localSsd;
    private String datacenterLocation;
    private String committedUsage;
}
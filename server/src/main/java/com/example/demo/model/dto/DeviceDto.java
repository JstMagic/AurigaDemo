package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

//{ “name”: "AurigaDC01", "agentVersion": "0.1.0", "howManyAlerts": 2, "architecture": "x64", "collector": "Test_Collector", "cpuModel": "Intel(R) Xeon(R) CPU E5-2673 v3 @ 2.40GHz", "description": "Auriga Test Device", "discoveryDate": "2019-05-21", "ipAddresses": ["1.0.0.4",”10.0.0.1”] }

public class DeviceDto {
    private String name = "";
    private String agentVersion;
    private List<String> alertIds;
    private String architecture;
    private Map<String, String> collector;
    private String cpuModel;
    private String description;
    private Date discoveryDate;
    private List<Map<String, String>> ipAddresses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAgentVersion() {
        return agentVersion;
    }

    public void setAgentVersion(String agentVersion) {
        this.agentVersion = agentVersion;
    }

    public List<String> getAlertIds() {
        return alertIds;
    }

    public void setAlertIds(List<String> alertIds) {
        this.alertIds = alertIds;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public Map<String, String> getCollector() {
        return collector;
    }

    public void setCollector(Map<String, String> collector) {
        this.collector = collector;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public Date getDiscoveryDate() {
        return discoveryDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    public void setDiscoveryDate(Date discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    public List<Map<String, String>> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(List<Map<String, String>> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    @Override
    public String toString() {
        return "device{" +
                "name='" + name + '\'' +
                ", agentVersion='" + agentVersion + '\'' +
                ", alertIds=" + alertIds +
                ", architecture='" + architecture + '\'' +
                ", collector=" + collector +
                ", cpuModel='" + cpuModel + '\'' +
                ", description='" + description + '\'' +
                ", discoveryDate=" + discoveryDate +
                ", ipAddresses=" + ipAddresses +
                '}';
    }
}


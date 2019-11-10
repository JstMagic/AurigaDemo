package com.example.demo.model.domains;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Device {
    private String name = "";
    private String agentVersion;
    private int howManyAlerts;
    private String architecture;
    private String collector;
    private String cpuModel;
    private String description;
    private Date discoveryDate;
    private List<String> ipAddresses;

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

    public int getHowManyAlerts() {
        return howManyAlerts;
    }

    public void setHowManyAlerts(int howManyAlerts) {
        this.howManyAlerts = howManyAlerts;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
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

    public Date getDiscoveryDate() {
        return discoveryDate;
    }

    public void setDiscoveryDate(Date discoveryDate) {
        this.discoveryDate = discoveryDate;
    }

    public List<String> getIpAddresses() {
        return ipAddresses;
    }

    public void setIpAddresses(List<String> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    @Override
    public String toString() {
        return "Device{" +
                "name='" + name + '\'' +
                ", agentVersion='" + agentVersion + '\'' +
                ", howManyAlerts=" + howManyAlerts +
                ", architecture='" + architecture + '\'' +
                ", collector='" + collector + '\'' +
                ", cpuModel='" + cpuModel + '\'' +
                ", description='" + description + '\'' +
                ", discoveryDate=" + discoveryDate +
                ", ipAddresses=" + ipAddresses +
                '}';
    }
}

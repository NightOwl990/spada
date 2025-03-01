package com.lgkk.base_project.base;

import lombok.Data;

@Data
public class BaseRequest {
    private String app_name;
    private String version;
    private String platform;
    private String device_id;
    private String os_version;
    private String model;
    private String lat;
    private String lng;
    private String current_city_id;
    private String manufacturer;
    private String uuid;
    private String android_device_id;

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getCurrent_city_id() {
        return current_city_id;
    }

    public void setCurrent_city_id(String current_city_id) {
        this.current_city_id = current_city_id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAndroid_device_id() {
        return android_device_id;
    }

    public void setAndroid_device_id(String android_device_id) {
        this.android_device_id = android_device_id;
    }
}

package com.workshop.workshopmongo.resources.exception;

public class StandardError {
    
    private Long timestamp;
    private Integer status;
    private String error;
    private String mesage;
    private String path;


    public StandardError(){

    }


    public StandardError(Long timestamp, Integer status, String error, String mesage, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.mesage = mesage;
        this.path = path;
    }


    public Long getTimestamp() {
        return timestamp;
    }


    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getError() {
        return error;
    }


    public void setError(String error) {
        this.error = error;
    }


    public String getMesage() {
        return mesage;
    }


    public void setMesage(String mesage) {
        this.mesage = mesage;
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }

    
}

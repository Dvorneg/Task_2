package com.denis.task2.util;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Response {
    private String path;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd MMMM yyyy HH:mm:ss z", timezone="Europe/Moscow")
    private Date data;

    private Long size; //byte

    public Response() {
    }

    public Response(String path, Date data, Long size) {
        this.path = path;
        this.data = data;
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}

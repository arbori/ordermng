package com.ordermng.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class Result {
    @JsonProperty("status")
    private Integer status = null;

    @JsonProperty("message")
    private String message = null;

    @JsonProperty("content")
    private Object content = null;

    public Result() {
    }

    public Result(Integer status, String message, Object content) {
        this.status = status;
        this.message = message;
        this.content = content;
    }

    public Result status(Integer status) {
        this.status = status;
        return this;
    }

    @Schema(example = "200", description = "")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Result message(String message) {
        this.message = message;
        return this;
    }

    @Schema(example = "OK", description = "")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result content(Object content) {
        this.content = content;
        return this;
    }

    @Schema(example = "{}", description = "")
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
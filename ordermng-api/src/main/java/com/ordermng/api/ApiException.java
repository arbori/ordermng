package com.ordermng.api;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-07-07T11:52:41.130101+01:00[Europe/Lisbon]")
public class ApiException extends Exception {
    private int code;

    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
}

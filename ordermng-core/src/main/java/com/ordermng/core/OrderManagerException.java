package com.ordermng.core;

public class OrderManagerException extends Exception {
    public OrderManagerException() {
    }
 
    public OrderManagerException(String var1) {
       super(var1);
    }
 
    public OrderManagerException(String var1, Throwable var2) {
       super(var1, var2);
    }
 
    public OrderManagerException(Throwable var1) {
       super(var1);
    }
 
    protected OrderManagerException(String var1, Throwable var2, boolean var3, boolean var4) {
       super(var1, var2, var3, var4);
    }
 }

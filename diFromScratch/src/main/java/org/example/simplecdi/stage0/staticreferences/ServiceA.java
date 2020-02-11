package org.example.simplecdi.stage0.staticreferences;

public class ServiceA {

    public static String jobA(){
        return "jobA(" + ServiceB.jobB() + ")";
    }

}

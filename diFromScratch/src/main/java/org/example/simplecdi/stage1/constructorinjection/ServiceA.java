package org.example.simplecdi.stage1.constructorinjection;

public class ServiceA {

    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB){
        this.serviceB = serviceB;
    }

    public String jobA(){
        return "jobA(" + this.serviceB.jobB() + ")";
    }

}

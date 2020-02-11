package org.example.simplecdi.stage2.constructorwithinterfaces;

public class ServiceAImpl implements ServiceA {

    private final ServiceB serviceB;

    public ServiceAImpl(ServiceB serviceB){
        this.serviceB = serviceB;
    }

    @Override
    public String jobA() {
        return "jobA(" + this.serviceB.jobB() + ")";
    }
}

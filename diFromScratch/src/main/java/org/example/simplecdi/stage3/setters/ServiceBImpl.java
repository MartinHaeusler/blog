package org.example.simplecdi.stage3.setters;

public class ServiceBImpl implements ServiceB {

    private ServiceA serviceA;

    @Override
    public ServiceA getServiceA() {
        return serviceA;
    }

    @Override
    public void setServiceA(final ServiceA serviceA) {
        this.serviceA = serviceA;
    }

    @Override
    public String jobB() {
        return "jobB()";
    }
}

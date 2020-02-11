package org.example.simplecdi.stage3.setters;

public class ServiceAImpl implements ServiceA {

    private ServiceB serviceB;

    @Override
    public ServiceB getServiceB() {
        return serviceB;
    }

    @Override
    public void setServiceB(final ServiceB serviceB) {
        this.serviceB = serviceB;
    }

    @Override
    public String jobA() {
        return "jobA(" + this.serviceB.jobB() + ")";
    }
}

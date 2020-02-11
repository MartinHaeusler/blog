package org.example.simplecdi.stage3.setters;

public class Main {

    public static void main(String[] args) {
        // create instances
        ServiceB serviceB = new ServiceBImpl();
        ServiceA serviceA = new ServiceAImpl();

        // wire them together
        serviceA.setServiceB(serviceB);
        serviceB.setServiceA(serviceA);

        // call business logic
        System.out.println(serviceA.jobA());
    }
}

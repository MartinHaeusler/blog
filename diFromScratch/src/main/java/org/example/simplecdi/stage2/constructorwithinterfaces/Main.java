package org.example.simplecdi.stage2.constructorwithinterfaces;

public class Main {

    public static void main(String[] args) {
        ServiceB serviceB = new ServiceBImpl();
        ServiceA serviceA = new ServiceAImpl(serviceB);
        System.out.println(serviceA.jobA());
    }
}

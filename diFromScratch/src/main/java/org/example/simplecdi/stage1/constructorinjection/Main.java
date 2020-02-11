package org.example.simplecdi.stage1.constructorinjection;

public class Main {

    public static void main(String[] args) {
        ServiceB serviceB = new ServiceB();
        ServiceA serviceA = new ServiceA(serviceB);

        System.out.println(serviceA.jobA());
    }
}

package org.example.simplecdi.stage7.classpathscan;

public class Main {

    public static void main(String[] args) throws Exception {
        DIContext context = createContext();
        doBusinessLogic(context);
    }


    private static DIContext createContext() throws Exception {
        String rootPackageName = Main.class.getPackage().getName();
        return DIContext.createContextForPackage(rootPackageName);
    }

    private static void doBusinessLogic(DIContext context){
        ServiceA serviceA = context.getServiceInstance(ServiceA.class);
        ServiceB serviceB = context.getServiceInstance(ServiceB.class);
        System.out.println(serviceA.jobA());
        System.out.println(serviceB.jobB());
    }

}

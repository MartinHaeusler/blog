package org.example.simplecdi.stage4.collectionofclasses;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        Set<Class<?>> serviceClasses = new HashSet<>();
        serviceClasses.add(ServiceAImpl.class);
        serviceClasses.add(ServiceBImpl.class);

        ServiceA serviceA = createServiceA(serviceClasses);

        // call business logic
        System.out.println(serviceA.jobA());
    }


    private static ServiceA createServiceA(Set<Class<?>> serviceClasses) throws Exception{
        // create an instance of each service class
        Set<Object> serviceInstances = new HashSet<>();
        for(Class<?> serviceClass : serviceClasses){
            Constructor<?> constructor = serviceClass.getConstructor();
            constructor.setAccessible(true);
            serviceInstances.add(constructor.newInstance());
        }
        // wire them together
        for(Object serviceInstance : serviceInstances){
            for(Field field : serviceInstance.getClass().getDeclaredFields()){
                Class<?> fieldType = field.getType();
                field.setAccessible(true);
                // find a suitable matching service instance
                for(Object matchPartner : serviceInstances){
                    if(fieldType.isInstance(matchPartner)){
                        field.set(serviceInstance, matchPartner);
                    }
                }
            }
        }
        // from all our service instances, find ServiceA
        for(Object serviceInstance : serviceInstances){
            if(serviceInstance instanceof ServiceA){
                return (ServiceA)serviceInstance;
            }
        }
        // we didn't find the requested service instance
        return null;
    }
}

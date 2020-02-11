package org.example.simplecdi.stage5.context;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DIContext {

    private final Set<Object> serviceInstances = new HashSet<>();

    public DIContext(Collection<Class<?>> serviceClasses) throws Exception {
        // create an instance of each service class
        for(Class<?> serviceClass : serviceClasses){
            Constructor<?> constructor = serviceClass.getConstructor();
            constructor.setAccessible(true);
            Object serviceInstance = constructor.newInstance();
            this.serviceInstances.add(serviceInstance);
        }
        // wire them together
        for(Object serviceInstance : this.serviceInstances){
            for(Field field : serviceInstance.getClass().getDeclaredFields()){
                Class<?> fieldType = field.getType();
                field.setAccessible(true);
                // find a suitable matching service instance
                for(Object matchPartner : this.serviceInstances){
                    if(fieldType.isInstance(matchPartner)){
                        field.set(serviceInstance, matchPartner);
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getServiceInstance(Class<T> serviceClass){
        for(Object serviceInstance : this.serviceInstances){
            if(serviceClass.isInstance(serviceInstance)){
                return (T)serviceInstance;
            }
        }
        return null;
    }

}

package org.example.simplecdi.stage7.classpathscan;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DIContext {

    private final Set<Object> serviceInstances = new HashSet<>();

    public static DIContext createContextForPackage(String rootPackageName) throws Exception {
        Set<Class<?>> allClassesInPackage = ClassPathScanner.getAllClassesInPackage(rootPackageName);
        Set<Class<?>> serviceClasses = new HashSet<>();
        for(Class<?> aClass : allClassesInPackage){
            if(aClass.isAnnotationPresent(Service.class)){
                serviceClasses.add(aClass);
            }
        }
        return new DIContext(serviceClasses);
    }


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
                if(!field.isAnnotationPresent(Inject.class)){
                    // this field is none of our business
                    continue;
                }
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

package org.example.simplecdi.stage7.classpathscan;

@Service
public class ServiceAImpl implements ServiceA {

    @Inject
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

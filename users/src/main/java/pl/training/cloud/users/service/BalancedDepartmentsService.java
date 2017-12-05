package pl.training.cloud.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.users.model.Department;

import java.util.List;
import java.util.Optional;

@Service
public class BalancedDepartmentsService implements DepartmentsService {

    private static final String DEPARTMENTS_MICROSERVICE = "departments-microservice";
    private static final String RESOURCE_NAME = "departments";

    private DiscoveryClient discoveryClient;

    @Autowired
    public BalancedDepartmentsService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Cacheable
    @Override
    public Optional<Department> getDepartmentById(Long id) {
        Optional<String> serviceUri = getServiceUri(id);
        if (!serviceUri.isPresent()) {
            return Optional.empty();
        }
        return getDepartment(serviceUri.get());
    }

    private Optional<Department> getDepartment(String serviceUri) {
       Department department = new RestTemplate()
               .getForObject(serviceUri, Department.class);
       return Optional.ofNullable(department);
    }

    private Optional<String> getServiceUri(Long departmentId) {
        List<ServiceInstance> serviceInstances = discoveryClient
                .getInstances(DEPARTMENTS_MICROSERVICE);
        if (serviceInstances.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(serviceInstances.get(0).getUri().toString()
                + RESOURCE_NAME + departmentId);
    }

}

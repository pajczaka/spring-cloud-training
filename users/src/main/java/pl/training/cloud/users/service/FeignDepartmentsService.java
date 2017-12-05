package pl.training.cloud.users.service;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.training.cloud.users.model.Department;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FeignDepartmentsService implements DepartmentsService {

    private FeignDepartmentsClient feignDepartmentsClient;

    @Autowired
    public FeignDepartmentsService(FeignDepartmentsClient feignDepartmentsClient) {
        this.feignDepartmentsClient = feignDepartmentsClient;
    }

    @Cacheable(value = "departments", unless = "#result == null")
    @Override
    public Optional<Department> getDepartmentById(Long id) {
        try {
            return Optional.of(feignDepartmentsClient.getDepartmentById(id));
        } catch (FeignException ex) {
            Logger.getLogger(getClass().getName()).log(Level.INFO, "### Fetching department failed");
        }
        return Optional.empty();
    }

}

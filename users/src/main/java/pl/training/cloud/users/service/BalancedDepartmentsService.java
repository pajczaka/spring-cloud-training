package pl.training.cloud.users.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import pl.training.cloud.users.dto.DepartmentDto;

import java.util.Optional;

@Service
@Log
@RequiredArgsConstructor
public class BalancedDepartmentsService implements DepartmentsService {

    private static final String RESOURCE_URI = "http://departments/departments/";

    @NonNull
    private RestTemplate restTemplate;

    @Cacheable(value = "departments", unless = "#result == null")
    @Override
    public Optional<String> getDepartmentName(Long departmentId) {
        try {
            DepartmentDto departmentDto = restTemplate.getForObject(RESOURCE_URI + departmentId, DepartmentDto.class);
            if (departmentDto != null) {
                log.info("Fetching department...");
                return Optional.of(departmentDto.getName());
            }
        } catch (HttpClientErrorException ex) {
            log.warning("Error fetching department with id: " + departmentId);
        }
        return Optional.empty();
    }

}

package pl.training.cloud.users.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import pl.training.cloud.users.dto.DepartmentDto;

import java.util.Optional;

@Primary
@Service
@Log
@RequiredArgsConstructor
public class FeignDepartmentsService implements DepartmentsService {

    @NonNull
    private FeignDepartmentsClient feignDepartmentsClient;

    @Override
    public Optional<String> getDepartmentName(Long departmentId) {
        try {
            DepartmentDto departmentDto = feignDepartmentsClient.getDepartment(departmentId);
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

package pl.training.cloud.departments.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import pl.training.cloud.departments.model.Department;
import pl.training.cloud.departments.repository.DepartmentsRepository;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class DepartmentsService {

    private static final String DEFAULT_DEPARTMENT_NAME = "MAIN";

    @NonNull
    private DepartmentsRepository departmentsRepository;

    @Notify
    public Department addDepartment(Department department) {
        departmentsRepository.saveAndFlush(department);
        return department;
    }

    @HystrixCommand(fallbackMethod = "getDepartmentByIdFallback",
            ignoreExceptions = DepartmentNotFoundException.class,
            commandProperties = @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"))
    public Department getDepartmentById(Long id) {
        //fakeDelay();
        throw new IllegalArgumentException();
//        return departmentsRepository.getById(id)
//                .orElseThrow(DepartmentNotFoundException::new);
    }

    public Department getDepartmentByIdFallback(Long id) {
        return new Department(DEFAULT_DEPARTMENT_NAME);
    }

    public void updateDepartment(Department department) {
        getDepartmentById(department.getId());
        departmentsRepository.save(department);
    }

    public void deleteDepartmentWithId(Long id) {
        departmentsRepository.deleteById(id);
    }

    private void fakeDelay() {
        Random random = new Random();
        if (random.nextBoolean()) {
            try {
                Thread.sleep(12_000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}

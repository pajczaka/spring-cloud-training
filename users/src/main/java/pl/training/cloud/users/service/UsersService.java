package pl.training.cloud.users.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.training.cloud.users.model.ResultPage;
import pl.training.cloud.users.model.User;
import pl.training.cloud.users.repository.UsersRepository;

@Service
public class UsersService {

    private UsersRepository usersRepository;
    private EventEmitter eventEmitter;
    @Setter
    @Value("${defaultDepartmentId}")
    private Long defaultDepartmentId;

    @Autowired
    public UsersService(UsersRepository usersRepository, EventEmitter eventEmitter) {
        this.usersRepository = usersRepository;
        this.eventEmitter = eventEmitter;
    }

    public void addUser(User user) {
        user.setDepartmentId(defaultDepartmentId) ;
        usersRepository.saveAndFlush(user);
        eventEmitter.emit(user);
    }

    public ResultPage<User> getUsers(int pageNumber, int pageSize) {
        Page<User> usersPage = usersRepository.findAll(new PageRequest(pageNumber, pageSize));
        return new ResultPage<>(usersPage.getContent(), usersPage.getNumber(), usersPage.getTotalPages());
    }

}

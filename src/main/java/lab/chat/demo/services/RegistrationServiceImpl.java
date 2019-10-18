package lab.chat.demo.services;

import lab.chat.demo.forms.UserForm;
import lab.chat.demo.models.Role;
import lab.chat.demo.models.State;
import lab.chat.demo.models.User;
import lab.chat.demo.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UsersRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public RegistrationServiceImpl(UsersRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void signUp(UserForm form) {
        String hashPassword = encoder.encode(form.getPassword());
        User user = User.builder()
                .login(form.getLogin())
                .hashPassword(hashPassword)
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();

        repository.save(user);
    }
}

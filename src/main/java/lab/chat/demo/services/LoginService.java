package lab.chat.demo.services;

import lab.chat.demo.forms.UserForm;
import lab.chat.demo.transfer.TokenDto;

public interface LoginService {

    TokenDto login(UserForm form);
}

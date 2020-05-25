package lab.chat.demo.controllers;

import lab.chat.demo.forms.UserForm;
import lab.chat.demo.services.LoginService;
import lab.chat.demo.transfer.TokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginService service;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<TokenDto> login(@RequestBody UserForm form) {
        return ResponseEntity.ok(service.login(form));
    }
}

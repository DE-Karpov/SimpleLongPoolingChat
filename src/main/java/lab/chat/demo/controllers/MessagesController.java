package lab.chat.demo.controllers;

import lab.chat.demo.forms.MessageForm;
import lab.chat.demo.transfer.MessageDto;
import lab.chat.demo.transfer.UserDto;
import lab.chat.demo.models.User;
import lab.chat.demo.security.details.UserDetailsImpl;
import lab.chat.demo.services.MessageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lab.chat.demo.transfer.UserDto.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/messages")
public class MessagesController {

    private final MessageService service;
    private final UserDetailsService detailsService;

    @Autowired
    public MessagesController(MessageService service, @Qualifier("userDetailsServiceImpl") UserDetailsService detailsService) {
        this.service = service;
        this.detailsService = detailsService;
    }

    @PostMapping
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageForm message) {
        UserDetailsImpl details = (UserDetailsImpl) detailsService.loadUserByUsername(message.getToken());
        User user = details.getUser();
        service.receiveMessage(message, user);
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("token") String token) {
        UserDetailsImpl details = (UserDetailsImpl) detailsService.loadUserByUsername(token);
        UserDto dto = form(details.getUser());
        List<MessageDto> response = service.getMessagesForPage(dto);
        System.out.println(response);
        return ResponseEntity.ok(response);
    }
}


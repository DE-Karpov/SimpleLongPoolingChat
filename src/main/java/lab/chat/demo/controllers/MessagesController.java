package lab.chat.demo.controllers;

import lab.chat.demo.transfer.MessageDto;
import lab.chat.demo.transfer.UserDto;
import lab.chat.demo.models.User;
import lab.chat.demo.security.details.UserDetailsImpl;
import lab.chat.demo.services.MessageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lab.chat.demo.transfer.UserDto.*;

@RestController
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
    public ResponseEntity<Object> receiveMessage(@RequestBody MessageDto message) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.receiveMessage(message, user);
        return ResponseEntity.ok().build();
    }

    @SneakyThrows
    @GetMapping
    public ResponseEntity<List<MessageDto>> getMessagesForPage(@RequestParam("token") String token) {
        UserDetailsImpl details = (UserDetailsImpl) detailsService.loadUserByUsername(token);
        UserDto dto = form(details.getUser());
        List<MessageDto> response = service.getMessagesForPage(dto);
        return ResponseEntity.ok(response);
    }
}


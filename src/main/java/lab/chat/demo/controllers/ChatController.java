//package lab.chat.demo.controllers;
//
//import lab.chat.demo.models.User;
//import lab.chat.demo.security.details.UserDetailsImpl;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/chat")
//public class ChatController {
//
//    @GetMapping
//    public ResponseEntity<Object> getChatPage(Authentication authentication, Model model) {
//        if (authentication != null) {
//            UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();
//            User user = details.getUser();
//            model.addAttribute("login", user.getLogin());
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.badRequest().build();
//    }
//}

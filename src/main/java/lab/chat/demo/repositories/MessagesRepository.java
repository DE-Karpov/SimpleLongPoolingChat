package lab.chat.demo.repositories;

import lab.chat.demo.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Message, Long> {
}

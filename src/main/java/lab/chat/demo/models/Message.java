package lab.chat.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message")
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = "user")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}

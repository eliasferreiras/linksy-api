package dev.eliaslabs.linksy.entities;

import dev.eliaslabs.linksy.dto.request.LinkRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "links")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "short_code", nullable = false, unique = true)
    private String shortCode;

    @Column(name = "target_url", nullable = false)
    private String targetURL;

    private String title;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Link(LinkRequestDTO dto) {
        this.title = dto.title();
        this.targetURL = dto.targetURL();
    }

}

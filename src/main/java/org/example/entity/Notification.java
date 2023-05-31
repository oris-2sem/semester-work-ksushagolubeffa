package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
public class Notification extends AbstractEntity{

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private User author;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private MediaContent content;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private MediaComments comment;

    @Column
    @Enumerated(value = EnumType.STRING)
    private Message message;

    public Notification(User user, MediaContent content, MediaComments comment, User author){
        this.comment = comment;
        this.user = user;
        this.content = content;
        this.author = author;
    }

    public Notification(User user, MediaContent content, Message message){
        this.message = message;
        this.user = user;
        this.content = content;
    }

    public enum Message{
        ACCEPTED, REJECTED
    }
}

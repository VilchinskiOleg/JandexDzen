package by.tms.entity;

import by.tms.entity.like.Like;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
@NamedQueries(value = {
        @NamedQuery(name = "Post.findAll", query = "from Post"),
        @NamedQuery(name = "Post.findByTitle", query = "from Post p where p.title = :title"),
        @NamedQuery(name = "Post.existByTitle", query = "select count(*) from Post p where p.title = :title")
})
public class Post {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String massage;
    private LocalDateTime date = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "posts_author_id")
    private User author;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "commented_post_id", referencedColumnName = "id")
    private List<Comment> comments = new ArrayList<>();
    private int likes;
}

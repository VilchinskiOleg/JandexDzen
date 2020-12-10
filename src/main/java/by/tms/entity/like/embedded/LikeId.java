package by.tms.entity.like.embedded;

import by.tms.entity.Post;
import by.tms.entity.User;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "likes_author_id", referencedColumnName = "id")
    private User author;

    @Column(name = "liked_post_id")
    private long postId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeId likeId = (LikeId) o;
        return postId == likeId.postId &&
                Objects.equals(author, likeId.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, postId);
    }
}

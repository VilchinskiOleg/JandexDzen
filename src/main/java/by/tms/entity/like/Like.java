package by.tms.entity.like;

import by.tms.entity.like.embedded.LikeId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "likes")
@NamedQueries(value = {
        @NamedQuery(name = "Like.findAll", query = "from Like l where l.id.postId = :postId"),
        @NamedQuery(name = "Like.deleteByPostId", query = "delete from Like l where l.id.postId = :postId"),
        @NamedQuery(name = "Like.existById", query = "select count(*) from Like l where l.id = :likeId"),
        @NamedQuery(name = "Like.quantityByPostId", query = "select count(*) from Like l where l.id.postId = :postId")
        })
public class Like {

    @EmbeddedId
    private LikeId id;
}

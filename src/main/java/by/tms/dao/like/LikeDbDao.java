package by.tms.dao.like;

import by.tms.entity.like.Like;
import by.tms.entity.like.embedded.LikeId;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class LikeDbDao implements LikeDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Like> getLikes(long postId) {
        if (postId < 1) {
            throw new IllegalArgumentException("PostID is not correct!");
        }
        List<Like> likes = entityManager.createNamedQuery("Like.findAll", Like.class)
                .setParameter("postId", postId)
                .getResultList();
        return likes;
    }

    @Override
    public int getQuantityLikesByPostId(long postId) {
        if (postId < 1) {
            throw new IllegalArgumentException("PostID is not correct!");
        }
        long result = (Long) entityManager.createNamedQuery("Like.quantityByPostId")
                .setParameter("postId", postId)
                .getSingleResult();
        return (int) result;
    }

    @Override
    public void saveLike(Like like) {
        if (like == null) {
            throw new IllegalArgumentException("Like is null!");
        }
        entityManager.persist(like);
    }

    @Override
    public void deleteLikesByPostId(long postId) {
        if (postId < 1) {
            throw new IllegalArgumentException("PostID is not correct!");
        }
        entityManager.createNamedQuery("Like.deleteByPostId")
                .setParameter("postId", postId)
                .executeUpdate();
    }

    @Override
    public void deleteLikeById(LikeId likeId) {
        if (likeId == null) {
            throw new IllegalArgumentException("LikeID is null!");
        }
        Like like = entityManager.find(Like.class, likeId);
        entityManager.remove(like);
    }

    @Override
    public boolean existLikeById(LikeId likeId) {
        if (likeId == null) {
            throw new IllegalArgumentException("LikeID is null!");
        }
        long result = (Long) entityManager.createNamedQuery("Like.existById")
                .setParameter("likeId", likeId)
                .getSingleResult();
        return result > 0;
    }
}

package by.tms.dao.like;

import by.tms.entity.like.Like;
import by.tms.entity.like.embedded.LikeId;

import java.util.List;

public interface LikeDao {
    List<Like> getLikes(long postId);
    int getQuantityLikesByPostId(long postId);
    void saveLike(Like like);
    void deleteLikesByPostId(long postId);
    void deleteLikeById(LikeId likeId);
    boolean existLikeById(LikeId likeId);
}

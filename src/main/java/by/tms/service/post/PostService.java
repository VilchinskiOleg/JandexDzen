package by.tms.service.post;

import by.tms.entity.Comment;
import by.tms.entity.like.Like;
import by.tms.entity.Post;
import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    Post getPostByTitle(String title);
    Post GetPostById(long id);

    void createPost(Post post);
    void deletePost(long postId);

    void setComment(Comment comment, long postId);
    void deleteComment(long commentId, long postId);

    void trySetLike(Like like);
    List<Like> getAllLikes(long postId);
}

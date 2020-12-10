package by.tms.dao.post;

import by.tms.entity.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PostDbDao implements PostDao {

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public List<Post> getAllPosts() {
        return entityManager.createNamedQuery("Post.findAll", Post.class).getResultList();
    }

    @Override
    public Post getPostByTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title is null!");
        }
        return entityManager.createNamedQuery("Post.findByTitle", Post.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public Post getPostById(long postId) {
        if (postId < 1) {
            throw new IllegalArgumentException("PostId is not correct!");
        }
        return entityManager.find(Post.class, postId);
    }



    @Override
    public void createPost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post is null!");
        }
        entityManager.persist(post);
    }

    @Override
    public void updatePost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post is null!");
        }
        entityManager.merge(post);
    }

    @Override
    public void deletePost(long postId) {
        if (postId < 1) {
            throw new IllegalArgumentException("PostId is not correct!");
        }
        Post post = entityManager.getReference(Post.class, postId);
        entityManager.remove(post);
    }



    @Override
    public boolean existPostByTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Title is null!");
        }
        long result = (Long) entityManager.createNamedQuery("Post.existByTitle")
                .setParameter("title", title)
                .getSingleResult();
        return result > 0;
    }

    @Override
    public boolean existPostById(long postId) {
        if (postId < 1) {
            throw new IllegalArgumentException("PostId is not correct!");
        }
        Post post = entityManager.find(Post.class, postId);
        return post != null;
    }
}

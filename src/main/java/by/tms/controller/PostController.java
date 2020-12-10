package by.tms.controller;

import by.tms.dto.CommentCreateDto;
import by.tms.dto.PostCreateDto;
import by.tms.entity.Comment;
import by.tms.entity.like.Like;
import by.tms.entity.Post;
import by.tms.entity.User;
import by.tms.entity.like.embedded.LikeId;
import by.tms.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "/create")
    public ModelAndView getCreatePostForm(ModelAndView modelAndView) {
        modelAndView.addObject("postCreateDto", new PostCreateDto());
        modelAndView.setViewName("post-form");
        return modelAndView;
    }
    @PostMapping(path = "/create")
    public ModelAndView createPost(@Valid @ModelAttribute("postCreateDto") PostCreateDto postCreateDto,
                                   BindingResult bindingResult,
                                   @SessionAttribute("user") User user,
                                   ModelAndView modelAndView) {
        if (!bindingResult.hasErrors()) {
            Post post = new Post();
            post.setTitle(postCreateDto.getTitle());
            post.setMassage(postCreateDto.getMassage());
            post.setAuthor(user);
            postService.createPost(post);
            modelAndView.setViewName("redirect:/home");
        } else {
            modelAndView.setViewName("post-form");
        }
        return modelAndView;
    }
    @GetMapping(path = "/delete")
    public ModelAndView deletePost(@RequestParam("post-id") long postId,
                                ModelAndView modelAndView) {
        postService.deletePost(postId);
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }




    @GetMapping(path = "/show")
    public ModelAndView getPost(@RequestParam("post-id") long postId,
                                ModelAndView modelAndView) {
        Post post = postService.GetPostById(postId);
        modelAndView.addObject("post", post);
        modelAndView.addObject("commentCreateDto", new CommentCreateDto());
        modelAndView.setViewName("post");
        return modelAndView;
    }



    
    @PostMapping(path = "/set-comment")
    public ModelAndView setCommentAndGetPost(@Valid @ModelAttribute("commentCreateDto") CommentCreateDto commentCreateDto,
                                             BindingResult bindingResult,
                                             @RequestParam("post-id") long postId,
                                             @SessionAttribute("user") User user,
                                             ModelAndView modelAndView) {

        Post post = postService.GetPostById(postId);
        if (!bindingResult.hasErrors()) {
            Comment comment = new Comment();
            comment.setMassage(commentCreateDto.getMassage());
            comment.setAuthor(user);
            postService.setComment(comment, postId);
            modelAndView.setViewName(String.format("redirect:/post/show?post-id=%s", postId));
        }else{
            modelAndView.addObject("post", post);
            modelAndView.setViewName("post");
        }
        return modelAndView;
    }
    @GetMapping(path = "/delete-comment")
    public ModelAndView deleteCommentAndGetPost(@RequestParam("comment-id") long commentId,
                                                @RequestParam("post-id") long postId,
                                                ModelAndView modelAndView) {

        postService.deleteComment(commentId, postId);
        modelAndView.setViewName(String.format("redirect:/post/show?post-id=%s", postId));
        return modelAndView;
    }




    @GetMapping(path = "/set-like")
    public ModelAndView setLikeAndGetPost(@RequestParam("post-id") long postId,
                                          @SessionAttribute("user") User user,
                                          ModelAndView modelAndView) {
        Like like = new Like(new LikeId(user, postId));
        postService.trySetLike(like);
        modelAndView.setViewName(String.format("redirect:/post/show?post-id=%s", postId));
        return modelAndView;
    }
    @GetMapping(path = "like")
    public ModelAndView getLikes(@RequestParam("post-id") long postId, ModelAndView modelAndView) {
        List<Like> likes = postService.getAllLikes(postId);
        modelAndView.addObject("likes", likes);
        modelAndView.addObject("postId", postId);
        modelAndView.setViewName("like");
        return modelAndView;
    }
}

package by.tms.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostLikeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
//            long postId = Long.parseLong(request.getParameter("post-id"));
            String postId = request.getParameter("post-id");
            response.sendRedirect(String.format("/post/show?post-id=%s", postId));
            return false;
        }
        return true;
    }
}

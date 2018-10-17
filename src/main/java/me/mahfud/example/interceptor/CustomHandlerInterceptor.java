package me.mahfud.example.interceptor;

import me.mahfud.example.exception.ApiError;
import me.mahfud.example.exception.BusNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Interceptor : pre handler");

        if (request.getHeader("Authorization") == null) {
            response.setStatus(403);

            ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, "Server Error", "You just cant access this resource");
            response.getWriter().append(apiError.toString());
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("Interceptor : post handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("Interceptor : after completion");
    }
}


package by.tms.controller;

import by.tms.dto.UserAuthDto;
import by.tms.entity.User;
import by.tms.service.user.exception.DuplicateUserException;
import by.tms.service.user.exception.NotFoundUserByIdException;
import by.tms.service.user.exception.NotFoundUserByLoginException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerHomeController {
    @ExceptionHandler(value = DuplicateUserException.class)
    public String DuplicateUserHandler(Model model, RuntimeException e) {
        model.addAttribute("massage", e.getMessage());
        model.addAttribute("user", new User());
        return "reg";
    }
}

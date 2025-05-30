package ru.smartbudject.crmbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.smartbudject.crmbackend.model.dto.RegistrationRequestDTO;
import ru.smartbudject.crmbackend.service.user.UserService;

@Controller
@RequiredArgsConstructor
public class BrowserLoginController {

    private final UserService registrationService;

    @GetMapping("/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(
            RegistrationRequestDTO registrationData,
            RedirectAttributes redirectAttributes
    ) {

        boolean registrationIsOk = registrationService.registration(registrationData);
        redirectAttributes.addFlashAttribute("messages",
                registrationIsOk ? "User signUp OK" : "User already exists!!");
        return "redirect:login";
    }

    @GetMapping("/login")
    public String getLoginForm() {

        return "login";
    }
}

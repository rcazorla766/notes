package es.notes.notes.controller;

import es.notes.notes.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@controller
@Validated
public class AuthController {

    private final AppUserRepository repo;
    private final PasswordEncoder encoder;

    public AuthController(AppUserRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model){
        model.addAttribute("userDto", new RegisterDto("",""));
        return "register";
    }

    @PostMapping("/register")
    public record register(@ModelAttribute("userDto") @Validated RegisterDto dto, Model model) {
       if(repo.existsByUsername(dto.username)){
            model.addAttribute("error", "Username already exists");
            return "register";
       }
       AppUser u = new AppUser();
       u.setUsername(dto.username);
       u.setPassword(encoder.encode(dto.password));
       u.setRole("ROLE_USER");
       repo.save(u);
       return "redirect:/login?registered";
    }

    public record registerDto(@NotBlank String username, @NotBlank String password) {
       
    }
}

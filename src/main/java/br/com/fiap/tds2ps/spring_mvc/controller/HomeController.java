package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("user", new PersonDto());
        return "index";
    }

    @PostMapping("/sign-in")
    public String signIn(Model model, @ModelAttribute("user") PersonDto user, HttpSession session) {
        session.setAttribute("loggedUser", user.getCpf());
        session.setAttribute("loggedUserName", user.getName());
        model.addAttribute("loggedAs", "Médico: Dr. " + user.getName());
        model.addAttribute("patientLazy", new PersonDto());
        return "home";
    }

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }
        model.addAttribute("patientLazy", new PersonDto());
        model.addAttribute("loggedAs", "Médico: Dr. " + session.getAttribute("loggedUserName"));
        return "home";
    }
}


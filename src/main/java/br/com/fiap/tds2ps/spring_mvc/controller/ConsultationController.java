package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/consultation")
public class ConsultationController {

    private final PatientService patientService;

    public ConsultationController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/start")
    public String start(@ModelAttribute("patientLazy") PersonDto patientDto, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }

        if (patientService.existsByCpf(patientDto.getCpf())) {
            session.setAttribute("currentPatientCpf", patientDto.getCpf());
            return "redirect:/medical-record/new";
        } else {
            session.setAttribute("cpfToRegister", patientDto.getCpf());
            return "redirect:/patient/new";
        }
    }
}


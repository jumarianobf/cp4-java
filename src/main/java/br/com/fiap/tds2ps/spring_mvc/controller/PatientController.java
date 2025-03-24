package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/new")
    public String newPatient(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }

        PersonDto patientDto = new PersonDto();
        if (session.getAttribute("cpfToRegister") != null) {
            patientDto.setCpf((String) session.getAttribute("cpfToRegister"));
            session.removeAttribute("cpfToRegister");
        }

        model.addAttribute("patient", patientDto);
        return "add-patient";
    }

    @PostMapping("/save")
    public String addPatient(@ModelAttribute("patient") PersonDto patientDto, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }

        patientService.save(patientDto);

        // Armazenar o CPF do paciente para a consulta
        session.setAttribute("currentPatientCpf", patientDto.getCpf());

        return "redirect:/medical-record/new";
    }
}


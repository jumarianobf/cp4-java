package br.com.fiap.tds2ps.spring_mvc.controller;

import br.com.fiap.tds2ps.spring_mvc.model.MedicalRecord;
import br.com.fiap.tds2ps.spring_mvc.model.Patient;
import br.com.fiap.tds2ps.spring_mvc.service.MedicalRecordService;
import br.com.fiap.tds2ps.spring_mvc.service.PatientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/medical-record")
public class MedicalRecordController {

    private final PatientService patientService;
    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(PatientService patientService, MedicalRecordService medicalRecordService) {
        this.patientService = patientService;
        this.medicalRecordService = medicalRecordService;
    }

    @GetMapping("/new")
    public String newMedicalRecord(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }

        String patientCpf = (String) session.getAttribute("currentPatientCpf");
        if (patientCpf == null) {
            return "redirect:/home";
        }

        Patient patient = patientService.findByCpf(patientCpf);
        if (patient == null) {
            return "redirect:/home";
        }

        model.addAttribute("patient", patient);
        model.addAttribute("medicalRecord", new MedicalRecord());
        model.addAttribute("medicalHistory", medicalRecordService.getFormattedMedicalHistory(patient));

        return "add-consultation";
    }

    @PostMapping("/save")
    public String saveMedicalRecord(@ModelAttribute("medicalRecord") MedicalRecord medicalRecord, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }

        String patientCpf = (String) session.getAttribute("currentPatientCpf");
        if (patientCpf == null) {
            return "redirect:/home";
        }

        Patient patient = patientService.findByCpf(patientCpf);
        if (patient == null) {
            return "redirect:/home";
        }

        medicalRecord.setPatient(patient);
        medicalRecord.setDoctorName((String) session.getAttribute("loggedUserName"));
        medicalRecordService.save(medicalRecord);

        // Limpar a sessão após salvar
        session.removeAttribute("currentPatientCpf");

        return "redirect:/home";
    }

    @GetMapping("/list")
    public String listMedicalRecords(Model model, HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/";
        }

        List<MedicalRecord> records = medicalRecordService.findAllRecords();
        model.addAttribute("records", records);
        model.addAttribute("loggedAs", "Médico: Dr. " + session.getAttribute("loggedUserName"));

        return "list-consultations";
    }
}


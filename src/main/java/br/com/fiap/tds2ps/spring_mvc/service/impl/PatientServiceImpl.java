package br.com.fiap.tds2ps.spring_mvc.service.impl;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.model.Patient;
import br.com.fiap.tds2ps.spring_mvc.repository.PatientRepository;
import br.com.fiap.tds2ps.spring_mvc.service.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return patientRepository.existsByCpf(cpf);
    }

    @Override
    @Transactional
    public void save(PersonDto personDto) {
        Patient patient = new Patient();
        patient.setCpf(personDto.getCpf());
        patient.setName(personDto.getName());
        patientRepository.save(patient);
    }

    @Override
    public Patient findByCpf(String cpf) {
        return patientRepository.findByCpf(cpf);
    }
}


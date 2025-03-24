package br.com.fiap.tds2ps.spring_mvc.service;

import br.com.fiap.tds2ps.spring_mvc.dto.PersonDto;
import br.com.fiap.tds2ps.spring_mvc.model.Patient;

public interface PatientService {
    boolean existsByCpf(String cpf);
    void save(PersonDto personDto);
    Patient findByCpf(String cpf);
}


package br.com.fiap.tds2ps.spring_mvc.service;

import br.com.fiap.tds2ps.spring_mvc.model.MedicalRecord;
import br.com.fiap.tds2ps.spring_mvc.model.Patient;

import java.util.List;

public interface MedicalRecordService {
    void save(MedicalRecord medicalRecord);
    List<MedicalRecord> findByPatient(Patient patient);
    String getFormattedMedicalHistory(Patient patient);
    List<MedicalRecord> findAllRecords();
}


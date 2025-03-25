package br.com.fiap.tds2ps.spring_mvc.service.impl;

import br.com.fiap.tds2ps.spring_mvc.model.MedicalRecord;
import br.com.fiap.tds2ps.spring_mvc.model.Patient;
import br.com.fiap.tds2ps.spring_mvc.repository.MedicalRecordRepository;
import br.com.fiap.tds2ps.spring_mvc.service.MedicalRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    @Transactional
    public void save(MedicalRecord medicalRecord) {
        if (medicalRecord.getRecordDate() == null) {
            medicalRecord.setRecordDate(LocalDateTime.now());
        }
        medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public List<MedicalRecord> findByPatient(Patient patient) {
        return medicalRecordRepository.findByPatientOrderByRecordDateDesc(patient);
    }

    @Override
    public String getFormattedMedicalHistory(Patient patient) {
        List<MedicalRecord> records = findByPatient(patient);

        if (records.isEmpty()) {
            return "Sem histórico médico anterior.";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        StringBuilder history = new StringBuilder();

        for (MedicalRecord record : records) {
            history.append("Data: ").append(record.getRecordDate().format(formatter)).append("\n");
            history.append("Médico: ").append(record.getDoctorName()).append("\n");
            history.append("Anamnese: ").append(record.getAnamnesis()).append("\n");
            history.append("Prescrição: ").append(record.getPrescription()).append("\n\n");
        }

        return history.toString();
    }

    @Override
    public List<MedicalRecord> findAllRecords() {
        return medicalRecordRepository.findAll();
    }
}


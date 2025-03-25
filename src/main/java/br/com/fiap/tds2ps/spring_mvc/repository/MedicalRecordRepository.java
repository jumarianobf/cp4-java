package br.com.fiap.tds2ps.spring_mvc.repository;

import br.com.fiap.tds2ps.spring_mvc.model.MedicalRecord;
import br.com.fiap.tds2ps.spring_mvc.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByPatientOrderByRecordDateDesc(Patient patient);
}


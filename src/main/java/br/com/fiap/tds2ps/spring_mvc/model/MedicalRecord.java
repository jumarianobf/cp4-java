package br.com.fiap.tds2ps.spring_mvc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "med_record_seq")
    @SequenceGenerator(name = "med_record_seq", sequenceName = "med_record_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(length = 4000)
    private String anamnesis;

    @Column(length = 4000)
    private String prescription;

    @Column(nullable = false)
    private LocalDateTime recordDate;

    @Column(nullable = false)
    private String doctorName;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public LocalDateTime getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDateTime recordDate) {
        this.recordDate = recordDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
}


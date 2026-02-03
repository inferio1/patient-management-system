package com.pm.patient_service.mapper;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.model.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PatientMapper {
    public  PatientResponseDTO toDTO(Patient patient)
    {
        PatientResponseDTO response=new PatientResponseDTO();
        response.setName(patient.getName());
        response.setAddress(patient.getAddress());
        response.setEmail(patient.getEmail());
        response.setDateOfBirth(patient.getDateOfBirth().toString());
        return response;
    }

    public Patient toModel(PatientRequestDTO patientRequestDTO)
    {
        Patient patient=new Patient();
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;
    }
}

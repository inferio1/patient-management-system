package com.pm.patient_service.service;

import com.pm.patient_service.dto.PatientRequestDTO;
import com.pm.patient_service.dto.PatientResponseDTO;
import com.pm.patient_service.exception.EmailAlreadyExistsException;
import com.pm.patient_service.exception.PatientNotFoundException;
import com.pm.patient_service.mapper.PatientMapper;
import com.pm.patient_service.model.Patient;
import com.pm.patient_service.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class PatientService {
    private PatientRepository patientRepository;
    private PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
        this.patientRepository = patientRepository;
        this.patientMapper = patientMapper;
    }

    public List<PatientResponseDTO> getAllPatient()
    {
        return patientRepository.findAll().stream().map(patientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO)
    {
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail()))
        {
            log.info("Email Already exists: {}",patientRequestDTO.getEmail());
            throw new EmailAlreadyExistsException("A patient with this email"+patientRequestDTO.getEmail()+" address already exists");
        }
        Patient newPatient=patientMapper.toModel(patientRequestDTO);
        patientRepository.save(newPatient);
        return patientMapper.toDTO(newPatient);

    }

    public PatientResponseDTO updatePatient(UUID id,PatientRequestDTO patientRequestDTO)
    {
       Patient patient= patientRepository.findById(id).orElseThrow(()->new PatientNotFoundException("patient not found with id"+id));

       if(patientRepository.existsByEmail(patientRequestDTO.getEmail()))
       {
           throw new EmailAlreadyExistsException("Email already exits with id ="+id+" ,cannot update ");
       }
       patient.setName(patientRequestDTO.getName());
       patient.setAddress(patientRequestDTO.getAddress());
       patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
       patient.setEmail(patientRequestDTO.getEmail());
       Patient updatedPatient=patientRepository.save(patient);
       return patientMapper.toDTO(updatedPatient);
    }


}

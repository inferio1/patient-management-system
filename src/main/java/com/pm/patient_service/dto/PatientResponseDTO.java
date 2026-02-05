package com.pm.patient_service.dto;

import lombok.Data;

@Data
public class PatientResponseDTO {
    public String id;
    public String name;
    public String email;
    public String address;
    public String dateOfBirth;

}

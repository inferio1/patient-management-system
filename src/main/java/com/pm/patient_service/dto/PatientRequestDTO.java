package com.pm.patient_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {
    @NotNull(message = "Name cannot be null")
    @Size(max = 100, message = "Size cannot exceed more tha 100")
    private String name;

    @NotNull(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Date of birth is required")
    private String dateOfBirth;

    @NotNull(message = "Registered Date is required")
    private String registeredDate;


}

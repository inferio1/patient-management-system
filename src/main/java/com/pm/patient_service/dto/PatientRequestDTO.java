package com.pm.patient_service.dto;

import com.pm.patient_service.dto.validators.PatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDTO {
    @NotBlank(message = "Name cannot be null")
    @Size(max = 100, message = "Size cannot exceed more tha 100")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(groups = PatientValidationGroup.class, message = "Registered Date is required")
    private String registeredDate;


}

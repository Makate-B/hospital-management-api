package com.bophelo.hospital._api_clean.controller;

import com.bophelo.hospital._api_clean.dataTransferObject.PatientRequestDTO;
import com.bophelo.hospital._api_clean.dataTransferObject.PatientResponseDTO;
import com.bophelo.hospital._api_clean.model.Patient;
import com.bophelo.hospital._api_clean.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController( PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<PatientResponseDTO> getAllPatients(){
        return patientService.getAllPatients();
    }

    @GetMapping("/{memberNumber}")
    public PatientResponseDTO getPatientPatientByMemberNumber(@PathVariable String memberNumber){

        return patientService.getPatientByMemberNumber(memberNumber);

    }

    @PostMapping
    public PatientResponseDTO addPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO){

        return patientService.addPatient(patientRequestDTO);
    }

    @PutMapping("/{memberNumber}")
    public PatientResponseDTO updatePatientByMemberNumber(@PathVariable String memberNumber, @Valid @RequestBody PatientRequestDTO patientRequestDTO){

        return patientService.updatePatientDetails(memberNumber,patientRequestDTO);
    }
    @DeleteMapping("/{memberNumber}")
    public ResponseEntity<String> deletePatientByMemberNumber(@PathVariable String memberNumber){
        patientService.deletePatient(memberNumber);

        return ResponseEntity.ok("Patient Deleted successfully");
    }

    @GetMapping("/doctor/{doctorMemberNumber}")
    public List<Patient> getPatientsByDoctorMemberNumber(@PathVariable String doctorMemberNumber) {
        return patientService.getPatientsByDoctorMemberNumber(doctorMemberNumber);
    }

}

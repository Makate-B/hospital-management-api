package com.bophelo.hospital._api_clean.controller;

import com.bophelo.hospital._api_clean.model.Doctor;
import com.bophelo.hospital._api_clean.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{memberNumber}")
    public ResponseEntity<Doctor> getDoctorByMemberNumber(@PathVariable String memberNumber){

        Doctor doctor = doctorService.getDoctorByMemberNumber(memberNumber);

        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<Doctor> saveDoctor(@Valid @RequestBody Doctor doctor){

     Doctor savedDoctor = doctorService.addDoctor(doctor);

    return ResponseEntity.status(201).body(savedDoctor);
    }

    @PutMapping("/{memberNumber}")
    public ResponseEntity<Doctor> updateDoctorByMemberNumber(@PathVariable String memberNumber, @Valid @RequestBody Doctor doctor){

        Doctor updatedDoctor = doctorService.updateDoctorByMemberNumber(memberNumber, doctor);

        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{memberNumber}")
    public ResponseEntity<String> deleteDoctorByMemberNumber(@PathVariable String memberNumber){

        doctorService.deleteDoctorByMemberNumber(memberNumber);

        return ResponseEntity.ok("Doctor deleted Successfully");
    }
}

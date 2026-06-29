package com.bophelo.hospital._api_clean.controller;

import com.bophelo.hospital._api_clean.model.Nurse;
import com.bophelo.hospital._api_clean.service.NurseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurses")
public class NurseController {

    private final NurseService nurseService;

    public NurseController(NurseService nurseService) {
        this.nurseService = nurseService;
    }

    @GetMapping
    public List<Nurse> getAllNurses(){

        return nurseService.getAllNurses();
    }

    @GetMapping("/{memberNumber}")
    public ResponseEntity<Nurse> getNurseByMemberId(@PathVariable String memberNumber){

        Nurse nurse = nurseService.getNurseByMemberNumber(memberNumber);

        return ResponseEntity.ok(nurse);
    }

    @PostMapping
    public ResponseEntity<Nurse> saveNurse(@Valid @RequestBody Nurse nurse){

        Nurse savedNurse = nurseService.saveNurse(nurse);

        return ResponseEntity.status(201).body(savedNurse);
    }

    @PutMapping("/{memberNumber}")
    public ResponseEntity<Nurse> updateExistingNurse(@PathVariable String memberNumber, @Valid @RequestBody Nurse updatedNurse){

        Nurse existingNurse = nurseService.updateNurseByMemberNumber(memberNumber, updatedNurse);

        return ResponseEntity.ok(existingNurse);
    }

    @DeleteMapping("/{memberNumber}")
    public ResponseEntity<String> deleteNurseByMemberNumber(@PathVariable String memberNumber){

        nurseService.deleteNurseByMemberNumber(memberNumber);

        return ResponseEntity.ok("Nurse Deleted Successfully");

    }



}

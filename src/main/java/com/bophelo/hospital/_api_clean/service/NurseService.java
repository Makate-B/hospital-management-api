package com.bophelo.hospital._api_clean.service;

import com.bophelo.hospital._api_clean.exception.ResourceNotFoundException;
import com.bophelo.hospital._api_clean.model.Nurse;
import com.bophelo.hospital._api_clean.repository.NurseRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class NurseService {

    private final NurseRepository nurseRepository;

    public NurseService(NurseRepository nurseRepository) {
        this.nurseRepository = nurseRepository;
    }

    public List<Nurse> getAllNurses(){
        return nurseRepository.findAll();
    }

    public Nurse getNurseByMemberNumber(String memberNumber){

        return nurseRepository.findByMemberNumber(memberNumber).orElseThrow(()-> new ResourceNotFoundException("Nurse with member Number " + memberNumber + " was not found"));

    }

    public Nurse saveNurse(Nurse nurse){

        Nurse savedNurse = nurseRepository.save(nurse);

        String memberNumber = generateMemberNumber(savedNurse.getId());

        savedNurse.setMemberNumber(memberNumber);

        return nurseRepository.save(savedNurse);
    }

    public Nurse updateNurseByMemberNumber(String memberNumber, Nurse updatedNurse){

        Nurse existingNurse = nurseRepository.findByMemberNumber(memberNumber).orElseThrow(()-> new ResourceNotFoundException("Nurse with member number " + memberNumber + " was not found"));

        existingNurse.setName(updatedNurse.getName());
        existingNurse.setAge(updatedNurse.getAge());
        existingNurse.setDepartment(updatedNurse.getDepartment());
        existingNurse.setShiftType(updatedNurse.getShiftType());

        return nurseRepository.save(existingNurse);

    }

    public void deleteNurseByMemberNumber(String memberNumber){

        Nurse nurse = nurseRepository.findByMemberNumber(memberNumber).orElseThrow(() -> new ResourceNotFoundException("Nurse with member number " + memberNumber + " was not found"));

        nurseRepository.delete(nurse);

    }

    public String generateMemberNumber(long id){

        int currentYear = Year.now().getValue();

        return String.format("NUR-%d-%06d", currentYear, id);
    }



}

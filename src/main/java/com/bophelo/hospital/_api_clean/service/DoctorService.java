package com.bophelo.hospital._api_clean.service;

import com.bophelo.hospital._api_clean.exception.ResourceNotFoundException;
import com.bophelo.hospital._api_clean.model.Doctor;
import com.bophelo.hospital._api_clean.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


   public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
   }

   public Doctor getDoctorByMemberNumber(String memberNumber){

        return doctorRepository.findByMemberNumber(memberNumber).orElseThrow(()-> new ResourceNotFoundException("Doctor with member number " + memberNumber + " was not found"));

   }

   public Doctor addDoctor(Doctor doctor){

       Doctor savedDoctor = doctorRepository.save(doctor);

       String memberNumber = generateMemberNumber(savedDoctor.getId());

       savedDoctor.setMemberNumber(memberNumber);

       return doctorRepository.save(savedDoctor);
   }

   public Doctor updateDoctorByMemberNumber(String memberNumber, Doctor updatedDoctor){

        Doctor existingDoctor = doctorRepository.findByMemberNumber(memberNumber).orElseThrow(()-> new ResourceNotFoundException("Doctor with member number " + memberNumber + " was not found"));

        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setAge(updatedDoctor.getAge());
        existingDoctor.setSpecialization(updatedDoctor.getSpecialization());

        return doctorRepository.save(existingDoctor);
   }

   public void  deleteDoctorByMemberNumber(String memberNumber){

        Doctor doctor = doctorRepository.findByMemberNumber(memberNumber).orElseThrow(()-> new ResourceNotFoundException("Doctor with member number " + memberNumber + " was not found"));

        doctorRepository.delete(doctor);
   }


   private String generateMemberNumber(long id){

        int currentYear = Year.now().getValue();

        return String.format("DOC-%d-%06d", currentYear, id);
   }

}

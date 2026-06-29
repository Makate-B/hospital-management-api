package com.bophelo.hospital._api_clean.service;

import com.bophelo.hospital._api_clean.dataTransferObject.PatientRequestDTO;
import com.bophelo.hospital._api_clean.dataTransferObject.PatientResponseDTO;
import com.bophelo.hospital._api_clean.exception.ResourceNotFoundException;
import com.bophelo.hospital._api_clean.model.Doctor;
import com.bophelo.hospital._api_clean.model.Patient;
import com.bophelo.hospital._api_clean.repository.DoctorRepository;
import com.bophelo.hospital._api_clean.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {

    //Meaning patient service will use patient repository to talk to database

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    //SB gives patient repository to patient service automatically
    public PatientService(PatientRepository patientRepository, DoctorRepository doctorRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    //Get all the patients from the database

    public List<PatientResponseDTO> getAllPatients(){

        List<Patient> patients = patientRepository.findAll();

        ArrayList<PatientResponseDTO> patientResponseDTOList = new ArrayList<>();

        for(Patient patient : patients){

            patientResponseDTOList.add(convertToPatientResponse(patient));
        }

        return patientResponseDTOList;


    }

    //Get patient by using Member Number

    public PatientResponseDTO getPatientByMemberNumber(String memberNumber){

        Patient patient = patientRepository.findByMemberNumber(memberNumber).orElseThrow(()-> new ResourceNotFoundException("Patient with member number " + memberNumber + " was not found"));

        return convertToPatientResponse(patient);

    }

    //Add patient

    public PatientResponseDTO addPatient(PatientRequestDTO patientRequestDTO){

        Patient patient = new Patient();

        patient.setName(patientRequestDTO.getName());
        patient.setAge(patientRequestDTO.getAge());
        patient.setIllness(patient.getIllness());
        patient.setDaysAdmitted(patient.getDaysAdmitted());

        Patient savedPatient = patientRepository.save(patient);

        String memberNumber = generateMemberNumber(savedPatient.getId());

        savedPatient.setMemberNumber(memberNumber);

        Patient finalSave = patientRepository.save(savedPatient);

        return convertToPatientResponse(finalSave);
    }


    //Update patient

    public PatientResponseDTO updatePatientDetails(String memberNumber, PatientRequestDTO patientRequestDTO){

        Patient existingPatient = patientRepository.findByMemberNumber(memberNumber).orElseThrow(() -> new ResourceNotFoundException("Patient with member number " + memberNumber + " was not found"));

        existingPatient.setName(patientRequestDTO.getName());
        existingPatient.setAge(patientRequestDTO.getAge());
        existingPatient.setIllness(patientRequestDTO.getIllness());
        existingPatient.setDaysAdmitted(patientRequestDTO.getDaysAdmitted());

        Patient savedPatient = patientRepository.save(existingPatient);
        return convertToPatientResponse(savedPatient);

    }

    //Delete patient

    public void deletePatient(String memberNumber){

     Patient patient = patientRepository.findByMemberNumber(memberNumber).orElseThrow(()-> new ResourceNotFoundException("Patient with member number " + memberNumber + " was not found"));

        patientRepository.delete(patient);
    }

    public Patient addPatientToDoctor(String doctorMemberNumber, Patient patient){

        Doctor doctor = doctorRepository.findByMemberNumber(doctorMemberNumber).orElseThrow(() -> new ResourceNotFoundException("Doctor with member number " + doctorMemberNumber + " was not found"));

        patient.setDoctor(doctor);

        Patient savedPatient = patientRepository.save(patient);

        String memberNumber = generateMemberNumber(savedPatient.getId());

        savedPatient.setMemberNumber(memberNumber);

        return patientRepository.save(savedPatient);

    }

    public List<Patient> getPatientsByDoctorMemberNumber(String doctorMemberNumber) {
        return patientRepository.findByDoctorMemberNumber(doctorMemberNumber);
    }

    private String generateMemberNumber(long id){

        int currentYear = Year.now().getValue();

        return String.format("PAT-%d-%06d", currentYear, id);
    }

    private PatientResponseDTO convertToPatientResponse(Patient patient){

        return new PatientResponseDTO(patient.getMemberNumber(), patient.getName(), patient.getAge(), patient.getIllness(), patient.getDaysAdmitted());
    }

}

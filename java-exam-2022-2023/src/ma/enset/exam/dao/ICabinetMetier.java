package ma.enset.exam.dao;

import ma.enset.exam.dao.entites.Consultation;
import ma.enset.exam.dao.entites.Medecin;
import ma.enset.exam.dao.entites.Patient;

import java.util.List;

public interface ICabinetMetier {
    List<Patient> getAllPatients();
    List<Patient> findByQuery(String mc);
    Patient addPatient(Patient p);
    Boolean deletePatient(Patient p);
    List<Consultation> getAllConsultationsPatient(Patient p);

    List<Medecin> getAllMedecins();
    Medecin addMedecin(Medecin m);
    Boolean deleteMedecin(Medecin m);
    List<Consultation> getAllConsultationsMedecin(Medecin m);

    List<Consultation> getAllConsultations();
    Consultation addConsultation(Consultation c);
    Boolean deleteConsultation(Consultation c);

    Patient findPatientById(int id);
    Medecin findMedecinById(int id);
    Consultation findConsultationById(int id);
}

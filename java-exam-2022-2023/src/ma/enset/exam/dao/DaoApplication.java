package ma.enset.exam.dao;

import ma.enset.exam.dao.entites.Consultation;
import ma.enset.exam.dao.entites.Medecin;
import ma.enset.exam.dao.entites.Patient;

import java.util.Date;
import java.util.List;

public class DaoApplication {
    public static void main(String[] args){
        ICabinetMetierImpl cdao = new ICabinetMetierImpl();


        /*
        Patient p = new Patient();
        p.setNom("patient");
        p.setPrenom("hich");
        p.setEmail("patient@gmail.com");
        p.setCin("hfg454");
        p.setTelephone("0611111111");
        Date date = new Date(2023,11,29);
        p.setDate_naissance(date);
        cdao.addPatient(p);List<Patient> patients = cdao.getAllPatients();
        for (Patient patient:patients) {
            System.out.println(patient.toString());
        }
        List<Patient> patients = cdao.findByQuery("mod");
        for (Patient patient:patients) {
            cdao.deletePatient(patient);
        }
         Medecin m = new Medecin();
        m.setNom("hicham");
        m.setPrenom("el moudni");
        m.setEmail("med@gmail.com");
        m.setTel("56388274783");
        cdao.addMedecin(m);
        Consultation c = new Consultation();
        c.setDate_consultation(new Date(2023,11,29));
        c.setPatient(cdao.findPatientById(28));
        c.setMedecin(cdao.findMedecinById(1));
        cdao.addConsultation(c);
        */




        List<Patient> patients = cdao.findByQuery("ff");
        for (Patient patient:patients) {
            System.out.println(cdao.getAllConsultationsPatient(patient));
        }



    }
}

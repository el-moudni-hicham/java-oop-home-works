package ma.enset.exam.dao;

import ma.enset.exam.dao.entites.Consultation;
import ma.enset.exam.dao.entites.Medecin;
import ma.enset.exam.dao.entites.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ICabinetMetierImpl implements ICabinetMetier{
    @Override
    public List<Patient> getAllPatients() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from patient");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Patient p = new Patient();
                p.setId_patient(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setCin(rs.getString(4));
                p.setTelephone(rs.getString(5));
                p.setEmail(rs.getString(6));
                p.setDate_naissance(rs.getDate(7));
                patients.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> findByQuery(String mc) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from patient where nom like ? ");
            pstm.setString(1,"%"+mc+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Patient p = new Patient();
                p.setId_patient(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setCin(rs.getString(4));
                p.setTelephone(rs.getString(5));
                p.setEmail(rs.getString(6));
                p.setDate_naissance(rs.getDate(7));
                patients.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public Patient addPatient(Patient p) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into patient(nom,prenom,cin,telephone,email,date_naissance) values (?,?,?,?,?,?)");
            pstm.setString(1,p.getNom());
            pstm.setString(2,p.getPrenom());
            pstm.setString(3,p.getCin());
            pstm.setString(4,p.getTelephone());
            pstm.setString(5,p.getEmail());
            int year = p.getDate_naissance().getYear();
            int month = p.getDate_naissance().getMonth();
            int day = p.getDate_naissance().getDate();
            pstm.setDate(6, new java.sql.Date(year - 1900, month - 1, day));
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    @Override
    public Boolean deletePatient(Patient p) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("delete from patient where id_patient=?");
            pstm.setInt(1,p.getId_patient());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Consultation> getAllConsultationsPatient(Patient p) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from consultation where id_patient=?");
            pstm.setInt(1,p.getId_patient());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Consultation c = new Consultation();
                c.setId_consultation(rs.getInt(1));
                c.setDate_consultation(rs.getDate(2));
                c.setPatient(findPatientById(rs.getInt(3)));
                c.setMedecin(findMedecinById(rs.getInt(4)));
                consultations.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return consultations;
    }

    @Override
    public List<Medecin> getAllMedecins() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Medecin> medecins = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from medecin");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Medecin m = new Medecin();
                m.setId_medecin(rs.getInt(1));
                m.setNom(rs.getString(2));
                m.setPrenom(rs.getString(3));
                m.setEmail(rs.getString(4));
                m.setTel(rs.getString(5));

                medecins.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medecins;
    }

    @Override
    public Medecin addMedecin(Medecin m) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into medecin(nom,prenom,email,tel) values (?,?,?,?)");
            pstm.setString(1,m.getNom());
            pstm.setString(2,m.getPrenom());
            pstm.setString(3, m.getEmail());
            pstm.setString(4, m.getEmail());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }

    @Override
    public Boolean deleteMedecin(Medecin m) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("delete from medecin where id_patient=?");
            pstm.setInt(1,m.getId_medecin());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Consultation> getAllConsultationsMedecin(Medecin m) {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from consultation where id_medecin=?");
            pstm.setInt(1,m.getId_medecin());
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                Consultation c = new Consultation();
                c.setId_consultation(rs.getInt(1));
                c.setDate_consultation(rs.getDate(2));
                c.setPatient(findPatientById(rs.getInt(3)));
                c.setMedecin(findMedecinById(rs.getInt(4)));
                consultations.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return consultations;
    }

    @Override
    public List<Consultation> getAllConsultations() {
        Connection connection = SingletonConnexionDB.getConnection();
        List<Consultation> consultations = new ArrayList<>();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from consultation");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Consultation c = new Consultation();
                c.setId_consultation(rs.getInt(1));
                c.setDate_consultation(rs.getDate(2));
                c.setPatient(findPatientById(rs.getInt(3)));
                c.setMedecin(findMedecinById(rs.getInt(4)));

                consultations.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consultations;
    }

    @Override
    public Consultation addConsultation(Consultation c) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("insert into consultation values (?,?,?,?)");
            pstm.setInt(1,c.getId_consultation());
            int year = c.getDate_consultation().getYear();
            int month = c.getDate_consultation().getMonth();
            int day = c.getDate_consultation().getDate();
            pstm.setDate(2, new java.sql.Date(year-1900, month-1 , day));
            pstm.setInt(3,c.getPatient().getId_patient());
            pstm.setInt(4,c.getMedecin().getId_medecin());
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public Boolean deleteConsultation(Consultation c) {
        Connection connection = SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("delete from medecin where id_consultation=?");
            pstm.setInt(1,c.getId_consultation());
            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Patient findPatientById(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        Patient p = new Patient();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from patient where id_patient=?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                p.setId_patient(rs.getInt(1));
                p.setNom(rs.getString(2));
                p.setPrenom(rs.getString(3));
                p.setCin(rs.getString(4));
                p.setTelephone(rs.getString(5));
                p.setEmail(rs.getString(6));
                p.setDate_naissance(rs.getDate(7));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public Medecin findMedecinById(int id) {
        Connection connection = SingletonConnexionDB.getConnection();
        Medecin m = new Medecin();
        try {
            PreparedStatement pstm = connection.prepareStatement("select * from medecin where id_medcin=?");
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                m.setId_medecin(rs.getInt(1));
                m.setNom(rs.getString(2));
                m.setPrenom(rs.getString(3));
                m.setEmail(rs.getString(4));
                m.setTel(rs.getString(5));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return m;
    }

    @Override
    public Consultation findConsultationById(int id) {
        return null;
    }
}

package ma.enset.exam.presentation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ma.enset.exam.dao.ICabinetMetier;
import ma.enset.exam.dao.ICabinetMetierImpl;
import ma.enset.exam.dao.entites.Consultation;
import ma.enset.exam.dao.entites.Patient;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @FXML
    private TextField textNom;

    @FXML
    private TextField textPrenom;

    @FXML
    private TextField textCin;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textTelephone;

    @FXML
    private DatePicker textDate;

    @FXML
    private TableView<Patient> patientsTableView;

    @FXML
    private TableColumn<Patient, Integer> colId;

    @FXML
    private TableColumn<Patient, String> colNom;

    @FXML
    private TableColumn<Patient, String> colPrenom;

    @FXML
    private TableColumn<Patient, String> colCin;

    @FXML
    private TableColumn<Patient, String> colEmail;

    @FXML
    private TableColumn<Patient, String> colTelephone;

    @FXML
    private TableColumn<Patient, Date> colDate;

    private ObservableList<Patient> patientsList = FXCollections.observableArrayList();

    private ICabinetMetier patientService;
    @FXML
    private ListView<Consultation> listView = new ListView<>();
    @FXML
    private ObservableList<Consultation> consultsList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientService = new ICabinetMetierImpl();
        patientsTableView.setItems(patientsList);

        colId.setCellValueFactory(new PropertyValueFactory<>("id_patient"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        colTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));

        loadPatients();
    }

    private void loadPatients(){
        patientsList.clear();
        patientsList.addAll(patientService.getAllPatients());
    }

    @FXML
    public void addPatient() {
        String nom = textNom.getText();
        String prenom = textPrenom.getText();
        String cin = textCin.getText();
        String email = textEmail.getText();
        String tel = textTelephone.getText();
        LocalDate date = textDate.getValue();
        int year = date.getYear();
        int month =  date.getMonthValue();
        int day = date.getDayOfMonth();

        Date d = new Date(year,month,day);
        Patient p = new Patient();
        p.setNom(nom);
        p.setPrenom(prenom);
        p.setCin(cin);
        p.setTelephone(tel);
        p.setEmail(email);
        p.setDate_naissance(d);

        patientService.addPatient(p);
        loadPatients();

    }
    @FXML
    private void deletePatient(){
        MultipleSelectionModel<Patient> patient = patientsTableView.getSelectionModel();
        patientService.deletePatient(patient.getSelectedItem());
        loadPatients();
    }


    public void ConsultOfOne() {
        MultipleSelectionModel<Patient> patient = patientsTableView.getSelectionModel();
        Patient pat = patient.getSelectedItem();
        ICabinetMetier consultService = new ICabinetMetierImpl();
        listView.setItems(consultsList);
        consultsList.clear();
        consultsList.addAll(consultService.getAllConsultationsPatient(pat));
    }
}
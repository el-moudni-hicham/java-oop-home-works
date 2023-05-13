package ma.enset.exam.presentation.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ma.enset.exam.dao.ICabinetMetier;
import ma.enset.exam.dao.ICabinetMetierImpl;
import ma.enset.exam.dao.entites.Consultation;
import ma.enset.exam.dao.entites.Medecin;
import ma.enset.exam.dao.entites.Patient;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {

    @FXML
    private ComboBox<Medecin> textMedecin;

    @FXML
    private ComboBox<Patient> textPatient;

    @FXML
    private DatePicker textDate;

    @FXML
    private TableColumn<Consultation, Integer> colId;

    @FXML
    private TableColumn<Consultation, Patient> colPatient;

    @FXML
    private TableColumn<Consultation, Medecin> colMedecin;

    @FXML
    private TableColumn<Consultation, Date> colDate;

    @FXML
    private TableView<Consultation> tableView;

    ObservableList<Consultation> observableListCons = FXCollections.observableArrayList();
    ObservableList<Medecin> observableListMed = FXCollections.observableArrayList();
    ObservableList<Patient> observableListPat = FXCollections.observableArrayList();
    ICabinetMetier consultService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        consultService = new ICabinetMetierImpl();
        tableView.setItems(observableListCons);
        observableListCons.setAll(consultService.getAllConsultations());

        observableListMed.setAll(consultService.getAllMedecins());
        textMedecin.setItems(observableListMed);

        observableListPat.setAll(consultService.getAllPatients());
        textPatient.setItems(observableListPat);
    }

    @FXML
    void addConsultation() {

    }

    @FXML
    void deleteConsultation() {

    }
}

package pitchfork_practitioners;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class NurseViewController {

    
    @FXML
    private Button messageCenterButton;

    @FXML
    private Button backButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button takeVitalsButton;

    @FXML
    private Button preExaminationButton;

    @FXML
    private Label patientsLabel;

    @FXML
    private Label nurseViewPt1Label;

    @FXML
    private Accordion patientsAccordion;

    @FXML
    private TitledPane selectPatientTitledPane;

    @FXML
    public void initialize() {
        populatePatientsAccordion();
    }

    @FXML
    private void populatePatientsAccordion() {
            TitledPane selectRecipientPane = (TitledPane) patientsAccordion.getPanes().get(0);
        
            VBox patientsList = new VBox();

            Database database = Database.getInstance();
            List<String> patientIDs = database.getAllPatientIDs();

            for (String patientId : patientIDs) {
                Button patientButton = new Button(patientId);
                patientButton.setOnAction(event -> handlePatientSelection(patientId));
                patientsList.getChildren().add(patientButton);
            }
            selectRecipientPane.setContent(patientsList);
        }

    @FXML
    private void handlePatientSelection(String patientId) {
        // Add your implementation here
    }
    
    @FXML
    private void handleMessageCenterButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageCenterView.fxml"));
            Parent root = loader.load();
            MessageCenterController controller = loader.getController();
            controller.setPreviousFXML("NurseView.fxml", "Nurse"); 
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleLogoutButtonAction(ActionEvent event) {
    	try {
			navigateTo("LoginPage.fxml", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    private void handleTakeVitalsButtonAction(ActionEvent event) {
    	try {
			navigateTo("TakeVitals.fxml", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    private void handlePastVisitsButtonAction() {
    	
    }
    @FXML
    private void handlePreExaminationButtonAction(ActionEvent event) {
    	try {
			navigateTo("NursePreExamination.fxml", event);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void navigateTo(String fxmlFile, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

    

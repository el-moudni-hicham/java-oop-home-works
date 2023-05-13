package ma.enset.exam.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationFX extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("view/dashboardView.fxml"));
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("css/style.css").toString());
        primaryStage.setTitle("Cabinet Mnagement");
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("view/img/logo.png")));
        //primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

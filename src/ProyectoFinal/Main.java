package ProyectoFinal;

import ProyectoFinal.controller.ContenedorPrincipalController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("view/contenedor-principal.fxml"));
        Parent root = loader.load();
        ContenedorPrincipalController controller=loader.getController();
        controller.setPrimaryStage(primaryStage);
        primaryStage.setTitle("Mecanicos Don Jose");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

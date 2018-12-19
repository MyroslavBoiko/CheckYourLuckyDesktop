package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Main extends AbstractJavaFxApplicationSupport {

    @Qualifier("mainView")
    @Autowired
    private ControllersConfiguration.ViewHolder view;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Itera random");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view.getView()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launchApp(Main.class, args);
    }
}

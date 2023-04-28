import Utils.DataSource;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.SQLException;



public class Main extends Application {




    public static void main(String[] args) throws SQLException {
        Connection conn = DataSource.getCon();
        Application.launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = FXMLLoader.load(getClass().getResource("./Views/AcceuilBanque.fxml"));
        primaryStage.setTitle("Acceuil Banque");
        primaryStage.setScene(new Scene(root, 900, 506));
        primaryStage.show();
    }


}

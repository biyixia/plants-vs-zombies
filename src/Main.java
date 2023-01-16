import javafx.application.Application;
import javafx.stage.Stage;

/**
 * @author dbc
 * @create ${YEAR}-${MONTH}-${DAY} ${TIME}
 */
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Director instance = Director.getInstance();
        instance.init(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
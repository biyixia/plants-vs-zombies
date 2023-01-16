import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author dbc
 * @create 2023-01-16 11:51
 */
public class Director {
    public static final double width = 800,height = 600;
    private Director(){}
    private static Director instance = new Director();

    public static Director getInstance(){
        return instance;
    }

    public void init(Stage stage){
        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root, width, height);
        stage.setTitle("植物大战僵尸");
        stage.getIcons().add(new Image("images/LoadFrame/ico.png"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

package pers.zhc.u.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFxTest extends Application {
    @Override
    public void start(Stage primaryStage) {
        StackPane stackPane = new StackPane();
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.show();

        TextArea textArea = new TextArea();
        stackPane.getChildren().add(textArea);
        textArea.textProperty().addListener((observable, oldValue, newValue) -> System.out.println(observable + " " + oldValue + " " + newValue));
    }

    static void main(String[] args) {
        launch(args);
    }
}

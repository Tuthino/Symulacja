
package gradle;


import java.util.ArrayList;
import java.util.List;
import gradle.controler;

import com.google.common.base.Optional;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        Group root  = new Group();
        Scene scene = new Scene(root, 640, 480);
        List<Rectangle> rectangles = new ArrayList<>();

        createRectangles(rectangles, 5);

        addRectangleToRoot(root, rectangles);
        stage.setScene(scene);
        stage.show();
    }

    // Goes through whole list and adds rectangles to the root group
    public void addRectangleToRoot(Group root, List<Rectangle> rectangles){
        for(int i = 0;i < rectangles.size() ; i++){
            root.getChildren().add(rectangles.get(i));
        }
    }

    //Create simple boxes
    public void createRectangles(List<Rectangle> rectangles,int rect_num){
        for(int i = 0; i < rect_num ; i++){
            rectangles.add(new Rectangle());    
            rectangles.get(i).setX(50.0+i*100.0);
            rectangles.get(i).setY(50.0);
            rectangles.get(i).setWidth(50);
            rectangles.get(i).setHeight(50);
        }   
    }


}
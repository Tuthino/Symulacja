package gradle;


import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Map extends Application {
    
    @Override
    public void start(Stage stage) {
        Group root  = new Group();
        Scene scene = new Scene(root, 640, 480);
        List<Rectangle> rectangles = new ArrayList<>();

        createRectangles(rectangles, 5);

        addRectangleToRoot(root, rectangles);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            
            @Override
            public void handle(KeyEvent event){
                System.out.println(event.getCode());
                if(event.getCode().toString()=="DOWN"){
                    double rect_y = rectangles.get(0).getY();
                    rectangles.get(0).setY(rect_y-10);;
                }
            }

        }); 

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

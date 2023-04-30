package gradle;


import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Map extends Application {
    //Hardcoded Scene size for testing 
    int scene_size = 600;

    @Override
    public void start(Stage stage) {
        Group root  = new Group();
        Scene scene = new Scene(root, scene_size+50, scene_size+50);
        List<List<Rectangle>> rectangles = new ArrayList<>();

        createRectangles(rectangles, scene_size/100, scene_size/100);

        addRectangleToRoot(root, rectangles);

        stage.setScene(scene);
        stage.show();
    }

    // Goes through whole list and adds rectangles to the root group
    public void addRectangleToRoot(Group root, List<List<Rectangle>> rectangles){
        for(int i = 0;i < rectangles.size() ; i++){
            for(int j = 0; j < rectangles.get(i).size(); j++){
                root.getChildren().add(rectangles.get(i).get(j));
            }
        }
    }

    //Create simple boxes
    public void createRectangles(List<List<Rectangle>> rectangles,int rect_num_x, int rect_num_y){
            for(int i = 0; i < rect_num_y ; i++){
                rectangles.add(new ArrayList<>());    

                for(int j = 0; j < rect_num_x; j++){
                    rectangles.get(i).add(new Rectangle());    
                    rectangles.get(i).get(j).setX(50.0+j*100.0);
                    rectangles.get(i).get(j).setY(50.0+i*100.0);
                    rectangles.get(i).get(j).setWidth(50);
                    rectangles.get(i).get(j).setHeight(50);
                }
        }   
    }


}

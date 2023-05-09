package gradle;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import gradle.Box;
import javafx.stage.Stage;

public class Map extends Application {
    // Hardcoded Scene size for testing
    int scene_size = 600;
    String scooby_image = "/Scooby.png";

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, scene_size + 50, scene_size + 50);
        List<Box> boxes = new ArrayList<>();


        createBoxes(boxes, scene_size / 100, scene_size / 100);

        addBoxToRoot(root, boxes);

        // Add scooby :D 
        MainCharacter Scooby = new MainCharacter(150, 150, scooby_image);
        root.getChildren().add(Scooby);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event){
                //Call movement funciotns
                switch(event.getCode()){
                    case UP:
                        Scooby.moveUp(Scooby,root,boxes);
                        break;
                    case DOWN:
                        Scooby.moveDown(Scooby,root,boxes);
                        break;
                    case RIGHT:
                        Scooby.moveRight(Scooby,root,boxes);                        
                        break;
                    case LEFT:
                        Scooby.moveLeft(Scooby,root,boxes);
                        break;
                }
                

            }
        });
        
        
        stage.setScene(scene);
        stage.show();
        

    }







    // ###################### BOXES ################################### //

    // Goes through whole list and adds boxes to the root group
    public void addBoxToRoot(Group root, List<Box> boxes) {
        for (int i = 0; i < boxes.size(); i++) {
                root.getChildren().add(boxes.get(i));
        }
    }

    // Create simple boxes
    public void createBoxes(List<Box> boxes, int rect_num_x, int rect_num_y) {
        int counter = 0;
        for (int i = 0; i < rect_num_y; i++) {
            // boxes.add(new ArrayList<>());

            for (int j = 0; j < rect_num_x; j++) {
                boxes.add(new Box());
                boxes.get(counter).setX((50.0 + j * 100.0));
                boxes.get(counter).setY(50.0 + i * 100.0);
                boxes.get(counter).setWidth(49);
                boxes.get(counter).setHeight(49);
                counter++;
            }
        }
    }

}

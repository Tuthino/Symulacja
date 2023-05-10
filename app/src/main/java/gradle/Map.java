package gradle;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Map extends Application {
    // Hardcoded Scene size for testing
    private int scene_size = 600;
    private String scooby_image = "/Scooby.png";
    private String red_ghost_image = "/red_ghost.jpg";


    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, scene_size + 50, scene_size + 50);
        List<Box> boxes = new ArrayList<>();


        createBoxes(boxes, scene_size / 100, scene_size / 100);

        addBoxToRoot(root, boxes);

        // Add scooby :D 
        MainCharacter Scooby = new MainCharacter(0, 0, scooby_image);
        root.getChildren().add(Scooby);
        // Add Ghost :D
        Looker ghost_looker = new Looker(150, 150, red_ghost_image);
        root.getChildren().add(ghost_looker);






        // ########## MOVING ON KEY PRESS ##################

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event){
                //Call movement funciotns
                switch(event.getCode()){
                    case SPACE:
                        String direction = ghost_looker.get_random_movement();
                        ghost_looker.move(ghost_looker, root, boxes, direction);
                        break;
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
       
        // ########## MOVING ON KEY PRESS (END) ################### 
        
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

package gradle;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.robot.Robot;
import javafx.stage.Stage;

public class Map extends Application {
    // Hardcoded Scene size for testing
    private double scene_size = 600;
    private String scooby_image = "/Scooby.png";
    private String red_ghost_image = "/red_ghost.jpg";
    private boolean move_ghost = false;
    private boolean animate = false;
    protected static List<MainCharacter> main_characters = new ArrayList<>();
    protected static List<Looker> ghosts_lookers = new ArrayList<>();
    protected static List<Box> boxes = new ArrayList<>();
    protected static Group root;
    protected static Scene scene;

    @Override
    public void start(Stage stage) {
        root = new Group();
        scene = new Scene(root, scene_size + 50, scene_size + 50);

        // ############## ADD CHARACTERS TO THE MAP #####################

        // Add scooby :D
        MainCharacter Scooby = new MainCharacter(0, 0, scooby_image, "Scooby");
        main_characters.add(Scooby);
        root.getChildren().add(main_characters.get(0));
        // Add Ghost :D
        Looker ghost_looker = new Looker(150, 150, red_ghost_image);
        ghosts_lookers.add(ghost_looker);
        root.getChildren().add(ghosts_lookers.get(0));

        // ############## ADD CHARACTERS TO THE MAP (END) ###################

        createBoxes(boxes, scene_size / 100, scene_size / 100);

        addBoxToRoot(root, boxes);

        // ########## MOVING ON KEY PRESS ##################
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                // Call movement funciotns
                switch (event.getCode()) {
                    case UP:
                        Map.main_characters.get(0).moveUp(Map.main_characters.get(0));
                        break;
                    case DOWN:
                        Map.main_characters.get(0).moveDown(Map.main_characters.get(0));
                        break;
                    case RIGHT:
                        Map.main_characters.get(0).moveRight(Map.main_characters.get(0));
                        break;
                    case LEFT:
                        Map.main_characters.get(0).moveLeft(Map.main_characters.get(0));
                        break;
                }

            }
        });

        // ########## MOVING ON KEY PRESS (END) ###################

        stage.setScene(scene);
        stage.show();
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new MyAnimate(), 0, 1, TimeUnit.SECONDS);

    }

    // ###################### BOXES ################################### //

    // Goes through whole list and adds boxes to the root group
    public void addBoxToRoot(Group root, List<Box> boxes) {
        for (int i = 0; i < boxes.size(); i++) {
            root.getChildren().add(boxes.get(i));
        }
    }

    // Create simple boxes
    public void createBoxes(List<Box> boxes, double rect_num_x, double rect_num_y) {
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

    // ###################### BOXES (END) ################################### //

    public double getMapWidth() {
        return scene_size;
    }

    public double getMapHeight() {
        return scene_size;
    }

    public void setMapSize(int size) {
        this.scene_size = size;
    }

    public List<MainCharacter> getMainCharacters() {
        return main_characters;
    }

    public List<Looker> getLookers() {
        return ghosts_lookers;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public Group getRootGroup() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }
}

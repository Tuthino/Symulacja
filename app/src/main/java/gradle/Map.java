package gradle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Map extends Application {
    // Hardcoded Scene size for testing
    public static double scene_size = 600;
    private String scooby_image = "Scooby.png";
    private String red_ghost_image = "red_ghost.jpg";
    private String ham_image = "ham_img.jpg";
    private String scooby_crisp_image = "scooby_crisp_img.jpg";
    private String pancake_image = "pancake_img.jpg";
    private String yellow_ghost_image = "yellow_ghost.jpg";
    private String blue_ghost_image = "blue_ghost.jpg";
    int Ham_number;
    int Pancake_number;
    int Scooby_crisp_number;

    protected static double character_size = 45;
    protected static List<MainCharacter> main_characters = new ArrayList<>();
    protected static List<Looker> ghosts_lookers = new ArrayList<>();
    protected static List<Listener> ghosts_listeners = new ArrayList<>();
    protected static List<Wallhacker> ghosts_wallhackers = new ArrayList<>();
    protected static List<Box> boxes = new ArrayList<>();

    protected static List<Food> food_list = new ArrayList<>();
    protected static Group root;
    protected static Scene scene;

    @Override
    public void start(Stage stage) {
        root = new Group(); // podscena
        scene = new Scene(root, scene_size + 50, scene_size + 50); // scena o danych wymiarach
        // FXMLLoader loader = new FXMLLoader(Map.class.getResource("Symulacja.fxml"));

        // ############## ADD CHARACTERS TO THE MAP #####################

        // Add scooby :D
        MainCharacter Scooby = new MainCharacter(0, 0, scooby_image, "Scooby");
        Scooby.setStepSize(10);
        main_characters.add(Scooby); // dodatnie do tablicy main_characters
        root.getChildren().add(main_characters.get(0)); // dodanie postaci do root

        // // Add Lookers :D
        ghosts_lookers.add(new Looker(100, 100, red_ghost_image)); // dodanie do ghosts_lookers
        root.getChildren().add(ghosts_lookers.get(0)); // dodanie postaci do root

        // Add Listeners :D 
        ghosts_listeners.add(new Listener(100, 100, yellow_ghost_image));
        root.getChildren().add(ghosts_listeners.get(0)); // dodanie postaci do root

        // Add Wallhackers :D
        ghosts_wallhackers.add(new Wallhacker(300, 300, blue_ghost_image));
        root.getChildren().add(ghosts_wallhackers.get(0)); // dodanie postaci do root


        // ############## ADD CHARACTERS TO THE MAP (END) ###################

        createBoxes(boxes, scene_size / 100, scene_size / 100);
        addBoxToRoot(root, boxes);

        // Add Food

        // TODO Making buttons for the options (for example version1: 3 Ham, 4 Pancake,
        // 1 Scooby_crisp; version2: 5 Ham, ...)
        // Asking how many items they want

        // Scanner input = new Scanner(System.in);

        // System.out.println("How many Ham items do you want?");
        // System.out.print("  Ham: ");

        // try {
        //     while(!input.hasNextInt()){
        //         input.next();
        //         System.out.print("  Please give a number: ");
                                
        //     };
        //     Ham_number = input.nextInt();

        //     for (int i = 0; i < Ham_number; i++) {
        //         food_list.add(new Food(ham_image));
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     e.getCause();
        // }

        // System.out.println("How many Pancake items do you want?");
        // System.out.print("  Pancake: ");
        // try {
        //     while(!input.hasNextInt()){
        //         input.next();
        //         System.out.print("  Please give a number: ");
                                
        //     };
        //     Pancake_number = input.nextInt();

        //     for (int i = 0; i < Pancake_number; i++) {
        //         food_list.add(new Food(pancake_image));
        //     }

        // } catch (Exception e) {
        //     e.printStackTrace();
        //     e.getCause();
        // }

        // System.out.println("How many Scooby_crisp items do you want?");
        // System.out.print("  Scooby_crisp: ");
        // try {
        //     while(!input.hasNextInt()){
        //         input.next();
        //         System.out.print("  Please give a number: ");
                                
        //     };
        //     Scooby_crisp_number = input.nextInt();
        //     for (int i = 0; i < Scooby_crisp_number; i++) {
        //         food_list.add(new Food(scooby_crisp_image));
        //     }

        // } catch (Exception e) {
        //     e.printStackTrace();
        //     e.getCause();
        // }

        // Adding food to the list

        addFoodToRoot(root, food_list);

        // ########## MOVING ON KEY PRESS ##################
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                // Call movement funcions
                Map.main_characters.get(0).steps_left = 2;
                Map.main_characters.get(0).setMovingDirection(event.getCode().toString());
                Map.main_characters.get(0).move(event.getCode().toString());
            }
        });

        // ########## MOVING ON KEY PRESS (END) ###################
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1); // tworzenie harmonogramu z jednym
                                                                                 // wątkiem
        executor.scheduleAtFixedRate(new MyAnimate(), 0, 200, TimeUnit.MILLISECONDS); // zadanie zostanie uruchomione
                                                                                      // natychmiast co 200 milisekund

        stage.setScene(scene);
        stage.show();
    }

    // ###################### FOOD #################################### //

    // Add all Food object to the root group
    private void addFoodToRoot(Group root, List<Food> food_list) {
        for (Food element : food_list) {
            root.getChildren().add(element);
        }
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
        for (int i = 0; i < rect_num_y; i++) {
            // boxes.add(new ArrayList<>());
            for (int j = 0; j < rect_num_x; j++) {
                boxes.add(new Box(i, j));
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
        Map.scene_size = size;
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

    public List<Food> getFood_list() {
        return food_list;
    }

    public Group getRootGroup() {
        return root;
    }

    public Scene getScene() {
        return scene;
    }
}

package gradle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Map extends Application {
    // Hardcoded Scene size for testing
    public static int scene_size = 300;
    protected static String level;
    protected static String ghost;
    private String scooby_image = "Scooby.png";
    private String red_ghost_image = "red_ghost.jpg";
    private String ham_image = "ham_img.jpg";
    private String scooby_crisp_image = "scooby_crisp_img.jpg";
    private String pancake_image = "pancake_img.jpg";
    private String yellow_ghost_image = "yellow_ghost.jpg";
    private String blue_ghost_image = "blue_ghost.jpg";
    private String PacDoo_image = "PacDoo.jpg";
    private String Ghosts_image = "Ghosts.jpg";
    private String Level_image = "Level.jpg";
    int Ham_number;
    int Pancake_number;
    int Scooby_crisp_number;

    protected static double character_size = 45;
    protected static List<MainCharacter> main_characters = new ArrayList<>();
    protected MainCharacter Scooby;
    // protected static List<Looker> ghosts_lookers = new ArrayList<>();
    // protected static List<Listener> ghosts_listeners = new ArrayList<>();
    // protected static List<Wallhacker> ghosts_wallhackers = new ArrayList<>();
    protected static List<Box> boxes = new ArrayList<>();
    protected static List<List<Ghost>> ghosts = new ArrayList<List<Ghost>>();
    protected static List<Food> food_list = new ArrayList<>();
    protected static Stage stage;
    protected static Group opening_root;
    protected static Group choosing_level_root;
    protected static Group choosing_ghost_root;
    protected static Group root;
    protected static Scene opening_scene;
    protected static Scene choosing_level_scene;
    protected static Scene choosing_ghost_scene;
    protected static Scene scene;
    protected static ScheduledExecutorService executor;
    protected static int seconds;
    protected static Timeline timeline;
    @Override
    public void start(Stage stage) {
        // Thanks to this override, application ends after closing windows

        /*stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                // @TODO We can list stats here ;p
                Platform.exit();
                System.exit(0);
            }
        });*/

        // ############## ADD MAIN SCENE #####################

        root = new Group(); // podscena
        scene = new Scene(root, scene_size + 50, scene_size + 50); // scena o danych wymiarach
        // FXMLLoader loader = new FXMLLoader(Map.class.getResource("Symulacja.fxml"));

        // Adding time

        Label timerLabel = new Label("Time: 0 seconds");
        root.getChildren().add(timerLabel);

        Duration duration = Duration.seconds(1);
        KeyFrame keyFrame = new KeyFrame(duration, event -> {
            seconds++;
            timerLabel.setText("Time: " + seconds + " seconds");
        });

        // Tworzenie Timeline z powtarzającymi się KeyFrame'ami
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);

        scene.setFill(Color.BLACK);

        // ############## ADD OPENINING SCENE #####################

        opening_root = new Group(); // podscena
        Button opening_button = new Button("Start the simulation");
        opening_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(choosing_level_scene);
                /*timeline.play();
                executor = Executors.newScheduledThreadPool(1);                                                  // tworzenie harmonogramu z jednym wątkiem
                executor.scheduleAtFixedRate(new MyAnimate(), 0, 200, TimeUnit.MILLISECONDS);                    // zadanie zostanie uruchomione natychmiast co 200 milisekund
                */
            }
        });
        opening_root.getChildren().add(opening_button);

        opening_button.setLayoutX(140);
        opening_button.setLayoutY(220);
        opening_button.setTextFill(Color.WHITE);
        opening_button.setStyle("-fx-background-color: blue");

        ImageView imageView = new ImageView(PacDoo_image);
        opening_root.getChildren().add(imageView);
        imageView.setFitHeight(80);
        imageView.setFitWidth(300);
        imageView.setLayoutX(50);
        imageView.setLayoutY(130);

        opening_scene = new Scene(opening_root, 400, 400);
        opening_scene.setFill(Color.BLACK);

        // ############## ADD CHOOSING_LEVEL SCENE #####################

        choosing_level_root = new Group(); // podscena

        Button easyLevelButton = new Button("Easy");
        easyLevelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level = "Easy";

                setFoodNumbers(1,1, 1);
                addFoodToList();
                addFoodToRoot(root, food_list);
                stage.setScene(choosing_ghost_scene);
            }
        });

        Button mediumLevelButton = new Button("Medium");
        mediumLevelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level = "Medium";

                setFoodNumbers(2,2, 2);
                addFoodToList();
                addFoodToRoot(root, food_list);
                stage.setScene(choosing_ghost_scene);
            }
        });

        Button hardLevelButton = new Button("Hard");
        hardLevelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                level = "Hard";

                setFoodNumbers(3,3, 3);
                addFoodToList();
                addFoodToRoot(root, food_list);
                stage.setScene(choosing_ghost_scene);
            }
        });
        ImageView imageView1 = new ImageView(Level_image);
        choosing_level_root.getChildren().add(imageView1);
        imageView1.setFitHeight(80);
        imageView1.setFitWidth(300);
        imageView1.setLayoutX(50);
        imageView1.setLayoutY(130);

        choosing_level_root.getChildren().add(easyLevelButton);
        easyLevelButton.setLayoutX(90);
        easyLevelButton.setLayoutY(220);
        easyLevelButton.setTextFill(Color.WHITE);
        easyLevelButton.setStyle("-fx-background-color: blue");

        choosing_level_root.getChildren().add(mediumLevelButton);
        mediumLevelButton.setLayoutX(170);
        mediumLevelButton.setLayoutY(220);
        mediumLevelButton.setTextFill(Color.WHITE);
        mediumLevelButton.setStyle("-fx-background-color: blue");

        choosing_level_root.getChildren().add(hardLevelButton);
        hardLevelButton.setLayoutX(270);
        hardLevelButton.setLayoutY(220);
        hardLevelButton.setTextFill(Color.WHITE);
        hardLevelButton.setStyle("-fx-background-color: blue");

        choosing_level_scene = new Scene(choosing_level_root, 400, 400);
        choosing_level_scene.setFill(Color.BLACK);

        // ############## ADD CHOOSING_GHOST SCENE #####################

        choosing_ghost_root = new Group(); // podscena

        // Making ghosts list
        for (int i = 0; i < 3; i++) {
            ghosts.add(new ArrayList<>());
        }

        Button WallhackerButton = new Button("Wallhacker");
        WallhackerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ghost = "Wallhacker";

                ghosts.get(2).add(new Wallhacker(300, 300, blue_ghost_image));
                addGhostsToRoot(root, ghosts);
                changingToMainScene(stage, scene, timeline);
            }
        });

        Button LookerButton = new Button("Looker");
        LookerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ghost = "Looker";

                ghosts.get(0).add(new Looker(100, 100, red_ghost_image));
                addGhostsToRoot(root, ghosts);
                changingToMainScene(stage, scene, timeline);
            }
        });

        Button ListenerButton = new Button("Listener");
        ListenerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ghost = "Listener";

                ghosts.get(1).add(new Listener(100, 100, yellow_ghost_image));
                addGhostsToRoot(root, ghosts);
                changingToMainScene(stage, scene, timeline);
            }
        });

        ImageView imageView2 = new ImageView(Ghosts_image);
        choosing_ghost_root.getChildren().add(imageView2);
        imageView2.setFitHeight(80);
        imageView2.setFitWidth(300);
        imageView2.setLayoutX(50);
        imageView2.setLayoutY(130);

        choosing_ghost_root.getChildren().add(WallhackerButton);
        WallhackerButton.setLayoutX(149);
        WallhackerButton.setLayoutY(220);

        ImageView blueGhost = new ImageView(blue_ghost_image);
        blueGhost.setFitWidth(32);
        blueGhost.setFitHeight(32);

        WallhackerButton.setGraphic(blueGhost);
        WallhackerButton.setTextFill(Color.WHITE);
        WallhackerButton.setStyle("-fx-background-color: blue");

        choosing_ghost_root.getChildren().add(ListenerButton);
        ListenerButton.setLayoutX(50);
        ListenerButton.setLayoutY(220);

        ImageView yellowGhost = new ImageView(yellow_ghost_image);
        yellowGhost.setFitWidth(32);
        yellowGhost.setFitHeight(32);

        ListenerButton.setGraphic(yellowGhost);
        ListenerButton.setTextFill(Color.WHITE);
        ListenerButton.setStyle("-fx-background-color: blue");

        choosing_ghost_root.getChildren().add(LookerButton);
        LookerButton.setLayoutX(265);
        LookerButton.setLayoutY(220);

        ImageView redGhost = new ImageView(red_ghost_image);
        redGhost.setFitWidth(32);
        redGhost.setFitHeight(32);

        LookerButton.setGraphic(redGhost);
        LookerButton.setTextFill(Color.WHITE);
        LookerButton.setStyle("-fx-background-color: blue");

        choosing_ghost_scene = new Scene(choosing_ghost_root, 400, 400);
        choosing_ghost_scene.setFill(Color.BLACK);

        // ############## ADD CHARACTERS TO THE MAP #####################

        // Add scooby :D
        Scooby = new MainCharacter(0, 0, scooby_image, "Scooby");
        Scooby.setStepSize(10);
        main_characters.add(Scooby); // dodatnie do tablicy main_characters
        root.getChildren().add(main_characters.get(0)); // dodanie postaci do root

        /* Adding Scooby details
        Label pointsLabel = new Label("Points: 0");
        root.getChildren().add(pointsLabel);
        pointsLabel.setLayoutX(scene_size/2);

        KeyFrame keyFrame1 = new KeyFrame(duration, event -> {
            pointsLabel.setText("Points: " + Scooby.getCharacter_points());
        });

        Label scaringLabel = new Label("Scaring level: 0");
        root.getChildren().add(scaringLabel);
        scaringLabel.setLayoutX(scene_size-40);

        KeyFrame keyFrame2 = new KeyFrame(duration, event -> {
            scaringLabel.setText("Scaring level: " + Scooby.getScaring_level());
        });*/

        // ############## ADD CHARACTERS TO THE MAP (END) ###################

        createBoxes(boxes, scene_size / 100, scene_size / 100);
        addBoxToRoot(root, boxes);

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

        stage.setScene(opening_scene);
        stage.show();
    }

    private void changingToMainScene(Stage stage, Scene scene, Timeline timeline){
        stage.setScene(scene);
        timeline.play();
        executor = Executors.newScheduledThreadPool(1);                              // tworzenie harmonogramu z jednym wątkiem
        executor.scheduleAtFixedRate(new MyAnimate(), 0, 200, TimeUnit.MILLISECONDS);                    // zadanie zostanie uruchomione natychmiast co 200 milisekund
    }

    // ###################### FOOD #################################### //

    // Add food to the list
    private void addFoodToList(){
        for (int i = 0; i < Scooby_crisp_number; i++) {
            food_list.add(new Food(scooby_crisp_image, "Scooby_crisp"));
        }
        for (int i = 0; i < Pancake_number; i++) {
            food_list.add(new Food(pancake_image, "Pancake"));
        }
        for (int i = 0; i < Ham_number; i++) {
            food_list.add(new Food(ham_image, "Ham"));
        }
    }

    // Add all Food object to the root group
    private void addFoodToRoot(Group root, List<Food> food_list) {
        for (Food element : food_list) {
            root.getChildren().add(element);
        }
    }

    // ###################### GHOSTS #################################### //

    // Add all Ghosts object to the root group
    private void addGhostsToRoot(Group root, List<List<Ghost>> ghosts) {
        for (int i = 0; i < ghosts.size(); i++) {
            for (int j = 0; j < ghosts.get(i).size(); j++) {
                root.getChildren().add(ghosts.get(i).get(j));
            }
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

    public void setFoodNumbers(int ham, int pancakes, int crisp){
        Ham_number = ham;
        Pancake_number = pancakes;
        Scooby_crisp_number = crisp;
    }
    public void getFoodNumbersInput(){
        Scanner input = new Scanner(System.in);

        System.out.println("How many Ham items do you want?");
        System.out.print("  Ham: ");

        try {
            while (!input.hasNextInt()) {
                input.next();
                System.out.print("  Please give a number: ");
            }
            ;
            Ham_number = input.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        System.out.println("How many Pancake items do you want?");
        System.out.print("  Pancake: ");
        try {
            while (!input.hasNextInt()) {
                input.next();
                System.out.print("  Please give a number: ");
            }
            ;
            Pancake_number = input.nextInt();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        System.out.println("How many Scooby_crisp items do you want?");
        System.out.print("  Scooby_crisp: ");

        try {
            while (!input.hasNextInt()) {
                input.next();
                System.out.print("  Please give a number: ");
            }
            ;
            Scooby_crisp_number = input.nextInt();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

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

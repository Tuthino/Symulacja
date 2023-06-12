package gradle;

import javafx.application.Platform;

public class Ghost extends Character{
    private int scaringPoints;
    Ghost(double x, double y, String ImagePath, String name){
        super(x, y, ImagePath, name);
        this.setScaringPoints(10);
    }

    public void scaring() {
            for (MainCharacter mainCharacter: Map.main_characters) {
               if (this.getBoundsInParent().intersects(mainCharacter.getBoundsInParent())) {
                    mainCharacter.increaseScaringLvl( this.getScaringPoints());
                    System.out.println("  scaring " + mainCharacter.getScaring_level());
            }
            if( mainCharacter.getScaring_level()>=100 ){
                mainCharacter.dying();
                System.out.println("Scooby DIED :((");
                mainCharacter.viewResults();
                Map.timeline.stop();
                Map.executor.shutdownNow();
                //Map.stage.setScene(Map.closing_scene);
                //Platform.exit();
                //System.exit(0);
            }
        }
    }

    public int getScaringPoints(){
        return scaringPoints;
    }

    public void setScaringPoints(int scaringPoints){
        this.scaringPoints = scaringPoints;
    }
}

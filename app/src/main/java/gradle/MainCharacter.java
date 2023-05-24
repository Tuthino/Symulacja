package gradle;

public class MainCharacter extends Character {

    private static int listening_size = 10;
    private static int scaring_level = 0;
    public MainCharacter(double x, double y, String imagePath, String name) {
        super(x, y, imagePath, name);
    }

    // TODO do funcions
    public void eating() {};
    public void getting_points() {}
    public void moving() {};
    public boolean check_if_ghost() {
        boolean is_nerby = false;
        for(int i=0; i<Map.ghosts.size(); i++){
            for (int j=0; j<Map.ghosts.get(i).size(); j++) {
                Ghost ghost = Map.ghosts.get(i).get(j);
                double distanceX = Math.abs(this.getMiddleX() - ghost.getMiddleX());
                double distanceY = Math.abs(this.getMiddleY() - ghost.getMiddleY());

                if (distanceX <= listening_size && this.getMiddleX() < ghost.getMiddleX()) {
                    setMovingDirection("LEFT");
                    is_nerby = true;
                } else if (distanceX <= listening_size && this.getMiddleX() > ghost.getMiddleX()) {
                    setMovingDirection("RIGHT");
                    is_nerby = true;
                } else if (distanceY <= listening_size && this.getMiddleY() < ghost.getMiddleY()) {
                    setMovingDirection("DOWN");
                    is_nerby = true;
                } else if (distanceY <= listening_size && this.getMiddleY() > ghost.getMiddleY()) {
                    setMovingDirection("UP");
                    is_nerby = true;
                }
            }
        }
        return is_nerby;
    }
    public void escape() {};
    public void check_if_food() {};
    public boolean get_scared() {
        for(int i=0; i<Map.ghosts.size(); i++){
            for (int j=0; j<Map.ghosts.get(i).size(); j++) {
                Ghost ghost = Map.ghosts.get(i).get(j);
                if (this.getBoundsInParent().intersects(ghost.getBoundsInParent())) {
                    scaring_level += ghost.getScaringPoints();
                    return true;
                }
            }
        }
        return false;
    }
    public void dying() {};
}

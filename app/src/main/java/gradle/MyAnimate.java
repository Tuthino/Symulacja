package gradle;

class MyAnimate implements Runnable {

    @Override
    public void run() {

        System.out.println("Animate task");
        System.out.println(Map.main_characters.get(0).getX());
        for (Looker ghost : Map.ghosts_lookers) {
            ghost.hitted_wall = false;
            int counter = 0;
            String direction = ghost.get_random_direction();
            while (ghost.hitted_wall == false && counter < 10) {
                switch (direction) {
                    case "UP":
                        ghost.moveUp(ghost);
                        counter++;

                        break;
                    case "DOWN":
                        ghost.moveDown(ghost);
                        counter++;

                        break;
                    case "RIGHT":
                        ghost.moveRight(ghost);
                        counter++;

                        break;
                    case "LEFT":
                        ghost.moveLeft(ghost);
                        counter++;

                        break;

                }
            }

        }
    }

}

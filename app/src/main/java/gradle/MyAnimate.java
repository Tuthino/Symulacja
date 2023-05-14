package gradle;

class MyAnimate implements Runnable {

    @Override
    public void run() {

        // System.out.println("Animate task");
        System.out.println(Map.main_characters.get(0).getX());
        for (Looker ghost : Map.ghosts_lookers) {
            ghost.hitted_wall = false;
            ghost.steps_left=5;
            if(ghost.checkIfEnemyIsDiagonal()) {} else {ghost.setRandomDirection();};
            System.out.println(ghost.getMovingDirection());

            while (ghost.hitted_wall == false && ghost.steps_left != 0) {
                switch (ghost.getMovingDirection()) {
                    case "UP":
                        ghost.moveUp();

                        break;
                    case "DOWN":
                        ghost.moveDown();

                        break;
                    case "RIGHT":
                        ghost.moveRight();

                        break;
                    case "LEFT":
                        ghost.moveLeft();

                        break;

                }
            }

        }
    }

}

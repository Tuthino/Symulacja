package gradle;

class MyAnimate implements Runnable {

    @Override
    public void run() {

        System.out.println("Animate task");
        System.out.println(Map.main_characters.get(0).getX());
        for (Looker ghost : Map.ghosts_lookers) {
            ghost.hitted_wall = false;
            int counter = 0;
            if(ghost.checkIfEnemyIsDiagonal()) {} else {ghost.setRandomDirection();};
            System.out.println(ghost.getMovingDirection());

            while (ghost.hitted_wall == false && counter < 1) {
                switch (ghost.getMovingDirection()) {
                    case "UP":
                        ghost.moveUp();
                        counter++;

                        break;
                    case "DOWN":
                        ghost.moveDown();
                        counter++;

                        break;
                    case "RIGHT":
                        ghost.moveRight();
                        counter++;

                        break;
                    case "LEFT":
                        ghost.moveLeft();
                        counter++;

                        break;

                }
            }

        }
    }

}

package gradle;

class MyAnimate implements Runnable {

    @Override
    public void run() {
        // System.out.println("Animate task");
        System.out.println(Map.main_characters.get(0).getX());


        for (Wallhacker ghost : Map.ghosts_wallhackers){
            ghost.steps_left = 2;
            if(ghost.checkWhereEnemy()) {

            } else if (ghost.hitted_wall==true){
                ghost.setRandomDirection();
            };
            ghost.hitted_wall = false;

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


        
        for (Listener ghost : Map.ghosts_listeners){
            ghost.steps_left = 2;
            if(ghost.checkIfNearby()) {

            } else if (ghost.hitted_wall==true){
                ghost.setRandomDirection();
            };
            ghost.hitted_wall = false;

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



        for (Looker ghost : Map.ghosts_lookers) {
            ghost.steps_left = 2;
            if(ghost.checkIfEnemyIsDiagonal()) {

            } else if (ghost.hitted_wall==true){
                ghost.setRandomDirection();
            };

            ghost.hitted_wall = false;

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

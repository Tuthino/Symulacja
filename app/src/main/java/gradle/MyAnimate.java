package gradle;

class MyAnimate implements Runnable {

    @Override
    public void run() {
        // System.out.println("Animate task");
        System.out.println(Map.main_characters.get(0).getX());

        for(MainCharacter mainCharacter : Map.main_characters){
             mainCharacter.steps_left = 2;

             if(mainCharacter.check_if_ghost()){
                 System.out.println("Running from ghost");
             } else if (mainCharacter.hitted_wall == true) {
                 mainCharacter.setRandomDirection();
                 mainCharacter.hitted_wall = false;
             }

            while (mainCharacter.hitted_wall == false && mainCharacter.steps_left != 0) {
                switch (mainCharacter.getMovingDirection()) {
                    case "UP":
                        mainCharacter.moveUp();
                        break;
                    case "DOWN":
                        mainCharacter.moveDown();
                        break;
                    case "RIGHT":
                        mainCharacter.moveRight();
                        break;
                    case "LEFT":
                        mainCharacter.moveLeft();
                        break;
                }
            }
        }

        for (int i=0; i<Map.ghosts.get(2).size(); i++){
            Wallhacker ghost = (Wallhacker) Map.ghosts.get(2).get(i);
            ghost.steps_left = 2;
            if(ghost.checkWhereEnemy()) {
                System.out.println("Going to enemy");
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

        for (int i=0; i<Map.ghosts.get(1).size(); i++){
            Listener ghost = (Listener) Map.ghosts.get(1).get(i);
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



        for (int i=0; i<Map.ghosts.get(0).size(); i++){
            Looker ghost = (Looker) Map.ghosts.get(0).get(i);
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

package gradle;

class MyAnimate implements Runnable {

    @Override
    public void run() {
        System.out.println("\nAnimate task");

        for(MainCharacter mainCharacter : Map.main_characters){
             mainCharacter.steps_left = 1;

            System.out.println(mainCharacter.getClass().getName());

             if (mainCharacter.hitted_wall == true) {
                 mainCharacter.setRandomDirection();
                 mainCharacter.hitted_wall = false;
             }else if(mainCharacter.check_if_ghost()){
                 System.out.println("  Running from ghost");
                 if(mainCharacter.getting_scared()){
                     System.out.println("  Getting scared");
                     if(mainCharacter.dying()){
                         System.out.println("  Dying");
                     }
                 }
             }
             else if(mainCharacter.check_if_food()){                                 //TODO wykonywanie pojedyncze funkcji
                System.out.println("  Going for food");
                if (mainCharacter.eating()){
                    System.out.println("  Eating");
                }
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

        System.out.println("Ghosts");

        for (int i=0; i<Map.ghosts.get(2).size(); i++){
            Wallhacker ghost = (Wallhacker) Map.ghosts.get(2).get(i);
            ghost.steps_left = 1;

            if (ghost.hitted_wall==true){
                ghost.setRandomDirection();
                ghost.hitted_wall = false;
            }else if(ghost.checkWhereEnemy()) {
                System.out.println("  Going to enemy");
            }

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
            ghost.steps_left = 1;

            if (ghost.hitted_wall==true){
                ghost.setRandomDirection();
                ghost.hitted_wall = false;
            }else if(ghost.checkIfNearby()) {
                System.out.println("Going to enemy");
            }

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
            ghost.steps_left = 1;

            if (ghost.hitted_wall==true){
                ghost.setRandomDirection();
                ghost.hitted_wall = false;
            }else if(ghost.checkIfEnemyIsDiagonal()) {
                System.out.println("Going to enemy");
            }

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

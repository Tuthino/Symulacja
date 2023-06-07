package gradle;

class MyAnimate implements Runnable {

    @Override
    public void run() {
        System.out.println("\nAnimate task");

        System.out.println("\n Main character");
        for(MainCharacter mainCharacter : Map.main_characters){
             mainCharacter.steps_left = 2;

            // System.out.println(mainCharacter.getClass().getName());

            if(mainCharacter.detect_ghost()){

            } else if(mainCharacter.check_if_food()) {

            } else if (mainCharacter.hitted_wall==true){
                mainCharacter.setRandomDirection();
            };

            mainCharacter.hitted_wall = false;
            while(mainCharacter.hitted_wall == false && mainCharacter.steps_left != 0 ){
                System.out.println("  Moving");
                mainCharacter.move(mainCharacter.getMovingDirection());
            }
        }

        for (int i=0; i<Map.ghosts.get(2).size(); i++){
            Wallhacker ghost = (Wallhacker) Map.ghosts.get(2).get(i);
            ghost.steps_left = 2;
            if(ghost.checkWhereEnemy()) {

            } else if (ghost.hitted_wall==true){
                ghost.setRandomDirection();
            };
            ghost.hitted_wall = false;

            while (ghost.hitted_wall == false && ghost.steps_left != 0) {
                ghost.move(ghost.getMovingDirection());
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
                ghost.move(ghost.getMovingDirection());
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
            while (ghost.hitted_wall == false && ghost.steps_left != 0) {
                ghost.move(ghost.getMovingDirection());
            }
        }
    }
}

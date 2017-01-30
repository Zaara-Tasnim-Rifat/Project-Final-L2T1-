import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

/**
 * Created by Zaara Tasnim Rifat on 5/19/2016.
 */

/*class Move{
    int x;
    int y;
    int score;

    public Move()
    {

    }

    public Move(int score)
    {
        this.score = score;
    }

    public void clone(Move newMove) {
        this.x = newMove.x;
        this.y = newMove.y;
        this.score = newMove.score;
    }
}*/
public class Medium extends Easy {


    public Medium(String playerName) {
        super(playerName);



        /*for (int i = 0; i < 9; i++) {
            moves[i] = new Move();
        }*/
        count = 0;

    }

    public int haswon() {
        for (int i = 0; i < 3; i++) {
            if (value[i][0] != -1 && value[i][0] == value[i][1] && value[i][1] == value[i][2]) {

                return value[i][0];
            }
        }

        for (int j = 0; j < 3; j++) {
            if (value[0][j] != -1 && value[0][j] == value[1][j] && value[1][j] == value[2][j]) {

                return value[0][j];
            }
        }

        if (value[0][0] != -1 && value[0][0] == value[1][1] && value[1][1] == value[2][2]) {

            return value[0][0];
        }
        else if (value[0][2] != -1 && value[0][2] == value[1][1] && value[1][1] == value[2][0]) {

            return value[0][2];
        }



        return 0;
    }



    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    int computerMoveX= 2;
    int computerMoveY= 2;


    public int minimax(int depth, int turn) {
        int rv = haswon();

        if (rv == 1) {
            return -10;
        }  if (rv == 2) {
            return 10;
        }  if (rv == 0 ) {
            return 0;
        }




        int i, j;


        for ( i = 0; i < 3; i++) {
            for ( j = 0; j < 3; j++) {
                if (value[i][j] == -1) {
                    System.out.println("have u visit me ever?");


                    if(turn == 2) {
                        value[i][j]= 2;
                        int currentScore = minimax(depth+1, 1);
                        max = Math.max(currentScore,max);
                        System.out.println(currentScore);
                        System.out.println("max: "+max);
                        if(currentScore >= 0)
                        {
                            if(depth == 0)
                            {

                                computerMoveX = i;
                                computerMoveY = j;
                                System.out.println(computerMoveX+ " "+computerMoveY);
                            }
                        }


                        if(currentScore ==10)
                        {
                            computerMoveX = i;
                            computerMoveY = j;
                            System.out.println(computerMoveX+ " "+computerMoveY);
                            value[i][j]=-1;
                            break;
                        }

                        if(i==2 && j==2 && max<0)
                        {
                            if(depth==0)
                            {
                                computerMoveX = i;
                                computerMoveY = j;
                            }
                        }

                    }


                    else {
                        value[i][j] = 1;
                        int currentScore = minimax(depth+1,2);
                        min = Math.min(currentScore,min);
                        if(min==-10)
                        {
                            value[i][j]=-1;

                            break;
                        }
                    }


                }
                value[i][j] =-1;
            }

            //value[i][j] =-1;
        }


        System.out.println(computerMoveX+ " "+computerMoveY);

        return turn==2?max:min ;
    }

    void computerTurn()
    {
         min = Integer.MAX_VALUE;
         max = Integer.MIN_VALUE;
        System.out.println("Allah bachao");
        System.out.println(computerMoveX+ " "+computerMoveY);
        minimax(0,2);
        System.out.println(computerMoveX+ " "+computerMoveY);
        value[computerMoveX][computerMoveY] = 2;
        buttons[computerMoveX][computerMoveY].setText("o");
        //System.out.println(bestMove.x +" "+ bestMove.y +" "+ value[bestMove.x][bestMove.y]);

    }

    void medium(String playerName)
    {


        computerStart.setOnAction(event-> {
            computerTurn();
            count++;

        });



        buttons[0][0].setOnAction(e ->
        {
            buttons[0][0].setText("x");
            value[0][0] = 1;
            count++;

            //n = rand.nextInt();
            if (isMatched() != 0) {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");

                } else if (isMatched() == 2) {
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

            while (count < 9) {

            computerTurn();
            count++;
        }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }
        });
        buttons[0][1].setOnAction(e ->
        {
            buttons[0][1].setText("x");
            value[0][1] = 1;
            count++;

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }


            while (count < 9) {

                computerTurn();
                count++;
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {

                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }
        });
        buttons[0][2].setOnAction(e ->
        {
            buttons[0][2].setText("x");
            value[0][2] = 1;
            count++;

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

            while (count < 9) {

                computerTurn();
                count++;
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

        });
        buttons[1][0].setOnAction(e ->
        {
            buttons[1][0].setText("x");
            value[1][0] = 1;
            count ++;

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }


            while (count < 9) {

                computerTurn();
                count++;
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

        });
        buttons[1][1].setOnAction(e ->
        {
            buttons[1][1].setText("x");
            value[1][1] = 1;
            count++;

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

            while (count < 9) {

                computerTurn();
                count++;
           }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

        });
        buttons[1][2].setOnAction(e ->
        {
            buttons[1][2].setText("x");
            value[1][2] = 1;
            count++;

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

            while (count < 9) {

                computerTurn();
                count++;
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat( " Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

        });
        buttons[2][0].setOnAction(e ->
        {
            buttons[2][0].setText("x");
            value[2][0] = 1;
            count++;

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

            while (count < 9) {
                computerTurn();
                count++;
            }
            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

        });
        buttons[2][1].setOnAction(e ->
        {
            buttons[2][1].setText("x");
            value[2][1] = 1;
            count++;

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

            while (count < 9) {

                computerTurn();
                count++;
            }
            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

        });
        buttons[2][2].setOnAction(e ->
        {
            buttons[2][2].setText("x");
            value[2][2] = 1;
            count++;

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat( " Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

            while (count < 9) {

                computerTurn();
                count++;
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));

                    System.out.println("Player wins");

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                }
                return;
            }

        });


    }

}







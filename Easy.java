import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

/**
 * Created by Zaara Tasnim Rifat on 5/15/2016.
 */
public class Easy {
    public Button[][] buttons;
    public Button computerStart;
    public Button playerStart;
    public int[][] value;
    String playerName = "Player";
    Label winner;
    public char firstTurn ;
    int count = 0;
    int totalMatch = 1, wonMatch=0, drawMatch =0, loseMatch =0;
    Label total, won, lost, draw;
    Button reload,back;

    public Easy(String name) {
        buttons = new Button[3][3];
        value = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                value[i][j] = -1;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setMinSize(120, 120);
            }
        }

        computerStart = new Button("You Start");
        computerStart.setId("startButton");
        //computerStart.setMaxSize(20,100);
        playerStart = new Button("I'll Start");
        playerStart.setId("startButton");
        //playerStart.setMaxle;

        winner = new Label("");
        playerName = name;

        total = new Label("Total Game played: "+ totalMatch);
        won = new Label("Match won: "+ wonMatch);
        lost= new Label("Match Lost:"+ loseMatch);
        draw = new Label("Match Drawn:"+ drawMatch);

        reload = new Button("Reload");
        back = new Button("Main Menu");
        reload.setId("startButton");
        back.setId("startButton");
    }





    public int isMatched() {
        for (int i = 0; i < 3; i++) {
            if (value[i][0] != -1 && value[i][0] == value[i][1] && value[i][1] == value[i][2]) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setId("button-blue");

                }
                return value[i][0];
            }
        }

        for (int j = 0; j < 3; j++) {
            if (value[0][j] != -1 && value[0][j] == value[1][j] && value[1][j] == value[2][j]) {
                for (int i = 0; i < 3; i++) {
                    buttons[i][j].setId("button-blue");

                }
                return value[0][j];
            }
        }

        if (value[0][0] != -1 && value[0][0] == value[1][1] && value[1][1] == value[2][2]) {
            for (int i = 0; i < 3; i++) {
                buttons[i][i].setId("button-blue");

            }
            return value[0][0];
        } else if (value[0][2] != -1 && value[0][2] == value[1][1] && value[1][1] == value[2][0]) {
            for (int i = 0; i < 3; i++) {
                buttons[i][2 - i].setId("button-blue");

            }
            return value[0][2];
        }

        return 0;
    }



    void computerTurn() {
        Random rand = new Random();

        while (count < 9) {

            int n = rand.nextInt(3);
            int m = rand.nextInt(3);
            System.out.println(m + " " + n);
            if (value[n][m] == -1) {
                value[n][m] = 2;
                count++;
                buttons[n][m].setText("o");

                return;
            }
        }
    }

    public void reload(String playerName){
        value = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                value[i][j] = -1;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setId("button");

            }
        }
        count=0;
        winner.setText("");
        easy(playerName);

    }

    public void easy(String playerName) {
        Random rand = new Random();
        won.setText(playerName+ " wins: "+wonMatch);
        lost.setText(playerName+" loses: "+loseMatch);
        draw.setText(playerName+ " draws: "+ drawMatch);




        //playerStart.setOnAction(event-> firstTurn = 'p');
        //computerStart.setOnAction(event-> firstTurn = 'c');
        reload.setOnAction(e->{
            totalMatch++;
            total.setText("Total Game played: " + totalMatch);

            reload(playerName);
        });
        computerStart.setOnAction(event-> {
            int n = rand.nextInt(3);
            int m = rand.nextInt(3);
            System.out.println(m + " " + n);
            if (value[n][m] == -1) {
                value[n][m] = 2;
                count++;
                buttons[n][m].setText("o");
            }
        });



        buttons[0][0].setOnAction(e ->
        {
            buttons[0][0].setText("x");
            value[0][0] = 1;
            count++;

            //n = rand.nextInt();
            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
                return;
            }

            while (count < 9) {

                int n =rand.nextInt(3);
                int m = rand.nextInt(3);
                if (value[n][m] == -1) {
                    value[n][m] = 2;
                    count++;
                    buttons[n][m].setText("o");

                    break;
                }
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
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
                        wonMatch++;
                        won.setText(playerName+ " wins: "+wonMatch);

                    } else if(isMatched()== 2){
                        System.out.println("Computer wins");
                        winner.setText("Computer Wins!!");
                        loseMatch++;
                        lost.setText(playerName+" loses: "+loseMatch);
                    }

                    return;
                }
                else if(count>=9)
                {
                    winner.setText("It's a Draw!!");
                    drawMatch++;
                    draw.setText(playerName+ " draws: "+ drawMatch);
                    return;
                }


            while (count < 9) {

                int n =rand.nextInt(3);
                int m = rand.nextInt(3);

                if (value[n][m] == -1) {
                    value[n][m] = 2;
                    count++;
                    buttons[n][m].setText("o");

                    break;
                }
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
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
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
                return;
            }

            while (count < 9) {

                int n =rand.nextInt(3);
                int m = rand.nextInt(3);

                if (value[n][m] == -1) {
                    value[n][m] = 2;
                    count++;
                    buttons[n][m].setText("o");

                    break;
                }
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
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
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
                return;
            }


            while (count < 9) {

                int n =rand.nextInt(3);
                int m = rand.nextInt(3);

                if (value[n][m] == -1) {
                    value[n][m] = 2;
                    count++;
                    buttons[n][m].setText("o");

                    break;
                }
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
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
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
                return;
            }

            while (count < 9) {

                int n =rand.nextInt(3);
                int m = rand.nextInt(3);

                if (value[n][m] == -1) {
                    value[n][m] = 2;
                    count++;
                    buttons[n][m].setText("o");

                    break;
                }
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

            }
            else if(count >=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
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
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
                return;
            }

            while (count < 9) {

                int n =rand.nextInt(3);
                int m = rand.nextInt(3);

                if (value[n][m] == -1) {
                    value[n][m] = 2;
                    count++;
                    buttons[n][m].setText("o");

                    break;
                }
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
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
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
                return;
            }
            while (count < 9) {

                int n =rand.nextInt(3);
                int m = rand.nextInt(3);

                if (value[n][m] == -1) {
                    value[n][m] = 2;
                    count++;
                    buttons[n][m].setText("o");

                    break;
                }
            }
            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
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
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
                return;
            }

            while (count < 9) {

                int n =rand.nextInt(3);
                int m = rand.nextInt(3);

                if (value[n][m] == -1) {
                    value[n][m] = 2;
                    count++;
                    buttons[n][m].setText("o");

                    break;
                }
            }
            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
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
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
                return;
            }

            while (count < 9) {

                int n =rand.nextInt(3);
                int m = rand.nextInt(3);

                if (value[n][m] == -1) {
                    value[n][m] = 2;
                    count++;
                    buttons[n][m].setText("o");

                    break;
                }
            }

            if(isMatched()!= 0)
            {
                if (isMatched() == 1) {
                    winner.setText(playerName.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch++;
                    won.setText(playerName+ " wins: "+wonMatch);

                } else if(isMatched()== 2){
                    System.out.println("Computer wins");
                    winner.setText("Computer Wins!!");
                    loseMatch++;
                    lost.setText(playerName+" loses: "+loseMatch);
                }

                return;
            }
            else if(count>=9)
            {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText(playerName+ " draws: "+ drawMatch);
                return;
            }

        });



    }


}

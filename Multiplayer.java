/**
 * Created by Zaara Tasnim Rifat on 6/7/2016.
 */
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;

/**
 * Created by Zaara Tasnim Rifat on 6/3/2016.
 */
public class Multiplayer {
    public Button[][] buttons;
    public int[][] value;
    String playerName = "Player";
    Label winner;
    int count = 0;
    int totalMatch = 1, wonMatch1 = 0,wonMatch2 =0, drawMatch = 0;
    Label total, won1, won2, draw;
    int turn = 1;
    Button reload, back;

    public Multiplayer() {
        buttons = new Button[3][3];
        value = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                value[i][j] = -1;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button("");
                buttons[i][j].setMinSize(120, 120);
            }
        }
        winner = new Label("");
        total = new Label("Total Game played: "+ totalMatch);
        won1 = new Label("Match won: "+ wonMatch1);
        won2= new Label("Match Lost:"+ wonMatch2);
        draw = new Label("Match Drawn:"+ drawMatch);
        reload = new Button("Reload");
        reload.setId("startButton");
        back = new Button("Main Menu");
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

        return -1;
    }

    public void reload(String playerName1, String playerName2) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
                buttons[i][j].setId("button");
            }

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                value[i][j] = -1;
            }

        count=0;
        winner.setText(" ");
        multi(playerName1, playerName2);
    }


    void multi(String playerName1, String playerName2) {
        //while(true){
        won1.setText(playerName1+" wins: "+wonMatch1);
        won2.setText(playerName2+" wins: "+wonMatch2);

        System.out.println(playerName2);

        reload.setOnAction(e -> {
            totalMatch++;
            total.setText("Total Game played: " + totalMatch);

            reload(playerName1, playerName2);

            return;

        });


            buttons[0][0].setOnAction(e ->
            {

                value[0][0] = turn;

                if (turn == 1) {
                    buttons[0][0].setText("x");
                    turn = 0;
                } else {
                    buttons[0][0].setText("o");
                    turn = 1;
                }

                count++;

                //n = rand.nextInt();
                    if (isMatched() == 1) {
                        winner.setText(playerName1.concat(" Wins!!!"));
                        System.out.println("Player wins");
                        wonMatch1++;
                        won1.setText(playerName1+ " wins: " + wonMatch1);


                    } else if (isMatched() == 0) {
                        System.out.println("Computer wins");
                        winner.setText(playerName2.concat(" Wins!!!"));
                        wonMatch2++;
                        won2.setText(playerName2 + "wins: " + wonMatch2);

                    } if(count>=9) {
                        winner.setText("It's a Draw!!");
                        drawMatch++;
                        draw.setText("Match drawn: " + drawMatch);

                    }
                    return;



            });
            buttons[0][1].setOnAction(e ->
            {
                value[0][1] = turn;

                if (turn == 1) {
                    buttons[0][1].setText("x");
                    turn = 0;
                } else {
                    buttons[0][1].setText("o");
                    turn = 1;
                }
                count++;

                    if (isMatched() == 1) {
                        winner.setText(playerName1.concat(" Wins!!!"));
                        System.out.println("Player wins");
                        wonMatch1++;
                        won1.setText(playerName1+ " wins: " + wonMatch1);


                    } else if (isMatched() == 0) {
                        System.out.println("Computer wins");
                        winner.setText(playerName2.concat(" Wins!!!"));
                        wonMatch2++;
                        won2.setText(playerName2 + " wins: " + wonMatch2);

                    } if(count>=9) {
                        winner.setText("It's a Draw!!");
                        drawMatch++;
                        draw.setText("Match drawn: " + drawMatch);

                    }
                    return;



            });
            buttons[0][2].setOnAction(e ->
            {
                value[0][2] = turn;

                if (turn == 1) {
                    buttons[0][2].setText("x");
                    turn = 0;
                } else {
                    buttons[0][2].setText("o");
                    turn = 1;
                }
                count++;

                if (isMatched() == 1) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1+ " wins: " + wonMatch1);


                } else if (isMatched() == 0) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                } if(count>=9) {
                    winner.setText("It's a Draw!!");
                    drawMatch++;
                    draw.setText("Match drawn: " + drawMatch);

                }
                return;


            });
            buttons[1][0].setOnAction(e ->
            {
                value[1][0] = turn;

                if (turn == 1) {
                    buttons[1][0].setText("x");
                    turn = 0;
                } else {
                    buttons[1][0].setText("o");
                    turn = 1;
                }
                count++;

                if (isMatched() == 1) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1+ " wins: " + wonMatch1);


                } else if (isMatched() == 0) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                } else if(count>=9) {
                    winner.setText("It's a Draw!!");
                    drawMatch++;
                    draw.setText("Match drawn: " + drawMatch);

                }
                return;

            });
            buttons[1][1].setOnAction(e ->
            {
                value[1][1] = turn;

                if (turn == 1) {
                    buttons[1][1].setText("x");
                    turn = 0;
                } else if(turn==0){
                    buttons[1][1].setText("o");
                    turn = 1;
                }
                count++;

                if (isMatched() == 1) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1+ " wins: " + wonMatch1);


                } else if (isMatched() == 0) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                } else if(count>=9) {
                    winner.setText("It's a Draw!!");
                    drawMatch++;
                    draw.setText("Match drawn: " + drawMatch);

                }
                return;

            });
            buttons[1][2].setOnAction(e ->
                    {
                        value[1][2] = turn;

                        if (turn == 1) {
                            buttons[1][2].setText("x");
                            turn = 0;
                        } else {
                            buttons[1][2].setText("o");
                            turn = 1;
                        }
                        count++;

                        if (isMatched() == 1) {
                            winner.setText(playerName1.concat(" Wins!!!"));
                            System.out.println("Player wins");
                            wonMatch1++;
                            won1.setText(playerName1+ " wins: " + wonMatch1);


                        } else if (isMatched() == 0) {
                            System.out.println("Computer wins");
                            winner.setText(playerName2.concat(" Wins!!!"));
                            wonMatch2++;
                            won2.setText(playerName2 + " wins: " + wonMatch2);

                        } else if(count>=9) {
                            winner.setText("It's a Draw!!");
                            drawMatch++;
                            draw.setText("Match drawn: " + drawMatch);

                        }
                        return;

                    });
            buttons[2][0].setOnAction(e ->
            {
                value[2][0] = turn;

                if (turn == 1) {
                    buttons[2][0].setText("x");
                    turn = 0;
                } else {
                    buttons[2][0].setText("o");
                    turn = 1;
                }
                count++;

                if (isMatched() == 1) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1+ " wins: " + wonMatch1);


                } else if (isMatched() == 0) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                } else if(count>=9) {
                    winner.setText("It's a Draw!!");
                    drawMatch++;
                    draw.setText("Match drawn: " + drawMatch);

                }
                return;


            });
            buttons[2][1].setOnAction(e ->
            {
                value[2][1] = turn;

                if (turn == 1) {
                    buttons[2][1].setText("x");
                    turn = 0;
                } else {
                    buttons[2][1].setText("o");
                    turn = 1;
                }
                count++;

                if (isMatched() == 1) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1+ " wins: " + wonMatch1);


                } else if (isMatched() == 0) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                } else if(count>=9) {
                    winner.setText("It's a Draw!!");
                    drawMatch++;
                    draw.setText("Match drawn: " + drawMatch);

                }
                return;


            });
            buttons[2][2].setOnAction(e ->
            {
                value[2][2] = turn;

                if (turn == 1) {
                    buttons[2][2].setText("x");
                    turn = 0;
                } else {
                    buttons[2][2].setText("o");
                    turn = 1;
                }
                count++;

                if (isMatched() == 1) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1+ " wins: " + wonMatch1);


                } else if (isMatched() == 0) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                } else if(count>=9) {
                    winner.setText("It's a Draw!!");
                    drawMatch++;
                    draw.setText("Match drawn: " + drawMatch);

                }
                return;

            });


    }
}
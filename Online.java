import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Zaara Tasnim Rifat on 6/3/2016.
 */


public class Online implements Runnable {
    public Button[][] buttons;
    public int[][] value;
    String playerName1 = "Player1";
    String playerName2 = "Player2";
    Label winner;
    int count = 0, myTurn=-1;
    int totalMatch = 1, wonMatch1 = 0, wonMatch2 = 0, drawMatch = 0;
    Label total, won1, won2, draw;

    int  m,f,l;
    String move = new String("");
    public Button O,X;

    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;

    String choice;
    Button reload, back;

    public Online() {
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

        O= new Button("o");
        X= new Button("x");
        O.setId("OX");
        X.setId("OX");
        O.setMinSize(50,50);
        X.setMinSize(50,50);


        total = new Label("Total Game played: "+ totalMatch);
        won1 = new Label("player1 wins: "+ wonMatch1);
        won2= new Label("player2 wins:"+ wonMatch2);
        draw = new Label("Match Drawn:"+ drawMatch);

        winner = new Label("");
        reload = new Button("Reload");
        back = new Button("Main Menu");
        reload.setId("startButton");
        back.setId("startButton");


    }

    public void startOnline( String playerName) {


            new Thread(this).start();
            this.playerName1 = playerName;




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

    public void reload(){
        for(int i=0 ; i<3 ; i++)
            for (int j=0; j<3; j++)
        {
            buttons[i][j].setText(" ");
            buttons[i][j].setId("button");
        }

        for(int i=0 ; i<3 ; i++)
            for (int j=0; j<3; j++)
            {
                value[i][j]=-1;
            }

        count=0;

        winner.setText(" ");
        online();
    }


    void online() {
        //while(true){

        won1.setText(playerName1+" wins: "+wonMatch1);
        won2.setText(playerName2+" wins: "+wonMatch2);

        reload.setOnAction(e -> reload());
        O.setOnAction(e -> {
            totalMatch++;
            total.setText("Total Game played: " + totalMatch);
            choice = "0";
            O.setId("button-red");
            myTurn=0;

        });
        X.setOnAction(e -> {
            choice = "1";
            X.setId("button-red");
            myTurn=1;
        });
        buttons[0][0].setOnAction(e -> {
            value[0][0] = choice.charAt(0) - '0';
            if (choice.equals("1"))
                buttons[0][0].setText("x");
            else if (choice.equals("0"))
                buttons[0][0].setText("o");
            System.out.println("Im okay");
            try {
                out.writeObject(choice.concat("00"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            count++;

            if(isMatched()!=-1) {
                if (isMatched() == myTurn) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1 + " wins: " + wonMatch1);


                } else if (isMatched() != myTurn) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                }
            }else if(count>=9) {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText("Match drawn: " + drawMatch);

            }
            return;

        });



        buttons[0][1].setOnAction(e -> {
            value[0][1] = choice.charAt(0) - '0';
            if (choice.equals("1"))
                buttons[0][1].setText("x");
            else if (choice.equals("0"))
                buttons[0][1].setText("o");

            try {
                out.writeObject(choice.concat("01"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }


            count++;

            if(isMatched()!=-1) {
                if (isMatched() == myTurn) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1 + " wins: " + wonMatch1);


                } else if (isMatched() != myTurn) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                }
            } else if(count>=9) {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText("Match drawn: " + drawMatch);

            }
            return;

        });
        buttons[0][2].setOnAction(e -> {
            value[0][2] = choice.charAt(0) - '0';
            if (choice.equals("1"))
                buttons[0][2].setText("x");
            else if (choice.equals("0"))
                buttons[0][2].setText("o");

            try {
                out.writeObject(choice.concat("02"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            count++;


            if(isMatched()!=-1) {
                if (isMatched() == myTurn) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1 + " wins: " + wonMatch1);


                } else if (isMatched() != myTurn) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                }
            } else if(count>=9) {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText("Match drawn: " + drawMatch);

            }
            return;

        });
        buttons[1][0].setOnAction(e -> {
            value[1][0] = choice.charAt(0) - '0';
            if (choice.equals("1"))
                buttons[1][0].setText("x");
            else if (choice.equals("0"))
                buttons[1][0].setText("o");

            try {
                out.writeObject(choice.concat("10"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            count++;


            if(isMatched()!=-1) {
                if (isMatched() == myTurn) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1 + " wins: " + wonMatch1);


                } else if (isMatched() != myTurn) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                }
            } else if(count>=9) {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText("Match drawn: " + drawMatch);

            }
            return;

        });
        buttons[1][1].setOnAction(e -> {
            value[1][1] = choice.charAt(0) - '0';
            if (choice.equals("1"))
                buttons[1][1].setText("x");
            else if (choice.equals("0"))
                buttons[1][1].setText("o");

            try {
                out.writeObject(choice.concat("11"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            count++;


            if(isMatched()!=-1) {
                if (isMatched() == myTurn) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1 + " wins: " + wonMatch1);


                } else if (isMatched() != myTurn) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                }
            }else if(count>=9) {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText("Match drawn: " + drawMatch);

            }
            return;

        });
        buttons[1][2].setOnAction(e -> {
            value[1][2] = choice.charAt(0) - '0';
            if (choice.equals("1"))
                buttons[1][2].setText("x");
            else if (choice.equals("0"))
                buttons[1][2].setText("o");

            try {
                out.writeObject(choice.concat("12"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            count++;


            if(isMatched()!=-1) {
                if (isMatched() == myTurn) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1 + " wins: " + wonMatch1);


                } else if (isMatched() != myTurn) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                }
            } else if(count>=9) {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText("Match drawn: " + drawMatch);

            }
            return;

        });
        buttons[2][0].setOnAction(e -> {
            value[2][0] = choice.charAt(0) - '0';
            if (choice.equals("1"))
                buttons[2][0].setText("x");
            else if (choice.equals("0"))
                buttons[2][0].setText("o");

            try {
                out.writeObject(choice.concat("20"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            count++;


            if(isMatched()!=-1) {
                if (isMatched() == myTurn) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1 + " wins: " + wonMatch1);


                } else if (isMatched() != myTurn) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                }
            } else if(count>=9) {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText("Match drawn: " + drawMatch);

            }
            return;

        });
        buttons[2][1].setOnAction(e -> {
            value[2][1] = choice.charAt(0) - '0';
            if (choice.equals("1"))
                buttons[2][1].setText("x");
            else if (choice.equals("0"))
                buttons[2][1].setText("o");

            try {
                out.writeObject(choice.concat("21"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            count++;


            if(isMatched()!=-1) {
                if (isMatched() == myTurn) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1 + " wins: " + wonMatch1);


                } else if (isMatched() != myTurn) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                }
            } else if(count>=9) {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText("Match drawn: " + drawMatch);

            }
            return;

        });
        buttons[2][2].setOnAction(e -> {
            value[2][2] = choice.charAt(0) - '0';
            if (choice.equals("1"))
                buttons[2][2].setText("x");
            else if (choice.equals("0"))
                buttons[2][2].setText("o");

            try {
                out.writeObject(choice.concat("22"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            count++;


            if(isMatched()!=-1) {
                if (isMatched() == myTurn) {
                    winner.setText(playerName1.concat(" Wins!!!"));
                    System.out.println("Player wins");
                    wonMatch1++;
                    won1.setText(playerName1 + " wins: " + wonMatch1);


                } else if (isMatched() != myTurn) {
                    System.out.println("Computer wins");
                    winner.setText(playerName2.concat(" Wins!!!"));
                    wonMatch2++;
                    won2.setText(playerName2 + " wins: " + wonMatch2);

                }
            } else if(count>=9) {
                winner.setText("It's a Draw!!");
                drawMatch++;
                draw.setText("Match drawn: " + drawMatch);

            }
            return;

        });




    }


    @Override
    public void run() {
        try {
            socket = new Socket("localhost",11111);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("problem");
        }


        try {
            out.writeObject(playerName1);
            Object o= in.readObject();
            playerName2 = o.toString();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("name reading problem*");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                won1.setText(playerName1 + " wins: " + wonMatch1);
                won2.setText(playerName2 + " wins: " + wonMatch2);
            }
        });




        while (true) {
            //online();
            Object o;

            try {

                o = in.readObject();
                move = o.toString();
                m = move.charAt(0) - '0';
                f = move.charAt(1) - '0';
                l = move.charAt(2) - '0';


                System.out.println("player1: "+playerName1);
                System.out.println("player2: "+playerName2);

                Platform.runLater(new Runnable(){

                    @Override
                    public void run() {
                        value[f][l] = m;
                        if(m==1)
                            buttons[f][l].setText("x");
                        else if(m==0)
                            buttons[f][l].setText("o");


                        count++;

                        if(isMatched()!=-1) {
                            if (isMatched() == myTurn) {
                                winner.setText(playerName1.concat(" Wins!!!"));
                                System.out.println("Player wins");
                                wonMatch1++;
                                won1.setText(playerName1 + " wins: " + wonMatch1);


                            } else if (isMatched() != myTurn) {
                                System.out.println("Computer wins");
                                winner.setText(playerName2.concat(" Wins!!!"));
                                wonMatch2++;
                                won2.setText(playerName2 + " wins: " + wonMatch2);

                            }
                        } else if(count>=9) {
                            winner.setText("It's a Draw!!");
                            drawMatch++;
                            draw.setText("Match drawn: " + drawMatch);

                        }
                    }


                });
/*                value[f][l] = m;
                if(m==1)
                    buttons[f][l].setText("x");
                else if(m==0)
                    buttons[f][l].setText("o");
*/

                /*if(isMatched()!= 0)
                {
                    if (isMatched() == 1) {
                        winner.setText(playerName.concat(" Wins!!!"));
                        System.out.println("Player wins");

                    } else if(isMatched()== 2){
                        System.out.println("Computer wins");
                        winner.setText("Computer Wins!!");
                    }
                    else
                    {
                        winner.setText("It's a Draw!!");
                    }
                    break;
                }*/


            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}

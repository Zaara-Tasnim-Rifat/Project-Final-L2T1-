/**
 * Created by Zaara Tasnim Rifat on 5/8/2016.
 */
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import  javafx.scene.image.*;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Main extends Application {

    static Stage window;
    String playerName = "Player1 ";
    ArrayList<String> scoreBoard= new ArrayList<>(20);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Tic Tac Toe");

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(2);

        Label heading = new Label("TIC TAC TOE");
        heading.setId("header");                    // import from css file how the style should be like
        grid.setConstraints(heading, 42, 0);


        Label welcome = new Label("Welcome .....");
        welcome.setId("Welcome");
        grid.setConstraints(welcome, 35, 8);
        Label user = new Label();
        user.setId("user");
        grid.setConstraints(user, 50, 8);
        Label warning= new Label("");
        grid.setConstraints(warning,42,18);
        //warning.setFont();



        TextField username = new TextField("Player1");
       // username.setMaxWidth(230);
        //username.setMaxHeight(70);
        username.setPromptText("username"); // it doesnt work//it works
       grid.setConstraints(username, 42, 17);


        /*HBox welcomeMessage = new HBox(welcome,user);
       // GridPane.setConstraints(welcomeMessage,25,10);
        //welcomeMessage.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(10, welcomeMessage, username);
        vBox.setAlignment(Pos.CENTER);*/

        user.textProperty().bind(username.textProperty());


        Label message = new Label("Enter Your Name :");
        message.setId("Welcome");
        grid.setConstraints(message, 42, 15);

        Button playbutton = new Button("Play Tic Tac Toe!!!");
        playbutton.setId("playbutton");
        grid.setConstraints(playbutton, 42, 25);



        //playbutton.setOnAction(e-> window.setScene(scene2));
        //mainmenu

        VBox mainmenu = new VBox(15);
        Label menu = new Label("  MAIN MENU  ");
        Button newgame = new Button(" Start a New Game ");
        Button savedgame = new Button ("I have a Saved One!");
        Button scoreboard = new Button("       Score Board      ");
        Button setting = new Button("          Settings          ");
        mainmenu.setAlignment(Pos.CENTER);



        //start a new game

        Label startgame = new Label("   Start a New Game   ");
        startgame.setId("Welcome");
        HBox numPlayers = new HBox(80);
        numPlayers.setAlignment(Pos.CENTER);

        Label p = new Label("Players: ");
        ChoiceBox<String> player = new ChoiceBox<String> ();
        player.getItems().addAll("Single", "Multiple");
        player.setValue("Single");
        numPlayers.getChildren().addAll(p,player);
        player.setId("choice-box");

        HBox level = new HBox(80);
        Label l = new Label("Level: ");

        ChoiceBox<String> levelchoice = new ChoiceBox<> ();
        levelchoice.getItems().addAll("Easy", "Medium", "Hard");
        level.getChildren().addAll(l,levelchoice);
        levelchoice.setValue("Easy");
        levelchoice.setId("choice-box");
        level.setAlignment(Pos.CENTER);

        HBox network = new HBox(80);
        Label n = new Label("Network: ");

        ChoiceBox<String> netchoice = new ChoiceBox<> ();
        netchoice.getItems().addAll("Play Online", "Play Offline");
        netchoice.setValue("Play Offline");
        network.getChildren().addAll(n,netchoice);
        network.setAlignment(Pos.CENTER);
        netchoice.setId("choice-box");

        Button nextScene3 = new Button("Play!!");
        VBox startnewgame = new VBox(30);
        startnewgame.setAlignment(Pos.CENTER);


        //building scene #4 easy mode


        Medium step1 = new Medium(playerName);

        HBox [] Hboard = new HBox[3];
        for(int i=0 ; i<3 ; i++)
        {
            Hboard[i] = new HBox();
        }
        HBox winnerName = new HBox();
        winnerName.setAlignment(Pos.CENTER_RIGHT);
        winnerName.getChildren().add(step1.winner);

        for(int i=0 ; i<3 ;i++)
        {
            for(int j=0 ; j<3 ; j++)
            {
                Hboard[i].setAlignment(Pos.CENTER_LEFT);
                Hboard[i].getChildren().add(step1.buttons[i][j]);

            }

        }
        VBox score = new VBox(5);
        score.getChildren().addAll(step1.total,step1.won,step1.lost,step1.draw);
        step1.total.setId("score");
        step1.won.setId("score");
        step1.lost.setId("score");
        step1.draw.setId("score");
        score.setAlignment(Pos.CENTER_RIGHT);


        HBox startFirst = new HBox(150);
        startFirst.setAlignment(Pos.BOTTOM_LEFT);
        startFirst.getChildren().addAll(step1.computerStart, step1.playerStart);

        HBox dummyBox = new HBox();
        Label dummyLabel = new Label("      ");
        Label dummyLabel1 = new Label("      ");

        Label dummyLabel2 = new Label("            ");
        HBox dummybox2= new HBox();
        dummybox2.getChildren().add(dummyLabel2);


        Hboard[0].getChildren().add(winnerName);
        Hboard[1].getChildren().addAll(dummyLabel1,startFirst);
        dummyBox.getChildren().add(dummyLabel);




        VBox Vboard = new VBox(0);
        Vboard.setPadding(new Insets(20, 20, 20, 100));




        HBox backButton = new HBox(150);
        backButton.getChildren().addAll(step1.reload,step1.back,score);
        Label name = new Label(playerName);
        name.setId("Welcome");
        Vboard.getChildren().addAll(Hboard[0],Hboard[1],Hboard[2],dummybox2,backButton);
        //Vboard.getChildren().add(name);

        Vboard.setAlignment(Pos.CENTER_LEFT);


        //building scene 5//
        Mid medium = new Mid();

        HBox [] Hboard2 = new HBox[3];
        for(int i=0 ; i<3 ; i++)
        {
            Hboard2[i] = new HBox();
        }
        HBox winnerName2 = new HBox();
        winnerName2.setAlignment(Pos.CENTER_RIGHT);
        winnerName2.getChildren().add(medium.winner);
        VBox score2 = new VBox(0);
        score2.getChildren().addAll(medium.total,medium.won, medium.lost, medium.draw);
        medium.total.setId("score");
        medium.won.setId("score");
        medium.lost.setId("score");
        medium.draw.setId("score");
        score2.setAlignment(Pos.CENTER_RIGHT);


        Hboard2[0].setAlignment(Pos.CENTER_LEFT);
        Hboard2[0].getChildren().addAll(medium.box[0],medium.box[1],medium.box[2], winnerName2);

        Hboard2[1].setAlignment(Pos.CENTER_LEFT);
        Hboard2[1].getChildren().addAll(medium.box[3],medium.box[4],medium.box[5],dummyBox);

        Hboard2[2].setAlignment(Pos.CENTER_LEFT);
        Hboard2[2].getChildren().addAll(medium.box[6],medium.box[7],medium.box[8]);


        //Hboard2[0].getChildren().add(winnerName);

        HBox backButton2 = new HBox(150);
        backButton2.setAlignment(Pos.BOTTOM_LEFT);
        backButton2.getChildren().addAll(medium.reload, medium.back,score2);




        VBox Vboard2 = new VBox(0);
        Vboard2.setPadding(new Insets(20, 20, 20, 100));

        Label name2 = new Label(playerName);
        name.setId("Welcome");
        Vboard2.getChildren().addAll(Hboard2[0],Hboard2[1],Hboard2[2],dummyBox,backButton2 );
        //Vboard.getChildren().add(name);

        Vboard2.setAlignment(Pos.CENTER_LEFT);


        //building scene #9  hard mode

        Hard hard = new Hard();

        HBox [] Hboard9 = new HBox[4];
        for(int i=0 ; i<4 ; i++)
        {
            Hboard9[i] = new HBox();
        }
        HBox winnerName9 = new HBox();
        winnerName9.setAlignment(Pos.CENTER_RIGHT);
        winnerName9.getChildren().add(hard.winner);
        VBox score9 = new VBox(0);
        score9.getChildren().addAll(hard.total,hard.won, hard.lost, hard.draw);
        hard.total.setId("score");
        hard.won.setId("score");
        hard.lost.setId("score");
        hard.draw.setId("score");
        score9.setAlignment(Pos.CENTER_RIGHT);


        Hboard9[0].setAlignment(Pos.CENTER_LEFT);
        Hboard9[0].getChildren().addAll(hard.box[0],hard.box[1],hard.box[2],hard.box[3], winnerName9);

        Hboard9[1].setAlignment(Pos.CENTER_LEFT);
        Hboard9[1].getChildren().addAll(hard.box[4],hard.box[5],hard.box[6],hard.box[7],dummyBox);

        Hboard9[2].setAlignment(Pos.CENTER_LEFT);
        Hboard9[2].getChildren().addAll(hard.box[8],hard.box[9],hard.box[10],hard.box[11]);

        Hboard9[3].setAlignment(Pos.CENTER_LEFT);
        Hboard9[3].getChildren().addAll(hard.box[12],hard.box[13],hard.box[14],hard.box[15]);



        //Hboard2[0].getChildren().add(winnerName);

        HBox backButton9 = new HBox(150);
        backButton9.setAlignment(Pos.BOTTOM_LEFT);
        backButton9.getChildren().addAll(hard.reload, hard.back,score9);




        VBox Vboard9 = new VBox(0);
        Vboard9.setPadding(new Insets(20, 20, 20, 100));

        Label name9 = new Label(playerName);
        name.setId("Welcome");
        Vboard9.getChildren().addAll(Hboard9[0],Hboard9[1],Hboard9[2],Hboard9[3],dummyBox,backButton9 );
        //Vboard.getChildren().add(name);

        Vboard9.setAlignment(Pos.CENTER_LEFT);


        //building scene #6  online

        Online online = new Online();
        HBox [] Hboard3 = new HBox[3];
        for(int i=0 ; i<3 ; i++)
        {
            Hboard3[i] = new HBox();
        }
        HBox winnerName3 = new HBox();
        winnerName3.setAlignment(Pos.CENTER_RIGHT);
        winnerName3.getChildren().add(online.winner);
        VBox score3 = new VBox(0);
        score3.getChildren().addAll(online.total,online.won1, online.won2, online.draw);
        online.total.setId("score");
        online.won1.setId("score");
        online.won2.setId("score");
        online.draw.setId("score");
        score3.setAlignment(Pos.CENTER_RIGHT);

        HBox OX = new HBox(50);
        OX.getChildren().addAll(dummyLabel,online.O, online.X);
        //OX.setAlignment(Pos.TOP_CENTER);


        Hboard3[0].setAlignment(Pos.CENTER_LEFT);
        Hboard3[0].getChildren().addAll(online.buttons[0][0],online.buttons[0][1],online.buttons[0][2], winnerName2);

        Hboard3[1].setAlignment(Pos.CENTER_LEFT);
        Hboard3[1].getChildren().addAll(online.buttons[1][0],online.buttons[1][1],online.buttons[1][2],OX);

        Hboard3[2].setAlignment(Pos.CENTER_LEFT);
        Hboard3[2].getChildren().addAll(online.buttons[2][0],online.buttons[2][1],online.buttons[2][2],online.reload);


        //Hboard2[0].getChildren().add(winnerName);

        HBox backButton3 = new HBox(150);
        backButton3.setAlignment(Pos.BOTTOM_LEFT);
        backButton3.getChildren().addAll(online.reload, online.back, score3);






        VBox Vboard3 = new VBox(0);
        Vboard3.setPadding(new Insets(20, 20, 20, 100));

        Label name3 = new Label(playerName);
        name3.setId("Welcome");
        Vboard3.getChildren().addAll(Hboard3[0],Hboard3[1],Hboard3[2],dummyLabel1,backButton3);
        //Vboard.getChildren().add(name);

        Vboard3.setAlignment(Pos.CENTER_LEFT);




        //building scene 8  multiplayer
        Multiplayer multiplayer= new Multiplayer();
        HBox [] Hboard4 = new HBox[3];
        for(int i=0 ; i<3 ; i++)
        {
            Hboard4[i] = new HBox();
        }
        HBox winnerName4 = new HBox();
        winnerName4.setAlignment(Pos.CENTER_RIGHT);
        winnerName4.getChildren().add(multiplayer.winner);
        VBox score4 = new VBox(0);
        score4.getChildren().addAll(multiplayer.total,multiplayer.won1, multiplayer.won2, multiplayer.draw);
        multiplayer.total.setId("score");
        multiplayer.won1.setId("score");
        multiplayer.won2.setId("score");
        multiplayer.draw.setId("score");
        score4.setAlignment(Pos.CENTER_RIGHT);


        Hboard4[0].setAlignment(Pos.CENTER_LEFT);
        Hboard4[0].getChildren().addAll(multiplayer.buttons[0][0],multiplayer.buttons[0][1],multiplayer.buttons[0][2]);

        Hboard4[1].setAlignment(Pos.CENTER_LEFT);
        Hboard4[1].getChildren().addAll(multiplayer.buttons[1][0],multiplayer.buttons[1][1],multiplayer.buttons[1][2]);

        Hboard4[2].setAlignment(Pos.CENTER_LEFT);
        Hboard4[2].getChildren().addAll(multiplayer.buttons[2][0],multiplayer.buttons[2][1],multiplayer.buttons[2][2]);


        Hboard4[0].getChildren().add(winnerName4);

        HBox backButton4 = new HBox(150);
        backButton4.setAlignment(Pos.BOTTOM_LEFT);
        backButton4.getChildren().addAll(multiplayer.reload,multiplayer.back,score4);




        VBox Vboard4 = new VBox(0);
        Vboard4.setPadding(new Insets(20, 20, 20, 100));

        Label name4 = new Label(playerName);
        name4.setId("Welcome");
        Vboard4.getChildren().addAll(Hboard4[0],Hboard4[1],Hboard4[2],dummyBox,backButton4);
        //Vboard.getChildren().add(name);

        Vboard4.setAlignment(Pos.CENTER_LEFT);



        //building scene 7
        Label enterPN = new Label("Enter Player2 Name: ");
        enterPN.setId("Welcome");

        TextField playerName2 = new TextField("player2");
        Button playbutton2 = new Button("start");
        playerName2.setMinHeight(15);
        playerName2.setMaxWidth(200);

        playbutton2.setId("startButton");
        VBox Vboard5 = new VBox(20);
        Vboard5.getChildren().addAll(enterPN,playerName2,warning,playbutton2);
        Vboard5.setAlignment(Pos.CENTER);


        //building scene #10

        VBox sboard = new VBox(5);
        Label [] scores = new Label[10];
        for(int i=0 ; i<10;i++){
            scores[i]= new Label("");
            scores[i].setId("scoreLabel");


        }
        sboard.setAlignment(Pos.CENTER);
        Button backButton5= new Button("Main Menu");
        backButton5.setId("startButton");





        //scences


        Scene scene9 = new Scene(Vboard9,1000,700);
        scene9.getStylesheets().add("scene4.css");


        Scene scene7 = new Scene(Vboard5,1000,700);
        scene7.getStylesheets().add("scene4.css");

        Scene scene8 = new Scene(Vboard4,1000,700);
        scene8.getStylesheets().add("scene4.css");



        Scene scene6 = new Scene(Vboard3,1000,700);
        scene6.getStylesheets().add("scene4.css");


        Scene scene5 = new Scene(Vboard2,1000,700);
        scene5.getStylesheets().add("scene4.css");

        Scene scene4 = new Scene(Vboard,1000,700);
        scene4.getStylesheets().add("scene4.css");


        startnewgame.getChildren().addAll(startgame,numPlayers,level,network,nextScene3);
        Scene scene3 = new Scene(startnewgame,900,700);
        scene3.getStylesheets().add("scene3.css");


        mainmenu.getChildren().addAll(menu,newgame,savedgame,scoreboard,setting);
        Scene scene2 = new Scene(mainmenu,900,700);
        scene2.getStylesheets().add("scene2.css");

        grid.getChildren().addAll(heading,message,username,welcome,user,warning,playbutton);
        Scene scene = new Scene(grid, 900, 700);
        scene.getStylesheets().add("style.css");
        window.setScene(scene);
        // switch to scene
        scoreboard.setOnAction(e->
        {

            for(int i=0 ; i<scoreBoard.size(); i++){
                scores[i].setText(scoreBoard.get(i));
                sboard.getChildren().add(scores[i]);

            }
            scores[0].setId("label");
            sboard.getChildren().add(backButton5);
            Scene scene10 = new Scene(sboard,1000,700);
            scene10.getStylesheets().add("scene4.css");
            window.setScene(scene10);
        });

        backButton5.setOnAction(e->              ////button to ScoreBoard
        {
            window.setScene(scene2);
        });

        playbutton.setOnAction(e-> {
            playerName = username.getText();
            scoreBoard.add("Scores of "+ playerName +": ");

            if(playerName.equals(""))
            {
                warning.setText("Playername is Required !");
            }
            else {
                window.setScene(scene2);
            }

            System.out.println(playerName);

        });





        newgame.setOnAction(e-> window.setScene(scene3));


        nextScene3.setOnAction(e-> {            //switching to scene4
            //window.setScene(scene4);
            String gameLevel =  levelchoice.getValue();
            String gameNetwork = netchoice.getValue();
            String gamePerson = player.getValue();


            if(gameNetwork.equals("Play Offline"))
            {
                if(gameLevel.equals("Easy")&& gamePerson.equals("Single"))
                {
                    window.setScene(scene4);
                    step1.playerStart.setOnAction(event-> step1.firstTurn = 'p');
                    step1.computerStart.setOnAction(event->step1.firstTurn = 'c');
                    step1.easy(playerName);
                    step1.back.setOnAction(event-> {
                        window.setScene(scene2);
                        scoreBoard.add("Easy: "+ step1.wonMatch*10);

                    });
                }
                else if(gameLevel.equals("Medium")&& gamePerson.equals("Single"))
                {
                    //step1.medium(playerName);
                    window.setScene(scene5);
                    medium.mid(playerName);
                    medium.back.setOnAction(event-> {
                        window.setScene(scene2);
                        scoreBoard.add("Medium: "+ medium.wonMatch*10);

                    });

                }
                else if(gameLevel.equals("Hard")&& gamePerson.equals("Single"))
                {
                    //step1.medium(playerName);
                    window.setScene(scene9);
                    hard.hard(playerName, scene);
                    hard.back.setOnAction(event-> {
                        window.setScene(scene2);
                        scoreBoard.add("Hard: "+ hard.wonMatch*10);

                    });

                }
                else if(gamePerson.equals("Multiple")){

                    window.setScene(scene7);
                    playbutton2.setOnAction(event->{
                        if(playerName2.getText().equals(""))
                        {
                            warning.setText("Playername is Required !");
                        }
                        else {
                            System.out.println("*" + playerName2.getText());
                            multiplayer.multi(playerName, playerName2.getText());
                            window.setScene(scene8);
                        }

                        multiplayer.back.setOnAction(event1 -> {
                            window.setScene(scene2);
                            scoreBoard.add("Multiple: ");
                            scoreBoard.add( playerName+": "+ multiplayer.wonMatch1*10);
                            scoreBoard.add( playerName2.getText()+": "+ multiplayer.wonMatch2*10);

                        });
                    });


                }
            }
            else if(gameNetwork.equals("Play Online")){
               if(gameLevel.equals("Medium")|| gameLevel.equals("Hard")|| player.equals("Multiple"))
                {
                    levelchoice.setValue("Easy");
                    player.setValue("Single");
                }

                else {

                    window.setScene(scene6);
                    online.startOnline(playerName);
                    online.online();
                    online.back.setOnAction(event -> {
                        window.setScene(scene2);
                        scoreBoard.add("Online: "+online.wonMatch1*10);

                    });
                }

            }
            System.out.println(playerName);
            //step1.easy(playerName);
        });


        window.show();
    }


}
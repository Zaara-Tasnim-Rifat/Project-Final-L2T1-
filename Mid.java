import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;

/**
 * Created by Zaara Tasnim Rifat on 5/21/2016.
 */
public class Mid {

    Button [] box = new Button[9];
    Button reload = new Button("Reload");
    Button back = new Button("Main Menu");
    //gameXO is the game
    static String[] gameXO = new String[9];
    //_sdepth is used to control the depth
    static int sdepth;
    Label winner;
    int totalMatch = 1, wonMatch=0, drawMatch =0, loseMatch =0;
    Label total, won, lost, draw;


    public Mid()
    {
        for(int i=0 ; i<9 ; i++)
        {
            box[i] = new Button();
            box[i].setMinSize(120, 120);
        }

        for(int i=0; i<9; i++)
            gameXO[i] = " ";

        winner = new Label("");
        total = new Label("Total Game played: "+ totalMatch);
        won = new Label("Match won: "+ wonMatch);
        lost= new Label("Match Lost:"+ loseMatch);
        draw = new Label("Match Drawn:"+ drawMatch);
        reload.setId("startButton");
        back.setId("startButton");
    }



    public int makeMove(int index)
    {	/*Step to do in this method
		*1- update gameXO. put X in the gameXO[index]
		*2- test if game is finished (draw or X win)
		*3- call MinMax algorithm and return the score and return the best position for O
		*4- update gameXO. put O in its position
		*5- test if game is finished (draw or O win)
		*/
        //return -1 to know that player X wins
        //return -2 to know that the game is draw
        //1
        gameXO[index] = "X";
        //2
        if(gameOver(gameXO)) { return -1; }
        if(drawGame(gameXO)) { return -2; }

        //3
        ResultMM res = MinMax(gameXO,"MAX", 0, 0);
        int i = res.getIntrus();

        //4
        gameXO[i] = "O";

        //5
        // return i+20 to know that o wins (i used this method for programming issues)
        // retrun i-30 to know that the game is draw (i used this method for programming issues)
       // if(gameOver(gameXO)) { return i+20; }
        //if(drawGame(gameXO)) { return i-30;	}

        return i;

    }

    public ResultMM MinMax(String[] demo, String level, int fils, int depth)
    {/*MinMax algorithm
		* 1- generate successor
		* 2- if no successor or game is finished return score
		* 3- if there is successor
		* 	a) apply MinMax for each successor
		*	b) after recursive call, i return the good score
		*/
        //1---------------
        ArrayList<String[]> children = genere_succ(demo,level);
        //2------------------
        if(children == null && sdepth != -1)
        {
            sdepth = -1;
            depth = depth + 1;
        }

        if(children == null || gameOver(demo))
        {
            return new ResultMM(demo, getScore(demo), depth);
        }
        else
        {//3------------------
            if(sdepth > children.size())
            {
                sdepth = children.size();
                depth = depth + 1;
            }

            ArrayList<ResultMM> listScore = new ArrayList<ResultMM>();
            //pass into each child
            for(int i = 0; i<children.size(); i++)
            {//3 a)---------------
                listScore.add( MinMax(children.get(i), inverse(level), 1, depth+1));
            }
            //3 b)----------------
            ResultMM res = getResult(listScore, level);
            if( fils == 1)
                res.updateMatrix(demo);

            return res;
        }
    }
    public ResultMM getResult(ArrayList<ResultMM> listScore, String level)
    {//this method is used to get the appropriate score
        //if level is MAX, i search for the higher score in the nearer depth
        //if level is MIN, i search for the lowest score in the nearer depth
        ResultMM result= listScore.get(0);
        if(level.equals("MAX"))
        {
            for(int i=1; i<listScore.size(); i++)
            {
                if( (listScore.get(i).getScore() > result.getScore())
                        ||
                        (listScore.get(i).getScore() == result.getScore() && listScore.get(i).depth < result.depth) )
                    result = listScore.get(i);
            }
        }
        else
        {
            for(int i=1; i<listScore.size(); i++)
            {
                if( (listScore.get(i).getScore() < result.getScore())
                        ||
                        (listScore.get(i).getScore() == result.getScore() && listScore.get(i).depth < result.depth) )

                    result = listScore.get(i);
            }
        }
        return result;
    }
    public ArrayList<String[]> genere_succ(String[] demo, String level)
    {//generate successor
        //if level is MAX, generate successor with o ( o in lowerCase)
        //if level is MIN, generate successor with x ( x in lowerCase)
        //if demo has no successor, return null
        ArrayList<String[]> succ = new ArrayList<String[]>();
        for(int i=0; i<demo.length; i++)
        {
            if( demo[i].equals(" ") )
            {
                String[] child = new String[9];
                for(int j=0; j<9; j++)
                    child[j] = demo[j];

                if(level.equals("MAX"))
                    child[i] = "o";
                else
                    child[i] = "x";
                succ.add(child);
            }
        }
        return ( succ.size() == 0 ) ? null : succ ;
    }
    /*public Controller()
    {//reInitialise the gameXO when i create a new controller
        for(int i=0; i<9; i++)
            gameXO[i] = " ";
    }*/
    public String inverse(String level)
    { //inverse level from MIN to MAX
        return (level.equals("MIN")) ?  "MAX" : "MIN" ;
    }
    public int getScore(String[] demo)
    { //return  the score:
        //if X win return -1;
        //if O win return 1;
        //else return 0, this mean draw
        if( (demo[0].equalsIgnoreCase("x") && demo[1].equalsIgnoreCase("x") && demo[2].equalsIgnoreCase("x")) || (demo[3].equalsIgnoreCase("x") && demo[4].equalsIgnoreCase("x") && demo[5].equalsIgnoreCase("x")) ||
                (demo[6].equalsIgnoreCase("x") && demo[7].equalsIgnoreCase("x") && demo[8].equalsIgnoreCase("x")) || (demo[0].equalsIgnoreCase("x") && demo[3].equalsIgnoreCase("x") && demo[6].equalsIgnoreCase("x")) ||
                (demo[1].equalsIgnoreCase("x") && demo[4].equalsIgnoreCase("x") && demo[7].equalsIgnoreCase("x")) || (demo[2].equalsIgnoreCase("x") && demo[5].equalsIgnoreCase("x") && demo[8].equalsIgnoreCase("x"))  ||
                (demo[0].equalsIgnoreCase("x") && demo[4].equalsIgnoreCase("x") && demo[8].equalsIgnoreCase("x")) || (demo[2].equalsIgnoreCase("x") && demo[4].equalsIgnoreCase("x") && demo[6].equalsIgnoreCase("x"))
                )
            return -1;

        if( (demo[0].equalsIgnoreCase("o") && demo[1].equalsIgnoreCase("o") && demo[2].equalsIgnoreCase("o")) || (demo[3].equalsIgnoreCase("o") && demo[4].equalsIgnoreCase("o") && demo[5].equalsIgnoreCase("o")) ||
                (demo[6].equalsIgnoreCase("o") && demo[7].equalsIgnoreCase("o") && demo[8].equalsIgnoreCase("o")) || (demo[0].equalsIgnoreCase("o") && demo[3].equalsIgnoreCase("o") && demo[6].equalsIgnoreCase("o")) ||
                (demo[1].equalsIgnoreCase("o") && demo[4].equalsIgnoreCase("o") && demo[7].equalsIgnoreCase("o")) || (demo[2].equalsIgnoreCase("o") && demo[5].equalsIgnoreCase("o") && demo[8].equalsIgnoreCase("o"))  ||
                (demo[0].equalsIgnoreCase("o") && demo[4].equalsIgnoreCase("o") && demo[8].equalsIgnoreCase("o")) || (demo[2].equalsIgnoreCase("o") && demo[4].equalsIgnoreCase("o") && demo[6].equalsIgnoreCase("o"))
                )
            return 1;

        return 0;
    }
    public boolean gameOver(String[] demo)
    {//if the score of the game is 0 then return false. this mean we have a winner
        return (getScore(demo)!=0) ? true : false;
    }
    public boolean drawGame(String[] demo)
    {
        //test if the game is draw.
        //if demo is full, this mean that game is draw
        //if demo still has empty square, this mean that the game isn't finished
        for(int i=0; i<9; i++)
            if(demo[i].equals(" "))
                return false;
        return true;
    }

    void getColored(){
       for(int i=0 ; i<3 ;i++){
           if(gameXO[i]==gameXO[i+3] && gameXO[i]==gameXO[i+6]){
               box[i].setId("button-blue");
               box[i+3].setId("button-blue");
               box[i+6].setId("button-blue");
               winner.setText("Computer Wins !!!");

               return;
           }
       }

        for(int i=0 ; i<3 ;i+=3){
            if(gameXO[i]==gameXO[i+1] && gameXO[i]==gameXO[i+2]){
                box[i].setId("button-blue");
                box[i+1].setId("button-blue");
                box[i+2].setId("button-blue");
                winner.setText("Computer Wins !!!");
                return;
            }
        }

        if(gameXO[0]==gameXO[4] && gameXO[0]==gameXO[8]){
            box[0].setId("button-blue");
            box[4].setId("button-blue");
            box[8].setId("button-blue");
            winner.setText("Computer Wins !!!");
            return;
        }

        if(gameXO[2]==gameXO[4] && gameXO[2]==gameXO[6]){
            box[2].setId("button-blue");
            box[4].setId("button-blue");
            box[6].setId("button-blue");
            winner.setText("Computer Wins !!!");
            return;
        }
    }


    public void reload(String playerName){
        for(int i=0 ; i<9 ; i++)
        {
            box[i].setText(" ");
            box[i].setId("button");
        }

        for(int i=0; i<9; i++)
            gameXO[i] = " ";

        winner.setText(" ");
        mid(playerName);
    }


    public void mid(String playerName)
    {
        reload.setOnAction(e-> {
            totalMatch++;
            total.setText("Total Game played: "+ totalMatch);

            reload(playerName);


            return;
        });

       /* back.setOnAction(e-> {
           Main.window.setScene(scene);

        });*/

        box[0].setOnAction(e->
        {
            box[0].setText("x");
            int move =makeMove(0);
            System.out.println(move);
            if(move == -1)
            {
                getColored();

            }
            else if(move == -2)
            {

                winner.setText("It's a Draw");
                drawMatch++;
                draw.setText("Match Drawn:"+ drawMatch);
            }
            else {
                box[move].setText("o");
                if (gameOver(gameXO)) {
                    getColored();
                    loseMatch++;
                    lost.setText("Match Lost:"+ loseMatch);

                }
                if (drawGame(gameXO)) {
                    winner.setText("It's a Draw");
                    drawMatch++;
                    draw.setText("Match Drawn:"+ drawMatch);

                }
            }




        });

        box[1].setOnAction(e->
        {
            box[1].setText("x");
            int move =makeMove(1);
            System.out.println(move);

            if(move == -1)
            {
                getColored();

            }
            else if(move == -2)
            {
                winner.setText("It's a Draw");
                drawMatch++;
                draw.setText("Match Drawn:"+ drawMatch);

            }
            else {
                box[move].setText("o");
                if(gameOver(gameXO)) {
                    getColored();
                    loseMatch++;
                    lost.setText("Match Lost:"+ loseMatch);
                }
                if(drawGame(gameXO)) {
                    winner.setText("It's a Draw");
                    drawMatch++;
                    draw.setText("Match Drawn:"+ drawMatch);

                }
            }



        });

        box[2].setOnAction(e->
        {
            box[2].setText("x");
            int move =makeMove(2);
            System.out.println(move);

            if(move == -1)
            {
                getColored();
            }
            else if(move == -2)
            {

                winner.setText("It's a Draw");
                drawMatch++;
                draw.setText("Match Drawn:"+ drawMatch);
            }
            else {
                box[move].setText("o");
                if(gameOver(gameXO)) {
                    getColored();
                    loseMatch++;
                    lost.setText("Match Lost:"+ loseMatch);
                }
                if(drawGame(gameXO)) {
                    winner.setText("It's a Draw");
                    drawMatch++;
                    draw.setText("Match Drawn:"+ drawMatch);

                }
            }



        });

        box[3].setOnAction(e->
        {
            box[3].setText("x");
            int move =makeMove(3);
            System.out.println(move);

            if(move == -1)
            {
                getColored();
            }
            else if(move == -2)
            {

                winner.setText("It's a Draw");
                drawMatch++;
                draw.setText("Match Drawn:"+ drawMatch);
            }
            else {
                box[move].setText("o");
                if(gameOver(gameXO)) {
                    getColored();
                    loseMatch++;
                    lost.setText("Match Lost:"+ loseMatch);
                }
                if(drawGame(gameXO)) {
                    winner.setText("It's a Draw");
                    drawMatch++;
                    draw.setText("Match Drawn:"+ drawMatch);

                }
            }



        });

        box[4].setOnAction(e->
        {
            box[4].setText("x");
            int move =makeMove(4);
            System.out.println(move);

            if(move == -1)
            {
                getColored();
            }
            else if(move == -2)
            {

                winner.setText("It's a Draw");
                drawMatch++;
                draw.setText("Match Drawn:"+ drawMatch);
            }
            else {
                box[move].setText("o");
                if(gameOver(gameXO)) {
                    getColored();
                    loseMatch++;
                    lost.setText("Match Lost:"+ loseMatch);
                }
                if(drawGame(gameXO)) {
                    winner.setText("It's a Draw");
                    drawMatch++;
                    draw.setText("Match Drawn:"+ drawMatch);

                }
            }



        });

        box[5].setOnAction(e->
        {
            box[5].setText("x");
            int move =makeMove(5);
            System.out.println(move);

            if(move == -1)
            {
                getColored();
            }
            else if(move == -2)
            {

                winner.setText("It's a Draw");
                drawMatch++;
                draw.setText("Match Drawn:"+ drawMatch);
            }
            else {
                box[move].setText("o");
                if(gameOver(gameXO)) {
                    getColored();
                    loseMatch++;
                    lost.setText("Match Lost:"+ loseMatch);
                }
                if(drawGame(gameXO)) {
                    winner.setText("It's a Draw");
                    drawMatch++;
                    draw.setText("Match Drawn:"+ drawMatch);

                }
            }



        });

        box[6].setOnAction(e->
        {
            box[6].setText("x");
            int move =makeMove(6);
            System.out.println(move);

            if(move == -1)
            {
                getColored();
            }
            else if(move == -2)
            {

                winner.setText("It's a Draw");
                drawMatch++;
                draw.setText("Match Drawn:"+ drawMatch);
            }
            else {
                box[move].setText("o");
                if(gameOver(gameXO)) {
                    getColored();
                    loseMatch++;
                    lost.setText("Match Lost:"+ loseMatch);
                }
                if(drawGame(gameXO)) {
                    winner.setText("It's a Draw");
                    drawMatch++;
                    draw.setText("Match Drawn:"+ drawMatch);

                }
            }



        });

        box[7].setOnAction(e->
        {
            box[7].setText("x");
            int move =makeMove(7);
            System.out.println(move);

            if(move == -1)
            {
                getColored();
            }
            else if(move == -2)
            {

                winner.setText("It's a Draw");
                drawMatch++;
                draw.setText("Match Drawn:"+ drawMatch);
            }
            else {
                box[move].setText("o");
                if(gameOver(gameXO)) {
                    getColored();
                    loseMatch++;
                    lost.setText("Match Lost:"+ loseMatch);
                }
                if(drawGame(gameXO)) {
                    winner.setText("It's a Draw");
                    drawMatch++;
                    draw.setText("Match Drawn:"+ drawMatch);

                }
            }



        });

        box[8].setOnAction(e->
        {
            box[8].setText("x");
            int move =makeMove(8);
            System.out.println(move);

            if(move == -1)
            {
                getColored();
            }
            else if(move == -2)
            {

                winner.setText("It's a Draw");
                drawMatch++;
                draw.setText("Match Drawn:"+ drawMatch);
            }
            else {
                box[move].setText("o");
                if(gameOver(gameXO)) {
                    getColored();
                    loseMatch++;
                    lost.setText("Match Lost:"+ loseMatch);
                }
                if(drawGame(gameXO)) {
                    winner.setText("It's a Draw");
                    drawMatch++;
                    draw.setText("Match Drawn:"+ drawMatch);

                }
            }



        });
    }




}





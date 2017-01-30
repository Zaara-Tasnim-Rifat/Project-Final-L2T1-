/**
 * Created by Zaara Tasnim Rifat on 6/8/2016.
 */
public class ResultMMhard {

    String[] matrix;
    int score;
    int depth;

    public ResultMMhard(String[] matrix, int score, int depth)
    {
        this.matrix = matrix;
        this.score = score;
        this.depth = depth;
    }

    public void updateMatrix(String[] matrix)
    {
        this.matrix = matrix;
    }

    public int getScore()
    {
        return score;
    }
    public int getIntrus()
    {
        for(int i=0; i<16; i++)
            if(matrix[i].equals("o"))
                return i;
        return -1;
    }
}

import java.util.*;
import java.io.*;
import java.math.*;

class Player
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        while (true)
        {
            int opponentRow = in.nextInt();
            int opponentCol = in.nextInt();
            int validActionCount = in.nextInt();
            for (int i = 0; i < validActionCount; i++)
            {
                int row = in.nextInt();
                int col = in.nextInt();
            }
            System.out.println("0 0");
        }
    }
}

class Case
{
    private int x, y;

    public Case(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }
}

class Grille
{
    private int player, ennemy, size;

    public Grille()
    {
        //TODO
    }

    public int getPlayer()
    {
        return player;
    }

    public int getEnnemy()
    {
        return ennemy;
    }

    public int getSize()
    {
        return size;
    }
}

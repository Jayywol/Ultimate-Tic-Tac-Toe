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
    private HashMap<String, Case> grille = new HashMap<String, Case>();

    public Grille()
    {
        for (int y = 0;y < size; y++)
            for (int x = 0; x < size; x++)
                grille.put("("+x+";"+y+")", new Case(x, y));
    }

    public Set<String> getKey()
    {
        return grille.keySet();
    }

    public Case getCase(String key)
    {
        return grille.get(key);
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

    public boolean checkEnd()
    {
        for (int i = 0; i < getSize(); i++)
            if (!(checkLine(i, 3)) || !(checkColumn(i, 3)))
                return true;
        for (String i : getKey())
            if (getKey(i).getValue())
                return false;
        return true;
    }

    public int checkLine(int y, int n)
    {
        int ennemy = 0, player = 0;
        for (int x = 0; x < getSize(); x++)
        {
            int val = getCase("("+x+";"+y+")").getValue();
            if (val == getPlayer())
                player++;
        }
        if (ennemy == n)
            return getEnemy();
        if (player == n)
            return getPlayer();
        return 0;
    }

    public int checkColumn(int y, int n)
    {
        int ennemy = 0, player = 0;
        for (int y = 0; x < getSize(); y++)
        {
            int val = getCase("("+x+";"+y+")").getValue();
            if (val == getPlayer())
                player++;
        }
        if (ennemy == n)
            return getEnemy();
        if (player == n)
            return getPlayer();
        return 0;
    }
}

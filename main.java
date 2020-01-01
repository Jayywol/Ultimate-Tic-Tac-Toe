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
    private int x, y, value;

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

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        this.value = value;
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

    public void Play(int x, int y, int player)
    {
        grille.get("("+x+";"+y+")").setValue(player);
    }

    public void Cancel(int x, int y)
    {
        grille.get("("+x+";"+y+")").setValue(0);
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
            return getEnnemy();
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
            return getEnnemy();
        if (player == n)
            return getPlayer();
        return 0;
    }
}

class Algo
{
    Grille grille;
    private int max_value = -1000, x, y, depth;

    public void Jouer(Grille grille)
    {
        this.grille = grille;
        grille.Play(x, y, grille.getEnnemy());
    }

    private int Min(int depth)
    {
        int min_val = 1000;
        for (String i : grille.Key())
        {
            if (grille.getCase(i).getValue() == 0)
            {
                int x = grille.getCase(i).getX(), y = grille.getCase(i).getY();
                grille.Play(x, y, grille.getPlayer());
                int val = Max(depth - 1);
                if (val < min_val)
                    min_val = val;
                grille.Cancel(x, y);
            }
        }
        return min_val;
    }

    private int Max(int depth)
    {
        int max_val = -1000;
        for (String i : grille.Key())
        {
            if (grille.GetCase(i).GetVal() == 0)
            {
                int x = grille.getCase(i).getX(), y = grille.getCase(i).getY();
                grille.Play(x, y, grille.Ennemy());
                int val = Min(depth-1);
                if (val > max_val)
                    max_val = val;
                grille.Cancel(x, y);
            }
        }
        return max_val;
    }
}

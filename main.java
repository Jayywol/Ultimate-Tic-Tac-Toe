import java.util.*;
//import java.io.*;
//import java.math.*;

class Player
{
    public static void main(String args[])
    {
        Scanner in = new Scanner(System.in);
        Grille grille = new Grille();
        Algo ennemy = new Algo();
        // Game loop
        while (true)
        {
            int x = in.nextInt();
            int y = in.nextInt();
            int validActionCount = in.nextInt();
            for (int i = 0; i < validActionCount; i++)
            {
                int row = in.nextInt();
                int col = in.nextInt();
            }
            if (x != -1) // Codingame playing first - Must avoid -1
                grille.Play(x, y, grille.getPlayer());
            ennemy.Play(grille);
        }
    }
}

class Case
{
    private int x, y, value = 0;

    // Constructor
    public Case(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // Getter X
    public int getX()
    {
        return x;
    }

    // Getter Y
    public int getY()
    {
        return y;
    }

    // Getter Value
    public int getValue()
    {
        return value;
    }

    // Setter Value
    public void setValue(int value)
    {
        this.value = value;
    }
}

class Grille
{
    private int player = 1, ennemy = 2, size = 3;
    private HashMap<String, Case> grille = new HashMap<String, Case>();

    // Constructor
    public Grille()
    {
        for (int y = 0;y < size; y++) // Fill grid 3x3 (size)
            for (int x = 0; x < size; x++)
                grille.put("("+x+";"+y+")", new Case(x, y));
    }

    // Get the set view of keys
    public Set<String> Key()
    {
        return grille.keySet();
    }

    // Getter grid
    public Case getCase(String key)
    {
        return grille.get(key);
    }

    // Getter player
    public int getPlayer()
    {
        return player;
    }

    // Getter ennemy
    public int getEnnemy()
    {
        return ennemy;
    }

    // Getter size
    public int getSize()
    {
        return size;
    }

    // Play with x, y given by parameters
    public void Play(int x, int y, int player)
    {
        grille.get("("+x+";"+y+")").setValue(player);
    }

    // Cancel with x, y given by parameters
    public void Cancel(int x, int y)
    {
        grille.get("("+x+";"+y+")").setValue(0);
    }

    // Check line grid's
    public int checkLine(int y, int n)
    {
        int ennemy = 0, player = 0;
        for (int x = 0; x < getSize(); x++)
        {
            int value = getCase("("+x+";"+y+")").getValue();
            if (value == getPlayer())
                player++;
            else if (value == getEnnemy())
                ennemy++;
        }
        return myCheck(ennemy, player, n);
    }

    // Check column grid's
    public int checkColumn(int x, int n)
    {
        int ennemy = 0, player = 0;
        for (int y = 0; y < getSize(); y++)
        {
            int value = getCase("("+x+";"+y+")").getValue();
            if (value == getPlayer())
                player++;
            else if (value == getEnnemy())
                ennemy++;
        }
        return myCheck(ennemy, player, n);
    }

    public int checkDiago(int n)
    {
        int ennemy = 0, player = 0;
        for (int i = 0; i < getSize(); i++)
        {
            int value = getCase("("+i+";"+i+")").getValue();
            if (value == getPlayer())
                player++;
            else if (value == getEnnemy())
                ennemy++;
        }
        return myCheck(ennemy, player, n);
    }

    public int myCheck(int ennemy, int player, int n)
    {
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
    private int max_value = -1000, x, y, depth = 2;

    public void Play(Grille grille)
    {
        this.grille = grille;
        checking();
        grille.Play(x, y, grille.getEnnemy());
        System.out.println(x+" "+y);
    }

    private void checking()
    {
        for (String i : grille.Key())
            if (grille.getCase(i).getValue() == 0)
            {
                int x = grille.getCase(i).getX(), y = grille.getCase(i).getY();
                grille.Play(x, y, grille.getEnnemy());
                int value = Min(depth);
                if (value >= max_value)
                {
                    max_value = value;
                    this.x = x;
                    this.y = y;
                }
                grille.Cancel(x, y);
            }
    }

    private int Min(int depth)
    {
        if (depth == 0)
            return myAlgo();
        int min_value = 1000;
        for (String i : grille.Key())
        {
            if (grille.getCase(i).getValue() == 0)
            {
                int x = grille.getCase(i).getX(), y = grille.getCase(i).getY();
                grille.Play(x, y, grille.getPlayer());
                int value = Max(depth - 1);
                if (value < min_value)
                    min_value = value;
                grille.Cancel(x, y);
            }
        }
        return min_value;
    }

    private int Max(int depth)
    {
        if (depth == 0)
            return myAlgo();
        int max_value = -1000;
        for (String i : grille.Key())
        {
            if (grille.getCase(i).getValue() == 0)
            {
                int x = grille.getCase(i).getX(), y = grille.getCase(i).getY();
                grille.Play(x, y, grille.getEnnemy());
                int value = Min(depth - 1);
                if (value > max_value)
                    max_value = value;
                grille.Cancel(x, y);
            }
        }
        return max_value;
    }

    // Algo giving eloscore
    private int myAlgo()
    {
        int score = 0;
        for (int i = 0; i < grille.getSize(); i++)
        {
            // Check Line
            if (grille.checkLine(i, 2) == grille.getEnnemy())
                score += 2;
            else if (grille.checkLine(i, 2) == grille.getPlayer())
                score -= 2;
            else
                score -= 1;
            if (grille.checkLine(i, 3) == grille.getEnnemy())
                score += 2000;
            else if (grille.checkLine(i, 3) == grille.getPlayer())
                score -= 1000;

            // Check Column
            if (grille.checkColumn(i, 2) == grille.getEnnemy())
                score += 2;
            else if (grille.checkColumn(i, 2) == grille.getPlayer())
                score -= 2;
            else
                score -= 1;
            if (grille.checkColumn(i, 3) == grille.getEnnemy())
                score += 2000;
            else if (grille.checkColumn(i, 3) == grille.getPlayer())
                score -= 1000;
        }
        if (grille.checkDiago(2) == grille.getEnnemy())
            score += 2;
        else if (grille.checkDiago(2) == grille.getPlayer())
            score -= 2;
        else score -= 1;

        if (grille.checkDiago(2) == grille.getEnnemy())
            score += 2;
        else if (grille.checkDiago(2) == grille.getPlayer())
            score -= 2;
        return score;
    }
}

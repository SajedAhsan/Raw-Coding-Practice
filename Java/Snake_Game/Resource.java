package Java.Snake_Game;

import java.util.Random;

public class Resource {
    private static int x = 0;
    private static int y = 0;
    private static char cur = 'd';
    // private static char prev = 'd';
    private static int xx = -1;
    private static int yy = -1;

    public static char[][] grid = 
    {
        {'P','.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.','.'},
        {'.','.','.','.','.','.','.','.','.','.'}
    };

    public static void print()
    {
        if(xx != -1)
            grid[xx][yy] = 'G';
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[i].length; j++)
            {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
        if(xx != -1)
            grid[xx][yy] = '.';
        
    }
    public static void movement(char c)
    {
        cur = c; 
    }
    public static void generatePoint()
    {
        Random rand = new Random();
        xx = rand.nextInt(grid.length); 
        yy = rand.nextInt(grid[0].length);
    }
    public static void omitPoint()
    {
        xx = -1; yy = -1;
    }
    public static void changePos()
    {
        grid[x][y] = '.';
        if(Character.toLowerCase(cur) == 'w')
            x = (x - 1 + grid.length)%grid.length;
        if(Character.toLowerCase(cur) == 'a')
            y = (y - 1 + grid[0].length)%grid[0].length;
        if(Character.toLowerCase(cur) == 's')
            x = (x + 1)%grid.length;
        if(Character.toLowerCase(cur) == 'd')
            y = (y + 1)%grid[0].length;
        grid[x][y] = 'P';
    }
}

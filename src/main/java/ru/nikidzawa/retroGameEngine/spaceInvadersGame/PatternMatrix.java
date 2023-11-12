package ru.nikidzawa.retroGameEngine.spaceInvadersGame;

public class PatternMatrix {
    public final static int[][] PLAYER = new int[][]
            {
                    {0, 0, 4, 0, 0},
                    {0, 4, 4, 4, 0},
                    {4, 4, 4, 4, 4}
            };
    public static final int[][] KILL_PLAYER_ANIMATION_FIRST = new int[][]{
            {7, 7, 7, 7, 7},
            {7, 7, 7, 7, 7},
            {7, 7, 7, 7, 7}
    };

    public static final int[][] KILL_PLAYER_ANIMATION_SECOND = new int[][]{
            {7, 0, 7, 0, 7},
            {0, 7, 0, 7, 0},
            {7, 0, 7, 0, 7}
    };

    public static final int[][] KILL_PLAYER_ANIMATION_THIRD = new int[][]{
            {0, 7, 0, 7, 0},
            {7, 0, 7, 0, 7},
            {0, 7, 0, 7, 0}
    };
    public static final int[][] DEAD_PLAYER = new int[][]{
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0}
    };

    public static final int[][] WIN_PLAYER = new int[][]{
            {0, 0, 5, 0, 0},
            {0, 5, 5, 5, 0},
            {5, 5, 5, 5, 5}
    };

    //------------------------------------------------------------------//

    public final static int[][] ENEMY = new int[][]
            {
                    {5, 0, 5},
                    {5, 5, 5},
                    {0, 5, 0}
            };
    public static final int[][] KILL_ENEMY_ANIMATION_FIRST = new int[][]{
            {7, 7, 7},
            {7, 7, 7},
            {7, 7, 7}
    };

    public static final int[][] KILL_ENEMY_ANIMATION_SECOND = new int[][]{
            {7, 0, 7},
            {0, 7, 0},
            {7, 0, 7}
    };

    public static final int[][] KILL_ENEMY_ANIMATION_THIRD = new int[][]{
            {0, 0, 0},
            {0, 7, 0},
            {0, 0, 0}
    };


    //------------------------------------------------------------------//
    public final static int[][] BULLET = new int[][]
            {
                    {1},
                    {1}
            };
}

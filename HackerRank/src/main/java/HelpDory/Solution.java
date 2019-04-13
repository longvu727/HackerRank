package HelpDory;

import java.util.*;

public class Solution {

    public static void main_test(String [] args) {
        Scanner scanner = new Scanner(System.in);

        int pokemonNumX = 4;
        int pokemonCityNumY = 3;
        String pokemonNamesStr = "charmander pikachu snorlax pikachu";//"P Q P Q R P";
        String pokemonCitiesStr = "charmander pikachu snorlax";//"P Q R";

        Solution solution = new Solution(pokemonNumX, pokemonCityNumY, pokemonNamesStr, pokemonCitiesStr);
        solution.DEBUG = true;
    }

    public static void main(String [] args) {
        main_test(args);
    }

}

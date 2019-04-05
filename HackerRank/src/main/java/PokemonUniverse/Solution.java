package PokemonUniverse;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    /*
        4
        2
        charmander pikachu snorlax pikachu
        charmander pikachu
     */
    int pokemonNum = 0;
    int pokemonCitiesNum = 0;
    String [] pokemons = null;
    String [] pokemonCities = null;

    public boolean DEBUG = false;

    public Solution(int pokemonNum, int pokemonCitiesNum, String pokemonNamesStr, String pokemonCitiesStr) {
        this.pokemonNum = pokemonNum;
        this.pokemonCitiesNum = pokemonCitiesNum;
        this.pokemons = pokemonNamesStr.split("\\s+");
        this.pokemonCities = pokemonCitiesStr.split("\\s+");
    }

    private void debug(String str) {
        if(this.DEBUG) {
            System.out.println(str);
        }
    }

    private String[] getMovesByCities(String[] cities) {
        this.debug("Cites order: " + String.join(" ", cities));
        this.debug("Pokemon order: " + String.join(" ", this.pokemons));

        int i = 0;
        ArrayList<String> moves = new ArrayList<String>();
        ArrayList<String> pokemonsList = new ArrayList<String>(Arrays.asList(this.pokemons));

        while(pokemonsList.size() > 0) {
            int cityIndex = i++ % pokemonCitiesNum;
            String city = cities[cityIndex];
            moves.add(city);

            this.debug("--City: " + city);

            Iterator<String> pokemonListIter = pokemonsList.iterator();
            while( pokemonListIter.hasNext() ) {
                String pokemon = pokemonListIter.next();
                if( pokemon.equals(city) ){
                    this.debug("----Same Pokemon: " + pokemon + ", moving to next city");
                    break;
                }
                else {
                    this.debug("----Remove: " + pokemon );
                    pokemonListIter.remove();
                }
            }
        }
        this.debug("Moves: " + moves.size() );

        return moves.toArray(new String[0]);
    }

    public ArrayList<String> combination(ArrayList<String> inputList, ArrayList<String> combinationHashSet ) {

        for( String item : inputList ) {
            ArrayList<String> inputListClone = (ArrayList<String>)inputList.clone();
            inputListClone.remove( item );

            //inputListClone size 3, 2, 1, 0
            ArrayList<String> combbinationList = this.combination( inputListClone, combinationHashSet );
        }

        return inputList;
    }

    /*
    public ArrayList<String> combination(ArrayList<String> inputList, ArrayList<String> combinationHashSet ) {

        if( inputList.size() == 0 ) {
            return combinationHashSet;
        }

        for( int i=0; i<inputList.size(); i++ ) {

            ArrayList<String> arrayListClone = (ArrayList<String>) inputList.clone();
            arrayListClone.remove(i);

            combinationHashSet.add( inputList.get(i) );

            Iterator<String> combinationIter = combinationHashSet.iterator();
            ArrayList<String> combinationHashSetNew = new ArrayList<String>();


            this.combination(arrayListClone, combinationHashSet);


            while( combinationIter.hasNext() ) {
                //combinationNew.add();
                combinationIter.next();
                for( String e : arrayListClone ) {
                    ;
                }

            }
        }

        return combinationHashSet;
    }
     */

    public String[] getMoves() {
        String[] cities = this.pokemonCities;
        String [] bestMoves = null;

        for(int i=0; i<this.pokemonCitiesNum; i++) {
            String[] moves = this.getMovesByCities(cities);

            if( bestMoves == null || bestMoves.length > moves.length ) {
                bestMoves = moves;
            }

            //combination
            List<String> list = new ArrayList<String>( Arrays.asList(cities) );
            list.add( list.remove(0) );
            cities = list.toArray(new String[0]);
        }


        ArrayList<String> combinationArrayList = new ArrayList<String>();
        ArrayList<String> cityArrayList = new ArrayList<String>( Arrays.asList(cities) );
        this.combination( cityArrayList, combinationArrayList );

        return bestMoves;
    }

    public static void main_submit(String [] args) {
        Scanner scanner = new Scanner(System.in);

        int pokemonNumX = Integer.parseInt( scanner.nextLine() );// 4
        int pokemonCityNumY = Integer.parseInt( scanner.nextLine() );// 2
        String pokemonNamesStr = scanner.nextLine();//"charmander pikachu snorlax pikachu";
        String pokemonCitiesStr = scanner.nextLine();//"charmander pikachu";

        Solution solution = new Solution(pokemonNumX, pokemonCityNumY, pokemonNamesStr, pokemonCitiesStr);
        solution.DEBUG = true;
        String [] output = solution.getMoves();

        System.out.println(String.join(" ", output));
    }

    public static void main_test(String [] args) {
        Scanner scanner = new Scanner(System.in);

        int pokemonNumX = 6;
        int pokemonCityNumY = 3;
        String pokemonNamesStr = "P Q P Q R P";
        String pokemonCitiesStr = "P Q R";

        Solution solution = new Solution(pokemonNumX, pokemonCityNumY, pokemonNamesStr, pokemonCitiesStr);
        solution.DEBUG = true;
        String [] output = solution.getMoves();

        System.out.println(String.join(" ", output));


        //solution.combination();
    }

    public static void main(String [] args) {
        main_test(args);
    }

}

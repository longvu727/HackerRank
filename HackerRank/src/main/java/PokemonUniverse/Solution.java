package PokemonUniverse;

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
    ArrayList<String> pokemons = null;
    ArrayList<String> pokemonCities = null;

    public boolean DEBUG = false;

    public Solution(int pokemonNum, int pokemonCitiesNum, String pokemonNamesStr, String pokemonCitiesStr) {
        this.pokemonNum = pokemonNum;
        this.pokemonCitiesNum = pokemonCitiesNum;

        String [] pokemons = pokemonNamesStr.split("\\s+");
        String [] pokemonCities = pokemonCitiesStr.split("\\s+");

        this.pokemons = new ArrayList<>( Arrays.asList(pokemons) );
        this.pokemonCities = new ArrayList<>( Arrays.asList(pokemonCities) );
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
        ArrayList<String> moves = new ArrayList<>();
        ArrayList<String> pokemonsList = (ArrayList<String>) this.pokemons.clone();

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
        //return cities;
    }
/*
P Q R
P R Q

Q P R
Q R P

R P Q
R Q P
 */
    public ArrayList<ArrayList<String>> permutation(ArrayList<String> inputList ) {

        if( inputList.size() == 1 ){
            ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
            ret.add(inputList);
            return ret;
        }

        ArrayList<ArrayList<String>> combinationList = new ArrayList<ArrayList<String>>();

        for( String item : inputList ) {
            ArrayList<String> inputListClone = (ArrayList<String>)inputList.clone();
            inputListClone.remove( item );

            //inputListClone size 3, 2, 1, 0
            ArrayList<ArrayList<String>> subPermutatedList = this.permutation( inputListClone );
            Iterator<ArrayList<String>> subPermutatedListIter = subPermutatedList.iterator();

            while ( subPermutatedListIter.hasNext() ) {
                ArrayList<String> combItem = subPermutatedListIter.next();
                combItem.add(0, item);
                //combinationList.add( (new StringBuilder(item)).append(combItem).toString() );
                combinationList.add( combItem );
            }
        }

        return combinationList;
    }

    public String[] getMovesPermutatedCities() {
        String [] bestMoves = null;
        ArrayList<String> bestRoute = new ArrayList<>();

        ArrayList<ArrayList<String>> permutatedCitiesList = this.permutation( (ArrayList<String>)this.pokemonCities.clone() );
        this.debug( permutatedCitiesList.toString() );

        for(ArrayList<String> cities : permutatedCitiesList) {
            String[] moves = this.getMovesByCities( cities.toArray(new String[cities.size()]) );

            if( bestMoves == null || bestMoves.length > moves.length ) {
                bestMoves = moves;
                bestRoute = cities;
            }
        }

        return bestRoute.toArray( new String[bestRoute.size()] );
    }

    public String[] getMoves() {
        String[] cities = this.pokemonCities.toArray( new String[this.pokemonCities.size()] );
        String [] bestMoves = null;

        for(int i=0; i<this.pokemonCitiesNum; i++) {
            String[] moves = this.getMovesByCities(cities);

            if( bestMoves == null || bestMoves.length > moves.length ) {
                bestMoves = moves;
            }

            //round robin
            List<String> list = new ArrayList<>( Arrays.asList(cities) );
            list.add( list.remove(0) );
            cities = list.toArray(new String[0]);
        }

        return bestMoves;
    }

    public static void main_submit(String [] args) {
        Scanner scanner = new Scanner(System.in);

        int pokemonNumX = Integer.parseInt( scanner.nextLine() );// 4
        int pokemonCityNumY = Integer.parseInt( scanner.nextLine() );// 2
        String pokemonNamesStr = scanner.nextLine();//"charmander pikachu snorlax pikachu";
        String pokemonCitiesStr = scanner.nextLine();//"charmander pikachu";

        Solution solution = new Solution(pokemonNumX, pokemonCityNumY, pokemonNamesStr, pokemonCitiesStr);
        solution.DEBUG = false;
        String [] output = solution.getMovesPermutatedCities();

        System.out.println(String.join(" ", output));
    }

    public static void main_test(String [] args) {
        Scanner scanner = new Scanner(System.in);

        int pokemonNumX = 4;
        int pokemonCityNumY = 3;
        String pokemonNamesStr = "charmander pikachu snorlax pikachu";//"P Q P Q R P";
        String pokemonCitiesStr = "charmander pikachu snorlax";//"P Q R";

        Solution solution = new Solution(pokemonNumX, pokemonCityNumY, pokemonNamesStr, pokemonCitiesStr);
        solution.DEBUG = true;
        String [] output = solution.getMovesPermutatedCities();

        System.out.println(String.join(" ", output));


        //solution.permutation();
    }

    public static void main(String [] args) {
        main_submit(args);
    }

}

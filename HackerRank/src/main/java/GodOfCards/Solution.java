package GodOfCards;
import java.util.*;

class Solution {

    HashMap<String, Integer> cardNameMap;
    public Solution() {
        this.cardNameMap = new HashMap<String, Integer>(){{
            put("A", 3); //"ACE"
            put("2", 3); //"TWO"
            put("3", 5); //"THREE"
            put("4", 4); //"FOUR"
            put("5", 4); //"FIVE"
            put("6", 3); //"SIX"
            put("7", 5); //"SEVEN"
            put("8", 5); //"EIGHT"
            put("9", 4); //"NINE"
            put("10", 3); //"TEN"
            put("J", 4); //"JACK"
            put("Q", 5); //"QUEEN"
            put("K", 4); //"KING"
        }};

    }

    public String[] godOfCards( String [] inputs ) {
        Iterator<String> i = Arrays.asList(inputs).iterator();
        String [] outputs = new String[inputs.length];
        int index = -1;
        int ct = 0;

        HashMap<String, Boolean> processed = new HashMap<String, Boolean>();

        while(i.hasNext()) {
            String input = i.next();
            Integer strLen = this.cardNameMap.get(input) + 1;

            index = (index + 1) % outputs.length;
            ct = 0;

            /*
            while(outputs[index] != null && ct++ < 99) {
                //index = (index + strLen) % outputs.length;
                for(int j=1; j<=strLen; j++) {
                    index = (index + j) % outputs.length;
                }
            }
            */

            while(strLen-- > 0) {
                String output = outputs[index];
                if(output != null && processed.containsKey(output)) {
                    strLen++;
                }

                index = (index + 1) % outputs.length;
            }
            if( index == 0) {
                index = outputs.length-1;
            }
            else {
                index--;
            }

            if(outputs[index] == null) {
                outputs[index] = input;
                processed.put(input, true);
            }

            //StringBuilder stringBuilder = new StringBuilder(strLen.toString()).append(":").append(input).append("index").append(index);
            //System.out.println(stringBuilder.toString());
            //System.out.println(Arrays.toString(outputs));
        }

        return outputs;
    }

    //--------------------------------------------
    //A 2 3 4 5 6 7 8 9 10 J Q K  -----> 3 8 7 A Q 6 4 2 J K 10 9 5
    //--------------------------------------------

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String myString = scanner.nextLine();

        String [] inputs = myString.split("\\s+");
        //System.out.println(inputs.length);

        Solution solution = new Solution();
        String[] outputs = solution.godOfCards(inputs);
        System.out.println(String.join(" ", outputs));

    }

}

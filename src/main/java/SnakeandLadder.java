import java.util.*;

public class SnakeandLadder {

   final static int winpoint =100;

   static Map<Integer,Integer> snakes = new HashMap<>();
   static Map<Integer, Integer> ladder = new HashMap<>();
    {
        snakes.put(99,7);
        snakes.put(92,35);
        snakes.put(78,39);
        snakes.put(73,53);
        snakes.put(37,17);
        snakes.put(31,14);

        ladder.put(5,25);
        ladder.put(10,29);
        ladder.put(22,41);
        ladder.put(28,55);
        ladder.put(44,95);
        ladder.put(70,79);
        ladder.put(79,81);
    }

    public int rollDice(){
        Random random = new Random();
        int n =random.nextInt(7);

        if(n==0){
            return 1;
        }else
            return n;
    }

    public int calculatePositionBasedonDice(int player, int diceValue){
        player = player + diceValue;

        if(player>winpoint){
            player=player-diceValue;
            return player;
        }
        if (null != snakes.get(player)) {
            player = snakes.get(player);
        }
        if (null != ladder.get(player)) {
            player=ladder.get(player);
        }
        return player;
    }

    public boolean isWin(int player){
        return winpoint==player;
    }

    public HashMap<Integer, Integer> calculatePlayerSequence(int noOfPlayers){
        Map<Integer,Integer> sequence = new HashMap<>();
        int i=0;
        while (noOfPlayers>0){
            sequence.put(i++,rollDice());
            noOfPlayers=noOfPlayers-1;
        }

        List<Map.Entry<Integer,Integer>> list = new LinkedList<>(sequence.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        HashMap<Integer,Integer> temp = new LinkedHashMap<>();
        for(Map.Entry<Integer,Integer> aa : list){
            temp.put(aa.getKey(),aa.getValue());
        }

        return temp;
    }

    public static void main(String[] args) {

        SnakeandLadder snakeandLadder = new SnakeandLadder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Number of Players :");
        int noOfPlayers = scanner.nextInt();
        HashMap<Integer, Integer> playerSequence = snakeandLadder.calculatePlayerSequence(noOfPlayers);
        System.out.println(playerSequence);

        HashMap<Integer, Integer> playerDetails = new HashMap<>();

        int player =0;
        int flag=0;
        while (flag==0){
        for(int i=1;i<=noOfPlayers;i++) {
            int value = snakeandLadder.rollDice();
            int position = snakeandLadder.calculatePositionBasedonDice(player, value);
            playerDetails.put(i, position);
            player=position;
            if (snakeandLadder.isWin(playerDetails.get(i))) {
                flag = i;
                break;
            }
        }
        } System.out.println("Player "+flag+ " has won the Match");
            System.out.println(playerDetails);

    }

}

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
  private PriorityQueue<Word> prioQueue;
  private HashSet<String> simpulEkspan;

  public AStar(){
    prioQueue = new PriorityQueue<Word>(Comparator.comparing(Word::getCost));
    simpulEkspan = new HashSet<String>();
  }
  
  public int calculateEstimedCost(Word now, String next, Word goal){
    char[] tempNext = next.toCharArray();
    char[] tempGoal = goal.getWord().toCharArray();
    int count = 0;
    for (int i = 0; i < tempNext.length; i++) {
        if (tempNext[i] != tempGoal[i]) {
            count++;
        }
    }
    return now.getCost() + 1 + count;
  }
  
  public void addQueue(Word word){
    prioQueue.add(word);
  }

  public void ubahKata(Word word, Word goal, BacaKamus kamus){
    simpulEkspan.add(word.getWord());
    ArrayList<String> tempprev = new ArrayList<>(word.getPrev());
    tempprev.add(word.getWord());
    for (int i = 0; i < word.getWord().length(); i++) {
      for (char c = 'a'; c <= 'z'; c++) {
        StringBuilder tempbuilder = new StringBuilder(word.getWord());
        tempbuilder.setCharAt(i, c);
        Word temp = new Word(tempbuilder.toString(), calculateEstimedCost(word, tempbuilder.toString(), goal), tempprev);
        if (temp.isValid(kamus) && !simpulEkspan.contains(tempbuilder.toString())) {
          addQueue(temp);
        }
      }  
    }
  }

  public static void mainAStar(String awal, String goal, BacaKamus kamus){
    boolean isKetemu = false;
    Word start = new Word(awal, 0, new ArrayList<String>());
    Word end = new Word(goal, 0, new ArrayList<String>());
    AStar aStar = new AStar();
    aStar.addQueue(start);
    while (!isKetemu && !aStar.prioQueue.isEmpty()) {
        start = aStar.prioQueue.poll();
        aStar.ubahKata(start, end, kamus);
        if (start.getWord().equals(end.getWord())) {
          isKetemu = true;
        }
    }
    if (isKetemu) {
      start.displayPath();
      System.out.println("Panjang Path : " + start.getPrev().size());
    } else {
        System.out.println("Algoritma A* tidak menemukan jalan dari " + awal + " ke " + goal + ".");
    }
    System.out.println("Jumlah node yang diperiksa : " + aStar.simpulEkspan.size());
  }

}

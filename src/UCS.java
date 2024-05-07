import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class UCS {
  private PriorityQueue<Word> prioQueue;
  private HashSet<String> simpulEkspan;

  public UCS(){
    prioQueue = new PriorityQueue<Word>(Comparator.comparing(Word::getCost));
    simpulEkspan = new HashSet<String>();
  }

  public int calculateCost(Word word){
    return word.getCost()+1;
  }
  
  public void addQueue(Word word){
    prioQueue.add(word);
  }

  public void ubahKata(Word word, BacaKamus kamus){
    simpulEkspan.add(word.getWord());
    ArrayList<String> tempprev = new ArrayList<>(word.getPrev());
    tempprev.add(word.getWord());
    for (int i = 0; i < word.getWord().length(); i++) {
      for (char c = 'a'; c <= 'z'; c++) {
        StringBuilder tempbuilder = new StringBuilder(word.getWord());
        tempbuilder.setCharAt(i, c);
        Word temp = new Word(tempbuilder.toString(), calculateCost(word), tempprev);
        if (temp.isValid(kamus) && !simpulEkspan.contains(tempbuilder.toString())) {
          addQueue(temp);
        }
      }  
    }
  }

  public static void mainUCS(String awal, String goal, BacaKamus kamus){
    boolean isKetemu = false;
    Word start = new Word(awal, 0, new ArrayList<String>());
    Word end = new Word(goal, 0, new ArrayList<String>());
    UCS ucs = new UCS();
    ucs.addQueue(start);
    while (!isKetemu && !ucs.prioQueue.isEmpty()) {
        start = ucs.prioQueue.poll();
        ucs.ubahKata(start, kamus);
        if (start.getWord().equals(end.getWord())) {
          isKetemu = true;
        }
    }
    if (isKetemu) {
      start.displayPath();
      System.out.println("Panjang Path : " + start.getPrev().size());
    } else {
        System.out.println("Algoritma Uniform cost Search tidak menemukan jalan dari " + awal + " ke " + goal + ".");
    }
    System.out.println("Jumlah node yang diperiksa : " + ucs.simpulEkspan.size());
  }
}

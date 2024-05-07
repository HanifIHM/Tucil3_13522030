import java.util.ArrayList;

class Word {
  private String word;
  private int cost;
  private ArrayList<String> prev;

  public Word(String word, int cost, ArrayList<String> prev){
    this.word = word;
    this.cost = cost;
    this.prev = prev;
  }

  public int getCost() {
    return cost;
  }

  public ArrayList<String> getPrev() {
    return prev;
  }

  public String getWord() {
    return word;
  }

  public void addPrev(String kata){
    prev.add(prev.size(), kata);
  }

  public void displayPath(){
    for (int i = 0; i < prev.size(); i++) {
        System.out.print(prev.get(i)+ " -> ");
    }
    System.out.println(word);
  }

  public boolean isValid(BacaKamus kamus){
    return kamus.getKamus().contains(word);
  }
}
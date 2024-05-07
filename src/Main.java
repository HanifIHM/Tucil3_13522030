import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BacaKamus kamus = new BacaKamus();
        Scanner sc = new Scanner(System.in);
        String start;
        String goal;
        do {
            do {
                System.out.println("Masukan kata awal :");
                System.out.print(">> ");
                start = sc.nextLine().toLowerCase();
                if (!kamus.getKamus().contains(start)) {
                    System.out.println("Kata masukan tidak ada dalam kamus");
                }

            } while (!kamus.getKamus().contains(start));

            do {
                System.out.println("Masukan kata tujuan :");
                System.out.print(">> ");
                goal = sc.nextLine().toLowerCase();
                if (!kamus.getKamus().contains(goal)) {
                    System.out.println("Kata masukan tidak ada dalam kamus");
                }

            } while (!kamus.getKamus().contains(goal));

            if (start.length() != goal.length()) {
                System.out.println("Kata awal dan akhir tidak memiliki panjang yang sama\n");
            }

        } while (start.length() != goal.length());

        String pilihan;
        long startTime;
        long endTime;
        do {
            System.out.println("Pilihlah Algoritma yang diinginkan : ");
            System.out.println("1. Algoritma Uniform Search Cost (UCS)");
            System.out.println("2. Algoritma Greedy Best First Search (Greedy BFS)");
            System.out.println("3. Algoritma A*");

            System.out.print(">> ");
            pilihan = sc.nextLine();
            System.out.println();
            startTime = System.currentTimeMillis();
            if (pilihan.equals("1")) {
                UCS.mainUCS(start, goal, kamus);
            } else if (pilihan.equals("2")) {
                GreedyBFS.mainGreedyBFS(start, goal, kamus);
            } else if (pilihan.equals("3")) {
                AStar.mainAStar(start, goal, kamus);
            } else {
                System.out.println("Pilihan tidak Valid");
            }

        } while (pilihan.equals("1") &&  pilihan.equals("2") && pilihan.equals("3"));

    endTime = System.currentTimeMillis();
    System.out.println("Runtime : " + (endTime-startTime) + " ms\n");
    sc.close();
    }
    
}
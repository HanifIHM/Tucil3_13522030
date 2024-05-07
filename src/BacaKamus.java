import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

class BacaKamus {
    private HashSet<String> kamus;

    public BacaKamus(){
        String fileName = "kamus.txt"; // Nama file yang ingin Anda baca
        HashSet<String> lines = new HashSet<>(); // Array untuk menyimpan setiap baris

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line); // Tambahkan setiap baris ke dalam array
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }  
        kamus = lines;
    }

    public HashSet<String> getKamus() {
        return kamus;
    }
}

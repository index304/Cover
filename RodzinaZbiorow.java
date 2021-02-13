// Jakub Bedełek
// file: RodzinaZbioror.java
// Plik zawiera implementacje klasy tworzącej rodzine zbiorów.

package cover;

import java.util.ArrayList;
import java.util.TreeSet;


public class RodzinaZbiorow {
    // rodzina - zbiór zbiorów należących do rodziny.
    private ArrayList<Zbior> rodzina;

    // Konstruktor.
    public RodzinaZbiorow() {
        rodzina = new ArrayList<Zbior>();
    }

    // Dodawanie nowego elementu do rodziny.
    public void dodaj(Zbior nowy) {
        rodzina.add(nowy);
    }

    // Funkcja zwracająca wszystkie liczby z zakresu do 1 do n
    // należące do zbiórów należących do rodziny.
    public TreeSet<Integer> daj_liczby(int n) {
        // Tablica zawierająca nieposortowane elementy należące do kolejnych
        // zbiorów z rodziny.
        ArrayList<ArrayList<Integer>> notSorted = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < rodzina.size(); i++) {
            notSorted.add(rodzina.get(i).elementy(n));
        }

        // Set odpowiedzialny za sumowanie elementów wszystkich zbiorów,
        // a także za usuwanie powtórzeń.
        TreeSet<Integer> tree_set = new TreeSet<Integer>();

        // Dodajemy wszystkie elementy ze zbiorów do tree_set.
        for (int j = 0; j < notSorted.size(); j++) {
            for (int k = 0; k < notSorted.get(j).size(); k++) {
                Integer liczba = Integer.valueOf(notSorted.get(j).get(k));
                tree_set.add(liczba);
            }
        }
        return tree_set;
    }
}

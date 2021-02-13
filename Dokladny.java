// Jakub Bedełek
// file: Dokladny.java
// Plik zawiera implementację algorytmu dokładnego.

package cover;

import java.util.ArrayList;
import java.util.TreeSet;

public class Dokladny extends Algorytm {
    // subsets - tablica zawierająca jeden z możliwych podzbiorów zbioru
    //           n elementowego.
    // Numbers - tablica zawierająca liczby od 1 do rozmiar - 1.
    // indeksy - tablica zawierająca nr. rodzin których suma pokrywa zbiór
    //           liczb od 1 do n.
    // sArray  - tablica tablic, gdzie każda tablica o nr. i składa się
    //           z posortowanych liczb należących do rodziny nr. i.
    // nr_of_el - liczba użytych rodzin do aktualnie najmniejszego pokrycia zbioru.
    // rozmiar  - liczba rodzin zbiorów.
    private ArrayList<Integer> subsets;
    private ArrayList<Integer> Numbers;
    private ArrayList<Integer> indeksy;
    private ArrayList<ArrayList<Integer>> sArray;
    private int nr_of_el;
    private int zakres;
    private int rozmiar;

    // Konstruktor.
    public Dokladny(int n, ArrayList<RodzinaZbiorow> zbior) {
        super(n, zbior);
        Numbers = new ArrayList<Integer>();
        subsets = new ArrayList<Integer>();
        indeksy = new ArrayList<Integer>();
        sArray = getsArray();
        zakres = getN();
        nr_of_el = getZbiorSize() + 1;
        rozmiar = getZbiorSize();
    }

    // Funkcja generująca kolejny podzbiór zbioru Numbers oraz sprawdzająca
    // czy suma rodzin o numerach ze z aktualnego podzbioru Numbers pokrywa
    // zbiór liczb od 1 do zakres.
    private void podzbiory(int index) {
        // Zmienna pozwalająca na opuszczenie pętli, w przypadku gdy dalsze
        // przejścia są już niepotrzebne.
        boolean petla = true;

        // Sprawdzamy czy ilość elementów w aktualnie rozważanym podzbiorze
        // jest <= od najlepszego już rozważonego podzbioru.
        if (subsets.size() <= nr_of_el) {
            // Część wspólna aktualnie rozważanych rodzin.
            TreeSet<Integer> com_part = new TreeSet<Integer>();
            for (int i = 0; i < subsets.size() && petla; i++) {
                ArrayList<Integer> tablica = sArray.get(subsets.get(i));

                for (int j = 0; j < tablica.size() && petla; j++) {
                    com_part.add(tablica.get(j));

                    // Sprawdzamy czy część wspólna pokryła już cały zakres liczb.
                    if (com_part.size() == zakres) {
                        // Sprawdzamy czy aktualny podzbiór ma mniej elementów.
                        if (subsets.size() < nr_of_el) {
                            indeksy.clear();
                            indeksy.addAll(subsets);
                            nr_of_el = subsets.size();
                        }
                        // Jeźeli podzbiór ma tyle samo elementów to sprawdzamy
                        // który z podzbiorów jest mniejszy leksykograficznie.
                        else {
                            int k = 0;
                            while (indeksy.get(k) == subsets.get(k))
                                k++;
                            if (indeksy.get(k) > subsets.get(k)) {
                                indeksy.clear();
                                indeksy.addAll(subsets);
                            }
                        }
                        petla = false;
                    }
                }
            }
        }

        // Generujemy kolejny podzbiór.
        for (int i = index; i < Numbers.size(); i++) {
            subsets.add(Numbers.get(i));
            podzbiory(i + 1);
            subsets.remove(subsets.size() - 1);
        }
    }

    public void wykonaj() {
        // Wypełniamy tablicę Numbers liczbami od 0 do rozmiar - 1.
        for (int i = 0; i < rozmiar; i++) {
            Numbers.add(i);
        }

        // Rozpoczynamy generowanie nowych podzbiorów.
        podzbiory(0);
        // Wypisujemy wynik.
        wypisz(indeksy, getN());
    }

}

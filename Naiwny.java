// Jakub Bedełek
// file: Naiwny.java
// Plik zawiera implementację algorytmu naiwnego.

package cover;

import java.util.ArrayList;
import java.util.TreeSet;

// Klasa zawierająca implementacje metod potrzebnych do poprawnego działania
// algorytmu naiwnego.
public class Naiwny extends Algorytm {

    public Naiwny(int n, ArrayList<RodzinaZbiorow> zbior) {
        super(n, zbior);
    }

    // Metoda odpowiedzialna za wykonanie algorytmu naiwnego.
    public void wykonaj() {
        int n = getN();
        int count = 0;

        ArrayList<ArrayList<Integer>> sArray = getsArray();
        // Tablica zawierająca szukane indeksy.
        TreeSet<Integer> indeksy = new TreeSet<Integer>();

        // Tablica przyjmująca true jeśli dany element został już wybrany
        // lub false jeśli nie został. 
        boolean[] was = new boolean[n + 1];

        for (int i = 1; i <= n; i++)
            was[i] = false;

        // Przechodzimy po wszystkich rodzinach zbiorów.
        for (int i = 0; i < sArray.size(); i++) {
            for (int j = 0; j < sArray.get(i).size(); j++) {
                int nr = sArray.get(i).get(j);
                // Sprawdzamy czy liczba została już dodana. 
                if (!was[nr]) {
                    count++;
                    was[nr] = true;
                    // Dodajemy nr. do tablicy indeksów. 
                    indeksy.add(i);            
                }
            }
        }
        // Zamiana tablicy indeksów z TreeSet na ArrayList. 
        ArrayList<Integer> indeksy2 = new ArrayList<Integer>();
        indeksy2.addAll(indeksy);

        wypisz(indeksy2, count);
    }
}


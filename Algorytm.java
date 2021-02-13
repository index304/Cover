// Jakub Bedełek
// file: Algorytm.java
// Plik zawiera implementację klasy posiadającej wspólne elementy i metody
// dla dla różnych algorytmów.

package cover;

import java.util.ArrayList;
import java.util.TreeSet;

// Klasa posiadająca wspólne elementy i metody dla różnych algorytmów.
public abstract class Algorytm {
    // sArray - tablica tablic, gdzie każda tablica o nr. i składa się
    //          z posortowanych liczb należących do rodziny nr. i.
    // zbior  - tablica rodzin zbiorów.
    // n      - zakres liczb z zapytania.
    private ArrayList<ArrayList<Integer>> sArray;
    private ArrayList<RodzinaZbiorow> zbior;
    private int n;

    // Konstruktor.
    public Algorytm(int n, ArrayList<RodzinaZbiorow> zbior) {
        this.n = n;
        this.zbior = zbior;
        sArray = new ArrayList<ArrayList<Integer>>();
        wypelnij();          // Wypełniamy tablicę sArray liczbami.
    }

    // Zwraca zakres liczb z zapytania.
    public int getN() {
        return n;
    }

    // Zwraca ilość rodzin zbiorów.
    public int getZbiorSize() {
        return zbior.size();
    }

    // Zwraca tablicę sArray.
    public ArrayList<ArrayList<Integer>> getsArray() {
        return sArray;
    }

    // Zwraca rodzinę zbiorów.
    public ArrayList<RodzinaZbiorow> getZbior() {
        return zbior;
    }

    // Wypełnia tablicę sArray posortowanymi tablicami.
    private void wypelnij() {
        for (int i = 0; i < zbior.size(); i++) {
            TreeSet<Integer> tree_set = zbior.get(i).daj_liczby(n);
            ArrayList<Integer> lSorted = new ArrayList<Integer>();
            lSorted.addAll(tree_set);
            sArray.add(lSorted);
        }
    }

    // Wypisuję na wyjście indeksy szukanych rodzin zbiorów.
    // liczba - liczba różnych elementów z przedziału od 1 do n należących
    //          do rodzin zbiorów o nr. z tablicy indeksy.
    public void wypisz(ArrayList<Integer> indeksy, int liczba) {
        if (indeksy.size() == 0 || liczba < n) {
            System.out.println(0);
        }
        else {
            String wyraz = "";
            for (int i = 0; i < indeksy.size(); i++) {
                wyraz += (indeksy.get(i) + 1);
                if (i != indeksy.size() - 1)
                    wyraz += " ";
            }

            System.out.println(wyraz);
        }
    }

    // Funkcja abstrakcyjna odpowiedzialna za wykonanie konkretnego algorytmu.
    public abstract void wykonaj();
}


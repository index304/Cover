// Jakub Bedełek
// file: Zachlanny.java
// Plik zawiera implementację algorytmu zachłannego.

package cover;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

// Klasa zawierająca implementacje metod oraz dane potrzebne do poprawnego
// działania algorytmu zachłannego.
public class Zachlanny extends  Algorytm {
    // Konstruktor.
    public Zachlanny(int n, ArrayList<RodzinaZbiorow> zbior) {
        super(n, zbior);
    }

    // Wykonanie algorytmu zachłannego.
    public void wykonaj() {
        // count - liczy ilość już użytych elementów od 1 do n.
        int count  = 0;
        int n = getN();
        ArrayList<RodzinaZbiorow> zbior = getZbior();
        // Tree set elementów klasy pomocnicza.
        TreeSet<Pomocnicza> pom = new TreeSet<Pomocnicza>();

        // Dodawanie elementów do seta pom.
        for (int i = 0; i < zbior.size(); i++) {
            pom.add(new Pomocnicza(zbior.get(i).daj_liczby(n), i + 1));
        }
        // Tablica indeksów rodzin już wykorzystanych.
        ArrayList<Integer> indeksy = new ArrayList<Integer>();
        // tmp - Tree set pomocniczy wykorzystywany do ponownego posortowania
        // elementów pom już bez elementów należących do rodzin wykorzystanych.
        TreeSet<Pomocnicza> tmp = new TreeSet<Pomocnicza>();

        while (pom.size() > 0) {
            if (pom.first().getSize() > 0) {
                // Zliczamy ilość dodanych elementów.
                count += pom.first().getSize();
                // Tworzymy listę z elementami wykorzystanymi w tym ruchu,
                // które będą usuwane z pozostałych rodzin zbiorów.
                ArrayList<Integer> lista = new ArrayList<Integer>();
                lista.addAll(pom.first().getTab());
                // Będziemy iterować się po wszystkich rodzinach i usuwać
                // elementy dodane w tym ruchu.
                Iterator it = pom.iterator();
                int counter = 0;

                while (it.hasNext()) {
                    if (counter > 0) {
                        Pomocnicza obj = (Pomocnicza) it.next();
                        for (int i = 0; i < lista.size(); i++) {
                            obj.remove(lista.get(i));
                        }
                        if (obj.getSize() > 0)
                            tmp.add(obj);
                    }
                    counter++;
                }
            }
            indeksy.add(pom.first().getNr() - 1); // Aktualizujemy tablicę indeksów.
            pom.clear();
            pom.addAll(tmp);
            tmp.clear();
        }
        Collections.sort(indeksy);
        wypisz(indeksy, count);
    }
}

// Klasa odpowiedzialna za sortowanie tablic elementów reprezentowanych
// w postaci tree setów, najpierw rosnąco względem wielkości tree seta, a potem
// rosnąco względem numerów rodzin.
class Pomocnicza implements Comparable<Pomocnicza> {
    // t - tree set zawierający uporządkowane rosnąco liczby z zakresu 1 do n
    //     należące do rodziny numer nr.
    // nr - numer rodziny.
    private TreeSet<Integer> t;
    private int nr;

    // Konstruktor
    public Pomocnicza(TreeSet<Integer> t, int nr) {
        this.t = t;
        this.nr = nr;
    }

    // Zwraca wielkość tree seta.
    public int getSize() {
        return this.t.size();
    }

    // Zwraca tree set t.
    public TreeSet<Integer> getTab() {
        return this.t;
    }

    // Zwraca nr rodziny.
    public int getNr() {
        return this.nr;
    }

    // Usuwa liczbę liczba z t.
    public void remove(int liczba) {
        t.remove(liczba);
    }

    // Funkcja porównująca dwa elemnty klasy pomocnicza. Najpierw porównuje
    // względem wielkość tree set t, a potem względem wartości nr (numeru rodziny).
    public int compareTo(Pomocnicza p) {
        if (t.size() == p.t.size()) {
            if (nr > p.nr)
                return 1;
            else
                return -1;
        }
        else if (t.size() > p.t.size()){
            return -1;
        }
        else {
            return 1;
        }
    }
}

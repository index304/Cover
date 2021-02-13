// Jakub Bedełek
// file: Wczytywanie.java
// Plik zawiera implementacje klasy odpowiedzialnej za czytanie wejścia.

package cover;

import java.util.ArrayList;
import java.util.Scanner;

// Klasa odpowiedzialna za czytanie wejścia.
public class Wczytywanie {
    // zbior - Tablica przechowywująca Rodzinę zbiorów.
    // rodzina - Zbiór aktualnie przetwarzanych zbiorów.
    private ArrayList<RodzinaZbiorow> zbior;
    private RodzinaZbiorow rodzina;

    // Funkcja odpowiedzialna za dodawanie konkretnych typów zbiorów
    // do aktualnie przetwarzanej rodziny zbiorów.
    private void dodaj(int a, int b, int c, int count) {
        if (count == 1) {
            rodzina.dodaj(new Element(a));
        }
        else if (count == 2) {
            rodzina.dodaj(new Nieskonczony(a, b));
        }
        else {
            rodzina.dodaj(new Skonczony(a, b, c));
        }
    }

    // Konstruktor klasy.
    public Wczytywanie() {
        zbior = new ArrayList<RodzinaZbiorow>();
        rodzina = new RodzinaZbiorow();
    }

    // Funkcja odpowiedzialna za wywoływanie odpowiedniego
    // algorytmu dla aktualnie przetwarzanego zapytania.
    public void startAlgorytm(int zakres, int d) {
        Algorytm algorytm;
        if (d == 1) {
            algorytm = new Dokladny(-zakres, zbior);
            algorytm.wykonaj();
        }
        else if (d == 2) {
            algorytm = new Zachlanny(-zakres, zbior);
            algorytm.wykonaj();
        }
        else {
            algorytm = new Naiwny(-zakres, zbior);
            algorytm.wykonaj();
        }
    }

    // Funkcja odpowiedzialna za czytanie wejścia.
    public void wczytaj() {
        Scanner sc = new Scanner(System.in);
        // Zmienna pomagająca odróżnić różne typy zbiorów.
        // Zlicza ona ilość zmiennych składających się na aktualnie
        // zczytywany zbiór.
        int count = 0;
        // Kolejne elementy składające się na zbiór.
        // d - liczba dodatnia.
        int a = 0, b = 0, c = 0, d = 0;

        while(sc.hasNextInt()) {
            int input = sc.nextInt();

            if (input < 0 && count == 0) {         // Przetwarzanie zapytania.
                d = sc.nextInt();
                startAlgorytm(input, d);
            }
            else if (input == 0 && count != 0) {   // Zakończenie przetwarzania
                dodaj(a, b, c, count);             // rodziny.
                count = 0;
                zbior.add(rodzina);                // Dodajemy rodzinę do zbioru.
                rodzina = new RodzinaZbiorow();
            }
            else if (input == 0 && count == 0) {   // Dodajemy zbiór pusty.
                rodzina.dodaj(new Pusty());
                zbior.add(rodzina);
                rodzina = new RodzinaZbiorow();
            }
            else if (input > 0 && count > 0) {     // Zaczynamy przetwarzać nowy
                dodaj(a, b, c, count);             // zbiór i dodajemy stary do
                a = input;                         // do rodziny.
                count = 1;
            }
            else {                                 // Zapisujemy kolejne zmienne
                count++;                           // składające się na aktualnie
                if (count == 1)                    // przetwarzany zbiór.
                    a = input;
                else if (count == 2)
                    b = input;
                else
                    c = input;
            }
        }
    }
}

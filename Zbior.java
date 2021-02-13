// Jakub Bedełek
// file: Zbior.java
// Plik zawiera klasy wszystkich możliwych występujących zbiorów.
// Ze względu na krótką implementację wszystkie typy zbiorów
// są implementowane w jednym pliku.

package cover;

import java.util.ArrayList;

// Klasa abstrakcyjna dla zbiorów.
public abstract class Zbior {
    // Funkcja zwracająca wszystkie elementy ze zbioru
    // należące do przedziału <1, 2, ..., n>.
    public abstract ArrayList<Integer> elementy(int n);
}

class Element extends Zbior {
    // Jedyny element zbioru.
    private int element;

    // Konstruktor.
    public Element(int element) {
        this.element = element;
    }

    // Implementacja funkcji abstrakcyjnej.
    public ArrayList<Integer> elementy(int n) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        if (element <= n)
            lista.add(element);
        return lista;
    }
    
    public String toString() {
		String wyraz = "";
		wyraz += element; 
		return wyraz;
	}
}

class Pusty extends Zbior {
    // Konstruktor.
    public Pusty () {}

    // Implementacja funkcji abstrakcyjnej.
    public ArrayList<Integer> elementy(int n) {
        return new ArrayList<Integer>();
    }
    
    public String toString() {
		return "";
	}	
}

class Nieskonczony extends Zbior {
    // p - pierwszy wyraz ciągu arytmetycznego.
    // r - różnica ciągu arytmetycznego.
    private int p;
    private int r;

    // Konstuktor.
    public Nieskonczony(int p, int r) {
        this.p = p;
        this.r = -r;
    }

    // Implementacja funkcji abstrakcyjnej.
    public ArrayList<Integer> elementy(int n) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        for (int i = p; i <= n; i += r) {
            lista.add(i);
        }
        return lista;
    }
    
    public String toString() {
		String wyraz = "Zbior nieskonczoy\np = " + p + "\nr = " + r;
		return wyraz;
	}
}

class Skonczony extends Zbior {
    // p - pierwszy wyraz ciągu arytmetycznego.
    // r - różnica ciągu arytmetycznego.
    // k - maksymalnie możliwy wyraz ciągu arytmetycznego.
    private int p;
    private int r;
    private int k;

    // Konstruktor.
    public Skonczony(int p, int r, int k){
        this.p = p;
        this.k = -k;
        this.r = -r;
    }

    // Implementacja funkcji abstrakcyjnej.
    public ArrayList<Integer> elementy(int n) {
        ArrayList<Integer> lista = new ArrayList<Integer>();
        for (int i = p; i <= Math.min(k, n); i += r) {
            lista.add(i);
        }
        return lista;
    }
    
    public String toString() {
		String wyraz = "";
		ArrayList<Integer> liczby = elementy(k);
		for (int i = 0; i < liczby.size(); i++) {
			wyraz += liczby.get(i); 
			wyraz += " "; 
		}
		return wyraz;
	}
}

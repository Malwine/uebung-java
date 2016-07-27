package klausur_2016;

import java.util.List;

public class Schuldbetrag {

	float summe;
	int laufzeit;

	Schuldbetrag(float summe, int laufzeit) {
		this.summe = summe;
		this.laufzeit = laufzeit;

		System.out.println("Erzeugt mit: " + this.summe + " und " + this.laufzeit);
	}

	float getSumme() {
		return summe;
	}

	int getLautzeit() {
		return laufzeit;
	}

	List<Float> getRaten() {
		// TODO
		return null;
	}
}

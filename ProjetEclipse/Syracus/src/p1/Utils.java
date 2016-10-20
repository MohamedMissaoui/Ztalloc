package p1;

public class Utils {
	
	static int max2 = 0;

	public static void initialiser(Zta nombre, int val, int MAX_PROFONDEUR, int MAX_LARGEUR) {

		if (nombre.getProfondeur() < MAX_PROFONDEUR) {
			if (val % 3 != 0) {

				if (val % 3 == 1 && val % 2 == 0) {

					if (val != 13 && val != 4 && val != 2 && val != 1) {

						Zta zta = new Zta((val - 1) / 3,
								nombre.getProfondeur() + 1);
						
						if (nombre.getL().size() < MAX_LARGEUR) {
							nombre.getL().add(zta);
							initialiser(zta, zta.getValeur(), MAX_PROFONDEUR, MAX_LARGEUR);
							
						}
					}
				}

				if (max2 < 20 && nombre.getL().size() < MAX_LARGEUR) {
					max2++;
					initialiser(nombre, val * 2, MAX_PROFONDEUR, MAX_LARGEUR);
				}

				max2 = 0;
			}
		}

	}
}

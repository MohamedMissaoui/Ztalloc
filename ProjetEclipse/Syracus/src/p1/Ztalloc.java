package p1;

import java.util.ArrayList;

public class Ztalloc {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Zta zz = new Zta(1);

		initialiser(zz, 1, 100);
		
		int profz = recupereMaxProf(zz, 0);
		System.out.println("prof = "+profz);
		afficher(zz, (profz));

		// Fenetre f = new Fenetre();
		if (args.length < 2) {
			System.out
					.println("Arguments obligatoires \n"
							+ "premier argument : dot ou txt pour le format désiré\n"
							+ "suivi de l'algo désiré algo1 pour recherche par profondeur et largeur\n"
							+ "algo2 pour recherche avec limite de grandeur");
		} else {
			switch (args[0]) {
			case "txt":
				switch (args[1]) {
				case "algo1":
					if (args.length > 3) {
						Zta z = new Zta(1);

						initialiser(z, 1, Integer.parseInt(args[2]),
								Integer.parseInt(args[3]));

						afficher(z, Integer.parseInt(args[2]));
					} else {
						System.out
								.println("rentrez la profondeur et la largeur souhaités, deux arguments nécessaires");
					}
					break;
				case "algo2":
					if (args.length > 2) {
						Zta z = new Zta(1);

						initialiser(z, 1, Integer.parseInt(args[2]));
						
						int prof = recupereMaxProf(z, 0);
						System.out.println("prof = "+prof);
						afficher(z, prof);
					} else {
						System.out
								.println("rentrez la valeur maximum, un arguments nécessaire");
					}
					break;
				default:

					break;
				}
				break;
			case "dot":
				break;
			default:
				System.out
						.println("Argument non valide : doit être txt ou dot");
				break;
			}

		}

	}
//	public function getTreeHeight($catId, $height) {
//		$arrayChild = $this->getChildArray($catId); //tableau contenu les enfants du noeud catId
//		echo 'noeud : '.$catId.'<br/>';
//		echo 'height : '.$height.'<br/><br/>';
//		$max = $height;	
//		if (count($arrayChild) != 0) {
//			foreach ($arrayChild as $c) {
//				$h = $this->getTreeHeight($c->getId(), $height + 1);
//				if ($h>$max) $max = $h;
//			}
//		}
//		return $max;
//	}
	
	
	//public static int profMax = 0;

	private static int recupereMaxProf(Zta zta, int prof) {
		int max = prof;
		if(!zta.getL().isEmpty()){
			for(Zta z : zta.getL()){
				int h = recupereMaxProf(z, prof + 1);
				if(h>max){
					max = h;
				}
			}
		}
		
		return max;

	}
	
	public 

	static int compteur = 0;

	public static void afficher(Zta zta, int maxProfondeur) {
		ArrayList<Zta>[] tabProfondeur = new ArrayList[maxProfondeur];

		for (int j = 0; j < maxProfondeur; j++) {
			tabProfondeur[j] = new ArrayList<Zta>();
		}

		remplir(zta, tabProfondeur);

		for (int i = 0; i < maxProfondeur; i++) {
			System.out.println("\nProfondeur " + (i + 1) + "\n");
			for (Zta z : tabProfondeur[i]) {
				System.out.println("  " + z.getValeur() + " : "
						+ Integer.toString(z.getValeur(), 2));
			}
		}
	}

	private static void remplir(Zta zta, ArrayList<Zta>[] tabProfondeur) {

		for (Zta z : zta.getL()) {
			tabProfondeur[z.getProfondeur() - 1].add(z);
			remplir(z, tabProfondeur);
		}

	}

	static int max2 = 0;

	public static void initialiser(Zta nombre, int val, int MAX_PROFONDEUR,
			int MAX_LARGEUR) {

		if (nombre.getProfondeur() < MAX_PROFONDEUR) {
			if (val % 3 != 0) {

				if (val % 3 == 1 && val % 2 == 0) {

					if (val != 13 && val != 4 && val != 2 && val != 1) {

						Zta zta = new Zta((val - 1) / 3,
								nombre.getProfondeur() + 1);

						if (nombre.getL().size() < MAX_LARGEUR) {
							nombre.getL().add(zta);
							initialiser(zta, zta.getValeur(), MAX_PROFONDEUR,
									MAX_LARGEUR);

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

	public static void initialiser(Zta nombre, int val, int nbMax) {

		if (val < nbMax) {
			if (val % 3 != 0) {

				if (val % 3 == 1 && val % 2 == 0) {

					if (val != 13 && val != 4 && val != 2 && val != 1) {

						Zta zta = new Zta((val - 1) / 3,
								nombre.getProfondeur() + 1);

						nombre.getL().add(zta);
						initialiser(zta, zta.getValeur(), nbMax);

					}
				}

				initialiser(nombre, val * 2, nbMax);

			}

		}

	}

}

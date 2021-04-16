package applicationV2.modele;
/*
 * NewClass.java
 *
 * Created on 26 octobre 2011, 12:50
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

import java.util.Scanner;





/*
 *
 * @author mariannesimonot
 */
public class UtilSaisie {

	public static int lireEntierPositif(String question)
			throws SaisieEntierException {
		Scanner scanner = new Scanner(System.in);
		System.out.println(question);
		String chaineEntier = scanner.nextLine();
		int nombre;
		try {
			nombre = Integer.parseInt(chaineEntier);
		} catch (NumberFormatException e) {
			throw new SaisieEntierException(chaineEntier + " pas un nombre ");
		}
		if (nombre < 0) {
			throw new SaisieEntierException("l'entier doit etre positif");
		}
		return nombre;
	}

	public static int lireEntierPositifEnBoucle(String question) {
		int nombre = -1;
		do {
			try {
				nombre = lireEntierPositif(question);
			} catch (SaisieEntierException e) {
				System.out.println(e.getMessage());
			}
		} while (nombre == -1);
		return nombre;
	}

	public static int lireEntierPositifInferieurA(int n)
			throws SaisieEntierException {
		if (n >= 1) {

			Scanner scanner = new Scanner(System.in);
			String chaineEntier = scanner.nextLine();
			int nombre;
			try {
				nombre = Integer.parseInt(chaineEntier);
			} catch (NumberFormatException e) {
				throw new SaisieEntierException(chaineEntier
						+ " pas un nombre ");
			}
			if (nombre < 0 || nombre > n) {
				throw new SaisieEntierException(
						"l'entier doit etre compris entre 1 et " + n);
			}
			return nombre;
		} else
			throw new SaisieEntierException("le parametre doit etre positif");
	}

	public static int lireEntierentre (String question, int min, int max)
			throws SaisieEntierException {
		if (min <=max) {

			Scanner scanner = new Scanner(System.in);
			System.out.println(question);
			System.out.println("doit être compris entre " + min + " et " + max);
			String chaineEntier = scanner.nextLine();
			int nombre;
			try {
				nombre = Integer.parseInt(chaineEntier);
			} catch (NumberFormatException e) {
				throw new SaisieEntierException(chaineEntier
						+ " pas un nombre ");
			}
			if (nombre < min || nombre > max) {
				throw new SaisieEntierException(
						"l'entier doit etre compris entre" + min +" et " + max);
			}
			return nombre;
		} else
			throw new SaisieEntierException("l'intervalle est vide");
	}
	public static int lireEntierEnBoucleEntre (String question, int min, int max) {
		int nombre = min-1;
		do {
			try {
				nombre = lireEntierentre(question, min, max);
			} catch (SaisieEntierException e) {
				System.out.println(e.getMessage());
			}
		} while (nombre == min-1);
		return nombre;
	}



}

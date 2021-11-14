package outils;

import java.util.Random;

public class Outils {
	/**
	 * Arrondi d'un réel à l'entier le plus proche
	 *
	 * @param x
	 * @return l'<code>int</code> le plus proche de <code>x</code>
	 */
	public static int round(double x) {
		// return Math.round((float)x) ;
		return (int) (x + 0.5);
	}

	public static int floor(double x) {
		return (int) x;
	}

	/**
	 * @param c
	 * @pre <code>c</code> est une chaîne ne contenant que des lettres présentes
	 *      dans <tt>alphabet</tt>
	 * @param alphabet
	 * @pre <code>p</code> est une chaîne non vide sans double représentant un
	 *      alphabet ordonné
	 * @return la chaîne suivant <tt>c</tt> dans l'ordre lexicographique sur
	 *         l'alphabet <tt>alphabet</tt>
	 */
	public static String suivantLexicographique(String c, String alphabet) {
		char a = alphabet.charAt(0);
		char z = alphabet.charAt(alphabet.length() - 1);
		if (c.equals("")) {
			return "" + a;
		} else {
			char faible = c.charAt(c.length() - 1);
			String forts = c.substring(0, c.length() - 1);
			if (faible == z) {
				return suivantLexicographique(forts, alphabet) + a;
			} else {
				return forts + alphabet.charAt(alphabet.indexOf(faible) + 1);
			}
		}
	}

	/**
	 *
	 * @param p
	 * @pre <code>p</code> est une chaîne de la forme "xx-yy-zz", où xx, yy et zz
	 *      représentent 3 sous chaînes sans '-'
	 * @return "yy"
	 */
	public static String leCentre(String p) {
		return p.substring(p.indexOf('-') + 1, p.indexOf('-', p.indexOf('-') + 1));
	}

	/**
	 *
	 * @param p
	 * @pre <code>p</code> est une chaîne de la forme "xx-yy-zz", où xx, yy et zz
	 *      représentent 3 sous chaînes sans '-'
	 * @return "xx"
	 */
	public static String leDébut(String p) {
		return p.substring(0, p.indexOf('-'));
	}

	/**
	 *
	 * @param p
	 * @pre <code>p</code> est une chaîne de la forme "xx-yy-zz", où xx, yy et zz
	 *      représentent 3 sous chaînes sans '-'
	 * @return "xx"
	 */
	public static String leDebut(String p) {
		return p.substring(0, p.indexOf('-'));
	}

	/**
	 *
	 * @param p
	 * @pre <code>p</code> est une chaîne de la forme "xx-yy-zz", où xx, yy et zz
	 *      représentent 3 sous chaînes sans '-'
	 * @return "zz"
	 */
	public static String laFin(String p) {
		return p.substring(p.indexOf('-', p.indexOf('-') + 1) + 1, p.length());
	}

	/*
	 * public static String auHasard() { Random r = new Random(); String lettres =
	 * "ABCDEFGHJKLMNPQRSTUWXYZ"; String chiffres = "0123456789"; String debut = ""
	 * + lettres.charAt(r.nextInt(lettres.length())) +
	 * lettres.charAt(r.nextInt(lettres.length())); while (debut.equals("SS") ||
	 * debut.equals("WW")) { debut = "" +
	 * lettres.charAt(r.nextInt(lettres.length())) +
	 * lettres.charAt(r.nextInt(lettres.length())); } String fin = "" +
	 * lettres.charAt(r.nextInt(lettres.length())) +
	 * lettres.charAt(r.nextInt(lettres.length())); while (debut.equals("SS")) { fin
	 * = "" + lettres.charAt(r.nextInt(lettres.length())) +
	 * lettres.charAt(r.nextInt(lettres.length())); } return debut + "-" +
	 * chiffres.charAt(r.nextInt(chiffres.length())) +
	 * chiffres.charAt(r.nextInt(chiffres.length())) +
	 * chiffres.charAt(r.nextInt(chiffres.length())) + "-" + fin; }
	 */
	/**
	 * Calcule un tableau T de n Pixels (palette) T[0] est noir (0, 0, 0) et les
	 * autres couleurs sont choisies au hasard
	 *
	 * @param n
	 * @return une palette tirée au hasard
	 */
	public static Pixel[] paletteAuHasard(int n) {
		Random r = new Random();
		Pixel[] plte = new Pixel[n];
		for (int i = 1; i < plte.length; i++) {
			plte[i] = Pixel.nouveauPixel(r.nextInt(256), r.nextInt(256), r.nextInt(256));
		}
		plte[0] = Pixel.nouveauPixel(0, 0, 0); // noir
		return plte;
	}

	/**
	 * Tests
	 *
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		double x = 0.0;
		for (int i = 0; i < 10; i++) {
			System.out.println("x : " + x + " round :" + Outils.round(x) + " floor : " + Outils.floor(x));
			x = i * 1.1;
		}
		String alphabet;
		String chaine;
		alphabet = "ab";
		chaine = "aa";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		chaine = "ab";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		chaine = "ba";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		chaine = "bb";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		System.out.println();
		alphabet = "ba";
		chaine = "aa";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		chaine = "ab";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		chaine = "ba";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		chaine = "bb";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		System.out.println();
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		chaine = "ZRZ";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		chaine = "ZZZZ";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		alphabet = "ABCDEFGHJKLMNPQRSTUWXYZ";
		chaine = "ZUZ";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");
		alphabet = "0123456789";
		chaine = "999";
		System.out.println("Avec l'alphabet \"" + alphabet + "\"\n\tle suivant de \"" + chaine + "\" est : \""
				+ suivantLexicographique(chaine, alphabet) + "\"");

		String test = "aaa-bbbbb-cc";
		System.out.println("le début de " + test + " : " + leDébut(test));
		System.out.println("le centre de " + test + " : " + leCentre(test));
		System.out.println("la fin de " + test + " : " + laFin(test));

		test = "aaa--cc";
		System.out.println("le début de " + test + " : " + leDébut(test));
		System.out.println("le centre de " + test + " : " + leCentre(test));
		System.out.println("la fin de " + test + " : " + laFin(test));

		test = "aaa--";
		System.out.println("le début de " + test + " : " + leDébut(test));
		System.out.println("le centre de " + test + " : " + leCentre(test));
		System.out.println("la fin de " + test + " : " + laFin(test));

		test = "--";
		System.out.println("le début de " + test + " : " + leDébut(test));
		System.out.println("le centre de " + test + " : " + leCentre(test));
		System.out.println("la fin de " + test + " : " + laFin(test));

		//TO DO Auto-generated method stub
		/*
		 * String p ; for (int i = 0; i < 100; i++) { p = auHasard();
		 * System.out.println(p+" : "+leDébut(p)+", "+leCentre(p)+", "+laFin(p)+", "
		 * +plus1(leDébut(p))+", "+plus1(leCentre(p))+", "+plus1(laFin(p))); }
		 */
		// System.out.println(auHasard());
	}

}

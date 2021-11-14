package outils;

public class MatBool {

	private boolean[][] M;

	public MatBool(int n, int p) {
		M = new boolean[n][p];
	}

	public int l() {
		return M.length;
	}

	public int c() {
		return M[0].length;
	}

	public String toString() {
		String r = "\n";
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				r = r + (M[i][j] ? "+" : "-") + " ";
			}
			r = r + "\n";
		}
		return r;
	}

	/**
	 * Délivre la valeur m[i, j]
	 *
	 * @param i ligne
	 * @param j colonne
	 * @return m[i, j]
	 */
	public boolean get(int i, int j) {
		return M[i][j];
	}

	/**
	 * Affecte la valeur v à m[i,j]
	 *
	 * @param i
	 * @param j
	 * @param v
	 */
	public void set(int i, int j, boolean v) {
		M[i][j] = v;
	}

	/**
	 * Délivre une matrice booléenne de <code>n</code> lignes et <code>p</code>
	 * colonnes, initialisée à <code>false</code>
	 *
	 * @param n
	 * @param p
	 * @return
	 */
	public static MatBool toutFaux(int n, int p) {
		MatBool r = new MatBool(n, p);
		for (int i = 0; i < r.l(); i++) {
			for (int j = 0; j < r.c(); j++) {
				r.set(i, j, false);
			}
		}
		return r;
	}

	/**
	 * Délivre une matrice booléenne carrée de <code>n</code> lignes et
	 * <code>n</code> colonnes, initialisée à <code>false</code>
	 *
	 * @param n
	 * @param p
	 * @return
	 */
	public static MatBool carree(int n) {
		return toutFaux(n, n);
	}

	public static void main(String[] args) {
		MatBool m = toutFaux(3, 5);
		m.set(0, 0, true);
		m.set(2, 4, true);
		System.out.println("Matrice " + m.l() + " x " + m.c() + m);
		MatBool m1 = toutFaux(3, 5);
		for (int i = 0; i < m1.l() && i < m1.c(); i++) {
			m1.set(i, i, true);
			;
		}
		System.out.println(m1);

		m1 = carree(5);
		for (int i = 0; i < m1.l() && i < m1.c(); i++) {
			m1.set(i, i, true);
			;
		}
		System.out.println(m1);

	}

}

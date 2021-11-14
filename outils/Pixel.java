package outils;

public class Pixel {
	/**
	 * Composante rouge (0..255)
	 */
	public int r;
	/**
	 * Composante verte (0..255)
	 */
	public int v;
	/**
	 * Composante bleue (0..255)
	 */
	public int b;

	/**
	 * crée un pixel blanc
	 */
	public Pixel() {
		this.r = 255;
		this.v = 255;
		this.b = 255;
	}

	/**
	 * Crée un pixel de composantes <r, v, b>
	 *
	 * @param r
	 * @param v
	 * @param b
	 * @pre r : 0..255, v : 0..255, b : 0..255
	 */
	public Pixel(int r, int v, int b) {
		this.r = r;
		this.v = v;
		this.b = b;
	}

	/**
	 * représentation textuelle du pixel
	 */
	public String toString() {
		return ("<" + this.r + ", " + this.v + ", " + this.b + ">");
	}

	/**
	 *
	 * @param r
	 * @param v
	 * @param b
	 * @pre r : 0..255, v : 0..255, b : 0..255
	 * @return un nouveau pixel de composantes <r, v, b>
	 */
	public static Pixel nouveauPixel(int r, int v, int b) {
		Pixel pix = new Pixel();
		pix.r = r;
		pix.v = v;
		pix.b = b;
		return pix;
	}
}

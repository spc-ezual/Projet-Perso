package outils;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BitMap {

  private BufferedImage img;

  /**
   * Construit une image <em>bitmap</em> à partir du fichier de nom <code>f</code>
   * (ou <code>f.bmp</code> si le suffixe <code>.bmp</code> est absent de
   * <code>f</code>)
   *
   * @param f
   * @pre le fichier de nom <code>f</code> (ou <code>f.bmp</code> si le suffixe
   *      <code>.bmp</code> est absent de <code>f</code>) doit exister et être un
   *      fichier au format BMP
   *
   */
  private BitMap(String f) {
    if (f.length() < 4 || !f.substring(f.length() - 4, f.length()).equals(".bmp")) {
      f = f + ".bmp";
    }
    try {
      DataInputStream inBMP = new DataInputStream(new FileInputStream(new File(f)));
      inBMP.skipBytes(18); // saute le début de l'entête
      // largeur et hauteur de l'image
      int largeur = litEntier32bits(inBMP);
      int hauteur = litEntier32bits(inBMP);
      inBMP.skipBytes(28); // saute les données inutiles del'entête
      // crée et remplit img
      img = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);
      int tailleBourrage = (4 - ((largeur * 3) % 4)) % 4; // nombre d'octets de bourrage dans chaque ligne
      // Lecture des pixels
      for (int y = hauteur - 1; y >= 0; y--) {
        for (int x = 0; x < largeur; x++) {
          img.setRGB(x, y, litPixel24bits(inBMP));
        }
        inBMP.skipBytes(tailleBourrage); // saute le bourrage
      }
      inBMP.close();
    } catch (Exception e) {
      System.out.println("problème avec la lecture du fichier " + f);
    }
  }

  /**
   * Construction d'un BitMap de dimensions données
   *
   * @param l largeur de l'image en pixels
   * @param h hauteur de l'image en pixels
   * @pre <code>l > 0 & h > 0</code>
   */
  public BitMap(int l, int h) {
    if (l <= 0 || h <= 0) {
      try {
        throw new Exception("création de Bitmap impossible, dimensions incorectes : (" + l + ", " + h + ")");
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
      }
    }
    img = new BufferedImage(l, h, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * Construction d'un BitMap de dimensions données dont les pixels sont blancs
   *
   * @param l largeur de l'image en pixels
   * @param h hauteur de l'image en pixels
   * @pre <code>l > 0 & h > 0</code>
   * @return une image <em>bitmap</em> blanche de <code>l</code> &times;
   *         <code>h</code> pixels
   */
  public static BitMap blanche(int l, int h) {
    BitMap iRGB = new BitMap(l, h);
    Pixel pix = new Pixel();
    pix.r = 255;
    pix.b = 255;
    pix.v = 255;
    for (int i = 0; i < l; i++) {
      for (int j = 0; j < h; j++) {
        iRGB.set(j, i, pix);
      }
    }
    return iRGB;
  }

  /**
   * Construit une image <em>bitmap</em> à partir du fichier de nom <code>f</code>
   * (ou <code>f.bmp</code> si le suffixe <code>.bmp</code> est absent de
   * <code>f</code>)
   *
   * @param f
   * @pre le fichier de nom <code>f</code> (ou <code>f.bmp</code> si le suffixe
   *      <code>.bmp</code> est absent de <code>f</code>) doit exister et être un
   *      fichier au format BMP
   *
   */
  public static BitMap aPartirDe(String f) {
    return new BitMap(f);
  }

  private static int litEntier32bits(DataInputStream in) {
    // effet : lecture d'un entier sur 4 octets depuis in
    // résultat : l'entier lu
    byte[] b = new byte[4];
    int resul = 0;
    try {
      in.read(b);
      resul = b[0] & 0xFF;
      resul = resul + ((b[1] & 0xFF) << 8);
      resul = resul + ((b[2] & 0xFF) << 16);
      resul = resul + ((b[3] & 0xFF) << 24);
    } catch (Exception e) {
    }
    return resul;
  }

  private static int litPixel24bits(DataInputStream in) {
    // effet : lecture d'un pixel sur 3 octets depuis in
    // résultat : l'entier correspondant au pixel lue
    byte[] b = new byte[3];
    int result = 0;
    try {
      in.read(b);
      result = b[0] & 0xFF;
      result = result + ((b[1] & 0xFF) << 8);
      result = result + ((b[2] & 0xFF) << 16);
    } catch (Exception e) {
    }
    return result;
  }

  /**
   * Enregistre une image <em>bitmap</em> dans un fichier au format BMP
   *
   * @param img
   * @param f
   * @return indique si l'enregistrement s'est bien effectué Crée à partir
   *         d'<code>img</code> un fichier de nom <code>f</code> (ou
   *         <code>f.bmp</code> si le suffixe <code>.bmp</code> est absent de
   *         <code>f</code>)
   */
  public static boolean enregistreBitMap(BitMap img, String f) {
    if (f.length() < 4 || !f.substring(f.length() - 4, f.length()).equals(".bmp")) {
      f = f + ".bmp";
    }
    try {
      String currentDirectory = System.getProperty("user.dir");
      System.out.println("Image écrite dans " + currentDirectory + "/" + f);
      DataOutputStream outBMP = new DataOutputStream(new FileOutputStream(f));
      // écriture de l'entête du fichier
      outBMP.write(0x42);
      outBMP.write(0x4D); // Type
      int largeur = img.img.getWidth();
      int hauteur = img.img.getHeight();
      int tailleBourrage = (4 - ((largeur * 3) % 4)) % 4; // nombre d'octets de remplissage en fin de chaque ligne
      ecritEntier32bits(outBMP, hauteur * (largeur * 3 + tailleBourrage) + 54); // Taille du fichier
      ecritEntier32bits(outBMP, 0); // Réservé
      ecritEntier32bits(outBMP, 54);
      ecritEntier32bits(outBMP, 40);
      ecritEntier32bits(outBMP, largeur); // Largeur
      ecritEntier32bits(outBMP, hauteur); // Hauteur
      outBMP.write(1);
      outBMP.write(0);
      outBMP.write(24);
      outBMP.write(0); // Nombre de bits par pixel
      ecritEntier32bits(outBMP, 0); // pas de compression
      ecritEntier32bits(outBMP, hauteur * (largeur * 3 + tailleBourrage)); // taille de l'image en octets
      ecritEntier32bits(outBMP, 2851);
      ecritEntier32bits(outBMP, 2851); // résolution en largeur et hauteur
      ecritEntier32bits(outBMP, 0); // nombre de couleurs = 2^^24
      ecritEntier32bits(outBMP, 0); // toutes les couleurs sont importantes
      // Ecriture du corps du fichier BMP
      for (int y = hauteur - 1; y >= 0; y--) {
        for (int x = 0; x < largeur; x++) {
          ecritPixel24bits(outBMP, img.img.getRGB(x, y));
        }
        // Bourrage
        for (int j = 0; j < tailleBourrage; j++) {
          outBMP.write(0);
        }
      }
      // Fermeture du fichier
      outBMP.close();
      System.out.println("Image bien écrite dans " + f);
      return true;
    } catch (Exception e) {
      System.out.println("ERREUR : " + e);
      return false;
    }
  }

  private static void ecritEntier32bits(DataOutputStream sortie, int n) {
    // effet : écrit n dans sortie sur 4 octets selon le format little indian
    try {
      sortie.write((n) & 0xFF);
      sortie.write((n >> 8) & 0xFF);
      sortie.write((n >> 16) & 0xFF);
      sortie.write((n >> 24) & 0xFF);
    } catch (Exception e) {
    }
  }

  private static void ecritPixel24bits(DataOutputStream sortie, int p) {
    // effet : écrit le pixel correspondant à p dans sortie sur 3 octets
    try {
      sortie.write((p) & 0xFF);
      sortie.write((p >> 8) & 0xFF);
      sortie.write((p >> 16) & 0xFF);
    } catch (Exception e) {
    }
  }

  /**
   *
   * @return hauteur de l'image en pixels
   */
  public int hauteur() {
    return img.getHeight();
  }

  /**
   *
   * @return largeur de l'image en pixels
   */
  public int largeur() {
    return img.getWidth();
  }

  /**
   * Délivre un nouveau Pixel, copie d'un pixel d'un BitMap
   *
   * @param i
   * @param j
   * @pre <code>0 <= i & i < img.hauteur()</code>
   * @pre <code>0 <= j & j < img.largeur()</code>
   * @return une copie du pixel en position verticale <code>i</code> et
   *         horizontale <code>j</code>
   */
  public Pixel get(int j, int i) {
    if (i < 0 || i >= this.hauteur() || j < 0 || j >= this.largeur()) {
      try {
        throw new Exception("le pixel (" + i + ", " + j + ") est hors des limites du Bitmap");
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
      }
    }
    int p = img.getRGB(j, i);
    Pixel pix = new Pixel();
    pix.r = (p >> 16) & 0xFF;
    pix.v = (p >> 8) & 0xFF;
    pix.b = p & 0xFF;
    return pix;
  }

  /**
   *
   * Change la valeur d'un pixel d'un BitMap
   *
   * @param i
   * @param j
   * @param pix
   * @pre <code>0 <= i & i < img.hauteur()</code>
   * @pre <code>0 <= j & j < img.largeur()</code> La valeur du pixel en position
   *      verticale <code>i</code> et horizontale <code>j</code> du Bitmap
   *      concerné devient <pix.r, pix.v, pix.b>
   */
  public void set(int j, int i, Pixel pix) {
    if (i < 0 || i >= this.hauteur() || j < 0 || j >= this.largeur()) {
      try {
        throw new Exception("le pixel (" + i + ", " + j + ") est hors des limites du Bitmap");
      } catch (Exception e) {
        e.printStackTrace();
        System.exit(0);
      }
    }
    int p = pix.b + ((pix.v) << 8) + ((pix.r) << 16);
    img.setRGB(j, i, p);
  }

  /**
   * Tests
   *
   * @param args
   */
  public static void main(String[] args) {
    BitMap m = blanche(3, 4);
    for (int i = 0; i < m.hauteur(); i++) {
      for (int j = 0; j < m.largeur(); j++) {
        System.out.print(m.get(i, j));
      }
      System.out.println();
    }
    System.out.println();
    m.set(0, 0, new Pixel(128, 128, 128));
    for (int i = 0; i < m.hauteur(); i++) {
      for (int j = 0; j < m.largeur(); j++) {
        System.out.print(m.get(i, j));
      }
      System.out.println();
    }
    m.get(0, 1).r = 0;
    System.out.println();
    for (int i = 0; i < m.hauteur(); i++) {
      for (int j = 0; j < m.largeur(); j++) {
        System.out.print(m.get(i, j));
      }
      System.out.println();
    }

  }
}

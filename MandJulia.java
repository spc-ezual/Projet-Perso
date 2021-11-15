import outils.*;
public class MandJulia {
    public static void main(String[] args) {
        //creation_mandelbrot_carré(new int[]{3000,3000}, -2, -1.5, 1, 1.5, 500, "mand");
        creation_mandelbrot_cosh(new int[]{500,500}, -2, -2, 2, 2, 500,"cosh");
        creation_mandelbrot_sin(new int[]{500,500}, -2, -2, 2, 2, 500,"sin");
        creation_mandelbrot_sinh(new int[]{500,500}, -2, -2, 2, 2, 500,"sinh");
    }
    public static int quadratique(complexe z,complexe constante,int pressision){
        int i=0,rep=1;
        double dist=constante.norme();
        while (dist<2&&rep<=pressision){
            i=0;
            rep++;
            while(dist<2&&i<271){
                z=z.Carré().add(constante);
                i++;
                dist=z.norme();
            }
        }
        return i;
    }
    public static complexe affixe(int[] co , int[] taille, double debutx, double finx, double debuty, double finy) {
        double r = debutx+((finx-debutx)/taille[0])*co[0],i=debuty+((finy-debuty)/taille[1])*co[1];
        return new complexe(r, i);
    }
    public static double[] coul(int longeur_Onde){
        double[]couleur;
        if      (longeur_Onde<60)couleur=   new double[]   {((-1*(double)longeur_Onde+60)/(60-0))*255,0,255};
        else if (longeur_Onde<110)couleur=  new double[]   {0,((longeur_Onde-60)/(110-60)*255),255};
        else if (longeur_Onde<130)couleur=  new double[]   {0,255,(-(longeur_Onde-130)/(130-110)*255)};
        else if (longeur_Onde<200)couleur=  new double[]   {((longeur_Onde-130)/(200-130)*255),255,0};
        else if (longeur_Onde<265)couleur=  new double[]   {255,(-(longeur_Onde-265)/(265-200)*255),0};
        else if (longeur_Onde<=270)couleur= new double[]   {255,0,0};
        else couleur=new double[]{0,0,0};
        
        return couleur;
    }
    public static void creation_mandelbrot_carré(int[] taille, double debut_x, double debut_y, double fin_x, double fin_y,int pressision,String nom){
        BitMap mand=new BitMap(taille[0], taille[1]);
        pressision=pressision/271+1;
        double couleur[];
        for(int i =0 ; i<taille[0];i++){
            for(int j =0;j<taille[1];j++){
                couleur=coul(quadratique(new complexe(0,0),affixe(new int[]{i,j},taille,debut_x,fin_x,debut_y,fin_y),pressision));
                mand.set(i, j, new Pixel((int)couleur[0],(int)couleur[1], (int)couleur[2]));
            }
        }
        BitMap.enregistreBitMap(mand, nom);
    }
    public static int cosin(complexe constante,int pressision){
        if(constante.estReel()&&constante.estImaginaire())return 271;
        int i=0 ,rep=1;
        complexe z= constante;
        while(z.norme()<50&&rep<=pressision){
            i=0;
            rep++;
            while(z.norme()<50&&i<271){
                z=z.div(constante).cosinus();
                i++;
            }
        }
        return i;
    }
    public static void creation_mandelbrot_cos(int[] taille, double debut_x, double debut_y, double fin_x, double fin_y,int pressision,String nom){
        BitMap mand=new BitMap(taille[0], taille[1]);
        pressision=pressision/271+1;
        double couleur[];
        for(int i =0 ; i<taille[0];i++){
            for(int j =0;j<taille[1];j++){
                couleur=coul(cosin(affixe(new int[]{i,j},taille,debut_x,fin_x,debut_y,fin_y),pressision));
                mand.set(i, j, new Pixel((int)couleur[0],(int)couleur[1], (int)couleur[2]));
            }
        }
        BitMap.enregistreBitMap(mand, nom);
    }
    public static int cosinh(complexe constante,int pressision){
        if(constante.estReel()&&constante.estImaginaire())return 271;
        int i=0 ,rep=1;
        complexe z= constante;
        while(z.norme()<50&&rep<=pressision){
            i=0;
            rep++;
            while(z.norme()<50&&i<271){
                z=z.div(constante).cosinusH();
                i++;
            }
        }
        return i;
    }
    public static void creation_mandelbrot_cosh(int[] taille, double debut_x, double debut_y, double fin_x, double fin_y,int pressision,String nom){
        BitMap mand=new BitMap(taille[0], taille[1]);
        pressision=pressision/271+1;
        double couleur[];
        for(int i =0 ; i<taille[0];i++){
            for(int j =0;j<taille[1];j++){
                couleur=coul(cosinh(affixe(new int[]{i,j},taille,debut_x,fin_x,debut_y,fin_y),pressision));
                mand.set(i, j, new Pixel((int)couleur[0],(int)couleur[1], (int)couleur[2]));
            }
        }
        BitMap.enregistreBitMap(mand, nom);
    }
    
    public static int sinu(complexe constante,int pressision){
        if(constante.estReel()&&constante.estImaginaire())return 271;
        int i=0 ,rep=1;
        complexe z= constante;
        while(z.norme()<50&&rep<=pressision){
            i=0;
            rep++;
            while(z.norme()<50&&i<271){
                z=z.div(constante).sinus();
                i++;
            }
        }
        return i;
    }
    public static void creation_mandelbrot_sin(int[] taille, double debut_x, double debut_y, double fin_x, double fin_y,int pressision,String nom){
        BitMap mand=new BitMap(taille[0], taille[1]);
        pressision=pressision/271+1;
        double couleur[];
        for(int i =0 ; i<taille[0];i++){
            for(int j =0;j<taille[1];j++){
                couleur=coul(sinu(affixe(new int[]{i,j},taille,debut_x,fin_x,debut_y,fin_y),pressision));
                mand.set(i, j, new Pixel((int)couleur[0],(int)couleur[1], (int)couleur[2]));
            }
        }
        BitMap.enregistreBitMap(mand, nom);
    }
    public static int sinh(complexe constante,int pressision){
        if(constante.estReel()&&constante.estImaginaire())return 271;
        int i=0 ,rep=1;
        complexe z= constante;
        while(z.norme()<50&&rep<=pressision){
            i=0;
            rep++;
            while(z.norme()<50&&i<271){
                z=z.div(constante).sinusH();
                i++;
            }
        }
        return i;
    }
    public static void creation_mandelbrot_sinh(int[] taille, double debut_x, double debut_y, double fin_x, double fin_y,int pressision,String nom){
        BitMap mand=new BitMap(taille[0], taille[1]);
        pressision=pressision/271+1;
        double couleur[];
        for(int i =0 ; i<taille[0];i++){
            for(int j =0;j<taille[1];j++){
                couleur=coul(sinh(affixe(new int[]{i,j},taille,debut_x,fin_x,debut_y,fin_y),pressision));
                mand.set(i, j, new Pixel((int)couleur[0],(int)couleur[1], (int)couleur[2]));
            }
        }
        BitMap.enregistreBitMap(mand, nom);
    }
}
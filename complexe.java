import java.lang.Math;
;public class complexe {
    protected double re;
    protected double im;
    complexe(double r, double i){
        re=r;
        im=i;
    }
    @Override
    public String toString() {
        double a = re;
        double b = im;
        if (a==0 && b==0) return("0");
        if (a!=0 && b==0) return(""+a);
        if (a==0 && b>0) return(b+"i");
        String signei;
        String res="";
        if (a!=0) res+=a;
        if (b>0) signei=" + "; 
        else {
            signei=" - "; 
            b=-b;
        };
        return(res+signei+b+"i");
    }

    public complexe add(complexe b){
        return new complexe(b.re+re,b.im+im);
    }
    public complexe sub(complexe b){
        return new complexe(-b.re+re,-b.im+im);
    }
    public complexe mult(complexe b){
        return new complexe(re*b.re-b.im*im,b.im*re+im*b.re);
    }
    public complexe div(complexe comp){
        return new complexe(  (re*comp.re+im*comp.im)/(comp.re*comp.re+comp.im*comp.im)  , (-re*comp.im+im*comp.re)/(comp.re*comp.re+comp.im*comp.im)  );
    }
    public boolean estReel(){
        return im==0;
    }
    public boolean estImaginaire(){
        return re==0;
    }
    public double partieReel(){
        return re;
    }
    public double partieImaginaire(){
        return im;
    }
    public complexe uneRacineNiem(int puissance){
        complexe y = new complexe(1, 1),ytemp;
        for(int i =0 ; i<100;i++){
            ytemp=new complexe(y.partieReel(), y.partieImaginaire());

        }
        return y;
    }
    public complexe RacineCarré(){
        if(this.estImaginaire()&&this.estReel())return this;
        complexe y = new complexe(1, 1),ytemp;
        for(int i =0 ; i<500;i++){
            ytemp=this.div(y);
            ytemp=ytemp.add(y);
            y=ytemp.mult(new complexe(0.5,0));
        }
        return y;
    }
    public double norme(){
        return Math.sqrt(Math.pow(re,2)+Math.pow(im, 2));
    }
    public complexe conjuger(){
        return new complexe(re, -im);
    }

}
class testcomplexe{
    public static void main(String[] args) {
        complexe a=new complexe(3,-5);
        complexe b=new complexe(4,-5);
        complexe c=new complexe(0.5,0);
        System.out.println(a);
        System.out.println(b);
        c=a.mult(b);
        System.out.println(c);
        System.out.println(a.RacineCarré());
    }
}
package exemples;
import java.io.Serializable;

public class Empleat implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int num=0;
    private String nom=null;
    private int departament=0;
    private int edat=0;
    private double sou=0.0;

    public Empleat(){
    }

    public Empleat(int num, String nom, int dep, int edat, double sou){

        this.num=num;
        this.nom=nom;
        this.departament=dep;
        this.edat=edat;
        this.sou=sou;

    }

    public int getNum(){

        return this.num;

    }

    public String getNom(){

        return this.nom;

    }

    public int getDepartament(){

        return this.departament;

    }

    public int getEdat(){

        return this.edat;

    }

    public double getSou(){

        return this.sou;

    }

}
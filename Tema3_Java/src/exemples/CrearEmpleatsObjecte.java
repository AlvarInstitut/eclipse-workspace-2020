package exemples;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class CrearEmpleatsObjecte {

    public static void main(String[] args) throws IOException {

        ObjectOutputStream f = new ObjectOutputStream(new FileOutputStream("Empleats_J.obj"));

        String[] noms = {"Andreu","Bernat","Clàudia","Damià"};
        int[] departaments = {10,20,10,10};
        int[] edats = {32,28,26,40};
        double[] sous = {1000.0,1200.0,1100.0,1500.0};
        Empleat e;

        for (int i=0;i<4;i++){

            e = new Empleat(i+1,noms[i],departaments[i],edats[i],sous[i]);
            f.writeObject(e);

        }

        f.close();

    }

}
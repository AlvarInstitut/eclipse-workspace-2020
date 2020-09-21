package exemples;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ConsultarEmpleatsObjecte {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectInputStream f = new ObjectInputStream(new FileInputStream("Empleats_K.obj"));

        Empleat e;
        try {

            while (true) {

                e = (Empleat) f.readObject();
                System.out.println("Número: " + e.getNum());
                System.out.println("Nom: " + e.getNom());
                System.out.println("Depart: " + e.getDepartament());
                System.out.println("Edat: " + e.getEdat());
                System.out.println("Sou: " + e.getSou());
                System.out.println();

            }

        } catch (EOFException eof) {

            f.close();

        }

    }

}
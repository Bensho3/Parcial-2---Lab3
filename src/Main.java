import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args)
    {

        SistemaSalud salita = new SistemaSalud(2);
        try
        {
            salita.registrarPaciente("Fernando Alonso", 42, "Monaco", 3333333, "Piloto F1");

            salita.registrarPaciente("Max Verstappen",  26, "Monaco", 2121212121, "Piloto F1");

            salita.registrarPaciente("Franco Colapinto", 21, "La Boca", 53214573, "Piloto F2");

        } catch (ExcpecionKit e)
        {
            //Aca para probar la funcion de consultar para mas Kits
            System.out.println(e.getMessage());
            salita.consultarMasKits();

            try
            {
                salita.registrarPaciente("Franco Colapinto", 21, "La Boca", 53214573, "Piloto F2");
            } catch (ExcpecionKit ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        salita.mostrarPersonas();

        //Punto 2
        salita.testear();
        salita.mostrarResultadosTest();

        //Punto 3
        salita.aislar();

        //No llegue a hacer el ultimo punto opcional :c
    }
}


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class SistemaSalud
{
    private int kitsDisponibles;
    private List<Paciente> personasRegistradas;
    private Set<Integer> dnisRegistrados;
    private int proximoNumeroKit;

    private Map<Integer, RegistroTest> registrosTest = new HashMap<>();


    public SistemaSalud(int kitsDisponibles)
    {
        this.kitsDisponibles = kitsDisponibles;
        this.personasRegistradas = new ArrayList<>();
        this.dnisRegistrados = new HashSet<>();
        this.proximoNumeroKit = 1;
    }

    public void consultarMasKits()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cuantos nuevos kits vas a querer?");
        int nuevosKits = scanner.nextInt();
        kitsDisponibles += nuevosKits;
    }

    public void registrarPaciente(String nombreYApellido, Integer edad, String barrio, Integer dni, String ocupacion) throws ExcpecionKit
    {
        if (dnisRegistrados.contains(dni))
        {
            System.out.println("Dni ya registrado!.");
            return;
        }

        if (kitsDisponibles <= 0) {
            throw new ExcpecionKit("No hay mas kits :c");
        }

        Paciente nuevoPaciente = new Paciente(nombreYApellido, edad, barrio, dni, ocupacion);
        nuevoPaciente.setNumeroKit(proximoNumeroKit);
        proximoNumeroKit++;

        personasRegistradas.add(nuevoPaciente);
        dnisRegistrados.add(dni);
        kitsDisponibles--;

    }

    public void mostrarPersonas()
    {
        for (Paciente pacientito : personasRegistradas)
        {
            System.out.println(pacientito);
        }
    }


    //Tarde 5 meses en acordarme como hacer lo del random
    public void testear()
    {
        Random temparaturita = new Random();
        for (Paciente pacientito : personasRegistradas)
        {
            double temperatura = 36 + (39 - 36) * temparaturita.nextDouble();
            RegistroTest registro = new RegistroTest(pacientito.getDni(), temperatura);
            registrosTest.put(pacientito.getNumeroKit(), registro);
        }
    }

    public void mostrarResultadosTest()
    {
        for (Map.Entry<Integer, RegistroTest> entry : registrosTest.entrySet())
        {
            System.out.println("Kit: " + entry.getKey() + "," + entry.getValue());
        }
    }


    public void aislar() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("urgente.txt")))
        {
            for (Paciente pacientito : personasRegistradas)
            {
                RegistroTest registro = registrosTest.get(pacientito.getNumeroKit());
                if (registro.getTemperatura() >= 38)
                {
                    ExcepcionAislamiento ex = new ExcepcionAislamiento(pacientito.getNumeroKit(), pacientito.getBarrio());
                    System.out.println("\n\nDNI y Temperatura del peligro potencial:" + registro.toString());
                    System.out.println("Aislando peligroPotencial: " + ex);
                    oos.writeObject(ex);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

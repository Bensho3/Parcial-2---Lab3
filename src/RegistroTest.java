public class RegistroTest
{
    private Integer dni;
    private double temperatura;

    public RegistroTest(Integer dni, double temperatura) {
        this.dni = dni;
        this.temperatura = temperatura;
    }

    public Integer getDni() {
        return dni;
    }

    public double getTemperatura() {
        return temperatura;
    }


    //No me acuerdo como limitar los digitos en un double, quedo re mil preciso el termometro, muestra como 8 numeros con decimales
    @Override
    public String toString() {
        return "DNI: " + dni + ", Temperatura: " + temperatura;
    }
}
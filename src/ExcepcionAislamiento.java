public class ExcepcionAislamiento extends Exception
{
        private int numeroKit;
        private String barrio;

     public ExcepcionAislamiento(int numeroKit, String barrio)
     {
            super("OJO, POTENCIAL AMENAZA PARA LA SALUBIRDAD PUBLICA!, AISLAR INMEDIATAMENTE");
            this.numeroKit = numeroKit;
            this.barrio = barrio;
        }

        public int getNumeroKit()
        {
            return numeroKit;
        }

        public String getBarrio()
        {
            return barrio;
        }
    }


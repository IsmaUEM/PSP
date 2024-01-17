package Json1;

public class PlacaBase extends Componente{
    private String modelo;
    private int slotsRam;

    @Override
    public String toString() {
        return "PlacaBase{" +
                "modelo='" + modelo + '\'' +
                ", slotsRam=" + slotsRam +
                '}';
    }

    public PlacaBase(String marca, String modelo, int slotsRam) {
        super(marca);
        this.modelo = modelo;
        this.slotsRam = slotsRam;
    }
}

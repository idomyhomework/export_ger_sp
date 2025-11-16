import java.io.Serializable;

public class TaxesGermany implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tasaMatricula = 40;
    private Integer placasFisicas = 25;
    private Integer seguroTemporal = 80;
    private Integer TuV = 120;

    public TaxesGermany(int tasaMatricula, int placasFisicas, int seguroTemporal, int TuV) {
        this.tasaMatricula = tasaMatricula;
        this.placasFisicas = placasFisicas;
        this.seguroTemporal = seguroTemporal;
        this.TuV = TuV;
    }

    public TaxesGermany() {

    }

    @Override
    public String toString() {
        return "(ALEMANIA)\nTasa de matrícula: " + this.tasaMatricula + "\nPlacas físicas: " + this.placasFisicas +
                "\nSeguro temporal: " + this.seguroTemporal + "\nITV aleman: " + this.TuV;
    }

    public Integer taxesAmount() {
        int total = 0;
        total = this.tasaMatricula + this.placasFisicas + this.seguroTemporal + this.TuV;
        return total;
    }

}

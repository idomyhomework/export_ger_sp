import java.io.Serializable;

public class TaxesSpain implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer IVTM = 120;
    private Integer ITV = 100;
    private Integer DGT = 100;
    private Integer placas = 30;
    private Double IEDMT = 0.0;

    public TaxesSpain(int IVTM, int ITV, int DGT, int placas, double IEDMT) {
        this.IVTM = IVTM;
        this.ITV = ITV;
        this.DGT = DGT;
        this.placas = placas;
        this.IEDMT = IEDMT;
    }

    public TaxesSpain() {

    }

    public void setIEDMT(double contam, int precio) {
        if (contam <= 120) {
            this.IEDMT = 0.0;
        } else if (contam >= 121 && contam <= 159) {
            this.IEDMT = precio * 0.0475;
        } else if (contam >= 160 && contam <= 199) {
            this.IEDMT = precio * 0.0975;
        } else if (contam >= 200) {
            this.IEDMT = precio * 0.1475;
        }
    }

    @Override
    public String toString() {
        return "\nIVTM (impuesto de circulación): " + this.IVTM + "\nITV (revisión) " + this.ITV +
                "\nTráfico (DGT): " + this.DGT + "\nLas placas españolas: " + this.placas + "\nIEDMT: " + this.IEDMT;
    }

    public Double taxesAmount() {
        double total = 0;
        total = IVTM + ITV + DGT + placas + IEDMT;
        System.out.println("El total de los impuestos en España: ");
        return total;
    }

}

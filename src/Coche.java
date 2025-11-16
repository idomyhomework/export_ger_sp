import java.io.Serializable;

import javax.swing.JOptionPane;

public class Coche implements Serializable{

    private static final long serialVersionUID = 1L;
    private String modelo;
    private String marca;
    private Integer precioSpain;
    private Integer precioGermany;
    private Double contaminación;
    public TaxesGermany taxesGermany;
    public TaxesSpain taxesSpain;
    private String urlGermany = "";
    private String urlSpain = "";

    public Coche(String modelo, String marca, int precioGermany,
            int precioSpain, double contaminación, TaxesGermany taxesGer, TaxesSpain taxesSp, String urlGer,
            String urlSpain) {
        this.modelo = modelo;
        this.marca = marca;
        this.precioSpain = precioSpain;
        this.precioGermany = precioGermany;
        this.contaminación = contaminación;
        this.taxesGermany = taxesGer;
        this.taxesSpain = taxesSp;
        this.urlGermany = urlGer;
        this.urlSpain = urlSpain;
    }

    public void contarLaRentabilidad() {
        double rentabilidad = this.precioSpain - (this.precioGermany + this.taxesGermany.taxesAmount() + this.taxesSpain.taxesAmount());

        if (rentabilidad > 0) {
            JOptionPane.showMessageDialog(null, "Vender este " + this.marca + " " + this.modelo + " sale rentable." +
            "La rentabilidad es de " + rentabilidad);
        } else {
            JOptionPane.showMessageDialog(null, "Vender este " + this.marca + " " + this.modelo + " NO sale rentable." +
            "pierdes " + Math.abs(rentabilidad));
        }
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Coche coche = (Coche) obj;
        return marca.equals(coche.marca) && modelo.equals(coche.modelo);
    }
}

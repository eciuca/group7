package rezolvate.bank;

import java.math.BigDecimal;

public class ContBancar {

    private String titular;
    private BigDecimal balanta;

    public ContBancar(String titular, BigDecimal balanta) {
        this.titular = titular;
        this.balanta = balanta;
    }

    public String getTitular() {
        return titular;
    }

    public ContBancar setTitular(String titular) {
        this.titular = titular;
        return this;
    }

    public BigDecimal getBalanta() {
        return balanta;
    }

    public ContBancar setBalanta(BigDecimal balanta) {
        this.balanta = balanta;
        return this;
    }

    @Override
    public String toString() {
        return "ContBancar{" +
                "titular='" + titular + '\'' +
                ", balanta=" + balanta +
                '}';
    }
}

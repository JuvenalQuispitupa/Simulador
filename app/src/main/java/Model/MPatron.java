package Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by root on 14/10/17.
 */

public class MPatron implements Parcelable {

    private Integer id;
    private String tipo;
    private Integer secuencia;
    private Integer valor;

    public static final String TABLE_NAME="patron";
    public static final String ID_FIELD="id";
    public static final String TIPO_FIELD="tipo";
    public static final String SECUENCIA_FIELD="secuencia";
    public static final String VALOR_FIELD="valor";

    public MPatron(Integer id, String tipo, Integer secuencia, Integer valor) {
        this.id = id;
        this.tipo = tipo;
        this.secuencia = secuencia;
        this.valor = valor;
    }
    public MPatron() {

    }

    protected MPatron(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tipo = in.readString();
        this.secuencia = (Integer) in.readValue(Integer.class.getClassLoader());
        this.valor =(Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<MPatron> CREATOR = new Creator<MPatron>() {
        @Override
        public MPatron createFromParcel(Parcel in) {
            return new MPatron(in);
        }

        @Override
        public MPatron[] newArray(int size) {
            return new MPatron[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.tipo);
        dest.writeValue(this.secuencia);
        dest.writeValue(this.valor);
    }

    @Override
    public String toString() {
        return "MPatron{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", secuencia=" + secuencia +
                ", valor=" + valor +
                '}';
    }
}

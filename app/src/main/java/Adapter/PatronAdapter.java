package Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.juvenalquisptitupayupa.simulador.R;

import java.util.List;

import Model.MPatron;

/**
 * Created by root on 14/10/17.
 */

public class PatronAdapter extends RecyclerView.Adapter<PatronAdapter.PatronViewHolder>{

    private List<MPatron> patrones;
    //paso 2. crear variables globales
    private OnRviItemClickListener onRviItemClick;

    @Override
    public PatronViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //crea item?
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_rv_item,parent,false);
        return new PatronViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PatronViewHolder holder, int position) {
        final MPatron patron = patrones.get(position);
        holder.tvTipo.setText(patron.getTipo());
        holder.tvSecuencia.setText(patron.getSecuencia().toString());
        holder.tvValor.setText(patron.getValor().toString());

        //paso 5
        holder.itemView.setOnClickListener((view) -> {
            if(onRviItemClick!= null)
            {
                onRviItemClick.onItemClick(patron);
            }
        });
        holder.btnEditar.setOnClickListener((view)-> {
            if(onRviItemClick!= null)
            {
                onRviItemClick.onBtnEditarClick(patron);
            }
        });
    }

    @Override
    public int getItemCount() {
        return patrones==null ?0:patrones.size();
    }

    //1er paso, crear  la interface listener
    public interface OnRviItemClickListener{
        void onItemClick(MPatron patron);// metodo para eliminar un item
        void onBtnEditarClick(MPatron patron);//metodo para el click del boton editar
    }

    //paso 3, recibir el listener por el constructor.
    public PatronAdapter(OnRviItemClickListener onRviItemClick){
        this.onRviItemClick =onRviItemClick;
    }

    class PatronViewHolder extends RecyclerView.ViewHolder{
        TextView tvTipo,tvSecuencia,tvValor;
        Button btnEditar;
        public PatronViewHolder(View itemView){
            super(itemView);
            tvTipo = itemView.findViewById(R.id.tv_tipo_rvi);
            tvSecuencia = itemView.findViewById(R.id.tv_secuencia_rvi);
            tvValor = itemView.findViewById(R.id.tv_valor_rvi);
            btnEditar = itemView.findViewById(R.id.btn_editar_rvi);
        }
    }

    public void addList(List<MPatron> patrones)
    {
        this.patrones = patrones;
        notifyDataSetChanged();//si el ADAPTER recibe una lista, el recyclerView se actualiza.
    }


}

package co.com.ansaca.reto2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ansaca.reto2.R;
import co.com.ansaca.reto2.entity.Animal;

public class AnimalAdapter extends BaseAdapter implements Filterable {

    Context context;
    CustomFilter filter;
    private List<Animal> listaAnimalesIn;
    private List<Animal> listaAnimalesOut;

    public AnimalAdapter(Context context, List<Animal> listaAnimales){
        this.context = context;
        listaAnimalesOut = listaAnimales;
        listaAnimalesIn = listaAnimales;
    }

    @Override
    public int getCount() {
        return listaAnimalesOut.size();
    }

    @Override
    public Object getItem(int i) {
        return listaAnimalesOut.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        ViewHolder holder;
        if(view != null){
            holder = (ViewHolder) view.getTag();
        }else {
            view = inflater.inflate(R.layout.animal_item_layout, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.imagen.setImageResource(listaAnimalesOut.get(i).getImagen());
        holder.txtNombre.setText(listaAnimalesOut.get(i).getNombre());
        holder.txtDescripcion.setText(listaAnimalesOut.get(i).getDescripcion());
        return view;
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter=new CustomFilter();
        }

        return filter;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();

            if(charSequence != null && charSequence.length() > 0){
                charSequence = charSequence.toString().toUpperCase();

                List<Animal> filters = new ArrayList<>();

                for(int i = 0; i< listaAnimalesIn.size(); i++){
                    if(listaAnimalesIn.get(i).getNombre().toUpperCase().contains(charSequence)){
                        Animal animal = new Animal(listaAnimalesIn.get(i).getImagen(), listaAnimalesIn.get(i).getNombre(), listaAnimalesIn.get(i).getDescripcion(), listaAnimalesIn.get(i).getSonido());
                        filters.add(animal);
                    }
                }
                results.count=filters.size();
                results.values=filters;

            }else {
                results.count= listaAnimalesIn.size();
                results.values= listaAnimalesIn;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            listaAnimalesOut = (List<Animal>) filterResults.values;
            notifyDataSetChanged();
        }
    }

    class ViewHolder{
        @BindView(R.id.imagen)
        ImageView imagen;
        @BindView(R.id.txtNombre)
        TextView txtNombre;
        @BindView(R.id.txtDescripcion)
        TextView txtDescripcion;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}

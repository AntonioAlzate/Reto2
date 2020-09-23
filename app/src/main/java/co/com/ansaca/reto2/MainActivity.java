package co.com.ansaca.reto2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.ansaca.reto2.adapters.AnimalAdapter;
import co.com.ansaca.reto2.entity.Animal;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listViewAnimales)
    public ListView listViewAnimales;
    @BindView(R.id.txtSearch)
    public SearchView txtSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //loadData();

        final AnimalAdapter adapter = new AnimalAdapter(this, getData());
        listViewAnimales.setAdapter(adapter);

        txtSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }
        });

        listViewAnimales.setOnItemClickListener((adapterView, view, i, l) -> {
            Animal animalSeleccionado = (Animal) listViewAnimales.getItemAtPosition(i);
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), animalSeleccionado.getSonido());
            mediaPlayer.start();
        });
    }

    public List<Animal> getData(){
        List<Animal> listaAnimales = new ArrayList<>();

        listaAnimales.add(new Animal(R.drawable.gato, "Gato", "Es un felino domesticado por el hombre desde el año 7500 a.c", R.raw.gato));
        listaAnimales.add(new Animal(R.drawable.leon, "Leon", "Es un mamimero, carniboro de la familia de los felidos conocido como el rey de la selva",R.raw.leon));
        listaAnimales.add(new Animal(R.drawable.perro, "Perro", "Los perros surgieron en Europa hace mas de 18000 años a partir de los lobos domesticados por los humanos",R.raw.perro));
        listaAnimales.add(new Animal(R.drawable.ave, "Canario", "Es un pajaro domestico criado como animal de compania más abundante del mundo", R.raw.pajaro));

        return listaAnimales;
    }

}
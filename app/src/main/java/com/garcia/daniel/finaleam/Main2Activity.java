package com.garcia.daniel.finaleam;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.garcia.daniel.finaleam.Controlador.CtlUsuario;
import com.garcia.daniel.finaleam.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements Fragmentos {

    ListView listUsu;
    CtlUsuario controlador;
    List<Usuario> listaUsuarios;


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //listUsu = findViewById(R.id.listViewUsuarios);
        //configurarLista();
        //controlador = new CtlUsuario(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.jljljlj
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_fragment_inactivos, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    FragmentInactivos inactivos = new FragmentInactivos();
                    return inactivos;


                case 1:
                    FragmentActivos activos = new FragmentActivos();
                    return activos;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }


    private void configurarLista() {
        int esta = 0;
        listaUsuarios = controlador.listarUsuario(esta);
        List<String> listaAdapter = new ArrayList<String>();
        ArrayAdapter<String> adapter;

        if (listaUsuarios.size() > 0) {
            for (int x = 0; x < listaUsuarios.size(); x++) {
                listaAdapter.add(
                        listaUsuarios.get(x).getNombres() + "-"
                        + listaUsuarios.get(x).getCedula() + "-"
                        + listaUsuarios.get(x).getRol());
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAdapter);
        } else {
            listaAdapter.add("No hay registros en la base de datos");
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaAdapter);
        }

        listUsu.setAdapter(adapter);

        listUsu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                mostrarInformacion(posicion);
            }
        });
    }

    public void mostrarInformacion(int pos) {
        Toast.makeText(this,
                "Nombre: " + listaUsuarios.get(pos).getNombres()+ " " +
                        "Cedula: " + listaUsuarios.get(pos).getCedula()+ " " +
                        "Rol: " + listaUsuarios.get(pos).getRol()+ " ", Toast.LENGTH_SHORT).show();
    }

    public void Regresar(View view) {
        Intent intent = new Intent(Main2Activity.this, PrincipalAdministrador.class);
        startActivity(intent);
    }

}

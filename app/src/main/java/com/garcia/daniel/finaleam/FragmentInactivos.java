package com.garcia.daniel.finaleam;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.garcia.daniel.finaleam.Controlador.CtlUsuario;
import com.garcia.daniel.finaleam.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentInactivos.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentInactivos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInactivos extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    CtlUsuario controlador;
    ListView listUsu;
    List<Usuario> listaUsuarios;
     private Adapter adaptador;

    public FragmentInactivos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentInactivos.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentInactivos newInstance(String param1, String param2) {
        FragmentInactivos fragment = new FragmentInactivos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragment_inactivos, container, false);
        controlador = new CtlUsuario(this.getActivity());
        listUsu = vista.findViewById(R.id.listViewUsuarios);
        //configurarLista();
        return vista;
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

            //adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listaAdapter);
            //adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listaAdapter);
            //adaptador = new Adapter(getContext(), (ArrayList<Usuario>) listaUsuarios);
            //listUsu.setAdapter(adapter);
        } else {
            listaAdapter.add("No hay registros en la base de datos");
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listaAdapter);
        }

        //listUsu.setAdapter(adapter);

        listUsu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                mostrarInformacion(posicion);
            }
        });
    }

    public void mostrarInformacion(int pos) {
        Toast.makeText(getContext(),
                "Nombre: " + listaUsuarios.get(pos).getNombres()+ " " +
                        "Cedula: " + listaUsuarios.get(pos).getCedula()+ " " +
                        "Rol: " + listaUsuarios.get(pos).getRol()+ " ", Toast.LENGTH_SHORT).show();
    }





    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package android.activity;

import java.util.ArrayList;

import layouts.DuploSimplesExpandableListAdapter;
import Modelos.Departamento;
import Modelos.Subdepartamento;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import controle.Visual.DepartamentosControle;

public class DepartamentosActivity extends ExpandableListActivity  {

	private static final String TAG = "DepartamentosActivity";
    private ExpandableListAdapter mAdapter;

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ArrayList<Departamento> departamentos = DepartamentosControle.get().buscarDepartamento();
        ArrayList<ArrayList<Subdepartamento>> subdepartamentos = DepartamentosControle.get().buscarSubdepartamentos(departamentos);

        DuploSimplesExpandableListAdapter<Departamento, Subdepartamento> customExpandableListAdapter = 
        		new DuploSimplesExpandableListAdapter<Departamento, Subdepartamento>();
        customExpandableListAdapter.setContext(DepartamentosActivity.this);
        customExpandableListAdapter.setList_pai(departamentos);
        customExpandableListAdapter.setList_filho(subdepartamentos);
        
		mAdapter = customExpandableListAdapter;
        setListAdapter(mAdapter);
        registerForContextMenu(getExpandableListView());
    }
    

    @Override
    public boolean onChildClick(ExpandableListView parent, View v,
    		int groupPosition, int childPosition, long id) {

    	Subdepartamento sub = (Subdepartamento) parent.getExpandableListAdapter().getChild(groupPosition, childPosition);
    	Log.d(TAG, sub.getNome()+ " sub ID " + sub.getIdSubdepartamento()+ " dep ID " + sub.getIdDepartamento());
    	
    	Intent intent = new Intent();
		intent.setClassName("android.activity",
				"android.activity.CodigobarrasDepartamentosActivity");
		intent.putExtra("subdepartamentoId", String.valueOf(sub.getIdSubdepartamento()));
		intent.putExtra("subdepartamentoDepartamento", String.valueOf(sub.getIdDepartamento()));
		intent.putExtra("subdepartamentoNome", sub.getNome());
    	startActivity(intent);

    	return super.onChildClick(parent, v, groupPosition, childPosition, id);
    }
}

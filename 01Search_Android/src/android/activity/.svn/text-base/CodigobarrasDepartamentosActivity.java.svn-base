package android.activity;

import java.util.ArrayList;


import conexao.Conexao;
import Modelos.Codigobarras;
import Modelos.Subdepartamento;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CodigobarrasDepartamentosActivity extends Activity{

	private ArrayAdapter<String> lits;
	private ArrayList<Codigobarras> listaProdutos;
	private Subdepartamento corrente;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.produtoscategorias);
		
		corrente = new Subdepartamento();
		
		corrente.setIdSubdepartamento(Integer.parseInt(String.valueOf(getIntent().getStringExtra("subdepartamentoId"))));
		corrente.setNome(getIntent().getStringExtra("subdepartamentoNome"));
		
		TextView subcategoria = (TextView)findViewById(R.id.notificacao);
		subcategoria.setText(corrente.getNome());
		
		lits = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);

		ListView listas = (ListView) findViewById(R.id.listView1);
		listas.setAdapter(lits);
		
		setaProdutos();

		
		listas.setOnItemClickListener(new OnItemClickListener() {
			 
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long id) {
				Intent intent = new Intent();

				intent.setClassName("android.activity", "android.activity.CodigobarrasActivity");

				intent.putExtra("Codigobarras", Conexao.xStream.toXML(listaProdutos.get(pos)));

				startActivity(intent);
			}
		});
		
		ImageView voltarBt = (ImageView) findViewById(R.id.voltarSubcategoria);
		
		voltarBt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}
	
	private void setaProdutos() {
		listaProdutos = Conexao.getReference().buscarCodigobarras(corrente);
		
		lits.clear();
		for (Codigobarras produto : listaProdutos) {
			lits.add(produto.getNome());
		}
	}
	
}

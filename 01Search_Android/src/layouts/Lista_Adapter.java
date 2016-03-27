package layouts;

import java.util.ArrayList;

import Modelos.Item;
import Modelos.Lista;
import android.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Lista_Adapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Lista> listas;

	public Lista_Adapter(Context context, ArrayList<Lista> lista) {
		// Itens que preencheram o listview
		this.listas = lista;
		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return listas.size();
	}

	public Lista getItem(int position) {
		return listas.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {

		// Pega o item de acordo com a posção.
		Lista lista = listas.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.linha_padrao, null);

		// mudar nome dos text da linha
		((TextView) view.findViewById(R.id.linha_t1))
				.setText("Compartilhando com:");
		((TextView) view.findViewById(R.id.linha_t2))
				.setText("Quantidade de itens:");

		// atravez do layout pego pelo LayoutInflater, pegamos cada id
		// relacionado
		// ao item e definimos as informações.
		((TextView) view.findViewById(R.id.linha_nome))
				.setText(lista.getNome());
		String qtdUsuario;

		if (lista.getQtd_usuarios() == 1) {
			qtdUsuario = lista.getQtd_usuarios().toString() + " amigo.";
		} else {
			qtdUsuario = lista.getQtd_usuarios().toString() + " amigos.";
		}

		((TextView) view.findViewById(R.id.linha_d1)).setText(qtdUsuario);
		((TextView) view.findViewById(R.id.linha_d2)).setText(lista
				.getQtd_itens().toString()+".");

		return view;
	}
}
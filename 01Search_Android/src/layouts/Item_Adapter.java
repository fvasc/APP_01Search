package layouts;

import java.util.ArrayList;

import Modelos.Item;
import android.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Item_Adapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Item> itens;

	public Item_Adapter(Context context, ArrayList<Item> itens) {
		// Itens que preencheram o listview
		this.itens = itens;
		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itens.size();
	} 

	public Item getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {

		// Pega o item de acordo com a posção.
		Item item = itens.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.linha_padrao, null);

		System.err.println("qtd "+ item.getPeriodicidade() + " " + item.getNome() + " " + item.getCodigoBarras().size());
		
		// atravez do layout pego pelo LayoutInflater, pegamos cada id
		// relacionado
		// ao item e definimos as informações.
		((TextView) view.findViewById(R.id.linha_nome)).setText(item
				.getNome());
		((TextView) view.findViewById(R.id.linha_d1)).setText(item
				.getPeriodicidade().toString()+".");
		((TextView) view.findViewById(R.id.linha_d2)).setText(item
				.getCodigoBarras().size()+".");

		return view;
	}
}
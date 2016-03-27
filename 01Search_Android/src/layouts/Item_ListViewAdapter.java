package layouts;

import java.util.ArrayList;

import android.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Item_ListViewAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<ItemListView> itens;

	public Item_ListViewAdapter(Context context, ArrayList<ItemListView> itens) {
		// Itens que preencheram o listview
		this.itens = itens;
		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itens.size();
	}

	public ItemListView getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {

		// Pega o item de acordo com a pos��o.
		ItemListView item = itens.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.item_listview, null);

		// atravez do layout pego pelo LayoutInflater, pegamos cada id
		// relacionado
		// ao item e definimos as informa��es.
		((TextView) view.findViewById(R.id.text)).setText(item.getTexto());
		((TextView) view.findViewById(R.id.text2)).setText(item.getTexto2());
		if (item.getIsDrawable())
			((ImageView) view.findViewById(R.id.imagemview))
					.setBackgroundDrawable(item.getImagem());
		else
			((ImageView) view.findViewById(R.id.imagemview))
					.setBackgroundResource(item.getIconeRid());

		return view;
	}
}
package layouts;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import conexao.Conexao;
import controle.Cache;
import controle.Visual.CodigobarrasControle;

import Modelos.Codigobarras;
import Modelos.Lista;
import android.activity.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Codigobarras_Adapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<Codigobarras> array_codigobarras;

	public Codigobarras_Adapter(Context context,
			ArrayList<Codigobarras> array_codigobarras) {
		// Itens que preencheram o listview
		this.array_codigobarras = array_codigobarras;
		// responsavel por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return array_codigobarras.size();
	}

	public Codigobarras getItem(int position) {
		return array_codigobarras.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View view, ViewGroup parent) {

		// Pega o codigobarras de acordo com a posção.
		Codigobarras codigobarras = array_codigobarras.get(position);
		// infla o layout para podermos preencher os dados
		view = mInflater.inflate(R.layout.linha_codigobarras, null);

		// ao codigobarras e definimos as informações.
		((TextView) view.findViewById(R.id.linha_codigobarras_nome))
				.setText(codigobarras.getNome());

		/*// imagem
		Drawable img = null;
		try {
			URL url = new URL(Conexao.caminho + codigobarras.getImagem().toString());
			InputStream is = (InputStream) url.getContent();
			img = Drawable.createFromStream(is, "src");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		
		((ImageView) view.findViewById(R.id.linha_codigobarras_imagem)).setBackgroundDrawable(Cache.get().getImagem(codigobarras.getImagem()));

		// sub e dep
		String depSub = codigobarras.getIdSubdepartamento().getIdDepartamento()
				.getNome()
				+ " > " + codigobarras.getIdSubdepartamento().getNome() + ".";
		((TextView) view.findViewById(R.id.linha_codigobarras_depSub))
				.setText(depSub);

		// marca
		((TextView) view.findViewById(R.id.linha_codigobarras_marca))
				.setText(codigobarras.getIdMarca().getNome() + ".");

		return view;
	}
}
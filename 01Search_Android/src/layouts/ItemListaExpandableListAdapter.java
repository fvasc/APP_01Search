package layouts;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import Modelos.Codigobarras;
import Modelos.Estabelecimento;
import Modelos.ItemLista;
import android.activity.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemListaExpandableListAdapter extends BaseExpandableListAdapter {

	private static final String TAG = "CustomExpandableListAdapter";
	private ArrayList<ItemLista> list_pai;
	private ArrayList<ArrayList<Codigobarras>> list_filho;
	private Context context;
	private int quantidadePai;
	private Estabelecimento estabelecimento;
	private static final int[] EMPTY_STATE_SET = {};
	private static final int[] GROUP_EXPANDED_STATE_SET = { android.R.attr.state_expanded };
	private static final int[][] GROUP_STATE_SETS = { EMPTY_STATE_SET, // 0
			GROUP_EXPANDED_STATE_SET // 1
	};

	public ItemListaExpandableListAdapter(Context context,
			ArrayList<ItemLista> list_pai,
			ArrayList<ArrayList<Codigobarras>> list_filho,
			Estabelecimento estabelecimento) {
		this.context = context;
		this.list_pai = list_pai;
		this.list_filho = list_filho;
		this.estabelecimento = estabelecimento;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public ItemListaExpandableListAdapter() {
	}

	public ArrayList<ArrayList<Codigobarras>> getList_filho() {
		return list_filho;
	}

	public void setList_filho(ArrayList<ArrayList<Codigobarras>> list_filho) {
		this.list_filho = list_filho;
	}

	public ArrayList<ItemLista> getList_pai() {
		return list_pai;
	}

	public void setList_pai(ArrayList<ItemLista> list_pai) {
		this.list_pai = list_pai;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public Object getChild(int groupPosition, int childPosition) {
		return list_filho.get(groupPosition).get(childPosition);
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int groupPosition) {
		return list_filho.get(groupPosition).size();
	}

	public Object getGroup(int groupPosition) {
		return list_pai.get(groupPosition);
		// return groups[groupPosition];
	}

	public int getGroupCount() {
		return list_pai.size();
		// return groups.length;
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		LayoutInflater infalter = LayoutInflater.from(context);
		convertView = infalter.inflate(R.layout.itemlista_linhafilho, parent, false);

		Codigobarras codigobarras = (Codigobarras) getChild(groupPosition,
				childPosition);

		((TextView) convertView.findViewById(R.id.linhafilho_codigobarras))
				.setText(codigobarras.getNome());

		((TextView) convertView.findViewById(R.id.linhafilho_preco))
				.setText("R$ "
						+ codigobarras.getPrecoCorrente()
								.setScale(2, BigDecimal.ROUND_HALF_UP)
								.toString());

		((TextView) convertView.findViewById(R.id.linhafilho_quantidade))
				.setText(codigobarras.getQtdComprada().toString());

		// escurecer fundo
		convertView.getBackground().setAlpha(100);

		return convertView;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		LayoutInflater infalter = LayoutInflater.from(context);
		convertView = infalter.inflate(R.layout.itemlista_linhapai, parent, false);

		if (getChildrenCount(groupPosition) == 0) {
			((ImageView) convertView.findViewById(R.id.indicator))
					.setVisibility(View.INVISIBLE);
		} else {
			((ImageView) convertView.findViewById(R.id.indicator))
					.setVisibility(View.VISIBLE);
			int stateSetIndex = (isExpanded ? 1 : 0);
			Drawable drawable = ((ImageView) convertView
					.findViewById(R.id.indicator)).getDrawable();
			drawable.setState(GROUP_STATE_SETS[stateSetIndex]);
		}

		ItemLista itemLista = (ItemLista) getGroup(groupPosition);

		((TextView) convertView.findViewById(R.id.linhapai_item))
				.setText(itemLista.getItem().getNome());

		((TextView) convertView.findViewById(R.id.linhapai_quantidade))
				.setText(itemLista.getQtdComprada() + "");
		quantidadePai = itemLista.getQtdComprada();

		// manipular data e quantidade restante
		String[] data_restante = manipularDataRestante(itemLista
				.getData_qtdRestante());

		// data_restante[0] = data
		((TextView) convertView.findViewById(R.id.linhaPaiDuracao))
				.setText(data_restante[0]);

		// data_restante[1] = qtd restante
		((TextView) convertView.findViewById(R.id.linhaPaiSobrou))
				.setText(data_restante[1]);

		// cor caso a duraçao Acabou(verm), é hoje(amar) ou no futuro(verd)
		String cor = definirCor(data_restante[0]);

		// setar cor
		((TextView) convertView.findViewById(R.id.linhaPaiDuracao))
				.setTextColor(Color.parseColor(cor));
		((TextView) convertView.findViewById(R.id.linhaPaiSobrou))
				.setTextColor(Color.parseColor(cor));

		// soma o itemlista com os codigobarras
		int somaQtds = somarQuantidades(groupPosition);

		((TextView) convertView.findViewById(R.id.linhapai_comprado))
				.setText(somaQtds + "/" + itemLista.getQuantidade());

		// riscar se comprou o suficiente
		if (somaQtds >= itemLista.getQuantidade()) {

			TextView t = (TextView) convertView
					.findViewById(R.id.linhapai_item);
			t.setPaintFlags(t.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

		} else {
			/*
			 * TextView t = (TextView) convertView
			 * .findViewById(R.id.linhapai_item);
			 * t.setPaintFlags(t.getPaintFlags() | Paint.);
			 */
		}

		return convertView;
	}

	private String definirCor(String data) {

		String cor;
		if (data.equals("Acabou")) {
			cor = "#ff6347";
		} else if (data.equals("Hoje") || data.equals("Amanhã")) {
			cor = "#FFD700";
		} else {
			cor = "#07b22c";
		}
		return cor;
	}

	private String[] manipularDataRestante(String data_restante) {

		String[] retorno = { "", "" };

		if (!data_restante.equals("") && !data_restante.equals("0000-00-00-0")) {

			// data e qtd restante do item
			String[] parametro = data_restante.split("-");

			// definir duraçao do item
			String dataString;

			LocalDate date = new LocalDate(Integer.parseInt(parametro[0]),
					Integer.parseInt(parametro[1]),
					Integer.parseInt(parametro[2]));
			LocalDate hoje = LocalDate.now(DateTimeZone.forTimeZone(TimeZone
					.getTimeZone("America/Sao_Paulo")));

			if (date.compareTo(hoje) == 0) {

				dataString = "Hoje";
			} else if (date.compareTo(hoje) < 0) {

				// passado
				String data = "Acabou";
				String restante = "0 item";

				retorno[0] = data;
				retorno[1] = restante;

				return retorno;
			} else {

				// futuro
				Days between = Days.daysBetween(hoje, date);
				if (between.getDays() == 1) {
					Log.e("EXPANDABLE", " == 1 ");
					dataString = "Amanhã";
				} else {
					dataString = date.getDayOfMonth() + "/"
							+ date.getMonthOfYear() + "/" + date.getYear();
				}

			}

			// definir texto da qtd restante
			Integer restante = Integer.parseInt(parametro[3]);
			String sobrou;
			if (restante <= 1) {
				sobrou = restante + " item";
			} else {
				sobrou = restante + " itens";
			}

			retorno[0] = dataString;
			retorno[1] = sobrou;

			return retorno;
		} else {

			// valores quando nãao há registros
			String data = "Acabou";
			String restante = "0 item";

			retorno[0] = data;
			retorno[1] = restante;

		}
		return retorno;

	}

	private int somarQuantidades(int groupPosition) {

		int qtdCodigobarras = 0;

		for (Codigobarras codigobarras : list_filho.get(groupPosition)) {
			qtdCodigobarras += codigobarras.getQtdComprada();
		}
		return (quantidadePai + qtdCodigobarras);
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}
}

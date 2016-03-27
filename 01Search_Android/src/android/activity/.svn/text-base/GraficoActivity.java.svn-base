package android.activity;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import layouts.ItemListView;
import layouts.Item_ListViewAdapter;
import Modelos.HistoricoPreco;
import Modelos.HistoricoPrecoPK;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import conexao.Conexao;
import controle.Cache;
import controle.ComparatorEstabelecimento;
import controle.Visual.CodigobarrasControle;

public class GraficoActivity extends Activity {

	private WebView myWebView;
	private String datas;
	private String precos;
	private String datasPos;
	private String precosPos;
	private BigDecimal precoMaior;
	private BigDecimal precoMenor;
	private Date dataMenor;
	private Date dataMaior;
	private HistoricoPrecoPK pk;
	private ArrayList<HistoricoPreco> historicoList;
	private String limitesData;
	private String limitesPreco;
	private ImageView voltarBt;
	private ImageView ordA_Z;
	private ImageView ordZ_A;
	private RelativeLayout menu;
	private ListView lsViewMenu;
	private ArrayList<ItemListView> itens;
	private Item_ListViewAdapter adapterListView;

	private ListView lsViewEstab;
	private Dialog dialog;
	private ImageView menuBt;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grafico);

		inicializaObjetos();
		inicializaMenu();

		eventoVoltar();
		eventoMenuBt();
		eventoItemMenu();

		myWebView.clearView();
		myWebView.loadUrl(geraURL());

	}

	private void eventoVoltar() {
		voltarBt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void processaDados() {
		for (HistoricoPreco historico : historicoList) {
			if (historico.equals(historicoList.get(0))) {
				dataMenor = historico.getHistoricoPK().getPrecoData();
				dataMaior = historico.getHistoricoPK().getPrecoData();

				datasPos = historico.getHistoricoPK().getPrecoData().getTime()
						+ "";
				precosPos = historico.getPreco().toString();
			} else {
				datasPos = datasPos + ","
						+ historico.getHistoricoPK().getPrecoData().getTime();
				precosPos = precosPos + "," + historico.getPreco().toString();
			}

			SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

			datas = datas + "|"
					+ formato.format(historico.getHistoricoPK().getPrecoData());
			precos = precos + "|R%24+" + historico.getPreco().toString();

			if (historico.getHistoricoPK().getPrecoData().after(dataMaior))
				dataMaior = historico.getHistoricoPK().getPrecoData();
			if (historico.getHistoricoPK().getPrecoData().before(dataMenor))
				dataMenor = historico.getHistoricoPK().getPrecoData();

			if (historico.getPreco().compareTo(precoMaior) == 1)
				precoMaior = historico.getPreco();
			if (historico.getPreco().compareTo(precoMenor) == -1)
				precoMenor = historico.getPreco();
		}

		limitesData = dataMenor.getTime() + "," + dataMaior.getTime();
		limitesPreco = precoMenor + "," + precoMaior;
	}

	private String geraURL() {

		limpaParametros();
		processaDados();

		String url = "http://chart.googleapis.com/chart" + "?chxl=0:"
				+ datas
				+ "|1:"
				+ precos
				+ "&chxp=0,"
				+ datasPos
				+ "|1,"
				+ precosPos
				+ "&chxr=0,"
				+ limitesData
				+ "|"
				+ "1,"
				+ limitesPreco
				+ // limites
				"&chxs=0,7A7A7A,11,0.5,l,676767|1,676767,11.5,0,l,676767"
				+ // Axis
					// Style
				"&chxt=x,y"
				+ // posiçao axis
				"&chs=600x300"
				+ // tamanho
				"&cht=lxy"
				+ // define o tipo do gráfico "linha xy"
				"&chco=224499"
				+ // Cor da linha
				"&chds="
				+ limitesData
				+ ","
				+ limitesPreco
				+ // limites
				"&chd=t:"
				+ datasPos
				+ "|"
				+ precosPos
				+ // valores X/Y
				"&chdl="
				+ CodigobarrasControle
						.get()
						.getEstabelecimentos()
						.get(CodigobarrasControle.get()
								.getEstabelecimentoCorrente()).getNome()
						.replace(' ', '+')
				+ // Legenda
				"&chdlp=b"
				+ // posição legenda (Bottom)
				"&chg=10,10,3,3"
				+ // Grid properties
				"&chls=2"
				+ "&chtt="
				+ CodigobarrasControle.get().getCorrente().getNome()
						.replace(' ', '+'); // tamanho da linha
		
		Log.e("url", url);

		return url;
	}

	private void inicializaMenu() {
		ArrayList<ItemListView> itens = new ArrayList<ItemListView>();
		ItemListView item1 = new ItemListView("Trocar Estab.",
				R.drawable.change);

		itens.add(item1);

		Item_ListViewAdapter adapterListView = new Item_ListViewAdapter(this,
				itens);

		lsViewMenu.setAdapter(adapterListView);
	}

	private void eventoItemMenu() {
		lsViewMenu.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 0:
					eventoTrocaEstab();
					break;
				}

				animacaoSaida(menu);
			}
		});
	}

	private void eventoMenuBt() {
		menuBt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (menu.getVisibility() == View.GONE)
					animacaoEntrada(menu);
				else
					animacaoSaida(menu);
			}
		});
	}

	private void animacaoEntrada(View objeto) {
		Animation in = AnimationUtils.makeInAnimation(getApplicationContext(),
				false);

		objeto.startAnimation(in);

		objeto.setVisibility(View.VISIBLE);
	}

	@SuppressWarnings("unchecked")
	private void ordA_Z() {
		Collections.sort(itens, new ComparatorEstabelecimento(true, "Nome"));
		adapterListView.notifyDataSetChanged();
	}

	@SuppressWarnings("unchecked")
	private void ordZ_A() {
		Collections.sort(itens, new ComparatorEstabelecimento(false, "Nome"));
		adapterListView.notifyDataSetChanged();
	}

	private void eventoTrocaEstab() {
		// cria o dialogo
		dialog.setContentView(R.layout.dialo_layout);
		dialog.setTitle("Estabelecimentos");

		ordA_Z = (ImageView) dialog.findViewById(R.id.orda);
		ordZ_A = (ImageView) dialog.findViewById(R.id.ordz);
		lsViewEstab = (ListView) dialog.findViewById(R.id.lsViewEstab);

		itens = new ArrayList<ItemListView>();

		for (int i = 0; i < CodigobarrasControle.get().getEstabelecimentos()
				.size(); i++) {
			itens.add(new ItemListView(CodigobarrasControle.get()
					.getEstabelecimentos().get(i).getNome(), Cache.get()
					.getImagem(
							CodigobarrasControle.get().getEstabelecimentos()
									.get(i).getLogo())));

		}

		adapterListView = new Item_ListViewAdapter(this, itens);
		lsViewEstab.setAdapter(adapterListView);

		ordA_Z();

		eventoItemEstabelecimentos();
		eventosOrdenacao();

		// mostrar dialogo na tela
		dialog.show();

	}

	private void eventosOrdenacao() {
		ordA_Z.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				ordA_Z();
			}
		});

		ordZ_A.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				ordZ_A();
			}
		});
	}

	private void eventoItemEstabelecimentos() {
		lsViewEstab.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				CodigobarrasControle.get().setEstabelecimentoCorrente(arg2);

				dialog.dismiss();

				myWebView.clearView();
				myWebView.loadUrl(geraURL());
			}
		});
	}

	private void animacaoSaida(View objeto) {
		Animation in = AnimationUtils.makeOutAnimation(getApplicationContext(),
				true);

		objeto.startAnimation(in);

		objeto.setVisibility(View.GONE);
	}

	private void limpaParametros() {
		datas = "";
		precos = "";
		datasPos = "";
		precosPos = "";
		precoMaior = new BigDecimal(0);
		precoMenor = new BigDecimal(0);
		dataMaior = new Date();
		dataMenor = new Date();
		limitesData = "";
		limitesPreco = "";
		
		pk = new HistoricoPrecoPK();
		pk.setIdCodigoBarras(CodigobarrasControle.get().getCorrente()
				.getIdCodigoBarras());
		pk.setIdEstabelecimento(CodigobarrasControle.get()
				.getEstabelecimentos()
				.get(CodigobarrasControle.get().getEstabelecimentoCorrente())
				.getIdEstabelecimento());

		historicoList = Conexao.getReference().buscarHistoricoPreco(pk);
	}

	private void inicializaObjetos() {

		myWebView = (WebView) findViewById(R.id.webview);
		voltarBt = (ImageView) findViewById(R.id.voltarGrafico);
		menu = (RelativeLayout) findViewById(R.id.menuGrafico);
		lsViewMenu = (ListView) findViewById(R.id.listMenuGrafico);
		menuBt = (ImageView) findViewById(R.id.menuBtGrafico);
		dialog = new Dialog(GraficoActivity.this);
	}

}

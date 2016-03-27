package android.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import layouts.ItemListView;
import layouts.Item_ListViewAdapter;
import Modelos.Codigobarras;
import Modelos.Produto;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import conexao.Conexao;
import controle.Cache;
import controle.ComparatorEstabelecimento;
import controle.Visual.CodigobarrasControle;

public class CodigobarrasActivity extends Activity {

	private TextView cNome;
	private TextView cMarca;
	private TextView cPrecoUnidade;
	private TextView cPrecoDecimal;
	private TextView cDepartamento;
	private TextView cSubcategoria;
	private TextView cLocalizacao;
	private TextView cDescricao;
	private TextView cDescricao2;
	private ImageView fotoProduto;
	private ImageView menuBt;
	private ImageView voltarBt;
	private ImageView ordA_Z;
	private ImageView ordZ_A;
	private ImageView ordMaior;
	private ImageView ordMenor;
	private RelativeLayout menu;
	private ListView lsViewEstab;
	private Dialog dialog;

	private ListView lsViewMenu;
	private ArrayList<ItemListView> itens;
	private Item_ListViewAdapter adapterListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.produto);

		inicializaObjetos();
		inicializaMenu();

		atualizaCodigoBarras();
		atualizaProduto();

		// eventos
		eventoMenuBt();
		eventoVoltar();
		eventoItemMenu();
	}

	private void inicializaMenu() {
		ArrayList<ItemListView> itens = new ArrayList<ItemListView>();
		ItemListView item1 = new ItemListView("Listas", R.drawable.adicionar);
		ItemListView item2 = new ItemListView("Gráficos", R.drawable.graficos);
		ItemListView item3 = new ItemListView("Comentários",
				R.drawable.comentarios);
		ItemListView item4 = new ItemListView("Trocar Estab.",
				R.drawable.change);

		itens.add(item1);
		itens.add(item2);
		itens.add(item3);
		itens.add(item4);

		Item_ListViewAdapter adapterListView = new Item_ListViewAdapter(this,
				itens);

		lsViewMenu.setAdapter(adapterListView);
	}

	private void eventoItemMenu() {
		lsViewMenu.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 1:
					Intent intent = new Intent();

					intent.setClassName("android.activity",
							"android.activity.GraficoActivity");

					startActivity(intent);
					break;
				case 3:
					eventoTrocaEstab();
					break;
				}

				animacaoSaida(menu);
			}
		});
	}

	private void eventoVoltar() {
		voltarBt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
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

	private void animacaoSaida(View objeto) {
		Animation in = AnimationUtils.makeOutAnimation(getApplicationContext(),
				true);

		objeto.startAnimation(in);

		objeto.setVisibility(View.GONE);
	}

	private void eventoTrocaEstab() {
		// cria o dialogo
		dialog.setContentView(R.layout.dialo_layout);
		dialog.setTitle("Estabelecimentos");

		ordA_Z = (ImageView) dialog.findViewById(R.id.orda);
		ordZ_A = (ImageView) dialog.findViewById(R.id.ordz);
		ordMaior = (ImageView) dialog.findViewById(R.id.ordmaior);
		ordMenor = (ImageView) dialog.findViewById(R.id.ordmenor);
		lsViewEstab = (ListView) dialog.findViewById(R.id.lsViewEstab);

		itens = new ArrayList<ItemListView>();

		for (int i = 0; i < CodigobarrasControle.get().getEstabelecimentos().size(); i++) {
			try {
				itens.add(new ItemListView(CodigobarrasControle.get().getEstabelecimentos().get(i).getNome(), 
						                   CodigobarrasControle.get().getProdutos().get(i).getPreco().toString(), 
						                   Cache.get().getImagem(CodigobarrasControle.get().getEstabelecimentos().get(i).getLogo())));

			} catch (Exception e) {
				itens.add(new ItemListView(CodigobarrasControle.get().getEstabelecimentos()
						.get(i).getNome(), Cache.get().getImagem(
						CodigobarrasControle.get().getEstabelecimentos().get(i).getLogo())));
			}
		}

		adapterListView = new Item_ListViewAdapter(this, itens);
		lsViewEstab.setAdapter(adapterListView);

		ordA_Z();

		eventoItemEstabelecimentos();
		eventosOrdenacao();

		// mostrar dialogo na tela
		dialog.show();

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

	@SuppressWarnings("unchecked")
	private void ordMaior() {
		Collections.sort(itens, new ComparatorEstabelecimento(false, "Preco"));
		adapterListView.notifyDataSetChanged();
	}

	@SuppressWarnings("unchecked")
	private void ordMenor() {
		Collections.sort(itens, new ComparatorEstabelecimento(true, "Preco"));
		adapterListView.notifyDataSetChanged();
	}

	private void eventosOrdenacao() {
		ordA_Z.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ordA_Z();
			}
		});

		ordZ_A.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ordZ_A();
			}
		});

		ordMenor.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ordMenor();
			}
		});

		ordMaior.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ordMaior();
			}
		});
	}

	private void eventoItemEstabelecimentos() {
		lsViewEstab.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				CodigobarrasControle.get().setEstabelecimentoCorrente(arg2);

				dialog.dismiss();

				atualizaProduto();
			}
		});
	}

	private void atualizaProduto() {
		try {
			Produto produto = CodigobarrasControle.get().getProdutos().get(
					CodigobarrasControle.get().getEstabelecimentoCorrente());
			Log.e("preco", produto.getPreco().toString());
			cPrecoUnidade.setText(produto.getPreco()
					.setScale(0, BigDecimal.ROUND_HALF_DOWN).toString());
			cPrecoDecimal.setText(String.format(
					",%02d",
					Integer.parseInt(produto.getPreco()
							.remainder(BigDecimal.ONE)
							.setScale(2, BigDecimal.ROUND_HALF_UP)
							.movePointRight(2).toString())));
			cLocalizacao.setText(produto.getLocalizacao());
			cDescricao2.setText(produto.getDescricao());
		} catch (Exception e) {
			cPrecoUnidade.setText("");
			cPrecoDecimal.setText("-----");
			cLocalizacao.setText("-----");
			cDescricao2.setText("");
		}
	}

	private void atualizaCodigoBarras() {

		cNome.setText(CodigobarrasControle.get().getCorrente().getNome());

		cMarca.setText(CodigobarrasControle.get().getCorrente().getIdMarca().getNome());

		cDescricao.setText(CodigobarrasControle.get().getCorrente().getDescricao());

		cDepartamento.setText(CodigobarrasControle.get().getCorrente().getIdSubdepartamento().getIdDepartamento()
				.getNome());
		cSubcategoria.setText(CodigobarrasControle.get().getCorrente().getIdSubdepartamento().getNome());
	}

	private void inicializaObjetos() {
		
		CodigobarrasControle.get().setCorrente((Codigobarras) Conexao.xStream.fromXML(getIntent()
						.getStringExtra("Codigobarras")));

		// objetos android
		cNome = (TextView) findViewById(R.id.nomeProduto);
		cMarca = (TextView) findViewById(R.id.marca);
		cDescricao = (TextView) findViewById(R.id.descricao);
		cDescricao2 = (TextView) findViewById(R.id.descricao2);
		cPrecoUnidade = (TextView) findViewById(R.id.cPrecoUnidade);
		cPrecoDecimal = (TextView) findViewById(R.id.cPrecoDecimal);
		cDepartamento = (TextView) findViewById(R.id.categoria);
		cSubcategoria = (TextView) findViewById(R.id.subcategoria);
		cLocalizacao = (TextView) findViewById(R.id.localizacao);
		fotoProduto = (ImageView) findViewById(R.id.fotoProduto);
		voltarBt = (ImageView) findViewById(R.id.voltarProduto);
		menuBt = (ImageView) findViewById(R.id.menuBtCodBarras);
		menu = (RelativeLayout) findViewById(R.id.menuCodBarras);
		lsViewMenu = (ListView) findViewById(R.id.menuProduto);
		dialog = new Dialog(CodigobarrasActivity.this);
		fotoProduto.setImageDrawable(CodigobarrasControle.get().getImagem());
	}
}
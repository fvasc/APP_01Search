package android.activity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimeZone;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.Period;

import layouts.ItemListView;
import layouts.ItemListaExpandableListAdapter;
import layouts.Item_ListViewAdapter;
import Modelos.Codigobarras;
import Modelos.CodigobarrasItem;
import Modelos.CodigobarrasItemPK;
import Modelos.Estabelecimento;
import Modelos.Item;
import Modelos.ItemLista;
import Modelos.ItemListaPK;
import Modelos.Lista;
import Modelos.UnidadeMedida;
import Modelos.UsuarioItem;
import Modelos.UsuarioItemPK;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ImageView;
import android.widget.TextView;
import conexao.Conexao;
import controle.Cache;
import controle.ComparatorEstabelecimento;
import controle.ComparatorNome;
import controle.Sessao;
import controle.Visual.ItemListaControle;

public class ItemListaActivity extends Activity {

	private ImageView imageVoltar;
	private ImageView imageMenu;
	private ImageView imageSuper;
	private ImageView imageAdd;
	private ImageView imageMais;
	private ImageView imageMenos;
	private ImageView imageUpDown;
	private ImageView imageFinalizarCompra;
	private ImageView ordA_Z;
	private ImageView ordZ_A;
	private ImageView ordMaior;
	private ImageView ordMenor;
	private TextView textNomeLista;
	private TextView textPreco;
	private TextView textItemSelecionado;
	private AutoCompleteTextView textAutoComplete;
	private ArrayAdapter<String> arrAdapterAutoComplete;
	private ExpandableListView expandableLista;
	private Lista lista;
	private Item_ListViewAdapter adapterListView;
	private ListView lsViewEstab;
	private ArrayList<Codigobarras> arrCodigobarrasQuantidade;
	private ArrayList<ItemLista> arrItemLista;
	private ArrayList<ArrayList<Codigobarras>> arrCodigobarras;
	private ArrayList<Item> arrItemDiferentes;
	private ArrayList<String> arrItemDaListaNome;
	private ArrayList<String> arrItemDiferentesNome;
	private ArrayList<Estabelecimento> arrEstabelecimentos;
	private ArrayList<ItemListView> arrItemListView;
	private ItemListaExpandableListAdapter customExpandableListAdapter;
	private BigDecimal valorCompra;
	private int grupoIndex;
	private int childIndex;
	private int qtdComprada;
	private int transient_qtdDaLista;
	private int transient_peridoItem;
	private int transient_peridoLista;
	private ItemLista itemLista;
	private Codigobarras codigobarras;
	private RelativeLayout menu;
	private RelativeLayout relativeOrdNome;
	private ListView lsViewMenu;
	private Boolean crescente;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemlista);

		
		inicializarObjetos();
		verificarStatusConexao();
		voltar();
		expandableListeners();
		quantidadeListeners();
		autoCompleteListers();
		addItemListaListener();
		imageSuperListener();
		relativeOrdernarListstener();
		inicializaMenu();
		MenuListener();
		eventoItemMenu();
		// carregarValorCompra();
		finalizarCompraListener();
		// addItem();
		// eventoButtonCriar();

	}


	private void finalizarCompraListener() {
		imageFinalizarCompra.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				finalizarCompra();

			}
		});
	}

	protected void finalizarCompra() {
		ItemListaControle.get().adicionarHistorico(arrItemLista,
				arrCodigobarras, Sessao.estabelecimento);

	}

	private void relativeOrdernarListstener() {
		relativeOrdNome.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				ordenarItemLista();
			}
		});

	}

	protected void ordenarItemLista() {

		if (crescente) {
			Collections.sort(arrItemLista, new ComparatorNome<ItemLista>(crescente));
			crescente = false;
			imageUpDown.setImageResource(R.drawable.up);
		} else {
			Collections.sort(arrItemLista, new ComparatorNome<ItemLista>(crescente));
			crescente = true;
			imageUpDown.setImageResource(R.drawable.down);
		}
		Collections.reverse(arrCodigobarras);
		customExpandableListAdapter.notifyDataSetChanged();
	}

	private void eventoItemMenu() {
		lsViewMenu.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 1:

					break;
				case 2:
					eventoTrocaEstab();
					break;
				}
				animacaoSaida(menu);
			}
		});
	}

	private void inicializaMenu() {
		ArrayList<ItemListView> itens = new ArrayList<ItemListView>();
		ItemListView item1 = new ItemListView("Trocar Estab.",
				R.drawable.change);
		ItemListView item2 = new ItemListView("Finalizar", R.drawable.accept);
		itens.add(item1);
		itens.add(item2);

		Item_ListViewAdapter adapterListView = new Item_ListViewAdapter(this,
				itens);

		lsViewMenu.setAdapter(adapterListView);
	}

	private void MenuListener() {
		imageMenu.setOnClickListener(new OnClickListener() {
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

	private void imageSuperListener() {
		imageSuper.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				eventoTrocaEstab();

			}
		});

	}

	private void eventoTrocaEstab() {

		ProgressDialog progressDialog;
		progressDialog = ProgressDialog.show(ItemListaActivity.this, "", "Carregando...");
		arrEstabelecimentos = ItemListaControle.get().buscarEstabelecimento(
				arrCodigobarrasQuantidade);

		// cria o dialogo
		final Dialog dialog = new Dialog(ItemListaActivity.this);
		dialog.setContentView(R.layout.dialo_layout);
		dialog.setTitle("Estabelecimentos");

		ordA_Z = (ImageView) dialog.findViewById(R.id.orda);
		ordZ_A = (ImageView) dialog.findViewById(R.id.ordz);
		ordMaior = (ImageView) dialog.findViewById(R.id.ordmaior);
		ordMenor = (ImageView) dialog.findViewById(R.id.ordmenor);
		lsViewEstab = (ListView) dialog.findViewById(R.id.lsViewEstab);

		arrItemListView = new ArrayList<ItemListView>();

		// criar visual do dialogo com os estabelecimentos e o preco da lista
		for (int i = 0; i < arrEstabelecimentos.size(); i++) {
			try {
				arrItemListView.add(new ItemListView(arrEstabelecimentos.get(i)
						.getNome(), "R$ "
						+ arrEstabelecimentos.get(i).getPrecoListaCorrente()
								.toString(), Cache.get().getImagem(
						arrEstabelecimentos.get(i).getLogo())));

			} catch (Exception e) {
				arrItemListView.add(new ItemListView(arrEstabelecimentos.get(i)
						.getNome(), Cache.get().getImagem(
						arrEstabelecimentos.get(i).getLogo())));
			}
		}

		adapterListView = new Item_ListViewAdapter(this, arrItemListView);
		lsViewEstab.setAdapter(adapterListView);

		// ordernar lista
		ordernarEstabelecimento("A_Z");

		// atualizar visual
		adapterListView.notifyDataSetChanged();

		eventosOrdenacaoEstabelecimento();

		lsViewEstab.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int index,
					long arg3) {

				dialog.dismiss();

				// se mudar o estabelecimento
				if (!Sessao.estabelecimento
						.getNome()
						.toString()
						.equals(arrItemListView.get(index).getTexto()
								.toString())) {

					for (Estabelecimento e : arrEstabelecimentos) {

						// quando o nome for igual
						if (e.getNome().equals(
								arrItemListView.get(index).getTexto())) {

							// setar estabelecimento
							Sessao.estabelecimento = arrEstabelecimentos
									.get(index);

							// mudar logo
							imageSuper.setImageDrawable(Cache.get().getImagem(
									e.getLogo()));
						}
					}

					// buscar novos precos
					arrCodigobarras = ItemListaControle.get()
							.buscarCodigobarrasPreco(arrCodigobarras,
									Sessao.estabelecimento);
					customExpandableListAdapter.setList_filho(arrCodigobarras);
					customExpandableListAdapter.notifyDataSetChanged();

					// mudar valor da compra
					textPreco.setText("R$ "
							+ Sessao.estabelecimento.getPrecoListaCorrente()
									.setScale(2, BigDecimal.ROUND_HALF_UP)
									.toString());

				}
			}
		});

		// mostrar dialogo na tela
		dialog.show();
		
		progressDialog.dismiss();

	}

	protected void carregarValorCompra() {

		arrCodigobarrasQuantidade = new ArrayList<Codigobarras>();
		valorCompra = new BigDecimal("0");

		for (int a = 0; a < arrCodigobarras.size(); a++) {
			for (int b = 0; b < arrCodigobarras.get(a).size(); b++) {

				BigDecimal preco = arrCodigobarras.get(a).get(b)
						.getPrecoCorrente();
				BigDecimal vezes = new BigDecimal(arrCodigobarras.get(a).get(b)
						.getQtdComprada());
				BigDecimal multiplicacao = preco.multiply(vezes);

				valorCompra = valorCompra.add(multiplicacao);

				// array para criar valor da compra
				if (vezes != new BigDecimal("0")) {

					String idCodigobarras = arrCodigobarras.get(a).get(b)
							.getIdCodigoBarras();
					Integer quantidade = arrCodigobarras.get(a).get(b)
							.getQtdComprada();

					Codigobarras c = new Codigobarras();
					c.setIdCodigoBarras(idCodigobarras);
					c.setQtdComprada(quantidade);

					arrCodigobarrasQuantidade.add(c);
				}

			}
		}
		textPreco.setText("R$ "
				+ valorCompra.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	}

	@SuppressWarnings("unchecked")
	private void ordernarEstabelecimento(String ordem) {

		if (ordem.equals("A_Z")) {
			Collections.sort(arrItemListView, new ComparatorEstabelecimento(
					true, "Nome"));
		} else if (ordem.equals("Z_A")) {
			Collections.sort(arrItemListView, new ComparatorEstabelecimento(
					false, "Nome"));
		} else if (ordem.equals("MAIOR")) {
			Collections.sort(arrItemListView, new ComparatorEstabelecimento(
					false, "Preco"));
		} else if (ordem.equals("MENOR")) {
			Collections.sort(arrItemListView, new ComparatorEstabelecimento(
					true, "Preco"));
		}
	}

	private void eventosOrdenacaoEstabelecimento() {
		ordA_Z.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ordernarEstabelecimento("A_Z");
				adapterListView.notifyDataSetChanged();
			}
		});

		ordZ_A.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ordernarEstabelecimento("Z_A");
				adapterListView.notifyDataSetChanged();
			}
		});

		ordMenor.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ordernarEstabelecimento("MENOR");
				adapterListView.notifyDataSetChanged();
			}
		});

		ordMaior.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				ordernarEstabelecimento("MAIOR");
				adapterListView.notifyDataSetChanged();
			}
		});
	}

	private void inicializarObjetos() {

		imageVoltar = (ImageView) findViewById(R.id.ItemListaImageViewVoltar);
		imageMenu = (ImageView) findViewById(R.id.ItemListaImageViewMenu);
		imageSuper = (ImageView) findViewById(R.id.ItemListaImageViewSuper);
		imageAdd = (ImageView) findViewById(R.id.itemlista_add);
		imageMais = (ImageView) findViewById(R.id.itemlista_mais);
		imageMenos = (ImageView) findViewById(R.id.itemlista_menos);
		imageUpDown = (ImageView) findViewById(R.id.itemListaImageViewUpOrDown);
		imageFinalizarCompra = (ImageView) findViewById(R.id.ItemListaFinalizarCompra);
		textNomeLista = (TextView) findViewById(R.id.ItemListaNomeLista);
		textPreco = (TextView) findViewById(R.id.ItemListaTextViewPreco);
		textItemSelecionado = (TextView) findViewById(R.id.ItemListaItemSelecionado);
		textAutoComplete = (AutoCompleteTextView) findViewById(R.id.ItemListaautoComplete);
		expandableLista = (ExpandableListView) findViewById(R.id.itemListaExpandableList);
		menu = (RelativeLayout) findViewById(R.id.itemListaMenuRelative);
		relativeOrdNome = (RelativeLayout) findViewById(R.id.itemListaRelativeOrdNome);
		lsViewMenu = (ListView) findViewById(R.id.itemListaMenuListView);

		// ordenar
		crescente = false;

		// nome da lista
		lista = (Lista) Conexao.xStream.fromXML(getIntent().getStringExtra(
				"Lista"));
		textNomeLista.setText(lista.getNome());

		// tirar foco do editfild
		tirarFocoAutoComplete();

	}

	private void verificarStatusConexao() {

		if (Sessao.isConectado) {

			buscarServidor();
			carregarItemLista(ItemListaActivity.this, arrItemLista,
					arrCodigobarras, expandableLista);
		} else {

		}
	}

	private void buscarServidor() {

		// mudar visualizacao do listview
		arrItemLista = ItemListaControle.get().buscarItemLista(lista);

		// ordenar crescente para buscar codigobarras na ordem correta
		Collections.sort(arrItemLista, new ComparatorNome(true));

		// buscar codigobarras
		arrCodigobarras = ItemListaControle.get().buscarCodigobarras(
				arrItemLista, Sessao.estabelecimento);

		arrItemDiferentes = buscarItensNaoAdicionados();

	}

	private ArrayList<Item> buscarItensNaoAdicionados() {

		// busca todos os itens do usuario
		ArrayList<Item> array = ItemListaControle.get().buscarItem(
				Sessao.usuario);
		ArrayList<Item> daLista = new ArrayList<Item>();

		// separa os itens nao adicionados dos adicionados
		for (ItemLista itemLista : arrItemLista) {
			array.remove(itemLista.getItem());
			daLista.add(itemLista.getItem());
		}

		// cria o array de nomes pro autoComplete
		arrItemDiferentesNome = new ArrayList<String>();
		for (Item i : array) {
			arrItemDiferentesNome.add(i.getNome());
		}
		arrItemDaListaNome = new ArrayList<String>();
		for (Item i : daLista) {
			arrItemDaListaNome.add(i.getNome());
		}

		return array;
	}

	private void voltar() {
		imageVoltar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				finish();
			}
		});
	}

	private void expandableListeners() {

		expandableLista.setOnGroupClickListener(new OnGroupClickListener() {

			public boolean onGroupClick(ExpandableListView arg0, View arg1,
					int groupPosition, long id) {

				// tirar foco do editfild
				tirarFocoAutoComplete();

				// expandir a lista qnd estiver fechada
				//expandableLista.expandGroup(groupPosition);

				grupoIndex = groupPosition;
				childIndex = -1;
				itemSelecionado();
				return false;
			}
		});

		expandableLista
				.setOnGroupCollapseListener(new OnGroupCollapseListener() {

					public void onGroupCollapse(int groupPosition) {

						// tirar foco do editfild
						tirarFocoAutoComplete();

						grupoIndex = groupPosition;
						childIndex = -1;
						itemSelecionado();
					}
				});

		expandableLista.setOnChildClickListener(new OnChildClickListener() {

			public boolean onChildClick(ExpandableListView arg0, View arg1,
					int groupPosition, int childPosition, long id) {

				// tirar foco do editfild
				tirarFocoAutoComplete();

				grupoIndex = groupPosition;
				childIndex = childPosition;
				System.out.println("GROUP " + grupoIndex + "CHILD "
						+ childPosition);
				itemSelecionado();
				return true;
			}
		});

	}

	private void quantidadeListeners() {
		imageMais.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				// tirar foco do editfild
				tirarFocoAutoComplete();

				somarQuantidadeComprada(1);

				carregarValorCompra();

			}
		});

		imageMenos.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// tirar foco do editfild
				tirarFocoAutoComplete();

				somarQuantidadeComprada(-1);

				carregarValorCompra();

			}
		});

	}

	protected void somarQuantidadeComprada(int quantidade) {

		int comprado = 0;
		
		
		if (childIndex != -1) {

			// codigobarras
			comprado = arrCodigobarras.get(grupoIndex).get(childIndex)
					.getQtdComprada();
			int soma = comprado + quantidade;
			if (soma < 0) {
				soma = 0;
			}
			// mudar na lista a quantidade que foi comprada
			arrCodigobarras.get(grupoIndex).get(childIndex)
					.setQtdComprada(soma);
			codigobarras.setQtdComprada(soma);
			
		}

		else {

			// itemlista
			comprado = arrItemLista.get(grupoIndex).getQtdComprada();
			int soma = comprado + quantidade;
			if (soma < 0) {
				soma = 0;
			}
			arrItemLista.get(grupoIndex).setQtdComprada(soma);
			itemLista.setQtdComprada(soma);
		}

		// setar nova duraçao
		String data_restante = calcularDuracao(quantidade);
		
		
		arrItemLista.get(grupoIndex).setData_qtdRestante(data_restante);
		itemLista.setData_qtdRestante(data_restante);

		// atualizar lista
		customExpandableListAdapter.notifyDataSetChanged();

		// somar no valor da compra
		//valorCompra(quantidade);
	}

	private String calcularDuracao(int quantidade) {

		LocalDate date;
		Integer restante;
		
		if (!itemLista.getData_qtdRestante().equals("") && !itemLista.getData_qtdRestante().equals("0000-00-00-0")) {
		

			String[] data_restante = itemLista.getData_qtdRestante().split("-");

			restante = Integer.parseInt(data_restante[3]);
			
			date = new LocalDate(Integer.parseInt(data_restante[0]),
					Integer.parseInt(data_restante[1]), Integer.parseInt(data_restante[2]));

		} else {
		
			date = LocalDate.now(DateTimeZone.forTimeZone(TimeZone
					.getTimeZone("America/Sao_Paulo")));
			
			date = date.plus(Period.days(-1));
			
			restante = 0;
			
		}
		System.err.println();
		System.err.println();
		System.err.println("restante "+ restante);
		System.err.println("quantidade "+ quantidade);
		System.err.println("peridicidade "+itemLista.getPeriodicidade());
		System.err.println();
		
		Integer dias = quantidade * itemLista.getPeriodicidade(); 
		
		
		System.err.println("dias "+ dias);
		System.out.println("ANTES " +date.getDayOfMonth());
		
		date = date.plus(Period.days(dias));
		
		System.out.println("DEPOIS "+date.getDayOfMonth());
	
		System.out.println();
		
		restante += quantidade;
		if(restante < 0){
			restante = 0;
		}

		String retorno = date.getYear()+"-"+date.getMonthOfYear()+"-"+date.getDayOfMonth()+"-"+restante;
		System.out.println("TESTE RETORNMO "+retorno);
		
		return retorno;

	}

/*	private void valorCompra(int quantidade) {

		if (childIndex != -1) {

		} else {

		}
	}*/

	private void autoCompleteListers() {

		arrAdapterAutoComplete = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line,
				arrItemDiferentesNome);
		textAutoComplete.setAdapter(arrAdapterAutoComplete);
		textAutoComplete.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {
				// fazer mascara
				/*
				 * String current = s.toString(); String letra =
				 * current.replaceAll("[^a-z]*", "");
				 */
			}
		});
	}

	private void addItemListaListener() {
		imageAdd.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (verificarItemJaAdicionado()) {

					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
							ItemListaActivity.this);
					myAlertDialog.setTitle("Alertar");
					myAlertDialog.setMessage("Esse item já foi adicionado");
					myAlertDialog.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
								}
							});
					myAlertDialog.show();

				} else if (textAutoComplete.getText().toString().equals("")) {

				} else {
					dialogoQtdLista(textAutoComplete.getText().toString());
				}
			}
		});

	}

	protected boolean verificarItemJaAdicionado() {

		boolean bool = arrItemDaListaNome.contains(textAutoComplete.getText()
				.toString());

		return bool;
	}

	private void dialogoQtdLista(CharSequence nome) {

		final Dialog dialog = new Dialog(ItemListaActivity.this);
		dialog.setContentView(R.layout.dialo_add_itemlista);
		dialog.setTitle("Configurações para " + nome + ": ");
		dialog.setCancelable(true);

		// editViews do dialogo-----------------------------------
		final EditText qtdComprada = (EditText) dialog
				.findViewById(R.id.dialo_itemlista_comprada_edit);
		qtdComprada.setText(1 + "");

		final EditText qtdLista = (EditText) dialog
				.findViewById(R.id.dialo_itemlista_lista_edit);
		qtdLista.setText(1 + "");

		final EditText qtdItem = (EditText) dialog
				.findViewById(R.id.dialo_itemlista_item_edit);
		qtdItem.setText(1 + "");

		// layout da lista
		final LinearLayout layout_lsita = (LinearLayout) dialog
				.findViewById(R.id.dialo_itemlista_dur_listalayout);

		// textview de opçao
		TextView textview = (TextView) dialog
				.findViewById(R.id.dialo_itemlista_textview);

		// verificar se o item ja existe
		final int index = arrItemDiferentesNome.indexOf(nome);

		if (index != -1) {
			// entrada para item ja existente

			// esconder opcao de duracao do item
			final RelativeLayout layout = (RelativeLayout) dialog
					.findViewById(R.id.dialo_itemlista_dur_itemlayout);
			layout.setVisibility(LinearLayout.INVISIBLE);

			Item itemCorrente = arrItemDiferentes.get(index);

			textview.setText("Duração especifica para esta lista?");
			// TESTEAR CENTER
			textview.setPadding(10, 0, 0, 0);

		} else {
			// entrada para novo item

			// esconder opçao de radios
			final LinearLayout layout = (LinearLayout) dialog
					.findViewById(R.id.dialo_itemlista_radiolayout);
			layout.setVisibility(LinearLayout.INVISIBLE);

			// mostrar layout lsita
			layout_lsita.setVisibility(layout_lsita.VISIBLE);

		}

		// radio buttonsss -------------------------------------
		final RadioButton rNao = (RadioButton) dialog
				.findViewById(R.id.dialo_itemlista_radionao);
		final RadioButton rSim = (RadioButton) dialog
				.findViewById(R.id.dialo_itemlista_radiosim);

		// radio sim-------------------------------------------
		rSim.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					rNao.setChecked(false);
					layout_lsita.setVisibility(layout_lsita.VISIBLE);

				}

			}
		});

		// rSim.seto-------------------------------------------
		rNao.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {

				if (isChecked) {
					layout_lsita.setVisibility(layout_lsita.GONE);
					rSim.setChecked(false);
				}
			}
		});

		// aumentar quantidade-------------------------------------------
		Button maisQtd = (Button) dialog
				.findViewById(R.id.dialo_itemlista_comprada_up);
		maisQtd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				try {
					qtdComprada.setText(String.valueOf(Integer
							.parseInt(qtdComprada.getText().toString()) + 1));
				} catch (Exception e) {
					qtdComprada.setText("1");
				}
			}
		});

		Button maisItem = (Button) dialog
				.findViewById(R.id.dialo_itemlista_dur_item_up);
		maisItem.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				try {
					qtdItem.setText(String.valueOf(Integer.parseInt(qtdItem
							.getText().toString()) + 1));
				} catch (Exception e) {
					qtdItem.setText("1");
				}
			}
		});

		Button maisLista = (Button) dialog
				.findViewById(R.id.dialo_itemlista_dur_lista_up);
		maisLista.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				try {
					qtdLista.setText(String.valueOf(Integer.parseInt(qtdLista
							.getText().toString()) + 1));
				} catch (Exception e) {
					qtdLista.setText("1");
				}
			}
		});

		// diminuir quatnidade-------------------------------------------
		Button menosQtd = (Button) dialog
				.findViewById(R.id.dialo_itemlista_comprada_down);
		menosQtd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					if (Integer.parseInt(qtdComprada.getText().toString()) - 1 != 0) {
						qtdComprada.setText(String.valueOf(Integer
								.parseInt(qtdComprada.getText().toString()) - 1));
					}
				} catch (Exception e) {
					qtdComprada.setText("1");
				}
			}
		});

		Button menosItem = (Button) dialog
				.findViewById(R.id.dialo_itemlista_dur_item_down);
		menosItem.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					if (Integer.parseInt(qtdItem.getText().toString()) - 1 != 0) {
						qtdItem.setText(String.valueOf(Integer.parseInt(qtdItem
								.getText().toString()) - 1));
					}
				} catch (Exception e) {
					qtdItem.setText("1");
				}
			}
		});

		Button menosLista = (Button) dialog
				.findViewById(R.id.dialo_itemlista_dur_lista_down);
		menosLista.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					if (Integer.parseInt(qtdLista.getText().toString()) - 1 != 0) {
						qtdLista.setText(String.valueOf(Integer
								.parseInt(qtdLista.getText().toString()) - 1));
					}
				} catch (Exception e) {
					qtdLista.setText("1");
				}
			}
		});

		// cancelar dialogo-------------------------------------------
		Button cancelarr = (Button) dialog
				.findViewById(R.id.dialoItemListaCancelar);
		cancelarr.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// confirmar dialogo-------------------------------------------
		Button ok = (Button) dialog.findViewById(R.id.dialoItemListaOk);
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if (index != -1) {
					// entrada para item ja existente

					transient_peridoItem = arrItemDiferentes.get(index)
							.getPeriodicidade();

					// verififcar se vai utilizar duracao para lista
					if (rSim.isChecked()) {

						// utilizar o periodo especifico
						transient_peridoLista = Integer.parseInt(qtdLista
								.getText().toString());
					} else {

						// utilizar o periodo padrao
						transient_peridoLista = transient_peridoItem;
					}

				} else {
					// entrada para NOVO item
					transient_peridoItem = Integer.parseInt(qtdItem.getText()
							.toString());
					transient_peridoLista = Integer.parseInt(qtdLista.getText()
							.toString());

				}

				int qtdEscolhida = Integer.parseInt(qtdComprada.getText()
						.toString());

				transient_qtdDaLista = qtdEscolhida;

				dialog.dismiss();

				adicionarItemLista(index);
			}
		});

		dialog.show();
	}

	protected void adicionarItemLista(int index) {

		// setar lista no item lista
		ItemListaPK ilPK = new ItemListaPK();
		ilPK.setIdLista(lista.getIdLista());

		// quantidade do item
		ItemLista il = new ItemLista();
		il.setQuantidade(transient_qtdDaLista);
		il.setUnidadeMedida(new UnidadeMedida(1));
		il.setQtdComprada(0);
		il.setAtivo(true);
		il.setPeriodicidade(transient_peridoLista);
		il.setData_qtdRestante("");

		if (index != -1) {
			// entrada para item ja existente
			// buscar na lsita de item nao adicionados
			Item diferente = arrItemDiferentes.get(index);

			// retirar item nao adicionado e do auto complete
			atualizarAutoComplete(index);

			// setar novo ItemLista
			ilPK.setIdItem(diferente.getIdItem());
			il.setItemListaPK(ilPK);
			il.setItem(diferente);

		} else {
			// entrada para NOVO item
			Item item = new Item();
			item.setNome(textAutoComplete.getText().toString());
			item.setPeriodicidade(transient_peridoItem);

			// gravar no banco e para ter um id
			item = ItemListaControle.get().adicionarItem(item);

			// setando o itemLista novo
			ilPK.setIdItem(item.getIdItem());
			il.setItemListaPK(ilPK);
			il.setItem(item);

			// ligacao entre usuario e item
			UsuarioItemPK uiPK = new UsuarioItemPK();
			uiPK.setIdItem(item.getIdItem());
			uiPK.setIdUsuario(Sessao.usuario.getIdUsuario());

			UsuarioItem ui = new UsuarioItem();
			ui.setUsuarioItemPK(uiPK);

			// gravar no banco
			ItemListaControle.get().adicionarUsuarioItem(ui);

		}

		// gravar no banco
		ItemListaControle.get().adicionarItemLista(il);

		// gravar na lista
		arrItemLista.add(arrItemLista.size(), il);

		// ordernar pela forma escolhida
		Collections.sort(arrItemLista, new ComparatorNome(!crescente));

		// index para inserir codigobarras
		int indexItemLista = arrItemLista.indexOf(il);

		// buscar codigobarras
		ArrayList<ItemLista> array_itemLista = new ArrayList<ItemLista>();
		array_itemLista.add(il);
		ArrayList<ArrayList<Codigobarras>> array_Codigobarras = ItemListaControle
				.get().buscarCodigobarras(array_itemLista,
						Sessao.estabelecimento);

		// colocar o codigobarras na ordem correta
		arrCodigobarras.add(indexItemLista, array_Codigobarras.get(0));

		// limpar autoComplete
		tirarFocoAutoComplete();

		// atualizar adapter
		customExpandableListAdapter.notifyDataSetChanged();
	}

	protected void tirarFocoAutoComplete() {

		textAutoComplete.clearFocus();
		textAutoComplete.setText("");
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(textAutoComplete.getWindowToken(), 0);
	}

	protected void itemSelecionado() {

		itemLista = arrItemLista.get(grupoIndex);
		String itemSelecionado;

		if (childIndex == -1) {

			// grupo
			codigobarras = null;
			itemSelecionado = itemLista.getItem().getNome();

		} else {

			// child
			codigobarras = arrCodigobarras.get(grupoIndex).get(childIndex);
			itemSelecionado = itemLista.getItem().getNome() + " : "
					+ codigobarras.getNome();
		}

		textItemSelecionado.setText(itemSelecionado);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		if (v.getId() == R.id.itemListaExpandableList) {
			ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) menuInfo;

			int type = ExpandableListView
					.getPackedPositionType(info.packedPosition);

			int group = ExpandableListView
					.getPackedPositionGroup(info.packedPosition);

			int child = ExpandableListView
					.getPackedPositionChild(info.packedPosition);

			// item corrente
			grupoIndex = group;
			childIndex = child;
			itemSelecionado();

			// opcoes do menu para pai ou para filho
			String[] menuItems;
			if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
				menuItems = getResources().getStringArray(
						R.array.menuItemListaFilho);
				menu.setHeaderTitle(textItemSelecionado.getText());
			} else {
				menuItems = getResources().getStringArray(
						R.array.menuItemListaPai);
				menu.setHeaderTitle(textItemSelecionado.getText());
			}
			menu.clear();
			for (int i = 0; i < menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		String menuTitle = item.toString();

		// qtd comprada
		if (menuTitle.equals("Quantidade comprada")) {
			mudarQuantidadeComprada();
		}
		if (menuTitle.equals("Quantidade da lista")) {
			mudarQuantidadeDaLista();
		}
		if (menuTitle.equals("Ver o produto")) {
			irCodigobarrasActivity();
		}
		if (menuTitle.equals("Excluir")) {
			desejaExcluirItem();
		}

		return true;
	}

	private void irCodigobarrasActivity() {

		Intent intent = new Intent();

		intent.setClassName("android.activity",
				"android.activity.CodigobarrasActivity");

		intent.putExtra("Codigobarras", Conexao.xStream.toXML(codigobarras));

		startActivity(intent);
	}

	private void desejaExcluirItem() {

		String mensagem;
		if (childIndex != -1) {
			// child
			mensagem = "Deseja desvincular o Codigo de Barra ao item?";
		} else {
			// pai
			mensagem = "Deseja desvincular o Item à lista?";
		}

		AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
				ItemListaActivity.this);
		myAlertDialog.setTitle("Excluir");
		myAlertDialog.setMessage(mensagem);
		myAlertDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {

						excluirItem();
					}
				});
		myAlertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
		myAlertDialog.show();
	}

	protected void excluirItem() {

		if (childIndex != -1) {
			// child

			CodigobarrasItemPK codigobarrasItemPK = new CodigobarrasItemPK();
			codigobarrasItemPK.setIdCodigobarras(codigobarras
					.getIdCodigoBarras());
			codigobarrasItemPK
					.setIdItem(itemLista.getItemListaPK().getIdItem());

			CodigobarrasItem codigobarrasItem = new CodigobarrasItem();
			codigobarrasItem.setCodigobarrasItemPK(codigobarrasItemPK);

			ItemListaControle.get().excluirCodigobarrasItem(codigobarrasItem);

			arrCodigobarras.get(grupoIndex).remove(childIndex);

		} else {
			// pai
			ItemListaPK itemListaPK = new ItemListaPK();
			itemListaPK.setIdLista(lista.getIdLista());
			itemListaPK.setIdItem(itemLista.getItem().getIdItem());

			itemLista.setItemListaPK(itemListaPK);

			ItemListaControle.get().excluirItemLista(itemLista);

			int index = arrItemLista.indexOf(itemLista);

			// removendo item lista e codigobarras
			arrItemLista.remove(index);
			arrCodigobarras.remove(index);

			// atualizar auto complete
			atualizarAutoComplete(-1);
		}
		customExpandableListAdapter.notifyDataSetChanged();

	}

	private void atualizarAutoComplete(int index) {
		if (index != -1) {
			// adicionar o nome q faz parte da lista
			arrItemDaListaNome.add(arrItemDiferentesNome.get(index).toString());

			// retirarr do autocompelte
			arrItemDiferentes.remove(index);
			arrItemDiferentesNome.remove(index);

		} else {
			// retirar o nome q faz parte da lista
			arrItemDaListaNome.remove(itemLista.getItem().getNome().toString());

			// adcionar no autocompelte

			arrItemDiferentesNome.add(itemLista.getItem().getNome());
			arrItemDiferentes.add(itemLista.getItem());
		}

		textAutoComplete.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line,
				arrItemDiferentesNome));
	}

	protected void somarQuantidadeDaLista(int quantidade) {

		int qtdLista = 0;

		// itemlista
		qtdLista = arrItemLista.get(grupoIndex).getQuantidade();
		int soma = qtdLista + quantidade;
		if (soma < 0) {
			soma = 0;
		}
		arrItemLista.get(grupoIndex).setQuantidade(soma);
		itemLista.setQuantidade(soma);

		customExpandableListAdapter.notifyDataSetChanged();

	}

	private void mudarQuantidadeDaLista() {

		// pai
		transient_qtdDaLista = itemLista.getQuantidade();

		final Dialog dialog = new Dialog(ItemListaActivity.this);
		dialog.setContentView(R.layout.dialo);
		dialog.setTitle(textItemSelecionado.getText()
				+ "\n> Quantidade da Lista");
		dialog.setCancelable(true);

		final EditText qtd = (EditText) dialog.findViewById(R.id.editText1);
		qtd.setText(String.valueOf(transient_qtdDaLista));

		// cancelar dialogo
		Button cancelarr = (Button) dialog.findViewById(R.id.cancelarr);
		cancelarr.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// confirmar dialogo
		Button ok = (Button) dialog.findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				int qtdEscolhida = Integer.parseInt(qtd.getText().toString());
				int qtdDaListaFinal = transient_qtdDaLista;
				int soma = (qtdEscolhida - qtdDaListaFinal);

				somarQuantidadeDaLista(soma);
				dialog.dismiss();
			}
		});

		// aumentar quantidade
		Button mais = (Button) dialog.findViewById(R.id.btmais);
		mais.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					qtd.setText(String.valueOf(Integer.parseInt(qtd.getText()
							.toString()) + 1));
				} catch (Exception e) {
					qtd.setText("1");
				}
			}
		});

		// diminuir quatnidade
		Button menos = (Button) dialog.findViewById(R.id.btmenos);
		menos.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					if (Integer.parseInt(qtd.getText().toString()) - 1 != 0) {
						qtd.setText(String.valueOf(Integer.parseInt(qtd
								.getText().toString()) - 1));
					}
				} catch (Exception e) {
					qtd.setText("1");
				}
			}
		});

		dialog.show();

	}

	private void mudarQuantidadeComprada() {

		try {
			if (childIndex != -1) {
				// child
				qtdComprada = codigobarras.getQtdComprada();
			} else {
				// pai
				qtdComprada = itemLista.getQtdComprada();
			}
		} catch (Exception e) {
			qtdComprada = 0;
		}

		final Dialog dialog = new Dialog(ItemListaActivity.this);
		dialog.setContentView(R.layout.dialo);
		dialog.setTitle(textItemSelecionado.getText()
				+ "\n> Quantidade Comprada");
		dialog.setCancelable(true);

		final EditText qtd = (EditText) dialog.findViewById(R.id.editText1);
		qtd.setText(String.valueOf(qtdComprada));

		// cancelar dialogo
		Button cancelarr = (Button) dialog.findViewById(R.id.cancelarr);
		cancelarr.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// confirmar dialogo
		Button ok = (Button) dialog.findViewById(R.id.ok);
		ok.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				int qtdEscolhida = Integer.parseInt(qtd.getText().toString());
				int qtdCompradaFinal = qtdComprada;
				int soma = (qtdEscolhida - qtdCompradaFinal);

				somarQuantidadeComprada(soma);
				dialog.dismiss();
			}
		});

		// aumentar quantidade
		Button mais = (Button) dialog.findViewById(R.id.btmais);
		mais.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					qtd.setText(String.valueOf(Integer.parseInt(qtd.getText()
							.toString()) + 1));
				} catch (Exception e) {
					qtd.setText("1");
				}
			}
		});

		// diminuir quatnidade
		Button menos = (Button) dialog.findViewById(R.id.btmenos);
		menos.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					if (Integer.parseInt(qtd.getText().toString()) - 1 != 0) {
						qtd.setText(String.valueOf(Integer.parseInt(qtd
								.getText().toString()) - 1));
					}
				} catch (Exception e) {
					qtd.setText("1");
				}
			}
		});

		dialog.show();
	}

	private void carregarItemLista(Context context,
			ArrayList<ItemLista> arrayItemLista,
			ArrayList<ArrayList<Codigobarras>> arrayCodigobarras,
			ExpandableListView expandable) {
		customExpandableListAdapter = new ItemListaExpandableListAdapter();
		customExpandableListAdapter.setContext(context);
		customExpandableListAdapter.setList_pai(arrayItemLista);
		customExpandableListAdapter.setList_filho(arrayCodigobarras);
		customExpandableListAdapter.setEstabelecimento(Sessao.estabelecimento);

		ExpandableListAdapter mAdapter = customExpandableListAdapter;

		expandable.setAdapter(mAdapter);
		registerForContextMenu(expandable);

	}
}

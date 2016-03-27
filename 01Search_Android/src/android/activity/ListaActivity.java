package android.activity;

import java.util.ArrayList;
import java.util.Collections;

import org.hibernate.criterion.Example;

import layouts.DuploSimplesExpandableListAdapter;
import layouts.ItemListView;
import layouts.Item_ListViewAdapter;
import layouts.Item_Adapter;
import layouts.Lista_Adapter;

import Modelos.Codigobarras;
import Modelos.Item;
import Modelos.ItemLista;
import Modelos.ItemListaPK;
import Modelos.Lista;
import Modelos.ListaUsuario;
import Modelos.Usuario;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import conexao.Conexao;
import controle.ComparatorNome;
import controle.Sessao;
import controle.Visual.ListaControle;

public class ListaActivity extends Activity {

	private static ListaActivity reference;
	private ArrayList<Lista> arrayList_Lista;
	private ListView listView;
	private ArrayAdapter<String> arrayAdapter;
	private Lista listaCorrente;
	private ImageView imageAdd;
	private ImageView imageMenu;
	private ImageView imageCompartilhar;
	private ImageView imageUpDown;
	private RelativeLayout relativeOrdNome;
	private RelativeLayout menu;
	private AutoCompleteTextView textAutoComplete;
	private TextView textListaSelecionado;
	private Boolean crescente;
	private ListView lsViewMenu;
	private Lista_Adapter adapter_lista;

	private ArrayList<String> arrListaNome;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);

		inicializarObjetos();
		verificarStatusConexao();

		autoCompleteListers();
		addItemListaListener();
		relativeOrdernarListstener();
		inicializaMenu();
		MenuListener();
		eventoItemMenu();
		compartilharListener();

		eventoClickListViewAbrirItemLista();


	}

	protected void tirarFocoAutoComplete() {

		textAutoComplete.clearFocus();
		textAutoComplete.setText("");
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(textAutoComplete.getWindowToken(), 0);
	}

	private void compartilharListener() {
		imageCompartilhar.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {

				final Dialog dialog = new Dialog(ListaActivity.this);
				dialog.setContentView(R.layout.compartilhar);
				dialog.setTitle("Compartilhar");
				dialog.setCancelable(true);
				dialog.show();
				
			}
		});
	}

	private void inicializarObjetos() {

		imageCompartilhar = (ImageView) findViewById(R.id.lista_compartilhar);
		imageMenu = (ImageView) findViewById(R.id.ListaImageViewMenu);
		imageAdd = (ImageView) findViewById(R.id.lista_add);
		imageUpDown = (ImageView) findViewById(R.id.ListaImageViewUpOrDown);
		textAutoComplete = (AutoCompleteTextView) findViewById(R.id.ListaautoComplete);
		relativeOrdNome = (RelativeLayout) findViewById(R.id.ListaRelativeOrdNome);
		textListaSelecionado = (TextView) findViewById(R.id.ListaItemSelecionado);
		listView = (ListView) findViewById(R.id.listViewLista);
		lsViewMenu = (ListView) findViewById(R.id.ListaMenuListView);
		menu = (RelativeLayout) findViewById(R.id.ListaMenuRelative);
		// ordenar
		crescente = false;

		reference = this;

		tirarFocoAutoComplete();

	}

	private void autoCompleteListers() {

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
							ListaActivity.this);
					myAlertDialog.setTitle("Alertar");
					myAlertDialog.setMessage("Essa lista já foi adicionada.");
					myAlertDialog.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								public void onClick(DialogInterface arg0,
										int arg1) {
								}
							});
					myAlertDialog.show();

				} else if (textAutoComplete.getText().toString().equals("")) {

				} else {
					adicionarLista();
				}
			}
		});

	}

	protected void adicionarLista() {
		// definir nova lista
		Lista lista = new Lista();
		lista.setNome(textAutoComplete.getText().toString());
		lista.setAtivo(true);
		lista.setVersao(0);
		lista.getUsuarios().add(Sessao.usuario);
		Lista l = Conexao.getReference().adicionarLista(lista);

		// adicionar nova lista e carregar na primeira tela
		arrayList_Lista.add(l);
		carregarListas();
		// ordernar na forma escolhida
		crescente = !crescente;
		ordenarLista();
	}

	protected boolean verificarItemJaAdicionado() {

		boolean bool = arrListaNome.contains(textAutoComplete.getText()
				.toString());

		return bool;
	}

	private void relativeOrdernarListstener() {
		relativeOrdNome.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				ordenarLista();
			}
		});

	}

	private void inicializaMenu() {
		ArrayList<ItemListView> itens = new ArrayList<ItemListView>();
		ItemListView item1 = new ItemListView("Fazer login", R.drawable.login);
		itens.add(item1);

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

	private void eventoItemMenu() {
		lsViewMenu.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				switch (arg2) {
				case 1:
					loginActivity();
					break;
				/*
				 * case 2: //eventoTrocaEstab(); break;
				 */
				}
				animacaoSaida(menu);
			}
		});
	}

	protected void loginActivity() {

		// ir para tela de login

		Intent intent = new Intent();
		intent.setClassName("android.activity",
				"android.activity.LoginActivity");

		startActivity(intent);

	}

	protected void ordenarLista() {

		if (crescente) {
			Collections.sort(arrayList_Lista, new ComparatorNome<Lista>(
					crescente));
			crescente = false;
			imageUpDown.setImageResource(R.drawable.up);
		} else {
			Collections.sort(arrayList_Lista, new ComparatorNome<Lista>(
					crescente));
			crescente = true;
			imageUpDown.setImageResource(R.drawable.down);
		}

		adapter_lista.notifyDataSetChanged();
	}

	public void verificarStatusConexao() {

		if (Sessao.isConectado) {

			buscarListaServidor();
			carregarListas();

			// button_novaLista.setVisibility(View.VISIBLE);
			// button_refresh.setVisibility(View.VISIBLE);
		} else {

			// button_novaLista.setVisibility(View.INVISIBLE);
			// button_refresh.setVisibility(View.INVISIBLE);
			// notificacao.setText("Offline");

			// listView.setAdapter(null);
		}

	}

	private void buscarListaServidor() {
		// buscar listas do usuario

		arrayList_Lista = ListaControle.get().buscarLista(Sessao.usuario);

		arrListaNome = new ArrayList<String>();
		for (Lista l : arrayList_Lista) {
			arrListaNome.add(l.getNome());
		}
	}

	private void carregarListas() {

		// adapter da lista
		adapter_lista = new Lista_Adapter(ListaActivity.this,
				arrayList_Lista);
		listView.setAdapter(adapter_lista);

		registerForContextMenu(listView);
	}

	private void eventoClickListViewAbrirItemLista() {

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				listaCorrente = arrayList_Lista.get(arg2);

				// abrir tela do itemLista
				Intent intent = new Intent();

				int idLista = listaCorrente.getIdLista();
				Lista lista = new Lista();
				lista.setNome(listaCorrente.getNome());
				lista.setIdLista(idLista);

				intent.putExtra("Lista", Conexao.xStream.toXML(lista));

				intent.setClassName("android.activity",
						"android.activity.ItemListaActivity");

				startActivityForResult(intent, 1);

			}
		});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		int menuItemIndex = item.getItemId();

		// Renomear Lista
		if (menuItemIndex == 0) {

			renomearLista(info.position);
		}

		// Deletar Lista
		else if (menuItemIndex == 1) {

			deletarLista(info.position);
		}

		return true;
	}

	private void renomearLista(final int position) {

		// Dialogo para mudar nome da lista
		final Dialog dialog = new Dialog(ListaActivity.this);
		dialog.setContentView(R.layout.dialo2);
		dialog.setTitle("Renomear " + arrayList_Lista.get(position).getNome()
				+ ":");
		dialog.setCancelable(true);

		final EditText nome = (EditText) dialog.findViewById(R.id.editText1);

		nome.setText(arrayList_Lista.get(position).getNome());

		// botao cancelar para mudar o nome da lista
		Button cancelarr = (Button) dialog.findViewById(R.id.cancelarr2);
		cancelarr.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// botao ok para mudar o nome da lista
		Button ok = (Button) dialog.findViewById(R.id.ok2);
		ok.setOnClickListener(new OnClickListener() {

			// atualizar nome da lista
			public void onClick(View v) {

				// alterar no servidor e na lista
				Lista lista = arrayList_Lista.get(position);
				lista.setNome(nome.getText().toString());

				Conexao.getReference().atualizarLista(lista);

				carregarListas();

				dialog.dismiss();
			}
		});

		dialog.show();
	}

	private void deletarLista(int position) {

		Lista lista = new Lista();
		lista.setIdLista(arrayList_Lista.get(position).getIdLista());

		Usuario usuario = new Usuario();
		usuario.setIdUsuario(Sessao.usuario.getIdUsuario());

		ListaUsuario listaUsuario = new ListaUsuario();
		listaUsuario.setLista(lista);
		listaUsuario.setUsuario(usuario);

		// deletar ListaUsuario
		Conexao.getReference().removerListaUsuario(listaUsuario);

		arrayList_Lista.remove(position);

		// recarregar lista
		carregarListas();
	}

	public static ListaActivity getReference() {
		return reference;
	}

}
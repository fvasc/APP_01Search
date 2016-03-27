package android.activity;

import java.util.ArrayList;
import java.util.Collections;

import layouts.DuploSimplesExpandableListAdapter;
import layouts.ItemListView;
import layouts.Item_Adapter;
import layouts.Item_ListViewAdapter;
import Modelos.Codigobarras;
import Modelos.Item;
import Modelos.ItemLista;
import Modelos.ItemListaPK;
import Modelos.Lista;
import Modelos.UnidadeMedida;
import Modelos.Usuario;
import Modelos.UsuarioItem;
import Modelos.UsuarioItemPK;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import conexao.Conexao;
import controle.ComparatorNome;
import controle.Sessao;
import controle.Visual.ItemControle;
import controle.Visual.ItemListaControle;

public class ItemActivity extends Activity {

	// private TextView informacao;
	// private Button button_criar;
	private ListView listView_Itens;
	private ArrayList<Item> arrayList_Item;
	private TextView nome;
	private ImageView imageAdd;
	private ImageView imageMenu;
	private ImageView imageUpDown;
	private ImageView imageVoltar;
	private RelativeLayout relativeOrdNome;
	private RelativeLayout menu;
	private AutoCompleteTextView textAutoComplete;
	private Boolean crescente;
	private ListView lsViewMenu;
	private ArrayList<String> arrListaNome;
	private Item_Adapter arrayAdapter;
	private Boolean isUpdating;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);

		inicializarObjetos();
		verificarStatusConexao();

		// eventoButtonCriar();
		voltar();
		autoCompleteListers();
		addItemListener();
		relativeOrdernarListstener();
		inicializaMenu();
		MenuListener();
		eventoItemMenu();
		eventoConfigurarItem();
	}

	private void voltar() {
		imageVoltar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				finish();
			}
		});
	}

	protected void tirarFocoAutoComplete() {

		textAutoComplete.setText("");
		textAutoComplete.clearFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(textAutoComplete.getWindowToken(), 0);
	}

	private void inicializarObjetos() {

		listView_Itens = (ListView) findViewById(R.id.item_listview);
		imageMenu = (ImageView) findViewById(R.id.ItemImageViewMenu);
		imageAdd = (ImageView) findViewById(R.id.item_add);
		imageUpDown = (ImageView) findViewById(R.id.itemImageViewUpOrDown);
		textAutoComplete = (AutoCompleteTextView) findViewById(R.id.ItemAutoComplete);
		relativeOrdNome = (RelativeLayout) findViewById(R.id.itemRelativeOrdNome);
		lsViewMenu = (ListView) findViewById(R.id.ItemMenuListView);
		menu = (RelativeLayout) findViewById(R.id.ItemMenuRelative);
		imageVoltar = (ImageView) findViewById(R.id.ItemImageViewVoltar);
		isUpdating = false;
		// ordenar
		crescente = true;

		// tirar foco do editfild
		tirarFocoAutoComplete();
	}

	public void verificarStatusConexao() {

		if (Sessao.isConectado) {

			buscarItensServidor();
			carregarItens();
		} else {

		}
	}

	private void buscarItensServidor() {

		// itens do usuario com codigosbarras atrelados
		arrayList_Item = Conexao.getReference().buscarItem(Sessao.usuario);

		arrListaNome = new ArrayList<String>();
		for (Item l : arrayList_Item) {
			arrListaNome.add(l.getNome());
		}
	}

	private void carregarItens() {

		// adapter da lista
		arrayAdapter = new Item_Adapter(ItemActivity.this, arrayList_Item);
		listView_Itens.setAdapter(arrayAdapter);

		registerForContextMenu(listView_Itens);

		ordenarItem();
	}

	private void eventoConfigurarItem() {

		listView_Itens.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Item item = new Item();

				item = arrayList_Item.get(arg2);

				// abrir tela do itemLista
				Intent intent = new Intent();

				intent.putExtra("Item", Conexao.xStream.toXML(item));

				intent.setClassName("android.activity",
						"android.activity.ConfigurarItemActivity");

				startActivityForResult(intent, 1);
			}
		});
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

			/*	if (isUpdating) {
					isUpdating = false;
					return;
				}

				isUpdating = true;
				String text = s.toString();

				text.replaceAll("[^0-9]*", "");
				textAutoComplete.setText(textAutoComplete.getText().append(text));*/
			}
		});
	}

	private void addItemListener() {
		imageAdd.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (verificarItemJaAdicionado()) {

					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
							ItemActivity.this);
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
					adicionarItemDialog();
				}
			}
		});

	}

	protected void adicionarItemDialog() {

		// dialogo
		final Dialog dialog = new Dialog(ItemActivity.this);
		dialog.setContentView(R.layout.dialo_quantidade);
		dialog.setTitle("Configurações para " + textAutoComplete.getText()
				+ ":");
		dialog.setCancelable(true);

		final EditText quantidade = (EditText) dialog
				.findViewById(R.id.dialo_quantidade_edit);
		quantidade.setText("1");

		Button cancelarr = (Button) dialog
				.findViewById(R.id.dialo_quantidade_Cancelar);

		// cancelar dialogo
		cancelarr.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// confirmar dialogo
		Button ok = (Button) dialog.findViewById(R.id.dialo_quantidade_Ok);
		ok.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				adicionarItem(Integer.parseInt(quantidade.getText().toString()));

				tirarFocoAutoComplete();

				dialog.dismiss();
			}
		});

		// diminuir quatnidade-------------------------------------------
		Button menosQtd = (Button) dialog
				.findViewById(R.id.dialo_quantidade_down);
		menosQtd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					if (Integer.parseInt(quantidade.getText().toString()) - 1 != 0) {
						quantidade.setText(String.valueOf(Integer
								.parseInt(quantidade.getText().toString()) - 1));
					}
				} catch (Exception e) {
					quantidade.setText("1");
				}
			}
		});

		Button maisQtd = (Button) dialog.findViewById(R.id.dialo_quantidade_up);
		maisQtd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				try {
					quantidade.setText(String.valueOf(Integer
							.parseInt(quantidade.getText().toString()) + 1));
				} catch (Exception e) {
					quantidade.setText("1");
				}
			}
		});

		dialog.show();

	}

	protected void adicionarItem(int quantidade) {

		Item item = new Item();

		item.setNome(textAutoComplete.getText().toString());
		item.setPeriodicidade(quantidade);
		ArrayList<Usuario> arrUsuario = new ArrayList<Usuario>();
		arrUsuario.add(Sessao.usuario);

		item.setUsuarios(arrUsuario);
		item = Conexao.getReference().adicionarItem(item);

		arrayList_Item.add(item);

		// ligacao entre usuario e item
		UsuarioItemPK uiPK = new UsuarioItemPK();
		uiPK.setIdItem(item.getIdItem());
		uiPK.setIdUsuario(Sessao.usuario.getIdUsuario());

		UsuarioItem ui = new UsuarioItem();
		ui.setUsuarioItemPK(uiPK);

		// gravar no banco
		ItemListaControle.get().adicionarUsuarioItem(ui);

		atualizarAdapter();

	}

	private void atualizarAdapter() {
		arrayAdapter.notifyDataSetChanged();
		crescente = !crescente;
		ordenarItem();
	}

	protected boolean verificarItemJaAdicionado() {

		boolean bool = arrListaNome.contains(textAutoComplete.getText()
				.toString());

		return bool;
	}

	private void relativeOrdernarListstener() {
		relativeOrdNome.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				tirarFocoAutoComplete();
				ordenarItem();
			}
		});

	}

	protected void ordenarItem() {

		if (crescente) {
			Collections.sort(arrayList_Item,
					new ComparatorNome<Item>(crescente));
			crescente = false;
			imageUpDown.setImageResource(R.drawable.up);
		} else {
			Collections.sort(arrayList_Item,
					new ComparatorNome<Item>(crescente));
			crescente = true;
			imageUpDown.setImageResource(R.drawable.down);
		}

		arrayAdapter.notifyDataSetChanged();
	}

	private void inicializaMenu() {
		ArrayList<ItemListView> itens = new ArrayList<ItemListView>();
		/*
		 * ItemListView item1 = new ItemListView("Fazer login",
		 * R.drawable.login); itens.add(item1);
		 */
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

					break;
				/*
				 * case 2: //eventoTrocaEstab(); break;
				 */
				}
				animacaoSaida(menu);
			}
		});
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

		// colocar o item como nome do menu
		menu.setHeaderTitle(arrayList_Item.get(info.position).getNome());
		// busca opcoes
		String[] menuItems = getResources().getStringArray(R.array.menuItem);

		menu.clear();
		// define opcoes
		for (int i = 0; i < menuItems.length; i++) {
			menu.add(Menu.NONE, i, i, menuItems[i]);
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem menuItem) {

		final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem
				.getMenuInfo();
		int menuItemIndex = menuItem.getItemId();

		// Renomear Item
		if (menuItemIndex == 0) {

			renomearItem(info.position);
		}

		// deletar Item
		else if (menuItemIndex == 1) {

			desejaExcluirItem(info.position);

		}

		return true;
	}

	private void desejaExcluirItem(final int position) {

		String mensagem = "Tem certeza que quer excluir?\nAo excluir você não poderá mais utiliza-lo.";

		AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
				ItemActivity.this);
		myAlertDialog.setTitle("Excluir");
		myAlertDialog.setMessage(mensagem);
		myAlertDialog.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {

						deletarItem(position);
					}
				});
		myAlertDialog.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {
					}
				});
		myAlertDialog.show();
	}

	private void renomearItem(final int position) {

		// Dialogo para mudar nome da Item
		final Dialog dialog = new Dialog(ItemActivity.this);
		dialog.setContentView(R.layout.dialo2);
		dialog.setTitle("Renomear " + arrayList_Item.get(position).getNome()
				+ ":");
		dialog.setCancelable(true);

		final EditText nome = (EditText) dialog.findViewById(R.id.editText1);

		nome.setText(arrayList_Item.get(position).getNome());

		// botao cancelar para mudar o nome da Item
		Button cancelarr = (Button) dialog.findViewById(R.id.cancelarr2);
		cancelarr.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		// botao ok para mudar o nome da Item
		Button ok = (Button) dialog.findViewById(R.id.ok2);
		ok.setOnClickListener(new OnClickListener() {

			// atualizar nome da Item
			public void onClick(View v) {

				// alterar no servidor e na Item
				arrayList_Item.get(position).setNome(nome.getText().toString());

				Conexao.getReference().atualizarItem(
						arrayAdapter.getItem(position));

				atualizarAdapter();

				tirarFocoAutoComplete();

				dialog.dismiss();
			}
		});

		dialog.show();
	}

	private void deletarItem(int position) {

		Item item = new Item();
		item.setIdItem(arrayList_Item.get(position).getIdItem());
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(Sessao.usuario.getIdUsuario());

		UsuarioItem usuarioItem = new UsuarioItem();
		usuarioItem.setItem(item);
		usuarioItem.setUsuario(usuario);
		Conexao.getReference().removerUsuarioItem(usuarioItem);

		arrayList_Item.remove(position);

		atualizarAdapter();

		// recarregar itens
		carregarItens();

	}

}

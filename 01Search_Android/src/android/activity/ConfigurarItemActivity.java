package android.activity;

import java.util.ArrayList;
import java.util.Collections;

import layouts.Codigobarras_Adapter;
import layouts.DuploSimplesExpandableListAdapter;
import layouts.Item_Adapter;
import layouts.Lista_Adapter;
import Modelos.Codigobarras;
import Modelos.CodigobarrasItem;
import Modelos.CodigobarrasItemPK;
import Modelos.Item;
import Modelos.ItemLista;
import Modelos.ItemListaPK;
import Modelos.Lista;
import Modelos.UnidadeMedida;
import Modelos.Usuario;
import Modelos.UsuarioItem;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
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

public class ConfigurarItemActivity extends Activity {

	private RelativeLayout relativeOrdNome;
	private ImageView imageVoltar;
	private ImageView imageAdd;
	private ImageView imageUpDown;
	private ListView listView_Codigobarras;
	private TextView nome;
	private Button buttonCamera;
	private AutoCompleteTextView textAutoComplete;
	private Item item;
	private Intent intent;
	private Codigobarras_Adapter adapter_Codigobarras;
	private ArrayList<Codigobarras> arrayList_Codigobarras;
	private ArrayList<String> arrListaNome;
	private Boolean crescente;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.configurar_item);

		inicializarObjetos();
		verificarStatusConexao();

		voltar();
		autoCompleteListers();
		addItemListener();
		relativeOrdernarListstener();
		eventoVerCodigobarras();
		cameraListener();

	}

	private void cameraListener() {
		buttonCamera.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				try {
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.setPackage("com.google.zxing.client.android");
				
					startActivityForResult(intent, 0);

				} catch (Exception e) {

					DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							switch (which) {
							case DialogInterface.BUTTON_POSITIVE:
								Intent intent = new Intent(Intent.ACTION_VIEW);
								intent.setData(Uri
										.parse("market://details?id=com.google.zxing.client.android"));
								startActivity(intent);
								break;

							case DialogInterface.BUTTON_NEGATIVE:
								break;
							}
						}
					};

					AlertDialog.Builder dialog = new AlertDialog.Builder(
							ConfigurarItemActivity.this);
					dialog.setTitle("Aviso");

					dialog.setPositiveButton("Sim", dialogClickListener);
					dialog.setNegativeButton("Nï¿½o", dialogClickListener);

					dialog.setMessage("Você não possui o programa 'Barcode Scanner'. Deseja baixa-lo?");

					dialog.show();

				}
			}
		});
	}

	private void eventoVerCodigobarras() {
		
		
		listView_Codigobarras.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				Codigobarras codigobarras = new Codigobarras();

				codigobarras = arrayList_Codigobarras.get(arg2);

				Intent intent = new Intent();

				intent.setClassName("android.activity",
						"android.activity.CodigobarrasActivity");

				intent.putExtra("Codigobarras", Conexao.xStream.toXML(codigobarras));

				startActivity(intent);
			}
		});
		
	}

	private void inicializarObjetos() {

		nome = (TextView) findViewById(R.id.ItemListaNomeLista);
		textAutoComplete = (AutoCompleteTextView) findViewById(R.id.ItemListaautoComplete);
		imageVoltar = (ImageView) findViewById(R.id.ItemImageViewVoltar);
		imageUpDown = (ImageView) findViewById(R.id.configurarImageViewUpOrDown);
		imageAdd = (ImageView) findViewById(R.id.configurarImageViewUpOrDown);
		listView_Codigobarras = (ListView) findViewById(R.id.item_listview);
		relativeOrdNome = (RelativeLayout) findViewById(R.id.configurarRelativeOrdNome);
		buttonCamera = (Button) findViewById(R.id.buttonCamera);

		item = (Item) Conexao.xStream.fromXML(getIntent()
				.getStringExtra("Item"));
		nome.setText(item.getNome());

		// ordenar
		crescente = true;

		// tirar foco do editfild
		tirarFocoAutoComplete();

	}

	private void voltar() {
		imageVoltar.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				finish();
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
				// fazer mascara
				/*
				 * String current = s.toString(); String letra =
				 * current.replaceAll("[^a-z]*", "");
				 */
			}
		});
	}

	private void addItemListener() {
		imageAdd.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (verificarItemJaAdicionado()) {

					AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(
							ConfigurarItemActivity.this);
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
					// adicionarCodigobarrasDialog();
				}
			}
		});

	}

	protected void adicionarCodigobarrasDialog() {

		// dialogo
		final Dialog dialog = new Dialog(ConfigurarItemActivity.this);
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

				// adicionarItem(Integer.parseInt(quantidade.getText().toString()));

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

	protected boolean verificarItemJaAdicionado() {

		boolean bool = arrListaNome.contains(textAutoComplete.getText()
				.toString());

		return bool;
	}

	private void relativeOrdernarListstener() {
		relativeOrdNome.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				tirarFocoAutoComplete();
				ordenarCodigobarras();
			}
		});

	}

	protected void ordenarCodigobarras() {

		if (crescente) {
			Collections.sort(arrayList_Codigobarras,
					new ComparatorNome<Codigobarras>(crescente));
			crescente = false;
			imageUpDown.setImageResource(R.drawable.up);
		} else {
			Collections.sort(arrayList_Codigobarras,
					new ComparatorNome<Codigobarras>(crescente));
			crescente = true;
			imageUpDown.setImageResource(R.drawable.down);
		}

		adapter_Codigobarras.notifyDataSetChanged();
	}

	protected void tirarFocoAutoComplete() {

		textAutoComplete.setText("");
		textAutoComplete.clearFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(textAutoComplete.getWindowToken(), 0);
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
		arrayList_Codigobarras = Conexao.getReference()
				.buscarCodigobarras(item);

		arrListaNome = new ArrayList<String>();
		for (Codigobarras l : arrayList_Codigobarras) {
			arrListaNome.add(l.getNome());
		}
	}

	private void carregarItens() {

		// adapter da lista
		adapter_Codigobarras = new Codigobarras_Adapter(
				ConfigurarItemActivity.this, arrayList_Codigobarras);
		listView_Codigobarras.setAdapter(adapter_Codigobarras);
		
		ordenarCodigobarras();

		registerForContextMenu(listView_Codigobarras);

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

		// colocar o codigobaras como nome do menu
		menu.setHeaderTitle(arrayList_Codigobarras.get(info.position).getNome());
		// busca opcoes
		String[] menuItems = getResources().getStringArray(
				R.array.menuCodigobarras);

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

		if (menuItemIndex == 0) {

			deletarCodigobarras(info.position);
		}

		return true;
	}

	private void deletarCodigobarras(int position) {

		CodigobarrasItemPK codigobarrasItemPK = new CodigobarrasItemPK();
		codigobarrasItemPK.setIdCodigobarras(arrayList_Codigobarras.get(
				position).getIdCodigoBarras());
		codigobarrasItemPK.setIdItem(item.getIdItem());

		CodigobarrasItem codigobarrasItem = new CodigobarrasItem();
		codigobarrasItem.setCodigobarrasItemPK(codigobarrasItemPK);

		Conexao.getReference().removerCodigobarrasItem(codigobarrasItem);

		arrayList_Codigobarras.remove(position);

		// recarregar itens
		carregarItens();

	}

}

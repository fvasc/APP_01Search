package android.activity;

import org.json.JSONObject;

import android.activity.R;

import controle.Sessao;
import login.facebook.facebookLogin;
import login.google.OAuthFlowApp;
import login.google.PrepareRequestTokenActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private ImageView google;
	private ImageView facebookLogo;
	private ImageView twitter;
	private ImageView logo;
	private Button sair;
	private TextView informacao;
	private facebookLogin controleFacebook;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		google = (ImageView) findViewById(R.id.loginGoogle);
		facebookLogo = (ImageView) findViewById(R.id.loginFacebook);
		twitter = (ImageView) findViewById(R.id.loginTwitter);
		sair = (Button) findViewById(R.id.sair);
		informacao = (TextView) findViewById(R.id.conexao);
		logo = (ImageView) findViewById(R.id.logo);

		controleFacebook = new facebookLogin(this);

		new OAuthFlowApp(LoginActivity.this);

		facebookLogo.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				if (!Sessao.isConectado) {
					if (controleFacebook.login()) {
						try {
							JSONObject jsonResponse = new JSONObject(
									controleFacebook.informacoes());

							Sessao.usuario.getIdLogin().setLogin(jsonResponse.getString("id"));
							Sessao.usuario.setNome(jsonResponse.getString("name"));
							Sessao.rede = "Facebook";
							Sessao.isConectado = true;

							atualizaInformacoes();
						} catch (Exception e) {
						}
					}
				} else {
					AlertDialog dialog = new AlertDialog.Builder(
							LoginActivity.this).create();
					dialog.setTitle("Aviso");
					dialog.setMessage("Voc� j� est� conectado.\nClique no bot�o 'Sair' para fazer um novo login.");
					dialog.setButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									return;
								}
							});
					dialog.show();
				}

			}
		});

		sair.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				
				Sessao.rede = "";
				Sessao.usuario = null;

				Sessao.isConectado = false;

				controleFacebook.clearCredentials();

				atualizaInformacoes();
			}
		});

		google.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {

				if (!Sessao.isConectado) {

					try {
						Sessao.ready = false;

						startActivity(new Intent().setClass(LoginActivity.this,
								PrepareRequestTokenActivity.class));

					} catch (Exception e) {
						Log.e(getClass().getName(), e.getMessage());
					}
				} else {
					AlertDialog dialog = new AlertDialog.Builder(
							LoginActivity.this).create();
					dialog.setTitle("Aviso");
					dialog.setMessage("Voc� j� est� conectado.\nClique no bot�o 'Sair' para fazer um novo login.");
					dialog.setButton("OK",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									return;
								}
							});
					dialog.show();
				}
			}
		});

		twitter.setOnClickListener(new OnClickListener() {

			 
			public void onClick(View v) {
				Toast.makeText(getBaseContext(),
						"Aguarde, esta rede estar� dispon�vel em breve.",
						Toast.LENGTH_LONG).show();

			}
		});
	}

	public void atualizaInformacoes() {
		if (Sessao.isConectado) {
			informacao.setText("Voc� est� conectado como \n'" + Sessao.usuario.getNome()
					+ "'");

			logo.setVisibility(View.VISIBLE);
			sair.setVisibility(View.VISIBLE);

			if (Sessao.rede.equals("Facebook"))
				logo.setImageResource(R.drawable.facebooklogo);
			else if (Sessao.rede.equals("Google"))
				logo.setImageResource(R.drawable.googlelogo);

		} else {
			informacao.setText("Voc� n�o est� conectado");
			logo.setVisibility(View.INVISIBLE);
			sair.setVisibility(View.INVISIBLE);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		try {
			controleFacebook.getFacebook().authorizeCallback(requestCode,
					resultCode, data);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	protected void onResume() {

		super.onResume();

		while (!Sessao.ready) {
		}

		atualizaInformacoes();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		ListaActivity.getReference().verificarStatusConexao();
	}
}

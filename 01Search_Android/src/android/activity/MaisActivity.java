package android.activity;

import conexao.Conexao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MaisActivity extends Activity {

	private Button item;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.mais);

		item = (Button) findViewById(R.id.mais_item);
		botao();

	}

	private void botao() {
		item.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();

				intent.setClassName("android.activity",
						"android.activity.ItemActivity");

				// intent.putExtra("Codigobarras",
				// Conexao.xStream.toXML(codigobarras));

				startActivity(intent);
			}
		});
	}

}

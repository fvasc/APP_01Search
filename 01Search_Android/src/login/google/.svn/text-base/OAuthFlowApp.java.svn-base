package login.google;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;


import controle.Sessao;
import android.activity.LoginActivity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

public class OAuthFlowApp {

	final String TAG = getClass().getName();
	private SharedPreferences prefs;
	private static LoginActivity activity;
	private static OAuthFlowApp reference;

	public static OAuthFlowApp getReference() {
		return reference;
	}

	public OAuthFlowApp(LoginActivity activity) {

		this.prefs = PreferenceManager.getDefaultSharedPreferences(activity);

		OAuthFlowApp.activity = activity;
		reference = this;
	}

	public void performApiCall() {

		String jsonOutput = "";

		try {
			jsonOutput = doGet(Constants.API_REQUEST, getConsumer(this.prefs));
			JSONObject jsonResponse = new JSONObject(jsonOutput);

			JSONObject m = (JSONObject) jsonResponse.get("feed");
			JSONObject entries = (JSONObject) m.get("id");

			JSONArray teste = (JSONArray) m.getJSONArray("author");
			JSONObject entriesx = (JSONObject) teste.getJSONObject(0);
			JSONObject entries2 = (JSONObject) entriesx.get("name");

			Sessao.usuario.setNome(entries2.getString("$t"));
			Sessao.usuario.getIdLogin().setLogin(entries.getString("$t"));
			Sessao.isConectado = true;
			Sessao.rede = "Google";

			Sessao.ready = true;
			
			Log.e(getClass().getName(), "sem erro");

		} catch (Exception e) {
			Log.e(getClass().getName(), e.getMessage());
		}		
	}

	public void clearCredentials() {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(activity);
		final Editor edit = prefs.edit();
		edit.remove(OAuth.OAUTH_TOKEN);
		edit.remove(OAuth.OAUTH_TOKEN_SECRET);
		edit.commit();
	}

	private OAuthConsumer getConsumer(SharedPreferences prefs) {
		String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
		String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer(
				Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		consumer.setTokenWithSecret(token, secret);
		return consumer;
	}

	private String doGet(String url, OAuthConsumer consumer) throws Exception {

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpGet request = new HttpGet(url);

		consumer.sign(request);

		HttpResponse response = httpclient.execute(request);

		InputStream data = response.getEntity().getContent();

		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(data));

		String responeLine;

		StringBuilder responseBuilder = new StringBuilder();

		while ((responeLine = bufferedReader.readLine()) != null) {
			responseBuilder.append(responeLine);
		}

		return responseBuilder.toString();
	}
}
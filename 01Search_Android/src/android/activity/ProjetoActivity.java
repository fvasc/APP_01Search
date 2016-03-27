package android.activity;


import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class ProjetoActivity extends TabActivity  {
    /** Called when the activity is first created. */

	private TabHost tabHost;
	private RelativeLayout menu;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        menu = (RelativeLayout) findViewById(R.id.relativeLayout_main);
        
        Resources res = getResources(); // Resource object to get Drawables
        tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab
        
        intent = new Intent().setClass(this, NovidadesActivity.class);
        spec = tabHost.newTabSpec("0").setIndicator("Novidades", res.getDrawable(R.drawable.novo)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, DepartamentosActivity.class);
        spec = tabHost.newTabSpec("1").setIndicator("Categorias", res.getDrawable(R.drawable.categorias)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, BuscaActivity.class);
        spec = tabHost.newTabSpec("2").setIndicator("Buscar", res.getDrawable(R.drawable.lupa)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ListaActivity.class);
        spec = tabHost.newTabSpec("3").setIndicator("Listas", res.getDrawable(R.drawable.cart)).setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, MaisActivity.class);
        spec = tabHost.newTabSpec("4").setIndicator("Mais", res.getDrawable(R.drawable.mnmais)).setContent(intent);
        tabHost.addTab(spec);
        
        
        tabHost.setCurrentTab(0);
        limparBarra();
    }

	private void limparBarra() {
		tabHost.setOnTabChangedListener(new OnTabChangeListener(){
			
			public void onTabChanged(String tabId) {
			    if(tabId.equals("3")){
			    	menu.setVisibility(menu.GONE);
			    }else{
			    	menu.setVisibility(menu.VISIBLE);
			    }
			}});
		}
    
}


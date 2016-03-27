package layouts;

import java.util.List;

import Modelos.ItemLista;
import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


class CustomAdapterView extends RelativeLayout {        
	
	public CustomAdapterView(Context context, ItemLista itemLista) 
	{
		super( context );
		    
		setPadding(0, 6, 0, 6);
		
		RelativeLayout.LayoutParams Params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);		
			
		Params.setMargins(415, 0, 0, 0);
		
		final CheckBox check = new CheckBox(context);
		check.setFocusable(false);
		check.setFocusableInTouchMode(false);
		//check.setChecked(item.getComprado());
		addView(check, Params);
		
//		check.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				item.setComprado(check.isChecked());	
//				
//				Conexao.getReference().atualizarProdutosLista(item);
//			}
//		});
		
		RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		params1.setMargins(0, 0, 0, 0);
		
		LinearLayout PanelV = new LinearLayout(context);
		PanelV.setOrientation(LinearLayout.VERTICAL);
		PanelV.setGravity(Gravity.BOTTOM);
		
		TextView textName = new TextView( context );
		textName.setTextSize(16);
		textName.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
		//textName.setText(itemLista.getItem().getNome());
		PanelV.addView(textName);       
		
		TextView textAddress = new TextView( context );
		textAddress.setTextSize(14);
		textAddress.setText( String.format("Qtd: %d", itemLista.getQuantidade()));
		PanelV.addView(textAddress);    
		
		addView(PanelV, params1);		
	}
}


public class CustomAdapter extends BaseAdapter{
	
	public static final String LOG_TAG = "BI::CA";
    private Context context;
    private List<ItemLista> deviceList;

    public CustomAdapter(Context context, List<ItemLista> deviceList ) { 
        this.context = context;
        this.deviceList = deviceList;
    }

    public int getCount() {                        
        return deviceList.size();
    }

    public Object getItem(int position) {     
        return deviceList.get(position);
    }

    public long getItemId(int position) {  
        return position;
    }

   public View getView(int position, View convertView, ViewGroup parent) 
    { 
    	ItemLista device = deviceList.get(position);
        View v = new CustomAdapterView(this.context, device );
        
        return v;
    }

}

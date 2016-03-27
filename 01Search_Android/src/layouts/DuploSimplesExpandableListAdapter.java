package layouts;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class DuploSimplesExpandableListAdapter<P, F>  extends BaseExpandableListAdapter {
	
	private static final String TAG = "CustomExpandableListAdapter";
	private ArrayList<P> list_pai;
	private ArrayList<ArrayList<F>> list_filho;
	private Context context;
    
	public DuploSimplesExpandableListAdapter(Context context, ArrayList<P> list_pai, ArrayList<ArrayList<F>> list_filho){
		this.context = context;
		this.list_pai = list_pai;
		this.list_filho = list_filho;
	}
	
	public DuploSimplesExpandableListAdapter() {
	}
	
    public ArrayList<ArrayList<F>> getList_filho() {
		return list_filho;
	}

	public void setList_filho(ArrayList<ArrayList<F>> list_filho) {
		this.list_filho = list_filho;
	}

	public ArrayList<P> getList_pai() {
		return list_pai;
	}

	public void setList_pai(ArrayList<P> list_pai) {
		this.list_pai = list_pai;
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public TextView getGroupTextView() {
    	
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
            ViewGroup.LayoutParams.FILL_PARENT, 65);

        TextView textView = new TextView(context);
        textView.setLayoutParams(lp);
        // Center the text vertically
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        textView.setPadding(60, 0, 0, 0);
        textView.setTextSize(22);
        textView.setTextColor(Color.WHITE);
        return textView;
    }
	
    
    public TextView getChildTextView() {
    	
        // Layout parameters for the ExpandableListView
        AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
            ViewGroup.LayoutParams.FILL_PARENT, 45);

        TextView textView = new TextView(context);
        textView.setLayoutParams(lp);
        // Center the text vertically
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        // Set the text starting position
        textView.setPadding(90, 0, 0, 0);
        textView.setTextSize(18);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(Color.BLACK);
        textView.getBackground().setAlpha(100);
        return textView;
    }
    
    

	public Object getChild(int groupPosition, int childPosition) {
        return list_filho.get(groupPosition).get(childPosition);
    }

    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    public int getChildrenCount(int groupPosition) {
    	return list_filho.get(groupPosition).size();
    }

    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
            View convertView, ViewGroup parent) {
    	
        TextView textView = getChildTextView();
        
        textView.setText((getChild(groupPosition, childPosition)).toString());
        
        return textView;
    }

    public Object getGroup(int groupPosition) {
        return list_pai.get(groupPosition);
    	//return groups[groupPosition];
    }

    public int getGroupCount() {
    	return list_pai.size();
        //return groups.length;
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
    	
        TextView textView = getGroupTextView();
        
        textView.setText((getGroup(groupPosition)).toString());

        return textView;
    }

    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public boolean hasStableIds() {
        return true;
    }
}

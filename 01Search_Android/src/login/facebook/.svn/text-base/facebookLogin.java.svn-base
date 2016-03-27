package login.facebook;

import android.app.Activity;
import android.os.Bundle;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;

public class facebookLogin {

	private Facebook facebook;
	private Activity activity;

	public facebookLogin(Activity activity) {
		this.activity = activity;
		facebook = new Facebook("251085421606144");
	}
		

	public Facebook getFacebook() {
		return facebook;
	}
	
	public Boolean login() {
		if (!facebook.isSessionValid()) {
			facebook.authorize(activity, new DialogListener() {
				
				public void onFacebookError(FacebookError e) {
					// TODO Auto-generated method stub
					
				}
				
				public void onError(DialogError e) {
					// TODO Auto-generated method stub
					
				}
				
				public void onComplete(Bundle values) {
					// TODO Auto-generated method stub
					
				}
				
				public void onCancel() {
					// TODO Auto-generated method stub
					
				}
			});
		}
		
		return true;
	}

	public String informacoes() {
		try{
		Bundle bundle = new Bundle();

		String str = facebook.request("me", bundle);
		
		return str;				
		} catch (Exception e) {
			return "";
		}
	}

	public void clearCredentials() {
		try {
			facebook.logout(activity.getBaseContext());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

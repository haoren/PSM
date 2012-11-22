package extension;

import psm.taxi.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Dispatcher extends Activity{
	
	  public void onCreate(Bundle savedInstanceState){
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.call);
		  
		  Bundle extras = getIntent().getExtras();
		  if (extras == null) {
			  return;
			  }
			
		  final Button telepon = (Button)findViewById(R.id.bPhoneOrder);
	  }
}
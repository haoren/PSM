package psm;

import extension.Dispatcher;
import extension.Feedback;
import extension.OrderHistory;
import psm.taxi.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Utama extends Activity
{
	private Context Mesej;
	private ProgressDialog c;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.menu_utama);
	    
	    ((Button)findViewById(R.id.bPhoneOrder)).setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View view){
	    		Intent telepon = new Intent(Utama.this, Dispatcher.class);
	    		startActivity(telepon);
	    	}
	    });
	    
	    ((Button)findViewById(R.id.bHistory)).setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View view){
	    		Intent history = new Intent(Utama.this, OrderHistory.class);
	    		startActivity(history);
	    	}
	    });
	    
	    ((Button)findViewById(R.id.bFeedback)).setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View view){
	    		Intent feedback = new Intent(Utama.this, Feedback.class);
	    		startActivity(feedback);
	    	}
	    });
	    ((Button)findViewById(R.id.bOrderOnline)).setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View view){
	    		Intent gunamap = new Intent(Utama.this, Lokasi.class);
	    		startActivity(gunamap);
	    	}
	    });
	    /*
	    ((Button)findViewById(bMapOrderOnline)).setOnClickListener(new p(this));
	    ((ImageView)findViewById(2131296319)).setOnClickListener(new Dispatcher(this));
	    ((ImageView)findViewById(2131296317)).setOnClickListener(new o(this));
	    ((Button)findViewById(bPhoneOrder)).setOnClickListener(new p(this));
	    ((Button)findViewById(bHistory)).setOnClickListener(new q(this));
	    ((Button)findViewById(bFeedback)).setOnClickListener(new r(this));
	    ((Button)findViewById(bHome)).setOnClickListener(new s(this));
	    */
	}
	
	public void onResume(){
		super.onResume();
		if (this.c != null)
		this.c.dismiss();
	}
}
package br.com.tairoroberto.testehapctfeedback;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.HapticFeedbackConstants;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	private EditText edtNome, edtEmail,edtSenha;
	private Button btnEnviar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edtNome = (EditText)findViewById(R.id.edtNome);
		edtEmail = (EditText)findViewById(R.id.edtEmail);
		edtSenha = (EditText)findViewById(R.id.edtSenha);
		btnEnviar = (Button)findViewById(R.id.button1);
		
		
		btnEnviar.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
				// Pega a resposta do servidor
				Toast.makeText(MainActivity.this, "Resposta do servidor", Toast.LENGTH_LONG).show();
				Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				vibrator.vibrate(100);
			}
		});
		
		
		// HAPTIC_PERSONALIZADO
			edtNome.setOnTouchListener(new HapticoPersonalizado());
			edtEmail.setOnTouchListener(new HapticoPersonalizado());
			edtSenha.setOnTouchListener(new HapticoPersonalizado());
		
		//HAPTIC_NATIVO
			/*edtNome.setOnTouchListener(new HapticoNativo());
			edtEmail.setOnTouchListener(new HapticoNativo());
			edtSenha.setOnTouchListener(new HapticoNativo());*/
	}
	
	//Classe para ouvir eventos de touch
	public class HapticoPersonalizado implements OnTouchListener{

		@Override
		public boolean onTouch(View view, MotionEvent event) {
			//Add um Vibrator para vibrar o device 
			Vibrator vibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			vibrator.vibrate(100);
			
			//Requesita o foco para a view
			view.requestFocus();
			InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			manager.showSoftInput(view, 0);
			return (true);
		}
		
	}
	
	public class HapticoNativo implements OnTouchListener{

		@Override
		public boolean onTouch(View view, MotionEvent event) {
			//Roda o HapticFeedBack
			view.performHapticFeedback(HapticFeedbackConstants.KEYBOARD_TAP);
			
			return(true);
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}

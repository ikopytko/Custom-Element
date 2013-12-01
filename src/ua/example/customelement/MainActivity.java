package ua.example.customelement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	private ActionOptionsView optionsView;
	
	private Button mBack;
	private Button mCoinsVis;
	private Button mTitle;
	private Button mCoins;
	
	private EditText mTitleText;
	private EditText mCoinsCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		optionsView = (ActionOptionsView) findViewById(R.id.actionView);
		optionsView.setTitle("Touch gloves bestellen");
		
		mBack = (Button) findViewById(R.id.vBack);
		mCoinsVis = (Button) findViewById(R.id.vCoins);
		mTitle = (Button) findViewById(R.id.vTitle);
		mCoins = (Button) findViewById(R.id.vCoinSet);
		
		optionsView.setListener(this);
		mBack.setOnClickListener(this);
		mCoinsVis.setOnClickListener(this);
		mTitle.setOnClickListener(this);
		mCoins.setOnClickListener(this);
		
		mTitleText = (EditText) findViewById(R.id.etTitle);
		mCoinsCount = (EditText) findViewById(R.id.etCoins);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.vBack:
			optionsView.setBackVisibility(!optionsView.isBackVisible());
			break;
		case R.id.vCoins:
			optionsView.setCoinsVisibility(!optionsView.isCoinsVisible());
			break;
		case R.id.vTitle:
			optionsView.setTitle(mTitleText.getText().toString());
			break;
		case R.id.vCoinSet:
			optionsView.setCoins(Integer.parseInt(mCoinsCount.getText().toString()));
			break;
		case R.id.buttonBack:
			Toast.makeText(this, "Pressed back", Toast.LENGTH_SHORT).show();
			break;
		}
		
	}

}

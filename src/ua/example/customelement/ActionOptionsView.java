package ua.example.customelement;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ActionOptionsView extends LinearLayout {

	private ImageView mCoins;
	private TextView mPoints;
	private TextView mTtitleText;
	private Button mBack;
	private boolean showCoins;
	private boolean showBack;
	private Context context;

	public ActionOptionsView(Context contx, AttributeSet attrs) {
		super(contx, attrs);
		this.context = contx;

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ColorOptionsView, 0, 0);
		String titleText = a.getString(R.styleable.ColorOptionsView_titleText);
		showBack = a.getBoolean(R.styleable.ColorOptionsView_showBackButton,
				false);
		showCoins = false;
		a.recycle();

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.custom_action, this, true);

        
		mCoins = (ImageView) this.findViewById(R.id.ivCoins);
		mPoints = (TextView) this.findViewById(R.id.tvPoints);
		mTtitleText = (TextView) this.findViewById(R.id.tvTitle);
		mBack = (Button) this.findViewById(R.id.buttonBack);

		Typeface font = Typeface.createFromAsset(contx.getAssets(), "fonts/SourceSansPro-Semibold.ttf");
		mTtitleText.setTypeface(font);
		font = Typeface.createFromAsset(contx.getAssets(), "fonts/DINB-Bold.ttf");
		mPoints.setTypeface(font);
		
		setCoinsVisibility(showCoins);
		setBackVisibility(showBack);

		setTitle(titleText);
	}

	@Override
	public Parcelable onSaveInstanceState() {

		Bundle bundle = new Bundle();
		bundle.putParcelable("instanceState", super.onSaveInstanceState());
		bundle.putBoolean("showBack", showBack);
		bundle.putBoolean("showCoins", showCoins);
		bundle.putString("title", getTitle());
		bundle.putInt("coins", getCoins());

		return bundle;
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {

		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			setCoins(bundle.getInt("coins"));
			setTitle(bundle.getString("title"));
			setBackVisibility(bundle.getBoolean("showBack"));
			setCoinsVisibility(bundle.getBoolean("showCoins"));
			super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
			return;
		}

		super.onRestoreInstanceState(state);
	}

	public String getTitle() {
		return mTtitleText.getText().toString();
	}

	public void setTitle(String showText) {
		mTtitleText.setText(showText);
	}

	public boolean isCoinsVisible() {
		return showCoins;
	}

	public void setCoinsVisibility(boolean state) {
		showCoins = state;
		mPoints.setVisibility(state ? VISIBLE : INVISIBLE);
		mCoins.setVisibility(state ? VISIBLE : INVISIBLE);
	}

	public void setCoins(int coins) {
		mPoints.setText(String.valueOf(coins));
	}

	public int getCoins() {
		return Integer.parseInt(mPoints.getText().toString());
	}

	public boolean isBackVisible() {
		return showBack;
	}

	public void setBackVisibility(boolean state) {
		showBack = state;
		mBack.setVisibility(showBack ? VISIBLE : INVISIBLE);
	}

	/**
	 * Set listener for the back button
	 * @param l listener to set. Use R.id.buttonBack to identity back button in onClick method
	 */
	public void setListener(OnClickListener l) {
		mBack.setOnClickListener(l);
	}
}

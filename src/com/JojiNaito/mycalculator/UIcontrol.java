package com.JojiNaito.mycalculator;

import com.JojiNaito.mycalculator.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/*
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.content.res.Resources;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
*/

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class UIcontrol extends Activity {
	/**
	 * Whether or not the system UI should be auto-hidden after
	 * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
	 */
	private static final boolean AUTO_HIDE = true;

	/**
	 * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
	 * user interaction before hiding the system UI.
	 */
	private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

	/**
	 * If set, will toggle the system UI visibility upon interaction. Otherwise,
	 * will show the system UI visibility upon interaction.
	 */
	private static final boolean TOGGLE_ON_CLICK = true;

	/**
	 * The flags to pass to {@link SystemUiHider#getInstance}.
	 */
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

	/**
	 * The instance of the {@link SystemUiHider} for this activity.
	 */
	private SystemUiHider mSystemUiHider;
	
	/*
	 * The instance of buttons
	 */
	protected Button btn0;
	protected Button btn1;
	protected Button btn2;
	protected Button btn3;
	protected Button btn4;
	protected Button btn5;
	protected Button btn6;
	protected Button btn7;
	protected Button btn8;
	protected Button btn9;
	protected Button btnPoint;
	protected Button btnEqual;
	protected Button btnAdd;
	protected Button btnSub;
	protected Button btnMult;
	protected Button btnDivid;
	protected Button btnClear;
	protected Button btnAllClear;
	protected Button btnInv;
	protected Button btnPow;
	protected Button btnVoice;
	
	/*
	 * The instance of TextView
	 */
	protected TextView txtNumber;

	/*
	 * The UI control variables
	 */
	private boolean bTypingNumber;
	private boolean bTypingPoint;
	protected Calculator calc;
	protected Dictionary dict;
	protected MorphologicalAnalysis morph;
	protected CharSequence ERR_INVALID_INPUT;
	protected CharSequence ERR_OVERFLOW;
	protected CharSequence ERR_UNDERFLOW;
	protected CharSequence ERR_ZERODIVIDE;
	protected CharSequence ERR_INVALIDPOW;
	protected CharSequence ERR_INVALID_FORMULA;	
	protected CharSequence ERR_INVALID_VOICERECOGN;
	

	/*
	 * The instance of SpeechRecognizer
	 */	
	private static int REQUEST_CODE = 3; // Temporary
	boolean bReversePolish;

	/*
	 *  OnCreate
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        // Instantiate member objects
        calc = new Calculator();
        dict = new Dictionary();
        morph = new MorphologicalAnalysis(dict,this);
  //      mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());

        // initialize member variables
        bTypingNumber=false;
        bTypingPoint=false;
        bReversePolish = false;

		// set up the view
		setContentView(R.layout.activity_uicontrol);
		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);
        btn0 = (Button)findViewById(R.id.b0);
        btn1 = (Button)findViewById(R.id.b1);
        btn2 = (Button)findViewById(R.id.b2);
        btn3 = (Button)findViewById(R.id.b3);
        btn4 = (Button)findViewById(R.id.b4);
        btn5 = (Button)findViewById(R.id.b5);
        btn6 = (Button)findViewById(R.id.b6);
        btn7 = (Button)findViewById(R.id.b7);
        btn8 = (Button)findViewById(R.id.b8);
        btn9 = (Button)findViewById(R.id.b9);
        btnPoint = (Button)findViewById(R.id.POINT);
        btnAdd = (Button)findViewById(R.id.ADD);
        btnSub = (Button)findViewById(R.id.SUB);
        btnMult = (Button)findViewById(R.id.MULT);
        btnDivid = (Button)findViewById(R.id.DIV);
        btnEqual = (Button)findViewById(R.id.EQUAL);
        btnAllClear = (Button)findViewById(R.id.AC);
        btnClear = (Button)findViewById(R.id.CLR);
        btnPow = (Button)findViewById(R.id.POW);
        btnInv = (Button)findViewById(R.id.INV);
        btnVoice = (Button)findViewById(R.id.dummy_button);
        txtNumber = (TextView)findViewById(R.id.textNumber);
        txtNumber.setText("0");

        btn0.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("0");} });
        btn1.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("1");} });
        btn2.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("2");} });
        btn3.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("3");} });
        btn4.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("4");} });
        btn5.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("5");} });
        btn6.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("6");} });
        btn7.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("7");} });
        btn8.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("8");} });
        btn9.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber("9");} });

        btnPoint.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setNumber(".");} });
        btnClear.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setCommand(Calculator.CLR_CMD);} });
        btnAllClear.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setCommand(Calculator.AC_CMD);} });
        btnEqual.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setCommand(Calculator.EQUAL_CMD);} });
        btnAdd.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setCommand(Calculator.ADD_CMD);} });
        btnSub.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setCommand(Calculator.SUB_CMD);} });
        btnMult.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setCommand(Calculator.MUL_CMD);} });
        btnDivid.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setCommand(Calculator.DIV_CMD);} });
        btnInv.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { invPolarity();} });
        btnPow.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { setCommand(Calculator.POW_CMD);} });
        btnVoice.setOnClickListener(new View.OnClickListener() { public void onClick(View view) { startVoiceRecognition();} });
        
		// get error message from the resouce
        ERR_INVALID_INPUT = getString(R.string.errMsg_invalidInput);
        ERR_OVERFLOW = getString(R.string.errMsg_overflow);
        ERR_UNDERFLOW = getString(R.string.errMsg_underflow);
        ERR_ZERODIVIDE = getString(R.string.errMsg_zeroDivide);
        ERR_INVALIDPOW = getString(R.string.errMsg_invalidPow);
        ERR_INVALID_FORMULA = getString(R.string.errMsg_invalidFormula);  
        ERR_INVALID_VOICERECOGN = getString(R.string.errMsg_invalidVoiceRecogn); 
        
        
		// Set up an instance of SystemUiHider to control the system UI for
		// this activity.
		mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
			// Cached values.
			int mControlsHeight;
			int mShortAnimTime;

			@Override
			@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
			public void onVisibilityChange(boolean visible) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
					// If the ViewPropertyAnimator API is available
					// (Honeycomb MR2 and later), use it to animate the
					// in-layout UI controls at the bottom of the
					// screen.
					if (mControlsHeight == 0) {
						mControlsHeight = controlsView.getHeight();
					}
					if (mShortAnimTime == 0) {
						mShortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
					}
					controlsView.animate().translationY(visible ? 0 : mControlsHeight).setDuration(mShortAnimTime);
				} else {
					// If the ViewPropertyAnimator APIs aren't
					// available, simply show or hide the in-layout UI
					// controls.
					controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
				}

				if (visible && AUTO_HIDE) {
					// Schedule a hide().
					delayedHide(AUTO_HIDE_DELAY_MILLIS);
				}
			}
		});

		// Set up the user interaction to manually show or hide the system UI.
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (TOGGLE_ON_CLICK) {
					mSystemUiHider.toggle();
				} else {
					mSystemUiHider.show();
				}
			}
		});

		// Upon interacting with UI controls, delay any scheduled hide()
		// operations to prevent the jarring behavior of controls going away
		// while interacting with the UI.
		findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// Trigger the initial hide() shortly after the activity has been
		// created, to briefly hint to the user that UI controls
		// are available.
		delayedHide(100);
	}

	/**
	 * Touch listener to use for in-layout UI controls to delay hiding the
	 * system UI. This is to prevent the jarring behavior of controls going away
	 * while interacting with activity UI.
	 */
	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};

	/**
	 * Schedules a call to hide() in [delay] milliseconds, canceling any
	 * previously scheduled calls.
	 */
	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}

	
	/*****************************************************
	 * UI control logic
	 ****************************************************/
    /*
     * @brief sinverse polarity of the number in the TextView
     * @section Description
     *
     * If intger number is set, flowing point won't be displayed
     *
     * @section Parameters
     *
     * @exception NumberFormatException
     * @return
     */
    void invPolarity(){
        CharSequence cs = txtNumber.getText();
        String str = cs.toString();
        double num=0.0;
        try {
            num = Double.parseDouble(str);
        }
        catch(NumberFormatException e){
            showAlert(ERR_INVALID_FORMULA);
            num =0.0;
        }
        txtNumber.setText(convertToIntExpression (Double.toString(-num)));
    }

    /**
     * @brief set the number to the TextView
     * @section Description
     *
     *
     *
     * @section Parameters
     *
     * @param[in] str - String of number key
     * @exception
     * @return
     */
    protected void setNumber(String str){
        if(str.compareTo(".")==0) {
            if (bTypingPoint) {
                return;
            } else {
                bTypingPoint = true;
                if(!bTypingNumber){
                	bTypingNumber = true;
                	txtNumber.setText("0");
                }
            }
        }
        if (bTypingNumber) {
            txtNumber.append(str);
        } else {
            txtNumber.setText(str);
            bTypingNumber = true;
        }
    }

    /**
     * @brief set the command or operator
     * @section Description
     *
     * If any number was input in TextView, set the number to Calculation class, then excute the command.
     *
     * @section Parameters
     *
     * @param[in] int - identifier of the command requested
     * @exception ArithmeticException
     * @return 		true: successfully done/ false: failed
     */
    protected boolean setCommand(int cmd){
        boolean err=false;
        if(bTypingNumber){
            CharSequence cs = txtNumber.getText();
            String str = cs.toString();
            double num=0.0;
            try {
                num = Double.parseDouble(str);
            }
            catch(NumberFormatException e){
                showAlert(ERR_INVALID_INPUT);
                cmd= Calculator.CLR_CMD;
                err=true;
                num =0.0;
            }
            if(bReversePolish){
				calc.reversePolish(num,Calculator.NO_CMD);
            }else{
            	calc.setOperand(num,false);
            }
            bTypingNumber=false;
            bTypingPoint =false;
        }else{
        	if((bReversePolish)&&(cmd!=Calculator.AC_CMD)&&(cmd!=Calculator.CLR_CMD)){
        		showAlert(ERR_INVALID_FORMULA);    // strict formula check for reverse polish mode;
                err=true;
        	}
        }
        if(!err) {
            try {
            	double answer;
				if(bReversePolish){
					answer = calc.reversePolish(0.0,cmd);
				}else{
					answer = calc.performOperation(cmd,false);
				}
                String cstr = convertToIntExpression (Double.toString(answer));
                txtNumber.setText(cstr);
            }
            catch(ArithmeticException e){
            	err=true;
                switch(e.getMessage()){
                    case "MsgOverflow":
                        showAlert(ERR_OVERFLOW);
                        break;
                    case "MsgUnderflow":
                        showAlert(ERR_UNDERFLOW);
                        break;
                    case "MsgZeroDivide":
                        showAlert(ERR_ZERODIVIDE);
                        break;
                    case "MsgInvalidPow":
                        showAlert(ERR_INVALIDPOW);
                        break;
                    case "MsgInvalidFormula":
                    	showAlert(ERR_INVALID_FORMULA);
                        break;                        
                    default:break;
                }
            }
        }
        return !err;
    }

    /*
     * @brief Trancate down below floating point, if it's an integer value.
     * @section Description
     *
     * Trancate down below floating point, if it's an integer value.
     *
     * @section Parameters
     *
     * @param[in] str - String of number key
     *
     * @exception
     * @return
     */
    protected String convertToIntExpression (String str)
    {
        if(str.indexOf("E")<0){  // There's no "E" in the String.
            int index=str.indexOf(".");
            if(index>=0){  // there's a "." in the String.
                boolean zero=true;
                for(int i=index+1; i<str.length(); i++) {
                    if (str.substring(i, i + 1).compareTo("0") != 0) {
                        zero = false;
                        break;
                    }
                }
                if(zero) {
                    return str.substring(0, index);
                }
            }
        }

        return str;
    }

    /*
     * @brief Show alert
     * @section Description
     *
     * Show alert in the TextView
     *
     * @section Parameters
     *
     * @param[in] txt - alert messsage
     *
     * @exception
     * @return
     */
    protected void showAlert(CharSequence txt){
    	txtNumber.setText(txt);
    }
    
    /*
     * voice recognition
     */
    protected void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please Speech");
        startActivityForResult(intent, REQUEST_CODE);
    }

    /*
     * recieve the result of the voice recognition
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check requestCode to see if it's from the result of own Intent for further procedure.
		if ((REQUEST_CODE == requestCode) && (RESULT_OK == resultCode)) {
			ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			StringBuffer stringBuffer = new StringBuffer();

			// Parse the result of recognition by refering arithmetic word dictionary.
			// Only one result with best matching with the dictionary will be selected.
			ArrayList<DictionaryEntry> result = morph.analyze(results);
			setCommand(Calculator.AC_CMD);
			if(result.size()==0){
				txtNumber.setText(ERR_INVALID_VOICERECOGN);
			}else{
				for (int i = 0; i < result.size(); i++){
					DictionaryEntry de = result.get(i);
					stringBuffer.append(de.word);
					// Perform the calculation with the result
					if(de.getID()==Calculator.NO_CMD){
						setNumber(de.num);
					}else{
						if(!setCommand(de.id)){
							break;
						}
					}
				}
				
				// show the result
				Toast.makeText(this, stringBuffer.toString(), Toast.LENGTH_LONG).show();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}

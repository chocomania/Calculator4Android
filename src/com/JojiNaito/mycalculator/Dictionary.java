package com.JojiNaito.mycalculator;

import java.util.ArrayList;

/*******************************************************************************
 * @file Dictionary
 * @brief  Calculator core
 * @date 2015/8/30
 * @Author	Joji Naito
 *******************************************************************************/
class Dictionary{
	
	int nEntry;
	ArrayList<DictionaryEntry> al;
	
    /**
     * @brief Constructor
     * @section Description
     *  Constructor
     * @return
     */
    Dictionary(){
    	al = new ArrayList<DictionaryEntry> ();
    	al.add(new DictionaryEntry("ぜろ","0",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("れい","0",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("0","0",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("いち","1",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("1","1",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("に","2",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("2","2",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("さん","3",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("3","3",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("よん","4",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("4","4",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("ご","5",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("5","5",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("ろく","6",Calculator.NO_CMD)); 	
    	al.add(new DictionaryEntry("6","6",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("なな","7",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("しち","7",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("7","7",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("はち","8",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("8","8",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("きゅう","9",Calculator.NO_CMD));
    	al.add(new DictionaryEntry("9","9",Calculator.NO_CMD));
    	al.add(new DictionaryEntry(".",".",Calculator.NO_CMD));    	
    	al.add(new DictionaryEntry("たす","cmd",Calculator.ADD_CMD));
    	al.add(new DictionaryEntry("足す","cmd",Calculator.ADD_CMD));
    	al.add(new DictionaryEntry("+","cmd",Calculator.ADD_CMD));
    	al.add(new DictionaryEntry("ひく","cmd",Calculator.SUB_CMD));
    	al.add(new DictionaryEntry("引く","cmd",Calculator.SUB_CMD));
    	al.add(new DictionaryEntry("-","cmd",Calculator.SUB_CMD));
    	al.add(new DictionaryEntry("かける","cmd",Calculator.MUL_CMD));
    	al.add(new DictionaryEntry("掛ける","cmd",Calculator.MUL_CMD));
    	al.add(new DictionaryEntry("*","cmd",Calculator.MUL_CMD));
    	al.add(new DictionaryEntry("わる","cmd",Calculator.DIV_CMD));
    	al.add(new DictionaryEntry("割る","cmd",Calculator.DIV_CMD));
    	al.add(new DictionaryEntry("/","cmd",Calculator.DIV_CMD));
    	al.add(new DictionaryEntry("べきじょう","cmd",Calculator.POW_CMD));
    	al.add(new DictionaryEntry("は","cmd",Calculator.EQUAL_CMD));
    	al.add(new DictionaryEntry("わ","cmd",Calculator.EQUAL_CMD));
    	al.add(new DictionaryEntry("では","cmd",Calculator.EQUAL_CMD));
    	al.add(new DictionaryEntry("イコール","cmd",Calculator.EQUAL_CMD));
    	al.add(new DictionaryEntry("=","cmd",Calculator.EQUAL_CMD));
    	al.add(new DictionaryEntry("クリア","cmd",Calculator.CLR_CMD));
    	al.add(new DictionaryEntry("オールクリア","cmd",Calculator.AC_CMD));
    	nEntry = al.size();
    };
    
    /**
     * @brief getter for size of the dictionary
     * @section Description
     * @return
     */
    public int getSize(){
    	return nEntry;
    }
    
    /**
     * @brief getter for Japanese phonetic expression identified by index
     * @section Description
     * @return
     */
    public String getWord(int index){
    	if((index>=0)&&(index<nEntry)){
    		DictionaryEntry e=al.get(index);
    		return e.getWord();
    	}else{
    		return "";
    	}
    }
    
    /**
     * @brief getter for an entry identified by index
     * @section Description
     * @return
     */
    DictionaryEntry getEntry(int index){
    	return al.get(index);
    }
}

class DictionaryEntry{
	String word;
	String num;
	int id;

    /**
     * @brief Constructor
     * @section Description
     *  Constructor
     * @return
     */
    public DictionaryEntry(String word, String num, int id){
        this.word = word;
        this.num = num;
        this.id = id;
    };

    /**
     * @brief getter for Japanese phonetic expression
     * @section Description
     * @return
     */
    public String getWord(){
    	return word;
    };

    /**
     * @brief getter for numeric number in String
     * @section Description
     * @return
     */
    public String getNum(){
    	return num;
    };

    /**
     * @brief getter for ID
     * @section Description
     * @return
     */
    public int getID(){
    	return id;
    };

}



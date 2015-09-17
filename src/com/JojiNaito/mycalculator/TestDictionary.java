package com.JojiNaito.mycalculator;

import junit.framework.TestCase;
import java.util.ArrayList;

public class TestDictionary extends TestCase {
	Dictionary dict;
	ArrayList<DictionaryEntry> reference;
	int nReference;
	
	public TestDictionary(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		dict = new Dictionary();
		reference = new ArrayList<DictionaryEntry>();
    	reference.add(new DictionaryEntry("ぜろ","0",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("れい","0",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("0","0",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("いち","1",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("1","1",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("に","2",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("2","2",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("さん","3",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("3","3",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("よん","4",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("4","4",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("ご","5",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("5","5",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("ろく","6",Calculator.NO_CMD)); 	
    	reference.add(new DictionaryEntry("6","6",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("なな","7",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("しち","7",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("7","7",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("はち","8",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("8","8",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("きゅう","9",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry("9","9",Calculator.NO_CMD));
    	reference.add(new DictionaryEntry(".",".",Calculator.NO_CMD));    	
    	reference.add(new DictionaryEntry("たす","cmd",Calculator.ADD_CMD));
    	reference.add(new DictionaryEntry("足す","cmd",Calculator.ADD_CMD));
    	reference.add(new DictionaryEntry("+","cmd",Calculator.ADD_CMD));
    	reference.add(new DictionaryEntry("ひく","cmd",Calculator.SUB_CMD));
    	reference.add(new DictionaryEntry("引く","cmd",Calculator.SUB_CMD));
    	reference.add(new DictionaryEntry("-","cmd",Calculator.SUB_CMD));
    	reference.add(new DictionaryEntry("かける","cmd",Calculator.MUL_CMD));
    	reference.add(new DictionaryEntry("掛ける","cmd",Calculator.MUL_CMD));
    	reference.add(new DictionaryEntry("*","cmd",Calculator.MUL_CMD));
    	reference.add(new DictionaryEntry("わる","cmd",Calculator.DIV_CMD));
    	reference.add(new DictionaryEntry("割る","cmd",Calculator.DIV_CMD));
    	reference.add(new DictionaryEntry("/","cmd",Calculator.DIV_CMD));
    	reference.add(new DictionaryEntry("べきじょう","cmd",Calculator.POW_CMD));
    	reference.add(new DictionaryEntry("は","cmd",Calculator.EQUAL_CMD));
    	reference.add(new DictionaryEntry("わ","cmd",Calculator.EQUAL_CMD));
    	reference.add(new DictionaryEntry("では","cmd",Calculator.EQUAL_CMD));
    	reference.add(new DictionaryEntry("イコール","cmd",Calculator.EQUAL_CMD));
    	reference.add(new DictionaryEntry("=","cmd",Calculator.EQUAL_CMD));
    	reference.add(new DictionaryEntry("クリア","cmd",Calculator.CLR_CMD));
    	reference.add(new DictionaryEntry("オールクリア","cmd",Calculator.AC_CMD));
    	nReference = reference.size();
    }

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testDictionary() {
		assertNotNull("Broken dictionary", reference);
	}

	public void testGetSize() {
		assertEquals("Broken dictionary", dict.getSize(),nReference);
	}
	
	public void testGetWord() {
		for(int i=0; i<dict.getSize(); i++){
			assertEquals("Broken dictionary",dict.getWord(i),reference.get(i).getWord());
		}
		assertEquals("Broken dictionary", dict.getSize(),nReference);
	}

	public void testGetEntry() {
		for(int i=0; i<dict.getSize(); i++){
			assertEquals("Broken dictionary",dict.getEntry(i).getNum(),reference.get(i).getNum());
		}
		for(int i=0; i<dict.getSize(); i++){
			assertEquals("Broken dictionary",dict.getEntry(i).getID(),reference.get(i).getID());
		}
	}
}

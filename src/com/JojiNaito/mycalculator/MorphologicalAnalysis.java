package com.JojiNaito.mycalculator;

import java.util.ArrayList;

/*******************************************************************************
 * @file MorphologicalAnalysis.c
 * @brief  MorphologicalAnalysis
 * @date 2015/9/5
 * @Author	Joji Naito
 *******************************************************************************/
class MorphologicalAnalysis{

	protected ArrayList<DictionaryEntry> finalResult;
	protected Dictionary dict;
	protected UIcontrol ui;

    /**
     * @brief Constructor
     * @section Description
     *  Constructor
     * @return
     */
	MorphologicalAnalysis(Dictionary dict,UIcontrol ui){
		this.finalResult = new ArrayList<DictionaryEntry>();
		this.dict = dict;
		this.ui = ui;
	}

    /**
     * @brief analyze and reconstruct a sentense for calculation
     * @section Description
     *
     *  Analyzes group of candidate and reconstruct a sentence
     *  which only includes words in pre-defined dictionary.
     *
     * @section Parameters
     *
     * @param[in] candidates - list of voice recognition results 
     *
     * @return  list of Dictionary entries selected from the candidate
     */
	ArrayList<DictionaryEntry> analyze(ArrayList<String> candidates)
	{
		// Parse the result of recognition by referring arithmetic word dictionary.
		int offset;
		int bestResult=0;
		finalResult.clear();
		ArrayList<DictionaryEntry> tmpBuf = new ArrayList<DictionaryEntry>();
		for (int i = 0; i < candidates.size(); i++) {
			offset=0;
			String candidate = candidates.get(i);
			String bestWord;
			tmpBuf.clear();
			boolean bFoundEqual=false;
			do{
				bestWord = null;
				int bestPos = candidate.length();
				// look up the dictionary
				for(int j=0; j<dict.getSize(); j++){
					String word = dict.getWord(j);
					// seek for the key word with offset
					int pos = candidate.indexOf(word, offset);
					if((pos>=0)   // found something
							&&(pos<bestPos)){ // pick one which comes earlier
						bestWord=new String(word);
						bestPos=pos;
						bestResult=j;
					}
				}
				if(bestWord!=null){  // found the word
					// put only the arithmetic word into the buffer
					DictionaryEntry de = dict.getEntry(bestResult);
					tmpBuf.add(de);
					offset = bestPos + bestWord.length(); // look for next charactors
					if(de.id == Calculator.EQUAL_CMD){ // reached '=' then stop the look up the dictionary
						bFoundEqual=true;
						bestWord = null;
					}
				}
			}while(bestWord!=null);
			
			if(!bFoundEqual){
				continue;
			}

			boolean bContinue=false;
			ui.setCommand(Calculator.AC_CMD);
			for (int j = 0; j < tmpBuf.size(); j++){
				// Perform the calculation with the result
				DictionaryEntry de = tmpBuf.get(j);
				if(de.getID()==Calculator.NO_CMD){
					ui.setNumber(de.getNum());
				}else{
					if(!ui.setCommand(de.getID())){
						bContinue=true;
						break;
					}
				}
			}
			if(bContinue){
				continue;
			}

			if(tmpBuf.size()>finalResult.size()){	// Pick the candidate with more matches
				// copy from tmpBuf to finalResult
				finalResult.clear();
				for(int j=0; j<tmpBuf.size(); j++){
					finalResult.add(tmpBuf.get(j));
				}
				break;
			}
			tmpBuf.clear();
		}
		return finalResult;
	}
}

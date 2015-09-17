package com.JojiNaito.mycalculator;

import static java.lang.Math.*;
import java.util.*;

/*******************************************************************************
 * @file Calculator
 * @brief  Calculator
 * @date 2015/8/30
 * @Author	Joji Naito
 *******************************************************************************/
public class Calculator {

    // command ID
    public static final int NO_CMD         =0;
    public static final int EQUAL_CMD      =1;
    public static final int ADD_CMD        =2;
    public static final int SUB_CMD        =3;
    public static final int MUL_CMD        =4;
    public static final int DIV_CMD        =5;
    public static final int POW_CMD	        =6;
    public static final int AC_CMD	        =7;
    public static final int CLR_CMD	         =8;

    // control status ID
    private static final int PHASE_A_READY	     =1; // operandA is ready to be set
    private static final int PHASE_A_DONE	     =2; // operandA was set
    private static final int PHASE_B_READY	     =3; // operandB is ready to be set
    private static final int PHASE_B_DONE	     =4; // operandB was set

    // variables
    private double operandA;
    private double operandB;
    private int operatorID;
    private int phase;
    
    // Stack buffer
    protected Stack<StackEntry> stackBuf1;
    protected Stack<StackEntry> stackBuf2;
    protected LinkedList<StackEntry> fifoBuf;
    
    protected int priorityTab[] = {0,0,1,1,2,2,3,5,5};

    /**
     * @brief Constructor
     * @section Description
     *  Constructor
     * @return
     */
    Calculator(){
        operandA=0.0;
        operandB=0.0;
        operatorID=NO_CMD;
        phase=PHASE_A_READY;
        
        stackBuf1 = new Stack<StackEntry>();
        stackBuf2 = new Stack<StackEntry>();
        fifoBuf = new LinkedList<StackEntry>();
    }
    
    public double reversePolish(double number,int id) throws java.lang.ArithmeticException{
    	double result=0;
    	if(id==AC_CMD){
    		fifoBuf.clear();
    		stackBuf1.clear();
    		stackBuf2.clear();
    		performOperation(id,false);
    	}else if(id==CLR_CMD){
    		fifoBuf.clear();
    		stackBuf1.clear();
    		stackBuf2.clear();
    		performOperation(id,false);
    	}else{
    		StackEntry se = new StackEntry(number,id,priorityTab[id]);
    		if(id!=NO_CMD){  // operator or command]
        		while(stackBuf1.size()>0){
    	    		if(((StackEntry)(stackBuf1.peek())).getPriority()>=(se.getPriority())){ // The operator on the top of the stackBuf1 has higher priority than input operator.
    	    			StackEntry ss = (StackEntry)stackBuf1.pop();
    	    	    	fifoBuf.add(ss);
    				}else{
    					break;
    				}
        		}
    			stackBuf1.push(se);

    			if(id==EQUAL_CMD){
    	    		while(stackBuf1.size()>1){  // leaving the last '=' in stackBuf1
    	    			StackEntry ss = (StackEntry)stackBuf1.pop();
    	    	    	fifoBuf.add(ss);
    	    		}
    	    		if(fifoBuf.size()<3){
    	    			throw new java.lang.ArithmeticException("MsgInvalidFormula");
    	    		}else{
	    	    		while(fifoBuf.size()>0){
	    	    			StackEntry fe = (StackEntry)(fifoBuf.pollFirst());
	    	    			if(fe.getID()==NO_CMD){  // number
	    	    				stackBuf2.push(fe);
	    	    			}else if(stackBuf2.size()>=2){
	    	    				// Execute non reverse polish procedure
	    	    				result=performOperation(AC_CMD,false);
	    	    				StackEntry opB = (StackEntry)stackBuf2.pop();
	    	    				StackEntry opA = (StackEntry)stackBuf2.pop();
	    	    				setOperand(opA.getNum(),false);		
	    	    				performOperation(fe.getID(),false);
	    	    				setOperand(opB.getNum(),false);
	    	    				result=performOperation(EQUAL_CMD,false);
	    	    				StackEntry re = new StackEntry(result,NO_CMD,priorityTab[NO_CMD]);
	    	    				stackBuf2.push(re);
	    	    			}
	    	    		}
    	    		}
    	    		fifoBuf.clear();
    	    		stackBuf1.clear();
    	    		stackBuf2.clear();
    	    	}

    		}else{  //  number
    			fifoBuf.add(se);
    		}
     	}
    	return result;
    }


    /**
     * @brief Set operand to class variables
     * @section Description
     *
     *  Set the input number to operandA when phase is PHASE_A_***  then proceed the phase
     *  Set the input number to operandB when phase is PHASE_B_***  then proceed the phase
     *
     * @section Parameters
     *
     * @param[in] data - input number
     * @param[in] reversePolish - true if reverse polish input is requested
     *
     * @return
     */
    public void setOperand(double data,boolean reversePolish){
    	
    	if(reversePolish){
    		fifoBuf.add(new StackEntry(data,NO_CMD,0));
    	}else{
	        if((phase==PHASE_A_READY)||(phase==PHASE_A_DONE)){
	            operandA = data;
	            phase = PHASE_A_DONE;
	        }else{
	            operandB = data;
	            phase = PHASE_B_DONE;
	        }
    	}
    }

    /**
     * @brief Get operand from class variables
     * @section Description
     *
     *  Get the input number from operandA when phase is PHASE_A_***
     *  Get the input number from operandB when phase is PHASE_B_***
     *
     * @return  numeric number in the operand variable in the input process
     */
    public double getOperand(){
        if((phase==PHASE_A_READY)||(phase==PHASE_A_DONE)){
            return operandA;
        }else{
            return operandB;
        }
    }

    /**
     * @brief Perform operation
     * @section Description
     *
     * Perform the operation when necessary operands and operation are all set.
     *
     * @section Parameters
     *
     * @param[in] cmd - identifier of operational command
     * @param[in] reversePolish - true if reverse polish input is requested
     * 
     * @exception  ArithmeticException
     * @return  the result of the operation
     */
    public double performOperation(int cmd,boolean reversePolish) throws java.lang.ArithmeticException{
        double retValue;
        switch(cmd){
            case CLR_CMD: 	// Clear the operand variable in the input process
                setOperand(0.0,reversePolish);
                retValue = 0.0;
                break;
            case AC_CMD: // Initialize all variables
                operandA = 0.0;
                operandB = 0.0;
                operatorID = cmd;
                phase = PHASE_A_READY;
                retValue = 0.0;
                break;
            default:
                if((EQUAL_CMD == cmd)  // Excute the operation when "=" operator was set
                        ||(phase == PHASE_B_DONE)) { // Excute the operation when any operator and operandB (and A) were set
                    switch (operatorID) {
                        case ADD_CMD:     //operandA = operandA + operandB;
                            operandA = opAdd(operandA, operandB);
                            break;
                        case SUB_CMD:    // operandA = operandA - operandB;
                            operandA = opSub(operandA, operandB);
                            break;
                        case MUL_CMD:  //  operandA = operandA * operandB;
                            operandA = opMult(operandA, operandB);
                            break;
                        case DIV_CMD:  //operandA = operandA / operandB;
                            operandA = opDivide(operandA, operandB);
                            break;
                        case POW_CMD:      //operandA = pow(operandA, operandB);
                            operandA = opPow(operandA, operandB);
                            break;
                        default:
                            break;
                    }
                }
                if(EQUAL_CMD == cmd){
                    phase = PHASE_A_READY;   // operandA will be set for the next number input
                }else{
                    operatorID = cmd;  // Set the input command to operatorID
                    if((phase == PHASE_A_READY)||(phase == PHASE_A_DONE)||(phase == PHASE_B_DONE)) {
                        operandB = operandA;  // This handles an input sequence "4 + = " returning 8
                    }
                    phase = PHASE_B_READY;  // operandB will be set for the next number input
                }
                retValue = operandA;
                break;
        }
        return retValue;
    }


    /**
     * @brief Perform ADD operation
     * @section Description
     *
     * When it's examined that the absolute value of the result
     * would excess MAX in double, returns ArithmeticException
     * for overflow.
     *
     * @section Parameters
     *
     * @param[in] cmd - identifier of operational command
     * @exception  ArithmeticException
     * @return  the result of the operation
     */
   private double opAdd(double a,double b)
   {
        if(( ( a > 0.0) == (b > 0.0) ) && (abs(a) > Double.MAX_VALUE - abs(b) )){
            throw new java.lang.ArithmeticException("MsgOverflow");
        }else{
            return a+b;
        }
    }

   /**
    * @brief Perform SUB operation
    * @section Description
    *
    * When it's examined that the absolute value of the result
    * would excess MAX in double, returns ArithmeticException
    * for overflow.
    *
    * @section Parameters
    *
    * @param[in] cmd - identifier of operational command
    * @exception  ArithmeticException
    * @return  the result of the operation
    */
    private double opSub(double a,double b)
    {
        if(( ( a > 0.0) == (b < 0.0) ) && (abs(a) > Double.MAX_VALUE - abs(b) )){
            throw new java.lang.ArithmeticException("MsgOverflow");
        }else{
            return a-b;
        }
    }

    /**
     * @brief Perform Multiply operation
     * @section Description
     *
     * When it's examined that the absolute value of the result
     * would excess MAX in double, returns ArithmeticException
     * for overflow.
     * When it's examined that the absolute value of the result
     * would be less than MIN in double, returns ArithmeticException
     * for underflow.
     *
     * @section Parameters
     *
     * @param[in] cmd - identifier of operational command
     * @exception  ArithmeticException
     * @return  the result of the operation
     */
    private double opMult(double a,double b)
    {
        if(( ( abs(a) > 1.0) && (abs(b) > 1.0) ) && ( abs(a) > Double.MAX_VALUE / abs(b) )){
            throw new java.lang.ArithmeticException("MsgOverflow");
        }else if(( ( abs(a) < 1.0) && (abs(b) < 1.0) ) && ( abs(a) < Double.MIN_VALUE / abs(b) )){
            throw new java.lang.ArithmeticException("MsgUnderflow");
        }else{
            return a*b;
        }
    }

    /**
     * @brief Perform Divide operation
     * @section Description
     *
     * When b is 0, returns ArithmeticException for zero divide.
     * When it's examined that the absolute value of the result
     * would excess MAX in double, returns ArithmeticException
     * for overflow.
     * When it's examined that the absolute value of the result
     * would be less than MIN in double, returns ArithmeticException
     * for underflow.
     *
     * @section Parameters
     *
     * @param[in] cmd - identifier of operational command
     * @exception  ArithmeticException
     * @return  the result of the operation
     */
    private double opDivide(double a,double b)
    {
        if(b==0.0){
            throw new java.lang.ArithmeticException("MsgZeroDivide");
        }else if(( ( abs(a) > 1.0) && (abs(b) < 1.0) ) && ( abs(a) > Double.MAX_VALUE * abs(b) )){
            throw new java.lang.ArithmeticException("MsgOverflow");
        }else if(( ( abs(a) < 1.0) && (abs(b) > 1.0) ) && ( abs(a) < Double.MIN_VALUE * abs(b) )){
            throw new java.lang.ArithmeticException("MsgUnderflow");
        }else{
            return a/b;
        }
    }

    /**
     * @brief Perform Power operation
     * @section Description
     *
     * When a is negative and b is non integer, returns ArithmeticException
     * for invalid Power operation.
     * When it's examined that the absolute value of the result
     * would excess MAX in double, returns ArithmeticException
     * for overflow.
     * When it's examined that the absolute value of the result
     * would be less than MIN in double, returns ArithmeticException
     * for underflow.
     *
     * @section Parameters
     *
     * @param[in] cmd - identifier of operational command
     * @exception  ArithmeticException
     * @return  the result of the operation
     */
    private double opPow(double a,double b)
    {
        if((b != (double)(long)b)&&(a<0)){
            throw new java.lang.ArithmeticException("MsgInvalidPow");
        }else if(( b > 1.0 ) && ( abs(a) > pow(Double.MAX_VALUE ,1.0/b) ) ) {
            throw new java.lang.ArithmeticException("MsgOverflow");
        }else if(( b < -1.0 ) && ( abs(a) > pow(Double.MIN_VALUE ,1.0/b) ) ) {
            throw new java.lang.ArithmeticException("MsgUnderflow");

        }else{
            return pow(a,b);
        }
    }
}
    
/* 
 * Defines each entry of the stack for reverse polish mode
 */
class StackEntry{    	

	double num;
	int id;
	int priority;

	StackEntry(double n,int i,int p){
		num=n;
		id=i;
		priority=p;
	};

    public double getNum(){
    	return num;
    };

    public int getID(){
    	return id;
    };

    public int getPriority(){
    	return priority;
    };

};



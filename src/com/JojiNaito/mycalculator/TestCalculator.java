package com.JojiNaito.mycalculator;

import junit.framework.TestCase;

public class TestCalculator extends TestCase {

	Calculator c;
	
	public TestCalculator(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		c = new Calculator();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/***************************************
	* Basic operation test cases
	***************************************/
	public void testBasicOperation() {
		testAdd1();
		testAdd2();
		testSub1();
		testSub2();
		testMult1();
		testMult2();
		testDiv1();
		testDiv2();
		testPow1();
		testPow2();
		testPow3();
		testCLR();
		testAC();
	}
	
	/***************************************
	* Special operation test cases
	***************************************/
	public void testSpecialOperation() {

		TestRepeatOpe();		
		TestOmitOpeA();
		TestOmitOpeB();
		TestReuseOpeB();
		TestRetryOpe();
	}

	/***************************************
	* Illegal test cases
	***************************************/
	public void testIllegalOperation() {
		testAddOverflow();
		testMultOverflow1();
		testMultOverflow2();
		testMultUnderflow();
		testZeroDivide();
		testDivUnderflow();
		testPowOverflow();
		testPowUnderflow();
		testPowInvalid();
	}
	
/***************************************
* Basic operation test cases
***************************************/
    /**
     * @brief test Add operation
     * @section Basic Operation test cases
     *
     * 123+456=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testAdd1() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(123.0,false);
		    c.performOperation(Calculator.ADD_CMD,false);
		    c.setOperand(456.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 579.0);
	}
	
    /**
     * @brief test Add operation
     * @section Basic Operation test cases
     *
     * -98+50=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testAdd2() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(-98.0,false);
		    c.performOperation(Calculator.ADD_CMD,false);
		    c.setOperand(50.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, -48.0);
	}
	
    /**
     * @brief test SUB operation
     * @section Basic Operation test cases
     *
     * 789-456=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testSub1() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(789.0,false);
		    c.performOperation(Calculator.SUB_CMD,false);
		    c.setOperand(456.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 333.0);
	}
	
    /**
     * @brief test SUB operation
     * @section Basic Operation test cases
     *
     * -97-6=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testSub2() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(-97.0,false);
		    c.performOperation(Calculator.SUB_CMD,false);
		    c.setOperand(6.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, -103.0);
	}
	
    /**
     * @brief test MULTPLY operation
     * @section Basic Operation test cases
     *
     * 99*123=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testMult1() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(99.0,false);
		    c.performOperation(Calculator.MUL_CMD,false);
		    c.setOperand(123.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 12177.0);
	}
	
    /**
     * @brief test MULTPLY operation
     * @section Basic Operation test cases
     *
     * 45*(-51)=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testMult2() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(45.0,false);
		    c.performOperation(Calculator.MUL_CMD,false);
		    c.setOperand(-51.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, -2295.0);
	}
	
    /**
     * @brief test DIVIDE operation
     * @section Basic Operation test cases
     *
     * 1/8=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testDiv1() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(1.0,false);
		    c.performOperation(Calculator.DIV_CMD,false);
		    c.setOperand(8.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 0.125);
	}
	
    /**
     * @brief test DIVIDE operation
     * @section Basic Operation test cases
     *
     * -1/7=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testDiv2() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(-1.0,false);
		    c.performOperation(Calculator.DIV_CMD,false);
		    c.setOperand(7.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, -0.14285714285714285);
	}
	
    /**
     * @brief test POW operation
     * @section Basic Operation test cases
     *
     * 12^11=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testPow1() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(12.0,false);
		    c.performOperation(Calculator.POW_CMD,false);
		    c.setOperand(11.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 7.43008370688E11);
	}
	
    /**
     * @brief test POW operation
     * @section Basic Operation test cases
     *
     * 0.5^0.3=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testPow2() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(0.5,false);
		    c.performOperation(Calculator.POW_CMD,false);
		    c.setOperand(0.3,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 0.8122523963562356);
	}
	
    /**
     * @brief test POW operation
     * @section Basic Operation test cases
     *
     * 34^(-1.3)=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testPow3() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(34,false);
		    c.performOperation(Calculator.POW_CMD,false);
		    c.setOperand(-1.3,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 0.0102112147029065);
	}
	
    /**
     * @brief test CLR command
     * @section Basic Operation test cases
     *
     * 4+9 -> CLR -> 3=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testCLR() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(4,false);
		    c.performOperation(Calculator.ADD_CMD,false);
		    c.setOperand(9,false);
		    c.performOperation(Calculator.CLR_CMD,false);
		    c.setOperand(3,false);		    
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 7.0);
	}
	
	/**
     * @brief test AC command
     * @section Basic Operation test cases
     *
     * 4+9 -> AC -> 3=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void testAC() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(4,false);
		    c.performOperation(Calculator.ADD_CMD,false);
		    c.setOperand(9,false);
		    c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(3,false);		    
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 3.0);
	}
/***************************************
* Special operation test cases
***************************************/
    /**
     * @brief test repeat operation
     * @section Special Operation test cases
     *
     * 200x1.01=　->　=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void TestRepeatOpe() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(200.0,false);
		    c.performOperation(Calculator.MUL_CMD,false);
		    c.setOperand(1.01,false);
		    c.performOperation(Calculator.EQUAL_CMD,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 204.02);
	}
	
    /**
     * @brief test omit operand A
     * @section Special Operation test cases
     *
     * -2=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void TestOmitOpeA() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(-2.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, -2.0);
	}
	
    /**
     * @brief test omit operand B
     * @section Special Operation test cases
     *
     * 3+= → +=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void TestOmitOpeB() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(3.0,false);
		    c.performOperation(Calculator.ADD_CMD,false);
		    c.performOperation(Calculator.EQUAL_CMD,false);
		    c.performOperation(Calculator.ADD_CMD,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);		    
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 12.0);
	}
	
    /**
     * @brief test reuse of operand B
     * @section Special Operation test cases
     *
     * 100x0.08= -> 200=

     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void TestReuseOpeB() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(100.0,false);
		    c.performOperation(Calculator.MUL_CMD,false);
		    c.setOperand(0.08,false);
		    c.performOperation(Calculator.EQUAL_CMD,false);
		    c.setOperand(200.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);		    
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 16.0);
	}

    /**
     * @brief test retry typing operator
     * @section Special Operation test cases
     *
     * 1+2+　→　*4=
     *
     * @section Parameters
     *
     * @exception  ArithmeticException
     * @return 
     */
	void TestRetryOpe() {
		double answer=0.0;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(1.0,false);
		    c.performOperation(Calculator.ADD_CMD,false);
		    c.setOperand(2.0,false);
		    c.performOperation(Calculator.ADD_CMD,false);
		    c.performOperation(Calculator.MUL_CMD,false);
		    c.setOperand(4.0,false);
		    answer = c.performOperation(Calculator.EQUAL_CMD,false);		    
		}catch(ArithmeticException e){
			fail("Unexpected exception");
		}
		assertEquals("Unexpected result", answer, 12.0);
	}
		
/***************************************
* Illegal test cases
***************************************/
    /**
     * @brief Overflow test by ADD operation
     * @section Illegal test cases
     *
     * (10^308)+(10^308)
     *
     * @section Parameters
     *
     * @exception  ArithmeticException MsgOverflow
     * @return 
     */
	void testAddOverflow() {
		boolean bErr=false;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(10.0,false);
		    c.performOperation(Calculator.POW_CMD,false);
		    c.setOperand(308.0,false);
		    c.performOperation(Calculator.ADD_CMD,false);
		    c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			assertEquals("Unexpected exception", e.getMessage().compareTo("MsgOverflow"), 0); 
			bErr=true;
		}
		assertEquals("no expected exception", bErr, true);
	}
	
    /**
     * @brief Overflow test by MULTPLY operation
     * @section Illegal test cases
     *
     * (10^308)*10
     *
     * @section Parameters
     *
     * @exception  ArithmeticException MsgOverflow
     * @return 
     */
	public void testMultOverflow1() {
		boolean bErr=false;
		try {
			c.performOperation(Calculator.AC_CMD,false);
	        c.setOperand(10.0,false);
	        c.performOperation(Calculator.POW_CMD,false);
	        c.setOperand(308.0,false);
	        c.performOperation(Calculator.MUL_CMD,false);
	        c.setOperand(10.0,false);
	        c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			assertEquals("Unexpected exception", e.getMessage().compareTo("MsgOverflow"), 0); 
			bErr=true;
		}
		assertEquals("no expected exception", bErr, true);
	}	
	
    /**
     * @brief Overflow test by MULTPLY operation
     * @section Illegal test cases
     *
     * (10^308)*(-10)
     *
     * @section Parameters
     *
     * @exception  ArithmeticException MsgOverflow
     * @return 
     */
	public void testMultOverflow2() {
		boolean bErr=false;
		try {
			c.performOperation(Calculator.AC_CMD,false);
	        c.setOperand(10.0,false);
	        c.performOperation(Calculator.POW_CMD,false);
	        c.setOperand(308.0,false);
	        c.performOperation(Calculator.MUL_CMD,false);
	        c.setOperand(-10.0,false);
	        c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			assertEquals("Unexpected exception", e.getMessage().compareTo("MsgOverflow"), 0); 
			bErr=true;
		}
		assertEquals("no expected exception", bErr, true);
	}	
	
    /**
     * @brief Underflow test by MULTPLY operation
     * @section Illegal test cases
     *
     * (10^(-323))*0.1
     *
     * @section Parameters
     *
     * @exception  ArithmeticException MsgUnderflow
     * @return 
     */
	public void testMultUnderflow() {
		boolean bErr=false;
		try {
			c.performOperation(Calculator.AC_CMD,false);
	        c.setOperand(10.0,false);
	        c.performOperation(Calculator.POW_CMD,false);
	        c.setOperand(-323.0,false);
	        c.performOperation(Calculator.MUL_CMD,false);
	        c.setOperand(0.1,false);
	        c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			assertEquals("Unexpected exception", e.getMessage().compareTo("MsgUnderflow"), 0); 
			bErr=true;
		}
		assertEquals("no expected exception", bErr, true);
	}
    
    /**
     * @brief Zero divide test
     * @section Illegal test cases
     *
     * 1/0
     *
     * @section Parameters
     *
     * @exception  ArithmeticException MsgZeroDivide
     * @return 
     */
	public void testZeroDivide() {
		boolean bErr=false;
		try {
			c.performOperation(Calculator.AC_CMD,false);
	        c.setOperand(1.0,false);
	        c.performOperation(Calculator.DIV_CMD,false);
	        c.setOperand(0.0,false);
	        c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			assertEquals("Unexpected exception", e.getMessage().compareTo("MsgZeroDivide"), 0); 
			bErr=true;
		}
		assertEquals("no expected exception", bErr, true);
	}	
	
    /**
     * @brief Underflow test by DIVIDE operation
     * @section Illegal test cases
     *
     * (10^(-323))/3
     *
     * @section Parameters
     *
     * @exception  ArithmeticException MsgUnderflow
     * @return 
     */
	public void testDivUnderflow() {
		boolean bErr=false;
		try {
			c.performOperation(Calculator.AC_CMD,false);
	        c.setOperand(10.0,false);
	        c.performOperation(Calculator.POW_CMD,false);
	        c.setOperand(-323.0,false);
	        c.performOperation(Calculator.DIV_CMD,false);
	        c.setOperand(3.0,false);
	        c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			assertEquals("Unexpected exception", e.getMessage().compareTo("MsgUnderflow"), 0); 
			bErr=true;
		}
		assertEquals("no expected exception", bErr, true);
	}	
	
    /**
     * @brief Overflow test by POW operation
     * @section Illegal test cases
     *
     * 100^155
     *
     * @section Parameters
     *
     * @exception  ArithmeticException MsgOverflow
     * @return 
     */
	public void testPowOverflow() {
		boolean bErr=false;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(100.0,false);
		    c.performOperation(Calculator.POW_CMD,false);
		    c.setOperand(155.0,false);
		    c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			assertEquals("Unexpected exception", e.getMessage().compareTo("MsgOverflow"), 0); 
			bErr=true;
		}
		assertEquals("no expected exception", bErr, true);
	}
	
    /**
     * @brief Underflow test by POW operation
     * @section Illegal test cases
     *
     * 10^(-324)
     *
     * @section Parameters
     *
     * @exception  ArithmeticException MsgUnderflow
     * @return 
     */
	public void testPowUnderflow() {
		boolean bErr=false;
		try {
			c.performOperation(Calculator.AC_CMD,false);
		    c.setOperand(10.0,false);
		    c.performOperation(Calculator.POW_CMD,false);
		    c.setOperand(-324.0,false);
		    c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			assertEquals("Unexpected exception", e.getMessage().compareTo("MsgUnderflow"), 0); 
			bErr=true;
		}
		assertEquals("no expected exception", bErr, true);
	}	
	
    /**
     * @brief Invalid POW operation test
     * @section Illegal test cases
     *
     * (-1)^1.1
     *
     * @section Parameters
     *
     * @exception  ArithmeticException MsgInvalidPow
     * @return 
     */
	public void testPowInvalid() {
		boolean bErr=false;
		try {
			c.performOperation(Calculator.AC_CMD,false);
	        c.setOperand(-1.0,false);
	        c.performOperation(Calculator.POW_CMD,false);
	        c.setOperand(1.1,false);
	        c.performOperation(Calculator.EQUAL_CMD,false);
		}catch(ArithmeticException e){
			assertEquals("Unexpected exception", e.getMessage().compareTo("MsgInvalidPow"), 0); 
			bErr=true;
		}
		assertEquals("no expected exception", bErr, true);
	}	


}

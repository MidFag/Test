package com.midfag.calc;

import java.math.BigDecimal;

import com.midfag.calc.Enums.Operands;

public class MathOperand implements MathInterface {
	
	public BigDecimal value=BigDecimal.valueOf(0);
	
	public String litera="";
	
	public int prioritet=0;
	public int id=0;
	
	public void some() {
		// TODO Auto-generated method stub
		
	}
	
	public MathOperand(String _value)
	{
		value=new BigDecimal(_value);
	}

	@Override
	public BigDecimal calculate(BigDecimal _num) {
		// TODO Auto-generated method stub
		return null;
	}

	public void log() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BigDecimal calculate() {
		// TODO Auto-generated method stub
		return null;
	}

	public int get_sign() {
		// TODO Auto-generated method stub
		return 1;
	}
	



	
}

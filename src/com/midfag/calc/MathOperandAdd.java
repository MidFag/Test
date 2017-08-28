package com.midfag.calc;

import java.math.BigDecimal;

import com.midfag.calc.Enums.Operands;

public class MathOperandAdd extends MathOperand {

	public MathOperandAdd(String _value) {
		super(_value);
		litera="+";
		prioritet=0;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public BigDecimal calculate(BigDecimal _num) {
		// TODO Auto-generated method stub
		return _num.add(value);
	}
	
	public int get_sign() {
		// TODO Auto-generated method stub
		return 1;
	}
}

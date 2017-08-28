package com.midfag.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.midfag.calc.Enums.Operands;

public class MathOperandSub extends MathOperand {

	public MathOperandSub(String _value) {
		super(_value);
		litera="-";
		prioritet=0;
		// TODO Auto-generated constructor stub
	}

	public BigDecimal calculate(BigDecimal _num) {
		// TODO Auto-generated method stub
		return _num.subtract(value);
	}
	
	public int get_sign() {
		// TODO Auto-generated method stub
		return -1;
	}
}

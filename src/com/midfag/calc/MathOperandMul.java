package com.midfag.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.midfag.calc.Enums.Operands;

public class MathOperandMul extends MathOperand {

	public MathOperandMul(String _value) {
		super(_value);
		litera="*";
		prioritet=1;
		// TODO Auto-generated constructor stub
	}

	public BigDecimal calculate(BigDecimal _num) {
		// TODO Auto-generated method stub
		return _num.multiply(value);
	}
}

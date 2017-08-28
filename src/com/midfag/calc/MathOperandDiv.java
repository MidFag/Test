package com.midfag.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.midfag.calc.Enums.Operands;

public class MathOperandDiv extends MathOperand {

	public MathOperandDiv(String _value) {
		super(_value);
		litera="/";
		prioritet=1;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public BigDecimal calculate(BigDecimal _num) {
		// TODO Auto-generated method stub
		if (value.equals(BigDecimal.ZERO)){System.out.println("Ошибка: деление на ноль выходит за рамки функционала данного калькулятора"); Main.error=true; return value;}
		else
		return _num.divide(value, 5, RoundingMode.HALF_EVEN);
	}
}

package com.midfag.calc;
  import java.applet.Applet;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;    
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.midfag.calc.Enums.Operands;
//import com.midfag.game.script.ScriptTimer;


public class Main {    

	public static List<MathOperand> operand_list=new ArrayList<MathOperand>(); 
		
		public static String[] num_array;
		
		public static BigDecimal summ_pool_main=new BigDecimal(0);
		public static BigDecimal summ_pool_muldiv=new BigDecimal(0);
		
		public static boolean error=false;
			
		public static void main(String[] args){    
            InputStreamReader is = new InputStreamReader(System.in);    
            BufferedReader bistream = new BufferedReader(is);    
            
                 try 
                 { 
	                     while(true)
	                     {
	                    	 error=false;
	                    	 
	                    	 operand_list.clear();//очищаем список операторов
	                    	 System.out.println("|------------------------------------------|");
	                    	 System.out.println("| введите математическое выражение <ввод>: |");   
	                    	 System.out.println("|------------------------------------------|");
	                         
	                    	 String nameStr = bistream.readLine();
	                    	 
	                    	 if (nameStr.toLowerCase().equals("end"))
	                    	 {System.exit(0);}
	                    	 else
	                    	 {nameStr = nameStr.replaceAll("[^-0-9,+/*.]", "");}//удаляем лишние символы, не имеющие отношения к цифрам и операторам }
	                    	 
	                    	 	if (nameStr.isEmpty()){error=true;}
	                    	 	
	                    	 	//проверяем первый символ на неугодные операторы. математическая формула не может начинаться с "/" и "*"
	                    	 	if (!error)
	                    	 	{
	                    	 		Pattern p = Pattern.compile("^[*/+.]$");  
	                    	 	
	                    	 		Matcher m = p.matcher(nameStr.charAt(0)+"");  
	                    	 		if (m.matches()){System.out.println("Ошибка: неуместный оператор <"+nameStr.charAt(0)+"> в начале"); error=true;}
	                    	 	}
	                    	 		
	                    	 	//костыль: добавляем символ "0" к началалу формулы, что бы калькулятор воспринял знак "-" как признак отрицательного числа, а не как оператор
	                    	 	nameStr="0"+nameStr;
	                    	 	
	                    	 	//убираем все операторы "минус", и заменяем все положительные числа отрицательными
	                    	 	String	parse_string=nameStr.replace("--", "+").replace("+-", "-");
	                    	 		
	                    	 	
	                    	 	//заменяем символы математических операторов, что бы в дальнейшем разбить оставшиеся числа на массив
	                    	 	String 	array_string=parse_string.replace("[/-]", "-").replace("[*-]", "-").replace("-", "#-").replace("+", "#").replaceAll("[/*]", "");
	                    	 	
	                    	 	//System.out.println("!!! "+array_string);
	                    	 	
	                    	 			parse_string=parse_string.replace("*-", "*").replace("/-", "/").replace("-", "+");
	                    	 	
	                    	 	//подготавливаем строку, содержащую только математические операторы. для этого удаляем все числа
	                    	 	parse_string=parse_string.replaceAll("[-0-9.]","");
	                    	 	
	                    	 	
	                    	 //
	                    	 num_array=array_string.split("#");
	                    	 
	                    	 //проверяем получившийся массив. если в массиве оказывается пустой элемент, это значит, что за одним оператором следует другой, что приведет к ошибке
	                    	 for (int i=0; i<num_array.length; i++)
	                    	 {
	                    		if (num_array[i].equals("")){System.out.println("Ошибка в операторах: 2 оператора следуют друг за другом"); error=true;}
	                    		else
	                    		{
	                    			try {
										BigDecimal test=new BigDecimal (num_array[i]);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										System.out.println("Неверное представление числа "+num_array[i]);
										error=true;
									}
	                    		}
 
	                    	 }

	                    	 //System.out.println("список чисел "+num_array.length);
	                    	 //прочесываем символы операторов в строке, и в соотствествии с литерой, добавляем в пул объект соответствующего оператора
	                    	 if (!error)
	                         for (int i=0; i<parse_string.length(); i++)
	                         {
	                        	 //количество операторов всегда равняется количеству чисел-1. если операторов больше или равно количеству чисел в формуле, значит, в формуле следует оператор без числа. пример 10/*5, или 7+1+
	                        	 if (i+1>=num_array.length)
	                        	 {System.out.println("Ошибка: не хватает числа возле оператора!"); error=true; break;}
	                        	 else
	                        	 {
		                        	if (parse_string.charAt(i)=='+'){parser(Operands.ADD,i);}
		                        	else
		                        	if (parse_string.charAt(i)=='-'){parser(Operands.SUB,i);}
		                        	else
		                        	if (parse_string.charAt(i)=='*'){parser(Operands.MUL,i);}
			                        else	 
			                        if (parse_string.charAt(i)=='/'){parser(Operands.DIV,i);} 
	                        	 }
	                         }
	                         
	                         //если операторов нету вовсе, то длина строки будет нулевой
	                         if (parse_string.length()==0) {System.out.println("Ошибка: не хватает операторов!"); error=true;}
	                         
	                         if (!error)
	                         {
	                        	 //счетчик для чисел сложения, и счетчик для чисел умножения/деления
		                        summ_pool_main=new BigDecimal(0);
		                        summ_pool_muldiv=new BigDecimal(0);
		                        
		                        //так как первое число не имеет оператора, его следует сразу добавить в один из счетчиков, в соответствии с первым оператором в списке
		                        //приоритет сложения - 0
		                        //приоритет умножения/деления - 1
		                        
		                        if (operand_list.get(0).prioritet==0){summ_pool_main=new BigDecimal(num_array[0]);}
		                        if (operand_list.get(0).prioritet==1){summ_pool_muldiv=new BigDecimal(num_array[0]);}
		                        
		                        //в случае сложения, мы производить вычисления последовательно. в случае 2+3+4+5 мы можем просто по очереди сложить числа.
		                        //в случае 2+3*4*5, мы не может сложить 2 и 3, так как после + идет оператор с высшим приоритетом
		                        //что бы сложить 2 с результатом следующих операторов, нам нужно запомнить знак, который следует перед оператором с высшим приоритетом,
		                        //затем в счетчик умножения произвести математические операции, и добавить/вычесть (в соответствии со знаком) полученный результат в список сложения
		                        int sign=1;
		                        
		                     	for (int i=0; i<operand_list.size(); i++)
		                     	{
		                     		//если следующий оператор имеет больший приоритет, значит, нам необходимо запомнить знак текущего оператора
		                     		if ((check_list_bound(i))&&(operand_list.get(i).prioritet<operand_list.get(i+1).prioritet))
		                     		{
		                     			summ_pool_muldiv=summ_pool_muldiv.add(operand_list.get(i).value);
		                     			sign=operand_list.get(i).get_sign();
		                     		}
		                     		
		                     		if ((check_list_bound(i)&&(operand_list.get(i).prioritet>=operand_list.get(i+1).prioritet))||(!check_list_bound(i)))
		                     		{
		                     			if (operand_list.get(i).prioritet==0){summ_pool_main=operand_list.get(i).calculate(summ_pool_main);}
		                     			if (operand_list.get(i).prioritet==1){summ_pool_muldiv=operand_list.get(i).calculate(summ_pool_muldiv);}
		                     		}
		                     		
		                     		 
		                     		if ((check_list_bound(i))&&(operand_list.get(i).prioritet>operand_list.get(i+1).prioritet))
		                     		{
		                     			if (sign==-1){summ_pool_main=summ_pool_main.subtract(summ_pool_muldiv);}
		                     			if (sign==1){summ_pool_main=summ_pool_main.add(summ_pool_muldiv);}
		                     			
		                     			summ_pool_muldiv=new BigDecimal(0);
		                     		}
	
		                     	}
		                    	
		                     	if (!error)
		                    	{System.out.println("RESULT: "+(summ_pool_main.add(summ_pool_muldiv)));}
		                     	else
		                     	{System.out.println("результат неизвестен из-за ошибки");}
		                      } 
	                     }
                 }
                 catch (IOException e)
                 {System.out.println("ошибка ввода " + e);}    

    }



		private static void parser(Operands _op, int _id)
		{
			if (_op.equals(Operands.ADD)) {operand_list.add(new MathOperandAdd(num_array[_id+1]));}
			if (_op.equals(Operands.MUL)) {operand_list.add(new MathOperandMul(num_array[_id+1]));}
			if (_op.equals(Operands.DIV)) {operand_list.add(new MathOperandDiv(num_array[_id+1]));}
			if (_op.equals(Operands.SUB)) {operand_list.add(new MathOperandSub(num_array[_id+1]));}
			
			
		}
		
		static boolean check_list_bound(int _i)
		{
			if (_i<operand_list.size()-1) {return true;}
			return false;
		}










}
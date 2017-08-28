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
	                    	 
	                    	 operand_list.clear();//������� ������ ����������
	                    	 System.out.println("|------------------------------------------|");
	                    	 System.out.println("| ������� �������������� ��������� <����>: |");   
	                    	 System.out.println("|------------------------------------------|");
	                         
	                    	 String nameStr = bistream.readLine();
	                    	 
	                    	 if (nameStr.toLowerCase().equals("end"))
	                    	 {System.exit(0);}
	                    	 else
	                    	 {nameStr = nameStr.replaceAll("[^-0-9,+/*.]", "");}//������� ������ �������, �� ������� ��������� � ������ � ���������� }
	                    	 
	                    	 	if (nameStr.isEmpty()){error=true;}
	                    	 	
	                    	 	//��������� ������ ������ �� ��������� ���������. �������������� ������� �� ����� ���������� � "/" � "*"
	                    	 	if (!error)
	                    	 	{
	                    	 		Pattern p = Pattern.compile("^[*/+.]$");  
	                    	 	
	                    	 		Matcher m = p.matcher(nameStr.charAt(0)+"");  
	                    	 		if (m.matches()){System.out.println("������: ���������� �������� <"+nameStr.charAt(0)+"> � ������"); error=true;}
	                    	 	}
	                    	 		
	                    	 	//�������: ��������� ������ "0" � �������� �������, ��� �� ����������� ��������� ���� "-" ��� ������� �������������� �����, � �� ��� ��������
	                    	 	nameStr="0"+nameStr;
	                    	 	
	                    	 	//������� ��� ��������� "�����", � �������� ��� ������������� ����� ��������������
	                    	 	String	parse_string=nameStr.replace("--", "+").replace("+-", "-");
	                    	 		
	                    	 	
	                    	 	//�������� ������� �������������� ����������, ��� �� � ���������� ������� ���������� ����� �� ������
	                    	 	String 	array_string=parse_string.replace("[/-]", "-").replace("[*-]", "-").replace("-", "#-").replace("+", "#").replaceAll("[/*]", "");
	                    	 	
	                    	 	//System.out.println("!!! "+array_string);
	                    	 	
	                    	 			parse_string=parse_string.replace("*-", "*").replace("/-", "/").replace("-", "+");
	                    	 	
	                    	 	//�������������� ������, ���������� ������ �������������� ���������. ��� ����� ������� ��� �����
	                    	 	parse_string=parse_string.replaceAll("[-0-9.]","");
	                    	 	
	                    	 	
	                    	 //
	                    	 num_array=array_string.split("#");
	                    	 
	                    	 //��������� ������������ ������. ���� � ������� ����������� ������ �������, ��� ������, ��� �� ����� ���������� ������� ������, ��� �������� � ������
	                    	 for (int i=0; i<num_array.length; i++)
	                    	 {
	                    		if (num_array[i].equals("")){System.out.println("������ � ����������: 2 ��������� ������� ���� �� ������"); error=true;}
	                    		else
	                    		{
	                    			try {
										BigDecimal test=new BigDecimal (num_array[i]);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										System.out.println("�������� ������������� ����� "+num_array[i]);
										error=true;
									}
	                    		}
 
	                    	 }

	                    	 //System.out.println("������ ����� "+num_array.length);
	                    	 //����������� ������� ���������� � ������, � � ������������� � �������, ��������� � ��� ������ ���������������� ���������
	                    	 if (!error)
	                         for (int i=0; i<parse_string.length(); i++)
	                         {
	                        	 //���������� ���������� ������ ��������� ���������� �����-1. ���� ���������� ������ ��� ����� ���������� ����� � �������, ������, � ������� ������� �������� ��� �����. ������ 10/*5, ��� 7+1+
	                        	 if (i+1>=num_array.length)
	                        	 {System.out.println("������: �� ������� ����� ����� ���������!"); error=true; break;}
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
	                         
	                         //���� ���������� ���� �����, �� ����� ������ ����� �������
	                         if (parse_string.length()==0) {System.out.println("������: �� ������� ����������!"); error=true;}
	                         
	                         if (!error)
	                         {
	                        	 //������� ��� ����� ��������, � ������� ��� ����� ���������/�������
		                        summ_pool_main=new BigDecimal(0);
		                        summ_pool_muldiv=new BigDecimal(0);
		                        
		                        //��� ��� ������ ����� �� ����� ���������, ��� ������� ����� �������� � ���� �� ���������, � ������������ � ������ ���������� � ������
		                        //��������� �������� - 0
		                        //��������� ���������/������� - 1
		                        
		                        if (operand_list.get(0).prioritet==0){summ_pool_main=new BigDecimal(num_array[0]);}
		                        if (operand_list.get(0).prioritet==1){summ_pool_muldiv=new BigDecimal(num_array[0]);}
		                        
		                        //� ������ ��������, �� ����������� ���������� ���������������. � ������ 2+3+4+5 �� ����� ������ �� ������� ������� �����.
		                        //� ������ 2+3*4*5, �� �� ����� ������� 2 � 3, ��� ��� ����� + ���� �������� � ������ �����������
		                        //��� �� ������� 2 � ����������� ��������� ����������, ��� ����� ��������� ����, ������� ������� ����� ���������� � ������ �����������,
		                        //����� � ������� ��������� ���������� �������������� ��������, � ��������/������� (� ������������ �� ������) ���������� ��������� � ������ ��������
		                        int sign=1;
		                        
		                     	for (int i=0; i<operand_list.size(); i++)
		                     	{
		                     		//���� ��������� �������� ����� ������� ���������, ������, ��� ���������� ��������� ���� �������� ���������
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
		                     	{System.out.println("��������� ���������� ��-�� ������");}
		                      } 
	                     }
                 }
                 catch (IOException e)
                 {System.out.println("������ ����� " + e);}    

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
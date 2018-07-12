package excelConverter;

import java.math.BigDecimal;

public class deneme {

	public static void main(String[] args) {

		

		BigDecimal a = new BigDecimal(989831.39);
		BigDecimal b = new BigDecimal(100.0);
		System.out.println(a.multiply(b).intValue());
		
		String sef = "1024 ";
		System.out.println(sef.replaceAll(" ", "").length());
		if(sef.charAt(4)==' ')
			System.out.println(true);
		else
			System.out.println(false);
	}

}

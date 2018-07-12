package excelConverter;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;

public class Converter {
	

	File tersBakiyeExcel;
	
    public Converter(String fisKategori, String aciklama, String tarih, String fisKesen, String kesilmeDurumu,
			String selectedDirectory, File tersBakiye, File teminatKodları) {
    	
    	tersBakiyeExcel = tersBakiye;
    	
  	  
    	ArrayList<TersBakiye> list = excelToTersBakiyeEntity(tersBakiyeExcel);
    	
    	list = filter(list);
    	
    	for(int i=0;i<list.size();i++)
    		System.out.println(list.get(i).subeKodu+" "+list.get(i).hesapKodu+" "+list.get(i).hesapKarakteri+" "+list.get(i).dovizKodu+" "+list.get(i).ypNetBakiye+" "+list.get(i).tpNetBakiye+" "+list.get(i).grupBilgisi);
    
    	
    	String url= selectedDirectory;

    	ArrayList<GLFormat> gl = createGLFormat(list, url+"GLFormat.xlsx", teminatKodları, fisKategori, aciklama, tarih, fisKesen, kesilmeDurumu);
    	
    	
    	for(int i=0;i<gl.size();i++)
    	{
    		System.out.println(gl.get(i).refNo+" "+gl.get(i).fisKategori+" "+gl.get(i).muhTarihi+" "+gl.get(i).dovizCinsi+" "+gl.get(i).subeKodu+" "+gl.get(i).karsiSube+" "+gl.get(i).borcHesabi+" "+gl.get(i).borcHesRiskGrString+" "+gl.get(i).alacakHesabi+" "+gl.get(i).alacakHesRiskGr+" "+gl.get(i).fisTutari+" "+gl.get(i).dovizTutari+" "+gl.get(i).aciklama+" "+gl.get(i).fisKesen+" "+gl.get(i).kesilmeDurumu);
    	}
 
    	writeToExcelasGLFORMAT(gl, selectedDirectory+"GLFORMAT.xlsx");
    	
    	writeToExcelasRUNMUH(gl,selectedDirectory+"RUNMUH.xls");
	}
    


    
    private void writeToExcelasRUNMUH(ArrayList<GLFormat> gl, String fileName) {
		
    	ArrayList<RunMuh> list = convertGLtoRunMuh(gl);
    	
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");


        int rowNum = 0;
        

        for (int i=0;i<list.size()+1;i++) {
        	

        	
            Row row = sheet.createRow(rowNum++);
          
            for (int j=0;j<16;j++) {
            	
            	if(i==0){
            		
            		row.createCell(0).setCellValue("BRSBKD");
            		row.createCell(1).setCellValue("BR-MUHKD");
            		row.createCell(2).setCellValue("BR-DVZ");
            		row.createCell(3).setCellValue("DUBPARM");
            		row.createCell(4).setCellValue("BR-REF");
            		row.createCell(5).setCellValue("BR-TUTAR");
            		row.createCell(6).setCellValue("BR-VALOR");
            		row.createCell(7).setCellValue("ALSBKD");
            		row.createCell(8).setCellValue("AL-MUHKD");
            		row.createCell(9).setCellValue("AL-DVZ");
            		row.createCell(10).setCellValue("DUBPARM");
            		row.createCell(11).setCellValue("AL-REF");
            		row.createCell(12).setCellValue("AL-TUTAR");
            		row.createCell(13).setCellValue("AL-VALOR");
            		row.createCell(14).setCellValue("MVALOR");
            		row.createCell(15).setCellValue("ACIKLAMA");
            		
            		
            		
            	}
            	else {
            		
            		RunMuh a = list.get(i-1);
            		
            		row.createCell(0).setCellValue(trim(a.brsbkd));
            		
            		
            		row.createCell(1).setCellValue(a.br_muhkd);
            		row.createCell(2).setCellValue(a.br_dvz);
            		
            		row.createCell(5).setCellValue(a.br_tutar);
            		row.createCell(6).setCellValue(a.br_valor);
            		row.createCell(7).setCellValue(trim(a.alsbkd));
            		row.createCell(8).setCellValue(a.al_muhkd);
            		row.createCell(9).setCellValue(a.al_dvz);
            		
            		row.createCell(12).setCellValue(a.al_tutar);
            		row.createCell(13).setCellValue(a.al_valor);
            		row.createCell(14).setCellValue(a.mvalor);
            		row.createCell(15).setCellValue(a.aciklama);
            		
            		
            	}
            	
            	

            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    	
    	
    	
		
	}

	private  ArrayList<RunMuh> convertGLtoRunMuh(ArrayList<GLFormat> gl) {
		ArrayList<RunMuh> list = new ArrayList<>();
		
		for(int i=0;i<gl.size();i++)
		{
			RunMuh a = new RunMuh();
			GLFormat b = gl.get(i);
			a.brsbkd = trim(b.subeKodu);
			System.out.println(a.brsbkd.length()+"*");
			a.br_muhkd = b.borcHesabi;
			a.br_dvz = b.dovizCinsi;
			a.br_tutar = removeAndConvert(""+b.fisTutari,false);
			a.br_valor = Integer.parseInt(removeAndConvert(b.muhTarihi, true));
			a.alsbkd = trim(b.subeKodu);
			a.al_muhkd = b.alacakHesabi;
			a.al_dvz = b.dovizCinsi;
			a.al_tutar = a.br_tutar;
			a.al_valor = a.br_valor;
			a.mvalor = a.br_valor;
			a.aciklama = b.aciklama;
			
			list.add(a);
		}
		
		
		
		
		return insertionSort(list);
	}
	

	

	private  String trim(String subeKodu) {
		
		subeKodu = subeKodu.replaceAll(" ", "");
		subeKodu = subeKodu.substring(0, 4);
		
		return subeKodu;
	}
	
	

	private  String removeAndConvert(String fisTutari, boolean isDate) {
		
		
		if(isDate) {
			String year = fisTutari.substring(fisTutari.lastIndexOf(".")+1);
			fisTutari = fisTutari.substring(0,fisTutari.lastIndexOf(".") );
			String month = fisTutari.substring(fisTutari.lastIndexOf(".")+1);
			fisTutari = fisTutari.substring(0,fisTutari.lastIndexOf(".") );
			String day = fisTutari;
			
			return year+month+day;
		}
		else {
			
			BigDecimal a = new BigDecimal(fisTutari);
			BigDecimal b = new BigDecimal(100.0);
			
			return a.multiply(b).intValue()+"";
			
		}
		
		
	}

	private  ArrayList<GLFormat> createGLFormat(ArrayList<TersBakiye> list, String glURL,File accountNumberURL, String fisKategori, String aciklama, String tarih, String fisKesen, String kesilmeDurumu) {
		
    	int refNo = 1;
    	ArrayList<GLFormat> glFormat = new ArrayList<GLFormat>();
    	
    	ArrayList<TeminatKodu> accountNumbers = new  ArrayList<>();
    	
    	accountNumbers = getAccountNumbers(accountNumberURL);
    	
    	for(int i=0;i<list.size();i++)
    	{
    		if(list.get(i).hesapKarakteri.equals("P"))
    		{
    			GLFormat a = new GLFormat();
    			a.setRefNo(refNo);
    			refNo++;
    			a.fisKategori = fisKategori;
    			a.muhTarihi = tarih;
    			a.dovizCinsi = list.get(i).dovizKodu;
    			a.subeKodu = list.get(i).subeKodu;
    			a.karsiSube = a.subeKodu;
    			a.alacakHesabi = list.get(i).hesapKodu;
    			a.borcHesabi = list.get(i-1).hesapKodu;
    			a.borcHesRiskGrString = "0000000000";
    			a.alacakHesRiskGr ="0000000000";
    			a.dovizTutari="";
    			if(a.dovizCinsi.equals("TRY"))
    				a.fisTutari = list.get(i).tpNetBakiye;
    			else
    				a.fisTutari = list.get(i).ypNetBakiye;
    			a.aciklama = aciklama;
    			a.fisKesen = fisKesen;
    			a.kesilmeDurumu = kesilmeDurumu;
    			
    			glFormat.add(a);
    			
    			i--;
    			list.remove(i);
    			list.remove(i);
    			i--;
    			
    			
    		}	
    		
    	}
    	
  		for(int i=0;i<list.size();i++)
		{
  			GLFormat a = new GLFormat();
			a.setRefNo(refNo);
			refNo++;
			a.fisKategori = fisKategori;
			a.muhTarihi = tarih;
			a.dovizCinsi = list.get(i).dovizKodu;
			a.subeKodu = list.get(i).subeKodu;
			a.karsiSube = a.subeKodu;
			a.borcHesabi = list.get(i).hesapKodu;
			a.alacakHesabi = findAccountNumber(accountNumbers,a.borcHesabi);
			a.borcHesRiskGrString = "0000000000";
			a.alacakHesRiskGr ="0000000000";
			a.dovizTutari="";
			if(a.dovizCinsi.equals("TRY"))
				a.fisTutari = list.get(i).tpNetBakiye;
			else
				a.fisTutari = list.get(i).ypNetBakiye;
			a.aciklama = aciklama;
			a.fisKesen = fisKesen;
			a.kesilmeDurumu = kesilmeDurumu;
			
			glFormat.add(a);
			
			
		}
		return glFormat;
	}

	private  String findAccountNumber(ArrayList<TeminatKodu> accountNumbers, String borcHesabi) {
		
		String alacakHesabi = null;
		for(int i=0;i<accountNumbers.size();i++)
		{
			if(accountNumbers.get(i).tlbMuh.equals(borcHesabi))
				alacakHesabi = accountNumbers.get(i).tlaMuh;
			else if(accountNumbers.get(i).ypbMuh.equals(borcHesabi))
				alacakHesabi = accountNumbers.get(i).ypaMuh;
			
		}
		return alacakHesabi;
	}

	private  ArrayList<TeminatKodu> getAccountNumbers(File url) {
		
		ArrayList<TeminatKodu> accountNumbers = new ArrayList<>();
		
	   	 try {
	 		
	         FileInputStream excelFile = new FileInputStream(url);
	         Workbook workbook = new XSSFWorkbook(excelFile);
	         Sheet datatypeSheet = workbook.getSheetAt(0);
	         Iterator<Row> iterator = datatypeSheet.iterator();
	         
	         int j=0;
	         while (iterator.hasNext()) {
	        	 
	        	 TeminatKodu a = new TeminatKodu();
	        	 accountNumbers.add(a);
	        	 
	        	 
	             Row currentRow = iterator.next();
	             Iterator<Cell> cellIterator = currentRow.iterator();
	             int i=0;
	             while (cellIterator.hasNext()) {
	            	 
	                 Cell currentCell = cellIterator.next();
	                 currentCell.setCellType(CellType.STRING);
	                 
	                switch (i) {
						case 8:
							if(currentCell.getCellTypeEnum() == CellType.STRING)
								accountNumbers.get(j).setTlbMuh(currentCell.getStringCellValue());
							break;
						case 9:
							if(currentCell.getCellTypeEnum() == CellType.STRING)
								accountNumbers.get(j).setTlaMuh(currentCell.getStringCellValue());
							break;
						case 10:
							if(currentCell.getCellTypeEnum() == CellType.STRING)
								accountNumbers.get(j).setYpbMuh(currentCell.getStringCellValue());
							break;	
						case 11:
							if(currentCell.getCellTypeEnum() == CellType.STRING)
								accountNumbers.get(j).setYpaMuh(currentCell.getStringCellValue());
							break;	
						default:
							break;
					}
	                 i++;
	             }
	        
	             j++;
	         }
	     } catch (FileNotFoundException e) {
	         e.printStackTrace();
	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	   	 
	   	 
	   	 
	   	 for(int i=0;i<accountNumbers.size();i++)
	   	 {
	   		 if(accountNumbers.get(i).tlaMuh.contains(" ") && accountNumbers.get(i).tlbMuh.contains(" ") && accountNumbers.get(i).ypaMuh.contains(" ") && accountNumbers.get(i).ypbMuh.contains(" ")) {
	   			 accountNumbers.remove(i);
	   			 i--;
	   		 }
	   		 
	   	 }
		
		
		return accountNumbers;
	}

	private ArrayList<TersBakiye> filter(ArrayList<TersBakiye> list) {
    	
    	ArrayList<TersBakiye> filtered = new ArrayList<TersBakiye>();
    	
    	
    	for(int i=0;i<3;i++)
    		list.remove(0);
    	
    	
    /*	for(int i=0;i<list.size();i++)
    		System.out.println(list.get(i).subeKodu+" "+list.get(i).hesapKodu+" "+list.get(i).hesapKarakteri+" "+list.get(i).dovizKodu+" "+list.get(i).ypNetBakiye+" "+list.get(i).tpNetBakiye+" "+list.get(i).grupBilgisi);
		*/
    	
    	for(int i=0;i<list.size();i++) {
    		
    		if(list.get(i).grupBilgisi != null &&  (list.get(i).grupBilgisi.contains("YNT TEMİNAT") || list.get(i).grupBilgisi.contains("TEMİNAT")))
    			filtered.add(list.get(i));
    		
    	}
    	
    	return filtered;
    	
	}

	public void writeToExcelasGLFORMAT(ArrayList<GLFormat> list,String fileName) {
    	

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet1");


        int rowNum = 0;
        

        for (int i=0;i<list.size()+1;i++) {
        	

        	
            Row row = sheet.createRow(rowNum++);
          
            for (int j=0;j<16;j++) {
            	
            	if(i==0){
            		
            		row.createCell(0).setCellValue("Ref No");
            		row.createCell(1).setCellValue("Fis Kateg.");
            		row.createCell(2).setCellValue("Muh_Tarihi");
            		row.createCell(3).setCellValue("Dvz Cinsi");
            		row.createCell(4).setCellValue("Sube Kodu");
            		row.createCell(5).setCellValue("Karsi Sube");
            		row.createCell(6).setCellValue("Borç Hesabi");
            		row.createCell(7).setCellValue("Borç Hes.Risk Gr.");
            		row.createCell(8).setCellValue("Alacak Hesabı");
            		row.createCell(9).setCellValue("Alacak Hes.Risk Gr.");
            		row.createCell(10).setCellValue("Fis Tutari");
            		row.createCell(11).setCellValue("Döviz Tutari");
            		row.createCell(12).setCellValue("Açiklama");
            		row.createCell(13).setCellValue("Fisi Kesen");
            		row.createCell(14).setCellValue("IMS'e kesildi mi?");
            		row.createCell(15).setCellValue("Mev-Kre Karton No");
            		
            		
            		
            	}
            	else {
            		
            		GLFormat a = list.get(i-1);
            		
            		row.createCell(0).setCellValue(a.refNo);
            		row.createCell(1).setCellValue(a.fisKategori);
            		row.createCell(2).setCellValue(a.muhTarihi);
            		row.createCell(3).setCellValue(a.dovizCinsi);
            		row.createCell(4).setCellValue(a.subeKodu);
            		row.createCell(5).setCellValue(a.karsiSube);
            		row.createCell(6).setCellValue(a.borcHesabi);
            		row.createCell(7).setCellValue(a.borcHesRiskGrString);
            		row.createCell(8).setCellValue(a.alacakHesabi);
            		row.createCell(9).setCellValue(a.alacakHesRiskGr);
            		row.createCell(10).setCellValue(a.fisTutari);
            		row.createCell(11).setCellValue(a.dovizTutari);
            		row.createCell(12).setCellValue(a.aciklama);
            		row.createCell(13).setCellValue(a.fisKesen);
            		row.createCell(14).setCellValue(a.kesilmeDurumu);
            		
            		
            	}
            	
            	

            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }
    public  ArrayList<TersBakiye> excelToTersBakiyeEntity(File file) {
    	
    		ArrayList<TersBakiye> tersBakiyeList = new ArrayList<TersBakiye>();
    	
		   	 try {
		
		         FileInputStream excelFile = new FileInputStream(file);
		         Workbook workbook = new XSSFWorkbook(excelFile);
		         Sheet datatypeSheet = workbook.getSheetAt(0);
		         Iterator<Row> iterator = datatypeSheet.iterator();
		         int j=0;
		         while (iterator.hasNext()) {
		        	 
		        	 TersBakiye a = new TersBakiye();
		        	 tersBakiyeList.add(a);
		        	 
		        	 
		             Row currentRow = iterator.next();
		             Iterator<Cell> cellIterator = currentRow.iterator();
		             int i=0;
		             while (cellIterator.hasNext()) {
		            	 
		                 Cell currentCell = cellIterator.next();
		                 
		                 
		                switch (i) {
							case 0:
								if(currentCell.getCellTypeEnum() == CellType.STRING)
									tersBakiyeList.get(j).subeKodu = currentCell.getStringCellValue();
								break;
							case 1:
								if(currentCell.getCellTypeEnum() == CellType.STRING)
									tersBakiyeList.get(j).hesapKodu = currentCell.getStringCellValue();
								break;
							case 2:
								if(currentCell.getCellTypeEnum() == CellType.STRING)
									tersBakiyeList.get(j).hesapKarakteri = currentCell.getStringCellValue();
								break;
							case 3:
								if(currentCell.getCellTypeEnum() == CellType.STRING)
									tersBakiyeList.get(j).dovizKodu = currentCell.getStringCellValue();
								break;
							case 4:
								if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
									tersBakiyeList.get(j).ypNetBakiye = currentCell.getNumericCellValue();
								break;
							case 5:
								if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
									tersBakiyeList.get(j).tpNetBakiye = currentCell.getNumericCellValue();
								break;
							case 6:
								if(currentCell.getCellTypeEnum() == CellType.STRING)
									tersBakiyeList.get(j).grupBilgisi = currentCell.getStringCellValue();
								break;	
							default:
								break;
						}
		                 i++;
		             }
		        
		             j++;
		         }
		     } catch (FileNotFoundException e) {
		         e.printStackTrace();
		     } catch (IOException e) {
		         e.printStackTrace();
		     }
		   	 
		   	 for(int i=0;i<tersBakiyeList.size();i++)
		   	 {
		   		 if(tersBakiyeList.get(i).subeKodu != null){
		   			 int index = tersBakiyeList.get(i).subeKodu.indexOf("0");
		   			 tersBakiyeList.get(i).subeKodu = tersBakiyeList.get(i).subeKodu.substring(index+1);
		   		 }
		   	 }
		   	 
		   	 
			return tersBakiyeList;
    	
    }
    
    public  ArrayList<RunMuh> insertionSort(ArrayList<RunMuh> temp){
    	
    	
    	ArrayList<RunMuh> arr = temp;
    	
    	for(int i=0;i<arr.size();i++)
    	{
    		
    		RunMuh key = arr.get(i);
    		int j = i;
    		
    		while(j>0 && Integer.parseInt(arr.get(j-1).brsbkd)> Integer.parseInt(key.brsbkd))
    		{
    			System.out.println("HOP");
    			arr.set(j, arr.get(j-1));
    			j = j-1;
    		}
    		
    		arr.set(j, key);
    	}
    	
    	
    	
    	
		return arr;		
	}
 	
 }
   
package FrameworkLibrary;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary extends ListenerClass{
 
	public static String getdata(String FieldName) // line 17
	{
	String StrValue = null;
	try
	{ System.out.println("------------20---------"+FieldName+"-----------");
		
		/*if (Commondata.containsKey(FieldName.toLowerCase())) 
			{
			return Commondata.get(FieldName.toLowerCase());
			}
		*/
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace(); 
		StackTraceElement stackTraceElement = stackTraceElements[2]; 
		String ClassName = stackTraceElement.getMethodName(); 
		
		System.out.println("---ClassName---------31---------"+ClassName);
		File fi = new File("C:\\workspace\\MyListener\\RunManager.xlsx"); 
		FileInputStream fin = new FileInputStream( fi);
	
		XSSFWorkbook wrk = new XSSFWorkbook(fin); 
		XSSFSheet shet= wrk.getSheet ("TestData");
	
		Row rw = shet.getRow(1);
	System.out.println("last row num is --39----"+shet.getLastRowNum());
		for ( int i = 0 ; i < shet.getLastRowNum() ; i++) 
		{
			rw = shet.getRow(i+1);
			System.out.println("---last cell num-43---"+i+"-- "+shet.getRow(i+1).getLastCellNum()); // we have 4 coll
			if (rw == null || rw.getCell(1)== null)	
				continue;
			
				String testCaseName = rw.getCell(1).getStringCellValue();
				System.out.println("---testCaseName------47---------"+testCaseName);
				if(testCaseName.equals(ClassName))
				{
					for (int j=1; j<rw.getLastCellNum();j++)
					{
						Cell c1 = rw.getCell(j+1);
						if (c1==null)
							continue;
						String val = c1.getStringCellValue();
						System.out.println(FieldName+"  ----- 55---"+ val);
						String arrval[] =val.split(":=");
						
						String StrFieldName = arrval[0];
						if(StrFieldName.equalsIgnoreCase(FieldName))
						{
							StrValue = arrval[1];
							System.out.println("----63------StrFieldName---"+StrFieldName+"--------StrValue---"+StrValue);
							break;
						}
					}
				}
			}
		
			wrk.close();
			fin.close(); // line 69
		}
	catch (Exception e)
	{
		
	}
	return StrValue;
}
	public static String putdata(String FieldName, String value) // line 77
	{
		String StrValue = "";
		try
		{
			StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace(); 
			StackTraceElement stackTraceElement = stackTraceElements[2]; 
			String ClassName = stackTraceElement.getMethodName(); 
			
			
			File fi = new File("RunManager.xlsx"); 
			FileInputStream fin = new FileInputStream( fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin); 
			XSSFSheet shet= wrk.getSheet ("TestData");

			Row rw = shet.getRow(1);

			for ( int i = 0 ; i < shet.getLastRowNum() ; i++) 
			{
				rw = shet.getRow(i+1);
				if (rw== null || rw.getCell(1)== null)	
					continue;
				
					String testCaseName = rw.getCell(1).getStringCellValue();
					if(testCaseName.equals(ClassName))
					{
						for (int j=1; j<rw.getLastCellNum();j++)
						{
							Cell c1 = rw.getCell(j);
							if (c1==null)
								continue; // line 108
							String val = c1.getStringCellValue();
							String arrval[] =val.split(":=");
							
							String StrFieldName = arrval[0];
							if(StrFieldName.equalsIgnoreCase(ClassName))
							{
								StrValue = arrval[1];
								break;
							}
						}
					}
				}
				fin.close();
				FileOutputStream fos = new FileOutputStream(fi);
				wrk.write(fos);
				wrk.close();
			}
			catch (Exception e)
			{
				
			}
		return StrValue;
		}
		public static boolean getExecuteStatus(String mthName) // line 132
		{
			boolean flag=false;
			
			try
			{
				XSSFWorkbook wrk; 
				XSSFSheet shet; 
				XSSFRow rww; 
				XSSFCell cll;

				File fi = new File("RunManager.xlsx"); 
				FileInputStream fin =new FileInputStream(fi);

				wrk = new XSSFWorkbook(fin);
				shet=wrk.getSheet("ExecutionController") ;

				rww=shet.getRow(1);
				for(int i=0;i<=shet.getLastRowNum();i++)
				{
					rww=shet.getRow(i+1);
						if(rww==null || rww.getCell(0)==null ) 
					continue;
					cll=rww.getCell(1);
					if ( cll.getStringCellValue().equals(mthName)) ;
					{
						cll=rww.getCell(3);
						String result = cll.getStringCellValue();
						flag = Boolean.parseBoolean(result); // line 160
					}
				
				}
				wrk.close();
				fin.close(); //line 165
			}
			catch (Exception e)
			{
				
			}
			return flag;
	}
	public static void loadCammonTestData() 
	{
	 Commondata = new HashMap <String,String>();
	 try
	{
		XSSFWorkbook wrk; 
		XSSFSheet shet; 
		XSSFRow rww; 
		XSSFCell cl1;
		
		File fi= new  File("RunManager.xlsx"); 
		FileInputStream fin =new FileInputStream(fi);

		wrk = new XSSFWorkbook(fin);
		shet=wrk.getSheet("CommonTestData" );
		
		rww=shet.getRow(1);
		for(int i=0;i<=shet.getLastRowNum();i++)
		{
			rww=shet.getRow(i);
			if(rww==null || rww.getCell(0)==null ) 
			continue;
			cl1=rww.getCell(0);
			Cell cl2 = rww.getCell(2);
			Commondata.put(cl1.getStringCellValue().toLowerCase(), cl2.getStringCellValue()) ; // line 197
			}
		wrk.close();
		fin.close(); //line 200
	}
	catch (Exception e)
	{
		
	}
}
	public static int getPriority(String mthName) 
	{
		int priority = 0;
		try
		{
			XSSFWorkbook wrk; 
			XSSFSheet shet; 
			XSSFRow rww; 
			XSSFCell cl1;
			
			File fi= new  File("RunManager.xlsx"); 
			FileInputStream fin =new FileInputStream(fi);
	
			wrk = new XSSFWorkbook(fin);
			shet=wrk.getSheet("ExecutionController" );
			
			rww=shet.getRow(1);
			for(int i=0;i<=shet.getLastRowNum();i++)
				
			{
				rww=shet.getRow(i+1);
				if(rww==null || rww.getCell(4)==null ) 
				continue;
				cl1=rww.getCell(1);
				if ( cl1.getStringCellValue().equals(mthName)) ;
				{
					cl1=rww.getCell(4);
					String result = cl1.getStringCellValue();
					priority = Integer.parseInt(result); // line 235
				}
			}
			
			wrk.close();
			fin.close(); //line 240
		}
		catch (Exception e)
		{
			
		}	
		return priority; // chek karvu	
	}
	
	
	public static String getDescription(String mthName)  // line 250
	{ 
			String tcDdiscription = null;
			
			try
			{
				XSSFWorkbook wrk; 
				XSSFSheet shet; 
				XSSFRow rww; 
				XSSFCell cl1;

				File fi = new File("RunManager.xlsx"); 
				FileInputStream fin =new FileInputStream(fi);

				wrk = new XSSFWorkbook(fin);
				shet=wrk.getSheet("ExecutionController") ;

				rww=shet.getRow(1);
				
				for(int i=0;i<=shet.getLastRowNum();i++)
					
				{
					rww=shet.getRow(i+1);
						if(rww==null || rww.getCell(0)==null ) 
					continue;
					cl1=rww.getCell(1);
					if ( cl1.getStringCellValue().equals(mthName)) ;
					{
						cl1=rww.getCell(2);
						tcDdiscription = cl1.getStringCellValue(); // line 279
					}
				
				}
				
				
				wrk.close();
				fin.close(); //line 286
			}
			catch (Exception e)
			{
				
			}
			
			return tcDdiscription;
	
	}
	public static String setExecuteStatus(String mthName, String TCStatus) // line 296
	{
		String StrValue = "";
		try
		{
			String ClassName = mthName;
			
			File fi = new File("RunManager.xlsx"); 
			FileInputStream fin =new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet=wrk.getSheet("ExecutionController") ;

			Row rw =shet.getRow(1);
			
			for(int i=0;i<=shet.getLastRowNum();i++)
			{
				rw =shet.getRow(i+1);
				if(rw ==null || rw.getCell(1)==null ) 
				continue;
					String TestCaseName = rw.getCell(1).getStringCellValue();
				 
				if ( TestCaseName.equals(ClassName)) ; // line 318
				{
					Cell cl=rw.getCell(5);
					if(cl== null)
					{
						cl= rw.createCell(5);
					}
					cl.setCellValue(TCStatus);
					break; // line 326
				}
			}
			fin.close(); //line 329 jovo
			FileOutputStream fos = new FileOutputStream(fi);
			wrk.write(fos);
			wrk.close();		
		}
		catch (Exception e)
		{
			
		}
		return StrValue;
} // line 339
	public static String setLastExecuted(String mthName) // line 340
	{
		String StrValue = "";
		try
		{
			String ClassName = mthName;
			
			File fi = new File("RunManager.xlsx"); 
			FileInputStream fin =new FileInputStream(fi);

			XSSFWorkbook wrk = new XSSFWorkbook(fin);
			XSSFSheet shet=wrk.getSheet("ExecutionController") ;

			Row rw =shet.getRow(1);
			
			for(int i=0;i<=shet.getLastRowNum();i++)
			{
				rw =shet.getRow(i+1);
				if(rw ==null || rw.getCell(1)==null ) 
				continue;
					
				String TestCaseName = rw.getCell(1).getStringCellValue();
				if ( TestCaseName.equals(ClassName)) ; // line 362
				{
					Cell cl=rw.getCell(6);
					if(cl== null)
					{
						cl= rw.createCell(6);
					}
					cl.setCellValue(CurrentDateAndTime);
					break; // line 370
				}
			}
			fin.close(); //line 373
			FileOutputStream fos = new FileOutputStream(fi);
			wrk.write(fos);
			wrk.close();		
		}
		catch (Exception e)
		{
			
		}
		return StrValue;
} // line 383				
}

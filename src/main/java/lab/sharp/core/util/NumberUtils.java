package lab.sharp.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 类名称: DoubleUtils
 * 类描述：double类型工具类
 * 创建时间: 2016-7-4 下午04:01:28
 * 创建人： 邢凌霄
 * 版本： 1.0
 * @since JDK 1.5
 */
public class NumberUtils {

	/**
	 * 截掉double数后小数部分，不进行四舍五入
	 * */
	public static double cutNumberAfterDecimal(double beforeDouble){
		Double before = new Double(beforeDouble);
		double afterDouble = (double)before.longValue();
		return afterDouble;
	}	
	
	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额 @return(四舍五入)
	 */
	public static String roundAmount ( double dAmount )
	{
		//四舍五入数据
		BigDecimal bigDecimal = new BigDecimal ( dAmount ) ;
		double dBigDecimal = bigDecimal.doubleValue ( ) ;
		String sBigDecimal = formatAmount ( dBigDecimal , 0 ) ;
		if ("0".equals ( sBigDecimal ))
		{
			return "&nbsp;&nbsp;" ;
		}
		int iLength = 0 ;
		String sReturn = "" ;
		for (int i = sBigDecimal.length ( ) - 1; i >= 0; i--)
		{
			if (iLength == 3)
			{
				sReturn = "," + sReturn ;
				iLength = 0 ;
			}
			iLength++ ;
			char cData = sBigDecimal.charAt ( i ) ;
			sReturn = cData + sReturn ;
		}
		return sReturn ;
	}
	/**
	 * 格式化数字添加后缀,例如:把3.6格式化成3.600000,小数点后六位
	 * 
	 * @param double
	 *            需要格式化的数字
	 * @param char
	 *            添加后缀的字符
	 * @param int
	 *            小数点后位数
	 * @return String 返回格式化后的字符串
	 */
	public static String formatTax ( double taxRate , char suffix , int decimal )
	{
		String strReturnValue = "" + taxRate ; //返回值
		int intDotPosition = -1 ; //小数点位置
		int intNumberLen = -1 ; //数字长度		
		String strSuffix = "" ; //后缀
		
		for (int n = 0; n < decimal; n++)
		{ //初始化后缀
			strSuffix += suffix ;
		}
		intDotPosition = strReturnValue.indexOf ( "." ) ;
		if (intDotPosition < 0)
		{ //如果需要格式化的数字没有小数
			strReturnValue += "." ;
			intNumberLen = strReturnValue.length ( ) + decimal ;
		} else
		{
			intNumberLen = intDotPosition + decimal + 1 ; //取得格式化后数字的长度
		}
		strReturnValue += strSuffix ; //添加后缀
		strReturnValue = strReturnValue.substring ( 0 , intNumberLen ) ;
		return strReturnValue ;
	}
	/**
	 * @param dfValue
	 *            the double value to be format
	 * @param nFmt
	 *            format is requested.
	 * @return String
	 */
	/**
	 * @deprecated this format fucntion is unsafe and uncompatible.
	 */
	public static double formatDouble ( double dValue , int nScale )
	{
		return new Double ( format ( dValue , nScale ) ).doubleValue ( ) ;
	}
	public static double formatDouble ( double dValue )
	{
		return formatDouble ( dValue , 2 ) ;
	}
	
	/**
	 * 格式化字符串为百分数
	 * Create Date: 2007-06-15
	 * @author xkli3
	 * @param strData
	 * @param lScale
	 * @return String 格式后的字符串
	 */
	public static String formatStringToPercent(String strData, int lScale )
	{
		double dData = 0;
		if(strData == null || strData.length() == 0)
		{
			return "";
		}
		else
		{
			dData = Double.parseDouble(strData) * 100;
			if(dData == 0)
			    return "";
			else
				 return format(dData, lScale);
		}
			
	}
	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额
	 * @param dAmount
	 *            lTypeID -将0转换，2-不将0转换
	 * @return 返回格式化的金额
	 */
	public static String formatListAmount ( double dAmount , int lTypeID )
	{
		String strData = formatDisabledAmount ( dAmount , lTypeID ) ;
		if (strData == null || strData.length ( ) <= 0)
		{
			strData = "&nbsp;" ;
		}
		return strData ;
	}
	
	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额
	 * 在dAmount值为0或为空的情况下在页面显示值:0.00
	 *            
	 * @return 返回格式化的金额
	 */
	public static String formatListAmount( double dAmount,boolean condition) {
		String strData = formatDisabledAmount ( dAmount ) ;
		if (strData == null || strData.length ( ) <= 0) {
			strData = "0.00" ;
		}
		return strData ;
	}
	
	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额
	 * @return 返回格式化的金额
	 */
	public static String formatListAmount ( double dAmount )
	{
		String strData = formatDisabledAmount ( dAmount ) ;
		if (strData == null || strData.length ( ) <= 0)
		{
			strData = "&nbsp;" ;
		}
		return strData ;
	}
	
	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额
	 * @param nDigit
	 *            小数点后的位数
	 * @return 返回格式化的金额
	 */
	public static String formatDisabledAmount ( int nDigit , double dAmount )
	{
		return formatDisabledAmount ( dAmount , 1 , nDigit ) ;
	}
	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额
	 * @return 返回格式化的金额
	 */
	public static String formatDisabledAmount ( double dAmount )
	{
		return formatDisabledAmount ( dAmount , 1 ) ;
	}
	
	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额
	 * @param nType
	 *            1-将0转换，2-不将0转换
	 * @param nDigit
	 *            小数点后的位数
	 * @return
	 */
	public static String formatDisabledAmount ( double dAmount , int nType ,
			int nDigit )
	{
		String strData = "" ;
		if (nType == 1)
		{
			strData = formatAmount ( dAmount , nDigit ) ;
		} else
		{
			strData = formatAmountUseZero ( dAmount , nDigit ) ;
		}
		//if (dAmount < 0)
		//	strData = formatAmount(java.lang.Math.abs(dAmount), nDigit);
		if (strData.equals ( "" ))
		{
			return strData ;
		} else
		{
			System.out.print ( "=======strData=" + strData ) ;
			//将小数点前和后的数据分别取出来
			int nPoint ;
			nPoint = strData.indexOf ( "." ) ;
			String strFront = new String ( strData ) , strEnd = "" ;
			if (nPoint != -1)
			{
				strFront = strData.substring ( 0 , nPoint ) ;
				strEnd = strData.substring ( nPoint + 1 , strData.length ( ) ) ;
			}
			String strTemp = "" ;
			//小数点前面的数据加","
			strTemp = new String ( strFront ) ;
			strFront = "" ;
			int nNum , i ;
			nNum = 0 ;
			for (i = strTemp.length ( ) - 1; i >= 0; i--)
			{
				if (nNum == 3)
				{
					strFront = "," + strFront ;
					nNum = 0 ;
				}
				nNum++ ;
				char cData ;
				cData = strTemp.charAt ( i ) ;
				strFront = cData + strFront ;
			}
			//补或者截小数点后面的值，保持小数点后的位数
			if (strEnd.length ( ) >= nDigit)
			{
				strEnd = strEnd.substring ( 0 , nDigit ) ;
			} else
			{
				strEnd = strEnd + formatInt ( 0 , nDigit - strEnd.length ( ) ) ;
			}
			if (nDigit != 0) {
				strData = strFront + "." + strEnd ;
			} else {
				strData = strFront;
			}
		}
		System.out.print("=======strData="+strData);
		return strData ;
	}
	
	
	/**
	 * 格式化金额
	 * 
	 * @param dValue
	 *            金额
	 */
	public static String formatAmount ( double dValue )
	{
		//
		if (dValue == 0)
			return "" ;
		else if (dValue > 0)
			return format ( dValue , 2 ) ;
		else
			return "-" + format ( java.lang.Math.abs ( dValue ) , 2 ) ;
	}
	/**
	 * 格式化金额包括0
	 * 
	 * @param dValue
	 * @return
	 */
	public static String formatAmountUseZero ( double dValue )
	{
		if (dValue == 0)
			return "0.00" ;
		else if (dValue > 0)
			return format ( dValue , 2 ) ;
		else
			return "-" + format ( java.lang.Math.abs ( dValue ) , 2 ) ;
	}
	/**
	 * 反向格式化金额，将","去掉
	 * 
	 * @param strData
	 *            数据
	 */
	public static String reverseFormatAmount ( String strData )
	{
		int i ;
		String strTemp ;
		//去掉所有的","
		strTemp = new String ( strData ) ;
		strData = "" ;
		for (i = 0; i < strTemp.length ( ); i++)
		{
			char cData ;
			cData = strTemp.charAt ( i ) ;
			if (cData != ',')
			{
				strData = strData + cData ;
			}
		}
		return strData ;
	}
	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额
	 * @return 返回格式化不戴小数点没有逗号分割符的金额
	 */
	public static String formatAmountNotDot ( double dAmount )
	{
		String strData = formatAmount ( dAmount ) ;
		if (strData.equals ( "" ))
		{
			return strData ;
		} else
		{
			//将小数点前和后的数据分别取出来
			int nPoint ;
			nPoint = strData.indexOf ( "." ) ;
			String strFront = new String ( strData ) , strEnd = "" ;
			if (nPoint != -1)
			{
				strFront = strData.substring ( 0 , nPoint ) ;
				strEnd = strData.substring ( nPoint + 1 , strData.length ( ) ) ;
			}
			//补或者截小数点后面的值，保持两位
			if (strEnd.length ( ) > 2)
			{
				strEnd = strEnd.substring ( 0 , 2 ) ;
			} else
			{
				if (strEnd.length ( ) == 1)
				{
					strEnd = strEnd + "0" ;
				} else
				{
					if (strEnd.length ( ) == 0)
					{
						strEnd = "00" ;
					}
				}
			}
			strData = strFront + strEnd ;
			for (int ii = 18; ii < strData.length ( ); ii--)
			{
				strData = "&nbsp;" + strData ;
			}
		}
		return strData ;
	}
	/**
	 * 格式化列表的金额
	 * 
	 * @param dAmount
	 *            金额
	 * @param nType
	 *            1-将0转换，2-不将0转换
	 * @return
	 */
	public static String formatDisabledAmount ( double dAmount , int nType )
	{
		String strData = "" ;
		if (nType == 1)
		{
			strData = formatAmount ( dAmount ) ;
		} else
		{
			strData = formatAmountUseZero ( dAmount ) ;
		}
		if (dAmount < 0)
			strData = formatAmount ( java.lang.Math.abs ( dAmount ) ) ;
		if (strData.equals ( "" ))
		{
			return strData ;
		} else
		{
			//将小数点前和后的数据分别取出来
			int nPoint ;
			nPoint = strData.indexOf ( "." ) ;
			String strFront = new String ( strData ) , strEnd = "" ;
			if (nPoint != -1)
			{
				strFront = strData.substring ( 0 , nPoint ) ;
				strEnd = strData.substring ( nPoint + 1 , strData.length ( ) ) ;
			}
			String strTemp = "" ;
			//小数点前面的数据加","
			strTemp = new String ( strFront ) ;
			strFront = "" ;
			int nNum , i ;
			nNum = 0 ;
			for (i = strTemp.length ( ) - 1; i >= 0; i--)
			{
				if (nNum == 3)
				{
					strFront = "," + strFront ;
					nNum = 0 ;
				}
				nNum++ ;
				char cData ;
				cData = strTemp.charAt ( i ) ;
				strFront = cData + strFront ;
			}
			//补或者截小数点后面的值，保持两位
			if (strEnd.length ( ) > 2)
			{
				strEnd = strEnd.substring ( 0 , 2 ) ;
			} else
			{
				if (strEnd.length ( ) == 1)
				{
					strEnd = strEnd + "0" ;
				} else
				{
					if (strEnd.length ( ) == 0)
					{
						strEnd = "00" ;
					}
				}
			}
			strData = strFront + "." + strEnd ;
		}
		if (dAmount < 0 && !strData.equals ( "0.00" ))
			strData = "-" + strData ;
		return strData ;
	}
	/**
	 * 格式化金额
	 * 
	 * @param dValue
	 *            金额
	 * @param nDigit
	 *            小数点后的位数
	 */
	public static String formatAmount ( double dValue , int nDigit )
	{
		//
		if (dValue == 0)
			return "" ;
		else if (dValue > 0)
			return format ( dValue , nDigit ) ;
		else
			return "-" + format ( java.lang.Math.abs ( dValue ) , nDigit ) ;
	}
	/**
	 * 格式化金额包括0
	 * 
	 * @param dValue
	 * @param nDigit
	 *            小数点后的位数
	 * @return
	 */
	public static String formatAmountUseZero ( double dValue , int nDigit )
	{
		if (dValue == 0)
		{
			return "0." + formatInt ( 0 , nDigit ) ;
		} else if (dValue > 0)
		{
			return format ( dValue , nDigit ) ;
		} else
		{
			return "-" + format ( java.lang.Math.abs ( dValue ) , nDigit ) ;
		}
	}
	/**
	 * 格式化数字，例如：将5转化为4位字符，则得到0005
	 * 
	 * @param dValue
	 *            被格式化的数值
	 * @param nWidth
	 *            需要转换的位数
	 * @return
	 */
	public static String formatInt ( long lValue , int nWidth )
	{
		String strReturn = "" + lValue ;
		int initLength = strReturn.length ( ) ;
		for (int i = nWidth; i > initLength; i--)
		{
			strReturn = "0" + strReturn ;
		}
		return strReturn ;
	}
	public static String format ( double dValue , int lScale )
	{
		//		////负数，则装化为正数后进行四舍五入
		boolean bFlag = false ;
		if (dValue < 0)
		{
			bFlag = true ;
			dValue = -dValue ;
		}
		long lPrecision = 10000 ; //精度值，为了避免double型出现偏差，增加校验位
		long l45Value = lPrecision / 2 - 1 ; //四舍五入的判断值
		long lLength = 1 ; //乘的数字
		for (int i = 0; i < lScale; i++)
		{
			lLength = lLength * 10 ; //比如保留2位，乘以100
		}
		long lValue = (long) dValue ; //值的整数部分
		long lValue1 = (long) ((dValue - lValue) * lLength) ; //乘以保留位数
		long lValue2 = (long) ((dValue - lValue) * lLength * lPrecision) ; //
		long lLastValue = lValue2 - (lValue2 / lPrecision) * lPrecision ;
		if (lLastValue >= l45Value)
		{
			lValue1++ ;
		}
		dValue = lValue + (double) lValue1 / lLength ; //四舍五入后的值
		if (bFlag)
		{
			dValue = -dValue ;
		}
		java.math.BigDecimal bd = new java.math.BigDecimal ( dValue ) ;
		bd = bd.setScale ( lScale , java.math.BigDecimal.ROUND_HALF_UP ) ;
		return bd.toString ( ) ;
	}
	public static String format ( float fValue , int lScale )
	{
		java.math.BigDecimal bd , bdup , bddown ;
		bd = new java.math.BigDecimal ( fValue ) ;
		bdup = bd.setScale ( lScale , java.math.BigDecimal.ROUND_UP ) ;
		bddown = bd.setScale ( lScale , java.math.BigDecimal.ROUND_DOWN ) ;
		if ((bdup.doubleValue ( ) - bd.doubleValue ( )) <= (bd.doubleValue ( ) - bddown
				.doubleValue ( )))
		{
			return bdup.toString ( ) ;
		} else
		{
			return bddown.toString ( ) ;
		}
	}
	/**
	 * 解析格式化的字符串，转化为数值，例如：12,345.00转化为12345
	 * 
	 * @param text
	 *            被格式化的数值
	 * @return
	 */
	public static double parseNumber ( String text )
	{
		int index = text.indexOf ( "," ) ;
		String sbNumber = "" ;
		while (index != -1)
		{
			sbNumber += text.substring ( 0 , index ) ;
			text = text.substring ( index + 1 , text.length ( ) ) ;
			index = text.indexOf ( "," ) ;
		}
		sbNumber += text ;
		System.out.println ( sbNumber ) ;
		return Double.parseDouble ( sbNumber ) ;
	}
	/**
	 * 得到数字随机数
	 * 
	 * @param nLen随机数长度
	 */
	public static String randomNumberPassword ( int nLen )
	{
		long lNum = 1 ;
		for (int i = 0; i < nLen - 1; i++)
		{
			lNum = lNum * 10 ;
		}
		return String
				.valueOf ( (long) ((lNum * 10 - lNum + 1) * Math.random ( ))
						+ lNum ) ;
	}
	
	public static String format ( java.math.BigDecimal bValue , int lScale )
	{
		if (bValue != null)
		{
			bValue = bValue.setScale ( lScale , java.math.BigDecimal.ROUND_HALF_UP ) ;
			return bValue.toString() ;
		}
		else
		{
			return "";
		}
	}
	public static double BigToDouble ( java.math.BigDecimal bValue , int lScale )
	{
		if (bValue != null)
		{
			bValue = bValue.setScale ( lScale , java.math.BigDecimal.ROUND_HALF_UP ) ;
			return bValue.doubleValue() ;
		}
		else
		{
			return 0;
		}
	}
	/**
	 * 格式化数字，例如：12345转化为12,345
	 * 
	 * @param dValue
	 *            被格式化的数值 
	 * @param iScale
	 *            小数点后保留位数,不足补0
	 * @return
	 */
	public static String formatNumber ( double dValue , int iScale )
	{
		DecimalFormat df = null ;
		StringBuffer sPattern = new StringBuffer ( ",##0" ) ;
		if (iScale > 0)
		{
			sPattern.append ( "." ) ;
			for (int i = 0; i < iScale; i++)
			{
				sPattern.append ( "0" ) ;
			}
		}
		df = new DecimalFormat ( sPattern.toString ( ) ) ;
		return df.format ( dValue ) ;
	}
	public static String formatNumber ( long lValue )
	{
		return formatNumber ( (double) lValue , 0 ) ;
	}
	public static long parseLong ( String text )
	{
		int index = text.indexOf ( "," ) ;
		String sbNumber = "" ;
		while (index != -1)
		{
			sbNumber += text.substring ( 0 , index ) ;
			text = text.substring ( index + 1 , text.length ( ) ) ;
			index = text.indexOf ( "," ) ;
		}
		sbNumber += text ;
		System.out.println ( sbNumber ) ;
		return Long.parseLong ( sbNumber ) ;
	}
	/**
	 * 格式化列表的整数
	 * 
	 * @param lCount
	 *            整数
	 * @return 返回格式化的整数
	 */
	public static String formatListLong ( long lCount )
	{
		String strTemp = "" ;
		if (lCount >= 0)
		{
			strTemp = String.valueOf ( lCount ) ;
		}
		String strFront = "" ;
		int nNum , i ;
		nNum = 0 ;
		for (i = strTemp.length ( ) - 1; i >= 0; i--)
		{
			if (nNum == 3)
			{
				strFront = "," + strFront ;
				nNum = 0 ;
			}
			nNum++ ;
			char cData ;
			cData = strTemp.charAt ( i ) ;
			strFront = cData + strFront ;
		}
		return strFront ;
	}
	
	//精确的除法
	public static BigDecimal div(double v1, double v2, int scale)
	{
		if (scale < 0)
		{
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
	}
}

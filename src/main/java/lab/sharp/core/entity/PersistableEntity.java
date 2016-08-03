package lab.sharp.core.entity;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.persistence.Transient;
import javax.servlet.ServletRequest;

import lab.sharp.core.util.DateUtils;
import lab.sharp.core.util.NumberUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * 类名称: PersistableEntity
 * 类描述：实体类基类
 * 1. 所有模块的Data Entity类应该继承此类并实现相关的抽象操作<p> 
 * 2. 所有数据库中有对应字段的变量在setXXX()时必须调用PersistableEntity中定义的putUsedField使得DAO操作能够识别被使用过的成员变量及其值
 * 3. 如果需要重复使用 PersistableEntity的子类，(例如先findByID进行查询，然后对返回的DataEntity set新值并执行update操作)必须首先调用clearUsedFields()清空已使用数据 <p>
 * 创建时间: 2016-7-4 下午03:20:02
 * 创建人： 邢凌霄
 * 版本： 1.0
 */
public abstract class PersistableEntity<ID extends Serializable> extends AbstractPersistableEntity<ID> {

    /**
     * serialVersionUID:流水号
     * @since JDK 1.5
     */
    private static final long serialVersionUID = -6214804266216022245L;
    private final static Logger logger = LoggerFactory.getLogger(PersistableEntity.class);

	/**
	 * 枚举所有当前DataEntity可能用到的数据类型
	 * 如果增加了新的数据类型,请在这里增加枚举类型
	 * */
	
	public final static class DataTypeName
	{
		public final static String[] DataTypeNames = {"string","long","double","timestamp"};
		
		public static String TYPE_STRING    = "java.lang.String";
		public static String TYPE_LONG      = "long";
		public static String TYPE_DOUBLE    = "double";
		public static String TYPE_TIMESTAMP = "java.sql.timestamp";
		
		/**
		 * 根据数据类型获取对应的前缀(如果有前缀)
		 * */
		public static String getPrefixByDataType(String dataType){
			if(dataType.equalsIgnoreCase(TYPE_STRING))
				return "s";
			else if(dataType.equalsIgnoreCase(TYPE_LONG))
				return "n";
			else if(dataType.equalsIgnoreCase(TYPE_DOUBLE))
				return "m";
			else if(dataType.equalsIgnoreCase(TYPE_TIMESTAMP))
				return "dt";
			return "";
		}
	} 	
	

	
	/**记录被使用的DataEntity中的字段，在所有的setXXX函数中必须(!!)增加以下操作：
	 * 		usedFields.put("FieldName", fieldValue);
	 * 便于DAO中update时只针对需要更新的数据进行更新
	*/
	protected HashMap<String, Object> usedFields = new HashMap<String, Object>();
	
	private final String[] dataType = {"double","long","java.lang.String","java.sql.Timestamp"};			//支持的数据类型
	
	/**
	 * 获取所有被设置过的成员名称及其值
	* @param
	* @param
	* @return Hashtable 
	* @throws
	 */
	final public HashMap<String, Object> gainAllUsedFieldsAndValue(){
		return usedFields;
	}
	
	/**
	 * put被使用的成员变量到已使用表
  	 * @param　变量名
	 * @param　long型变量值　
	 * @return
	 * @throws
	 */
	protected void putUsedField(String key, long value){
		usedFields.put(key, new Long(value));		
	}
	
	/**
	 * put被使用的成员变量到已使用表
	 * @param　变量名
	 * @param　double型变量值　
	 * @return
	 * @throws
	 */	
	protected void putUsedField(String key, double value){
		usedFields.put(key, new Double(value));		
	}
	
	/**
	 * put被使用的成员变量到已使用表
	 * @param　变量名
	 * @param　Object型变量值,如果为空则不被设置　
	 * @return
	 * @throws
	 */	
	protected void putUsedField(String key, Object value){
		if(value != null)
			usedFields.put(key, value);		
	}
	/**
	 * remove从已使用表中清除单个成员变量
	 * @param　变量名
	 * @param　Object型变量值,如果为空则不被设置　
	 * @return
	 * @throws
	 */	
	public void removeUsedField(String key){
		if(key != null&&key.length()>0){
			usedFields.remove(key);	
		}
	}
					
	/**
	 * 清除已使用变量
	 * @param　
	 * @param　　
	 * @return
	 * @throws
	 */	
	public void clearUsedFields(){
		if(usedFields != null)
			usedFields.clear();
	}	
	
	/**
	 * 返回系统定义的空时间
	 * 由于ITreasuryDAO无法支持通过在DataEntity中Setter函数中通过set null将数据库中的Date类型更新为空值，因此通过
	 * 定义一个系统内的空时间(1970-01-01 08:00:00.0)作为空时间的标志时间，需要将数据库中Date类型数据更新为null的
	 * 操作，在执行setXXX操作时，调用此操作获取系统定义的空时间标志，ITreasuryDAO将在setTimeStamp时判断时间是否为
	 * 此时间，如果是，则更新该字段为null
	 * */
	static public Timestamp getNullTimeStamp(){
		return new Timestamp(0);
	}
		
	/**
	 * 重载Object的toString方法，所有的子类都可以通过此方法输出所有内容
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		StringBuffer res = new StringBuffer(getClass().getName() + " \n");
		Method[] methods = getClass().getDeclaredMethods();
		for (int i = 0; i < methods.length; i++)
		{
			Method tmp = methods[i];
			String mName = tmp.getName();
			if (mName.startsWith("get"))
			{
				String fName = mName.substring(3);
				res.append(fName + " = ");
				try
				{
					Object o = tmp.invoke(this, new Object[]{});
					if (o == null)
					{
						res.append(" null \n");
						continue;
					}
					if (o instanceof Double)
					{
						res.append("" + ((Double) o).doubleValue() + " \n");
					}
					if (o instanceof Float)
					{
						res.append("" + ((Float) o).floatValue() + " \n");
					}
					else if (o instanceof Long)
					{
						res.append("" + ((Long) o).longValue() + " \n");
					}
					else if (o instanceof String)
					{
						res.append("" + (String) o + " \n");
					}
					else if (o instanceof Timestamp)
					{
						res.append("" + ((Timestamp) o).toString() + " \n");
					}
					else
						continue;
				}
				catch (IllegalAccessException e)
				{
					continue;
				}
				catch (InvocationTargetException e)
				{
					continue;
				}
			}

		}
		return res.toString();		
	}
	/**
	 * 方法名称：convertDataEntityToRequest
	 * 方法描述：把dataentity转换成Attribute置入request
	 * 创建人： 邢凌霄
	 * 创建时间：2016年8月1日 下午5:17:29
	 * @param request
	 * @throws Exception
	 * @since JDK 1.5
	 */
	public void convertDataEntityToRequest(ServletRequest request) throws Exception
	{
		BeanInfo info = null;
		try{
			info = Introspector.getBeanInfo(this.getClass());
		}catch (IntrospectionException e) {
			throw new Exception("Java Bean.内省异常发生",e);			
		}
		logger.debug("----------从dataentity转化到request----------");
		PropertyDescriptor[] p = info.getPropertyDescriptors();
		for (int n=0;n<p.length;n++){
			if (p[n].getName().compareToIgnoreCase("class")==0) continue;
			try{
				if (p[n].getReadMethod()!=null){
					//Log.print("key:" + p[n].getName() + "// value:" + p[n].getReadMethod().invoke(this,null));
					String strValue = (p[n].getReadMethod().invoke(this,new Object[]{})==null)?"":String.valueOf(p[n].getReadMethod().invoke(this,new Object[]{}));
					
					String strReturnType = p[n].getReadMethod().getReturnType().getName();
					
					if( strReturnType.equals(dataType[0]) &&  Double.parseDouble(strValue)==0.0){//parameter type is double
						strValue = null;
					}
					else if(p[n].getReadMethod().getReturnType().getName().equals(dataType[1]) && Long.parseLong(strValue)==-1){//parameter type is long
						strValue = null;
					}
					else if(p[n].getReadMethod().getReturnType().getName().equals(dataType[2]) && strValue.equals("")){			//parameter type is String
						strValue = null;
					}
					
					if (strValue != null) request.setAttribute(p[n].getName(),strValue);	//如果返回值不是null则置入request
				}
					
			}

			catch (IllegalAccessException e)
			{
				throw new Exception("把dataentity置入request中出现错误",e);
			}
			catch(InvocationTargetException e){
				throw new Exception("把dataentity置入request中出现错误",e);
			}
		}
	}

	/**@author Barry
	 * 2004-1-9
	 * 从request中得到完整的dataentity
	 */
	public void convertRequestToDataEntity(ServletRequest request) throws Exception
	{
		BeanInfo info = null;
		try{
			info = Introspector.getBeanInfo(this.getClass());
		}catch (IntrospectionException e) {
			throw new Exception("Java Bean.内省异常发生",e);			
		}
		//Log.print("----------从request转化到dataentity----------");
		PropertyDescriptor[] p = info.getPropertyDescriptors();
		for (int n=0;n<p.length;n++){
			if (p[n].getName().compareToIgnoreCase("class")==0) continue;
				
			String strValue = (String) request.getAttribute(p[n].getName());
			
			logger.debug("key:" + p[n].getName() + "// Value:"+strValue);
			
			if (strValue != null){
				Object[] oParam = new Object[]{};
				Method m = p[n].getWriteMethod(); 
				if (m!=null){
					if(m.getParameterTypes()[0].getName().equals(dataType[0])){			//parameter type is double
						if (strValue.trim().equals("")){
							strValue = "0.0";
						}
						oParam = new Double[]{new Double(NumberUtils.parseNumber(strValue.trim()))};
					}
					else if(m.getParameterTypes()[0].getName().equals(dataType[1])){	//parameter type is long
						/**
						 * 针对ID做的特殊处理,如果对象参数的长度为0,说明更改了,赋予参数初始值
						 */
						if (strValue.trim().equals("")) strValue = "-1";
						oParam = new Long[]{new Long(strValue.trim())};
					}
					else if(m.getParameterTypes()[0].getName().equals(dataType[2])){	//parameter type is String
						oParam = new String[]{strValue.trim()};
					}
					else if(m.getParameterTypes()[0].getName().equals(dataType[3])){	//parameter type is Timestamp
						oParam = new Timestamp[]{DateUtils.getDateTime(strValue.trim())};
					}
					try
					{
						m.invoke(this,oParam);				//set parameters to dataentity
					}
					catch (IllegalArgumentException e)
					{
						throw new Exception("从request中获得dataentity错误",e);
					}
					catch (IllegalAccessException e)
					{
						throw new Exception("从request中获得dataentity错误",e);
					}
					catch(InvocationTargetException e){
						throw new Exception("从request中获得dataentity错误",e);
					}
				}
			}
		}
	}
	/**
	 * 将实体类值转换成input标签，type=hidden
	 * 
	 * */
	public static String outputDataEntityHiddenElement(Object dataEntity) throws Exception
	{
		String strOutput = "";
		final String[] dataType = { "double", "long", "java.lang.String", "java.sql.Timestamp" }; //支持的数据类型
		BeanInfo info = null;
		try
		{
			info = Introspector.getBeanInfo(dataEntity.getClass());
		}
		catch (IntrospectionException e)
		{
			throw new Exception("Java Bean.内省异常发生", e);
		}
		logger.debug("----------从dataentity转化到hidden----------");
		PropertyDescriptor[] p = info.getPropertyDescriptors();
		for (int n = 0; n < p.length; n++)
		{
			if (p[n].getName().compareToIgnoreCase("class") == 0)
				continue;
			try
			{
				//Log.print("key:" + p[n].getName() + "// value:" + p[n].getReadMethod().invoke(dataEntity,null));
				String strValue = (p[n].getReadMethod().invoke(dataEntity, new Object[]{}) == null) ? "" : String.valueOf(p[n].getReadMethod().invoke(dataEntity, new Object[]{}));
				String strReturnType = p[n].getReadMethod().getReturnType().getName();
				if (strReturnType.equals(dataType[0]) && Double.parseDouble(strValue) == 0.0)
				{ //parameter type is double
					strValue = null;
				}
				else
					if (p[n].getReadMethod().getReturnType().getName().equals(dataType[1]) && Long.parseLong(strValue) == -1)
					{ //parameter type is long
						strValue = null;
					}
					else
						if (p[n].getReadMethod().getReturnType().getName().equals(dataType[2]) && strValue.equals(""))
						{ //parameter type is String
							strValue = null;
						}
				if (strValue != null)
				{
					if (!p[n].getName().equals("id") && !p[n].getName().equals("currencyId") && !p[n].getName().equals("officeId"))
					{
						strOutput += "<input type='hidden' name= '" + p[n].getName() + "' value='" + strValue + "'> \n";
					}
				}
			}
			catch (IllegalAccessException e)
			{
				// TODO Auto-generated catch block
				throw new Exception("把dataentity置入转化为hidden域出现错误", e);
			}
			catch (InvocationTargetException e)
			{
				throw new Exception("把dataentity置入转化为hidden域出现错误", e);
			}
		}
		return strOutput;
	}

    /*
     * 用于快速判断对象是否新建状态
     * @see org.springframework.data.domain.Persistable#isNew()
     */
    @Transient
    @JsonIgnore
    public boolean isNew() {
        Serializable id = getId();
        return id == null || StringUtils.isBlank(String.valueOf(id));
    }

    /*
     * 用于快速判断对象是否编辑状态
     */
    @Transient
    @JsonIgnore
    public boolean isNotNew() {
        return !isNew();
    }



}

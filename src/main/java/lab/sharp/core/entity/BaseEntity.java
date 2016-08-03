package lab.sharp.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 抽象实体基类
 * 将公用变量抽象出来便于以后重复使用
 * 此处对变量命名进行统一规范
 * 以下全部采用骆驼命名法
 * 其中long类型以l开头，double类型以d开头,Timestamp类型以dt开头，String类型以s开头
 * 为提高开发效率，实体类字段尽量与前端页面以及数据库字段做到一一对应
 * 创建时间: 2016年8月1日 下午5:26:05
 * 创建人： 邢凌霄
 * 版本： 1.0
 * */
public abstract class BaseEntity<ID extends Serializable> extends PersistableEntity<ID> {
	/**
	 * serialVersionUID:流水号
	 * @since JDK 1.5
	 */
	private static final long serialVersionUID = 3830178058088689112L;

	private long lInputUserID ;// 创建人ID

	private Timestamp dtInputDate ;	//创建日期
	
	private long lLastModifiedUserID ;//最后修改人ID
	
	private Timestamp dtLastModifiedDate ;//最后修改时间：时分秒
	
	public void resetCommonProperties() {
		lInputUserID = -1;
		dtInputDate = null;
		lLastModifiedUserID = -1;
		dtLastModifiedDate = null;
	
	}
	public long getlLastModifiedUserID() {
		return lLastModifiedUserID;
	}
	public void setlLastModifiedUserID(long lLastModifiedUserID) {
		this.lLastModifiedUserID = lLastModifiedUserID;
		putUsedField("lLastModifiedUserID", lLastModifiedUserID);
	}
	public Timestamp getDtLastModifiedDate() {
		return dtLastModifiedDate;
	}
	public void setDtLastModifiedDate(Timestamp dtLastModifiedDate) {
		this.dtLastModifiedDate = dtLastModifiedDate;
		putUsedField("dtLastModifiedDate", dtLastModifiedDate);
	}
	public long getlInputUserID() {
		return lInputUserID;
	}
	public void setlInputUserID(long lInputUserID) {
		this.lInputUserID = lInputUserID;
		putUsedField("lInputUserID", lInputUserID);
	}
	public Timestamp getDtInputDate() {
		return dtInputDate;
	}
	public void setDtInputDate(Timestamp dtInputDate) {
		this.dtInputDate = dtInputDate;
		putUsedField("dtInputDate", dtInputDate);
	}


}

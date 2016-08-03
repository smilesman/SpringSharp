package lab.sharp.core.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 类名称: ActionMessages
 * 类描述：用于保存提示信息
 * 创建时间: 2016-7-4 下午05:51:36
 * 创建人： 邢凌霄
 * 版本： 1.0
 * @since JDK 1.5
 */
public class ActionMessages implements Serializable
{
	/**
	 * serialVersionUID:序列号
	 * @since JDK 1.5
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 存储所有提示信息
	 */
	private Collection<String> m_collMessages = new ArrayList<String>();
	
	/**
	 * ActionException默认构造方法。
	 * @see java.lang.Object#Object()
	 */
	public ActionMessages()
	{
		super();
	}

	/**
	 * 用ActionException异常构造一个对象
	 * @param actionException
	 */
	public ActionMessages(Exception exception)
	{
			this.m_collMessages.add(exception.getMessage());
	}
	/**
	 * 构造一个指定的信息ActionMessages。
	 * @param message 信息。
	 */
	public ActionMessages(String message)
	{
		this.m_collMessages.add(message);
	}

	/**
	 * 向当前对象中添加一条信息
	 * @param messageKey
	 */
	public void addMessage(String message)
	{
		this.m_collMessages.add(message);
	}

	/**
	 * 将一个异常对象中的消息添加到当前对象中
	 * @param actionException
	 */
	public void addMessage(Exception exception)
	{
		if (exception != null)
		{
				this.m_collMessages.add(exception.getMessage());
		}
	}

	/**
	 * 获得所有信息
	 * @return Enumeration
	 */
	public Collection<String> getMessages()
	{
		return this.m_collMessages;
	}

	/**
	 * 判断当前ActionError对象中是否有错误信息。
	 * @return boolean
	 */
	public boolean isEmpty()
	{
		return this.m_collMessages.isEmpty();
	}

	/**
	 * 清空当前对象中的消息
	 */
	public void clear()
	{
		this.m_collMessages.clear();
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer(128);

		Iterator<String> itTemp = this.m_collMessages.iterator();

		while (itTemp.hasNext())
		{
			sb.append((String) itTemp.next());
		}

		return sb.toString();
	}

}

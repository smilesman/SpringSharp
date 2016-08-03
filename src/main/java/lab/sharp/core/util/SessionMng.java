
package lab.sharp.core.util;

import java.io.Serializable;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import lab.sharp.core.action.ActionMessages;

public class SessionMng extends Object implements Serializable ,HttpSessionBindingListener
{
	private static final long serialVersionUID = 1L;
	private ActionMessages	m_actionMessages	= new ActionMessages () ;

	/**
	 * Returns the actionMessages.
	 * 
	 * @return ActionMessages
	 */
	public ActionMessages getActionMessages ( )
	{
		return m_actionMessages ;
	}

	public void valueBound(HttpSessionBindingEvent event) {
		
		
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		
		
	}
	
}
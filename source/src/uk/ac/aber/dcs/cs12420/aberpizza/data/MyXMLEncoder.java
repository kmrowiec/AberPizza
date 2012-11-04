/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.XMLEncoder;
import java.io.OutputStream;
import java.math.BigDecimal;

/** 
 * XMLEncoder that supports BigDecimals serialization.
 * The code is strongly based on the one from https://forums.oracle.com/forums/message.jspa?messageID=4664166#4664166
 * 
 */
public class MyXMLEncoder extends XMLEncoder {

	/**
	 * @param out OutputStream 
	 */
	public MyXMLEncoder(OutputStream out) {
		super(out);
		this.setPersistenceDelegate(BigDecimal.class, new DefaultPersistenceDelegate() {
			protected Expression instantiate(Object oldInstance, Encoder out) {
				BigDecimal bd = (BigDecimal) oldInstance;
				return new Expression(oldInstance, oldInstance.getClass(), "new", new Object[]{bd.toString()} );
			}
			protected boolean mutatesTo(Object oldInstance, Object newInstance) {
				return oldInstance.equals(newInstance);
				}
			}
			);
	}

}

/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.math.BigDecimal;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import uk.ac.aber.dcs.cs12420.aberpizza.data.Order;

/**
 * Panel contained by pop-up window that appears when user clicks "PAY" button.
 * It allows user to input tendered amount, calculates change and sets those 
 * values in the Order.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
@SuppressWarnings("serial")
public class CheckoutPanel extends JPanel implements CaretListener{
	
	private final Font font = new Font("Helvetica",Font.PLAIN, 30);
	
	/**
	 * Class representing JLabel followed by JTextField.
	 * @author Kamil Mrowiec <kam20@aber.ac.uk>
	 *
	 */
	private class NumericPanel extends JPanel{
		private JTextField text;
		public NumericPanel(String description){
			this.setLayout(new GridLayout(1, 2, 0, 0));
			JLabel label = new JLabel(description, JLabel.LEFT);
			label.setFont(font);
			text = new JTextField(8);
			text.setFont(font);
			text.setText("");
			this.add(label);
			this.add(text);
		}
	}
		
	private BigDecimal tend, chng;
	private NumericPanel tendered, change;
	private Order order;
	
	public CheckoutPanel(Order or){
		super();
		order = or;
		tendered = new NumericPanel("TENDERED : ");
		change = new NumericPanel("CHANGE : ");
		change.text.setEditable(false);
		tendered.text.addCaretListener(this);
		this.setLayout(new BorderLayout());
		this.add(tendered, BorderLayout.NORTH);
		this.add(change, BorderLayout.SOUTH);
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.CaretListener#caretUpdate(javax.swing.event.CaretEvent)
	 */
	@Override
	public void caretUpdate(CaretEvent arg0) {
		if(tendered.text.getText().length()<1) return;
		try{
		tend = new BigDecimal(tendered.text.getText());
		chng = tend.subtract(order.getSubtotal());
		if(chng.signum()==-1){ 
			change.text.setText("NEED MORE");
			order.setTendered(new BigDecimal("0.00"));
			return;
		}
		change.text.setText(chng.toString());
		order.setTendered(tend);
		order.setTendered(order.getTendered().add(new BigDecimal("0.00")));
		this.validate();
		this.repaint();
		}catch(Exception ex){
			order.setTendered(new BigDecimal("0.00"));
		};
	}
	
	
	
	
}

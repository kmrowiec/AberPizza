/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

/**
 * Simple JPanel with JSpinner, that allows user to pick a date.
 * 
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class DatePicker extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSpinner year;
	
	/**
	 * Default constructor, sets minimum date to 1 January 2012,
	 * and maximum to current date. 
	 */
	public DatePicker(){
		super();
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Calendar min = Calendar.getInstance();
		min.set(112, 0, 0);
		Date minDate = min.getTime();
	
		year = new JSpinner(new SpinnerDateModel(new Date(), 
				minDate, new Date(), Calendar.YEAR));
		year.setEditor(new JSpinner.DateEditor(year, "dd MMMMM yyyy"));
		
		this.add(year);
		
	}
	
	/**
	 * Returns the date set on the picker.
	 * @return Date that is shown by the picker
	 */
	public Date getDate(){
		return (Date)year.getValue();
	}
}

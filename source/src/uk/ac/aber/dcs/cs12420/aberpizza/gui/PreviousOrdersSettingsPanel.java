/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;

/**
 * Panel shown on the right side of PreviousOrders screen.
 * It contains sub-panel that allows user to choose time interval from which orders are displayed,
 * and information about total number of orders and total value.
 * 
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
public class PreviousOrdersSettingsPanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	private static final String CURR = (Currency.getInstance(Locale.UK)).getSymbol(Locale.UK);

	private PreviousOrdersPanel parent;
	
	private JRadioButton todayButton, oneDayButton, rangeButton, allButton;
	private ButtonGroup radioGroup;
	private DatePicker oneDayPicker, fromPicker, toPicker;
	private JPanel oneDayPanel, rangePanel;
	
	private JButton showButton;
	
	private JLabel statLabel;
	private JProgressBar progressBar;
	
	public PreviousOrdersSettingsPanel(PreviousOrdersPanel p){
		super();
		parent = p;
		this.setLayout(new BorderLayout());
		
		JPanel choicePanel = new JPanel(new GridLayout(4,1));
		choicePanel.setBorder(BorderFactory.createTitledBorder("TIME INTERVAL"));
		
		todayButton = new JRadioButton("TODAY");
		todayButton.setSelected(true);
		
		oneDayPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		oneDayButton = new JRadioButton("ONE DAY : ");
		oneDayPanel.add(oneDayButton);
		oneDayPicker = new DatePicker();
		oneDayPanel.add(oneDayPicker);
		
		rangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
		rangeButton = new JRadioButton("RANGE ");
		rangePanel.add(rangeButton);
		JPanel insideRangePanel = new JPanel(new BorderLayout(0,0));
		JPanel insideUpperRangePanel = new JPanel(new BorderLayout(0,0));
		JPanel insideLowerRangePanel = new JPanel(new BorderLayout(0,0));
		insideUpperRangePanel.add(new JLabel("FROM : "), BorderLayout.WEST);
		fromPicker = new DatePicker();
		insideUpperRangePanel.add(fromPicker,BorderLayout.EAST);
		insideLowerRangePanel.add(new JLabel("TO : "),BorderLayout.WEST);
		toPicker = new DatePicker();
		insideLowerRangePanel.add(toPicker, BorderLayout.EAST);
		insideRangePanel.add(insideUpperRangePanel, BorderLayout.NORTH);
		insideRangePanel.add(insideLowerRangePanel, BorderLayout.SOUTH);
		rangePanel.add(insideRangePanel);
		
		allButton = new JRadioButton("ALL ORDERS");
		
		radioGroup = new ButtonGroup();
		radioGroup.add(todayButton);
		radioGroup.add(oneDayButton);
		radioGroup.add(rangeButton);
		radioGroup.add(allButton);
		
		choicePanel.add(todayButton);
		choicePanel.add(oneDayPanel);
		choicePanel.add(rangePanel);
		choicePanel.add(allButton);
		
		JPanel lowerPanel = new JPanel();
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
		showButton = new JButton("SHOW");
		showButton.setPreferredSize(new Dimension(150,50));
		showButton.addActionListener(this);
		JButton back = new JButton("BACK TO TILL");
		back.addActionListener(this);
		back.setPreferredSize(new Dimension(150,50));
		JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		buttonsPanel.add(showButton);
		buttonsPanel.add(back);
		JPanel outerStatPanel = new JPanel(new BorderLayout(0,10));
		JPanel statPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		statLabel = new JLabel("");
		lowerPanel.add(buttonsPanel);
		statPanel.add(statLabel);
		outerStatPanel.add(statPanel, BorderLayout.WEST);
		lowerPanel.add(outerStatPanel);

		progressBar = new JProgressBar();
		progressBar.setIndeterminate(true);
		progressBar.setVisible(false);
		lowerPanel.add(progressBar);
		
		this.add(choicePanel, BorderLayout.NORTH);
		this.add(lowerPanel, BorderLayout.CENTER);
	}
	
	public void resetLoadedOrdersLabel(){
		statLabel.setText("<html>LOADED <h2>" + parent.getTotalOrders() + "</h2>" +
				((parent.getTotalOrders()>1) ? " ORDERS" : " ORDER") +". \n" +
				"TOTAL VALUE: <h2>" +CURR+ parent.getTotalAmount().toString()+"</h2></html>"); 
	}

	/**
	 * Listener that checks which option of time interval user choose,
	 * and takes actions. It listens to the "SHOW" button, and checks which radio button is selected.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("BACK TO TILL")){
			parent.getFrame().switchView("TILL");
			return;
		}
		if(todayButton.isSelected()){
			PreviousOrdersLoader loader = new PreviousOrdersLoader(parent, new Date(), null);
			loader.start();
		}
		if(oneDayButton.isSelected()){
			PreviousOrdersLoader loader = new PreviousOrdersLoader(parent, oneDayPicker.getDate(), null);
			loader.start();
		}
		if(rangeButton.isSelected()){
			PreviousOrdersLoader loader = new PreviousOrdersLoader(parent, fromPicker.getDate(), toPicker.getDate());
			loader.start();
		}
		if(allButton.isSelected()){
			Calendar cal = Calendar.getInstance();
			cal.clear();
			cal.set(2012, 3, 1, 0, 0, 0);
			PreviousOrdersLoader loader = new PreviousOrdersLoader(parent, cal.getTime(), new Date());
			loader.start();
		}
		
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}
}

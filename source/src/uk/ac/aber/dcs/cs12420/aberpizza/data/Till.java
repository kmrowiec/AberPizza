/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.data;

import java.beans.XMLDecoder;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Most important class, represents a Till.
 * 
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 * 
 */
public class Till {
	
	private ArrayList<Order> orders;
	private ArrayList<StoreItem> pizzas, sides, drinks;
	
	/**
	 * Creates empty Till.
	 * @see uk.ac.aber.dcs.cs12420.aberpizza.data.Till#load()
	 */
	public Till(){
		orders = new ArrayList<Order>();
		pizzas = new ArrayList<StoreItem>();
		sides = new ArrayList<StoreItem>();
		drinks = new ArrayList<StoreItem>();
	}
	
	/**
	 * Adds order to the list of orders.
	 * @param order Order to be added
	 */
	public void addOrder(Order order){
		orders.add(order);		
	}
	
	/**
	 * Calculates total for day, by simply taking subtotal from each order and adding it.
	 * @return total for a day
	 */
	public BigDecimal getTotalForDay(){
		BigDecimal total = new BigDecimal("0.00");
		for(int i=0;i<orders.size();i++){
			total = total.add(orders.get(i).getSubtotal());
		}
		return total;
	}
	
	/**
	 * Method saves the till to xml files.
	 * Separate xml's are created for pizzas, sides and drinks.
	 */
	public void save(){
		
		try{
			
			MyXMLEncoder encoder = new MyXMLEncoder(new BufferedOutputStream(new FileOutputStream("settings.xml")));
			encoder.writeObject(pizzas);
			encoder.writeObject(sides);
			encoder.writeObject(drinks);
			encoder.close();
			
			//Saving orders that were taken that day.
			//The idea is that separate files contain orders from different days.
			//All the files will be in folder "orders", and filename is: YYYYMMDD.xml
			Date date = new Date(); // Getting current date.
			Calendar cal = Calendar.getInstance(); //Crating calendar to extract day, month and year.
			cal.setTime(date);
			String y, m, d;
			y = Integer.toString(cal.get(Calendar.YEAR));
			m = Integer.toString(cal.get(Calendar.MONTH)+1);
			d = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
			String filename = y+"_"+m+"_"+d+".xml"; //Setting name of the file.
			
			
			encoder = new MyXMLEncoder(new BufferedOutputStream(new FileOutputStream("./orders/" + filename)));
			encoder.writeObject(orders);
			encoder.close();
			
		}catch(IOException e){
			System.out.println(e);
		}
		
	}
	
	/**
	 * Method that loads till from xml files.
	 * @return Till that has been loaded
	 */
	@SuppressWarnings("unchecked")
	public static Till load() throws IOException{
		Till till = new Till();
		
		//loading pizzas, sides and drinks
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream("settings.xml")));
		till.pizzas = (ArrayList<StoreItem>)decoder.readObject();
		till.sides = (ArrayList<StoreItem>)decoder.readObject();				
		till.drinks = (ArrayList<StoreItem>)decoder.readObject();
		decoder.close();
		
		// loading orders using current date
		// if there is no orders for the day, empty list will be returned
		try{
		till.orders = Till.loadOrders(new Date());
		}catch(FileNotFoundException ex){
			till.orders = new ArrayList<Order>();
		}
		return till;
	}
	
	/**
	 * Loads list of orders from the day specified by Date parameter.
	 * 
	 * @param date Date that specifies the day the orders were taken.
	 * @return loaded Orders
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<Order> loadOrders(Date date) throws IOException{
		
		// preparing the name of the file to load 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String y, m, d;
		y = Integer.toString(cal.get(Calendar.YEAR));
		m = Integer.toString(cal.get(Calendar.MONTH)+1);
		d = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		String filename = y+"_"+m+"_"+d+".xml";
		
		//creating variable where loaded list will be written into
		ArrayList<Order> loaded = new ArrayList<Order>();
		
		//reading from file 
		File file = new File("./orders/" + filename);
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(file)));
		loaded = (ArrayList<Order>)decoder.readObject();
		decoder.close();

		return loaded;
	}
	
	/**
	 * Method adds a store item, available for sale, to the till.
	 * @param item item to be added
	 */
	public void addStoreItem(StoreItem item){
		switch(item.getProductType()){
		case SIDE:
			sides.add(item);
			break;
		case DRINK:
			drinks.add(item);
			break;
		default:
			pizzas.add(item);
			break;
		}
	}

	/**
	 * @return the orders
	 */
	public ArrayList<Order> getOrders() {
		return orders;
	}

	/**
	 * @return the pizzas
	 */
	public ArrayList<StoreItem> getPizzas() {
		return pizzas;
	}

	/**
	 * @return the sides
	 */
	public ArrayList<StoreItem> getSides() {
		return sides;
	}

	/**
	 * @return the drinks
	 */
	public ArrayList<StoreItem> getDrinks() {
		return drinks;
	}

	/**
	 * Method compares one till to another, by checking if pizzas, snacks and drinks are the same.
	 * It will be used mostly for testing saving and loading, other applications are very unlike.
	 */
	@Override
	public boolean equals(Object obj) {
		Till other = (Till) obj;
		
		if((other.getPizzas()).equals(pizzas))
		if((other.getSides()).equals(sides))
		if((other.getDrinks()).equals(drinks))
	    if(other.getOrders().equals(orders))
		return true; 
		
		return false; 
	}
	
	
	

}

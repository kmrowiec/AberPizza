/**
 * 
 */
package uk.ac.aber.dcs.cs12420.aberpizza.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Convenient way of running all tests.
 * @author Kamil Mrowiec <kam20@aber.ac.uk>
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	TestDiscounts.class, 
	TestOrder.class,
	TestOrderItem.class,
	TestStoreItem.class,
	TestTill.class}
)
public class RunAllTests {

}

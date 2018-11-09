package ShipRS.com.ntl.SRS;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ntl.UtilImpl.LoggedInTest;
import com.ntl.UtilImpl.PaymentImplTest;
import com.ntl.UtilImpl.SignedUpTest;
import com.ntl.daoImpl.CredentialsBeanDaoImplTest;
import com.ntl.daoImpl.PassengerBeanDaoImplTest;
import com.ntl.daoImpl.PaymentDaoImplTest;
import com.ntl.daoImpl.ProfileBeanDaoImplTest;
import com.ntl.daoImpl.ReservationBeanDaoImplTest;
import com.ntl.daoImpl.RouteBeanDaoImplTest;
import com.ntl.daoImpl.ScheduleBeanDaoImplTest;
import com.ntl.daoImpl.ShipBeanDaoImplTest;
import com.ntl.srs.serviceImpl.AdministratorTest;
import com.ntl.srs.serviceImpl.CustomerImpl;
import com.ntl.srs.serviceImpl.CustomerImplTest;


/**
 * Unit test for simple App.
 */





@RunWith(Suite.class)
@Suite.SuiteClasses({
	LoggedInTest.class,
	SignedUpTest.class,
	PaymentImplTest.class,
	AdministratorTest.class,
	CustomerImplTest.class,
	ShipBeanDaoImplTest.class,
	CredentialsBeanDaoImplTest.class,
	ProfileBeanDaoImplTest.class,
	PassengerBeanDaoImplTest.class,
	PaymentDaoImplTest.class,
	ReservationBeanDaoImplTest.class,
	ScheduleBeanDaoImplTest.class,
	RouteBeanDaoImplTest.class
	
})
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}

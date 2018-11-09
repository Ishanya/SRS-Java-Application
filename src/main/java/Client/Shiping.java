package Client;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.ntl.srs.bean.CredentialsBean;
import com.ntl.srs.bean.PassengerBean;
import com.ntl.srs.bean.PaymentBean;
import com.ntl.srs.bean.ProfileBean;
import com.ntl.srs.bean.ReservationBean;
import com.ntl.srs.bean.RouteBean;
import com.ntl.srs.bean.ScheduleBean;
import com.ntl.srs.bean.ShipBean;
import com.ntl.srs.daoImpl.CredentialsBeanDaoImpl;
import com.ntl.srs.daoImpl.ReservationBeanDaoImpl;
import com.ntl.srs.daoImpl.RouteBeanDaoImpl;
import com.ntl.srs.daoImpl.ScheduleBeanDaoImpl;
import com.ntl.srs.service.Administrator;
import com.ntl.srs.serviceImpl.AdministratorImpl;
import com.ntl.srs.serviceImpl.CustomerImpl;
import com.ntl.srs.utilImpl.LoggedIn;
import com.ntl.srs.utilImpl.PaymentImpl;
import com.ntl.srs.utilImpl.SignedUp;




public class Shiping {
	static String reserveId;
	static Scanner sc=new Scanner(System.in);
	LoggedIn log=new LoggedIn();
	static double tfare=0.0;
	CredentialsBean sign=new CredentialsBean();
	ProfileBean profile=new ProfileBean();
	SignedUp signup=new SignedUp();
	AdministratorImpl admin=new AdministratorImpl();
	CustomerImpl cust=new CustomerImpl();
	static CredentialsBean credit=null;
	PaymentImpl pay=new PaymentImpl();
	RouteBeanDaoImpl rt=new RouteBeanDaoImpl();
	ScheduleBeanDaoImpl st=new ScheduleBeanDaoImpl();
	
	ReservationBeanDaoImpl re=new ReservationBeanDaoImpl();
	
	static Logger loggr=Logger.getLogger(Shiping.class);
	
	public static void main(String z[]) throws ClassNotFoundException, IOException, SQLException
	{
		System.out.println("hello");
		boolean val=true;
		while(val) {
		Shiping userid=new Shiping();
		val=userid.getData();
		}
	}
	
	public boolean getData() throws IOException, ClassNotFoundException, SQLException
	{
		loggr.info("checking whether it is existing user!");
		Shiping user=new Shiping();
		System.out.println("Are you an existing User? (yes/no/out)");
		String choose=sc.nextLine();
		if(choose.equalsIgnoreCase("yes"))
		{
			return user.getLogin();
			
		}
		else if(choose.equalsIgnoreCase("no")) {
			return user.getSignup();
			
		}
		else {
			
			System.out.println("Program will close!! you really wanna proceed?");
			String fin=sc.nextLine();
			if(fin.equalsIgnoreCase("yes"))
			System.exit(0);
			else {
				return true;
			}
		}
		
		return false;
	}
	
	
	public boolean getLogin() throws ClassNotFoundException, IOException, SQLException
	{
		loggr.info("Logging in");
		Shiping use=new Shiping();
		
		System.out.println("enter Id");
		String lid=sc.nextLine();
	
		System.out.println("enter password");
		String pass=sc.nextLine();
	
		credit=new CredentialsBean(lid,pass);
//		CredentialsBeanDaoImpl cr=new CredentialsBeanDaoImpl();
//		cr.findByID(lid);
		String result=signup.login(credit);
		//System.out.println(result);
		if(result.equals("A"))
		{
			System.out.println("hello admin");
			boolean admn=true;
			String activity="";
			while(admn)
			{
				System.out.println("want to do any activity?(yes/no/out/Change)");
				 activity=sc.nextLine();
				if(activity.equalsIgnoreCase("yes"))
				{
					
					
					System.out.println("enter the field you wanna Alter (Ship/Route/Schedule)");
					String field=sc.nextLine();
					if(field.equalsIgnoreCase("ship"))
					{
						ArrayList<ShipBean> alShip=new ArrayList<ShipBean>();
						alShip=admin.viewByAllShips();
						for(ShipBean shi:alShip)
						{
							System.out.println(shi);
						}
						System.out.println("\n");
						System.out.println("enter the specific task(add/modify)");
						String taskship=sc.nextLine();
						if(taskship.equalsIgnoreCase("add"))
						{
							if(use.shipAdd())
								System.out.println("added successfully");
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("modify")) {
							if(use.shipModify())
								System.out.println("modified successfully");
							else {
								System.out.println("can't modify");
							}
							admn=true;
						}
						else {
							System.out.println("something wrong input");
							admn=true;
						}
						
					}
					else if(field.equalsIgnoreCase("Route")){
						
						ArrayList<RouteBean> alroute=new ArrayList<RouteBean>();
						alroute=admin.viewByAllRoute();
						for(RouteBean shi:alroute)
						{
							System.out.println(shi);
						}
						System.out.println("\n");
						
						
						System.out.println("enter the specific task(add/modify/delete)");
						String taskship=sc.nextLine();
						if(taskship.equalsIgnoreCase("add"))
						{
							if(use.routeAdd())
							{
								System.out.println("added successfully");
							}
							else {
								System.out.println("something wrong");
							}
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("modify")) {
							if(use.routeModify())
								System.out.println("modified successfully");
							else {
								System.out.println("can't modify");
							}
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("delete")) {
							if(use.routeDelete())
								System.out.println("deleted successfully");
							else {
								System.out.println("something wrong");
							}
							admn=true;
						}
						else {
							System.out.println("something wrong input");
							admn=true;
						}
						
					}
					else if(field.equalsIgnoreCase("Schedule")){
						ArrayList<ScheduleBean> alSchedule=new ArrayList<ScheduleBean>();
						alSchedule=admin.viewByAllSchedule();
						for(ScheduleBean shi:alSchedule)
						{
							System.out.println(shi);
						}
						System.out.println("\n");
						System.out.println("enter the specific task(add/modify/delete)");
						String taskship=sc.nextLine();
						if(taskship.equalsIgnoreCase("add"))
						{
							if(use.scheduleAdd())
							{
								System.out.println("added successfully");
							}
							else {
								System.out.println("something wrong");
							}
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("modify")) {
							if(use.scheduleModify())
								System.out.println("modified successfully");
							
							else {
								System.out.println("something wrong");
								
							}
							admn=true;
						}
						else if(taskship.equalsIgnoreCase("delete")) {
							if(use.scheduleDelete())
							{
								System.out.println("deleted successfully");
								
							}
							else {
								System.out.println("something wrong");
								
							}
							admn=true;
						}
						else {
							System.out.println("something wrong input");
							admn=true;
						}
						
					}
				else{
					System.out.println(" wrong input");
					admn=true;
				}
				}
				else if(activity.equalsIgnoreCase("no"))  {
					System.out.println("Thankyou, hope to see you soon!");
					admn=false;
					signup.logout(credit.getUserID());
				}
				else if(activity.equalsIgnoreCase("Change"))
				{
					use.changingPassword(credit);
					admn=true;
				}
				else if(activity.equalsIgnoreCase("out"))
				{
					admn=false;
					signup.logout(credit.getUserID());
				}
				else {
					admn=true;
				}
			}
		}
		else if(result.equals("C"))
		{
			System.out.println("hello customer");
			boolean custm=true;
			while(custm) {
			System.out.println("ticket functionality (reserve/cancel/view/payment/out)");
			String ticket=sc.nextLine();
			if(ticket.equalsIgnoreCase("reserve"))
			{
				reserveId=use.reserveTicket();
				custm=true;
			}
			else if(ticket.equalsIgnoreCase("cancel"))
			{
				System.out.println("enter reservation Id");
				String rrid=sc.nextLine();
				
				cust.cancelTicket(rrid);
				custm=true;
			}
			else if(ticket.equalsIgnoreCase("payment"))
			{
				System.out.println("enter the reservation Id");
				String reId=sc.nextLine();
				
				if(use.paymentCredit())
				{
					ReservationBean reser=new ReservationBean();
					reser=re.findByID(reId);
					cust.changeBookingStatus(reser);
					ArrayList<PassengerBean> ans=new ArrayList<PassengerBean>();
					 ans=use.passengerInput(reId,reser.getNoOfSeats());
					if(ans!=null)
					{
					if(cust.addingPassengers(ans)!=null) {
						System.out.println("Yours Seats are reserved. Thankyou");
					}
					}
				}
				custm=true;
			}
			else if(ticket.equalsIgnoreCase("view"))
			{
				System.out.println("enter reservation Id");
				String rrid=sc.nextLine();
				
				Map<ReservationBean,PassengerBean> map = new HashMap<ReservationBean,PassengerBean>();
				map=cust.viewTicket(rrid);
				
				 for ( ReservationBean key : map.keySet() )
			        {
					 for(PassengerBean val:map.values())
					 {
						 System.out.println(key.getReservationID()+" "+key.getTotalFare()+" "+key.getJourneyDate()+" "+val.getName());
					 }
					 
			            //System.out.println( map.get( key ) );
			        }
				 custm=true;
			}
			else if(ticket.equalsIgnoreCase("out"))
			{
				custm=false;
				signup.logout(credit.getUserID());
			}
			else {
				custm=true;
			}
			}
		}
		else if(result.equals("fail"))
		{
			System.out.println(" Already logged in!");
		}
		else {
			System.out.println("no such user exists");
		}
		return true;
		
	}
		/*if(log.authenticate(credit))
		{
			String userType=log.authorize(lid);
			 log.changeLoginStatus(credit,credit.getLoginStatus());
			 
			if(userType.equals("A"))
			{
				System.out.println("hello admin");
			}
			else if(userType.equals("C")) {
				System.out.println("hello customer");
			}
			else {
			
			return true;
			}
		
		}
		else {
			System.out.println("something wrong with password or already logged in!");
			return true;
		}
		return true;
	}
	*/
	public boolean getSignup() throws IOException, ClassNotFoundException
	{
		loggr.info("Signing UP");
		System.out.println("Enter information in standard format");
		
		boolean a=true;
		String fname=null;
		while(a) {
		System.out.println("enter Firstname");
		 fname=sc.nextLine();
		 if(signup.validating(fname)) {
			 a=false;
		 }
		}
		//profile.setFirstName(fname);
		
		
		boolean b=true;
		String lname=null;
		while(b) {
		System.out.println("enter Lastname");
		 lname=sc.nextLine();
		 if(signup.validating(lname)) {
			 b=false;
		 }
		}
		//profile.setPassword(lname);
		
		boolean toCheckDate=true;
		String dobirth=null;
		while(toCheckDate) {
		System.out.println("Date of Birth in format (dd/mm/yyyy)");
		 dobirth=sc.nextLine();
		 if(Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)",dobirth))
		 {
			 toCheckDate=false;
		 }
		}
		String dateOfBirth[]=dobirth.split("/");
		LocalDate dob=LocalDate.of(Integer.parseInt(dateOfBirth[2]),Integer.parseInt(dateOfBirth[1]), Integer.parseInt(dateOfBirth[0]));
		//profile.setDateOfBirth(dob);
		
		boolean c=true;
		String gender=null;
		while(c) {
		System.out.println("enter gender(Male/Female)");
		 gender=sc.nextLine();
		 if(signup.validating(gender)) {
			 c=false;
		 }
		}
		//profile.setGender(gender);
		
		boolean d=true;
		String street=null;
		while(d) {
		System.out.println("enter Street");
		 street=sc.nextLine();
		 if(signup.validating(street)) {
			 d=false;
		 }
		}
	//	profile.setStreet(street);
		
		boolean e=true;
		String location =null;
		while(e) {
		System.out.println("enter Location");
		 location=sc.nextLine();
		 if(signup.validating(location)) {
			 e=false;
		 }
		}
//		profile.setLocation(location);
		
		boolean f=true;
		String city=null;
		while(f) {
		System.out.println("enter city");
		 city=sc.nextLine();
		 if(signup.validating(city)) {
			 f=false;
		 }
		}
	//	profile.setCity(city);
		boolean g=true;
		String state=null;
		while(g) {
		System.out.println("enter state");
		state=sc.nextLine();
		 if(signup.validating(state)) {
			 g=false;
		 }
		}
		//	profile.setState(state);
		
		String pincode=null;
		boolean toCheckPincode=true;
		while(toCheckPincode) {
		System.out.println("enter Pincode");
		 pincode=sc.nextLine();
		 if(Pattern.matches("[0-9]{6}", pincode)) {
			 toCheckPincode=false;
		 }
		}
	//	profile.setPincode(pincode);
		
		String mobile=null;
		boolean toCheckMobile=true;
		while(toCheckMobile) {
		System.out.println("enter MobileNo");
		mobile=sc.nextLine();
		if(Pattern.matches("[7-9][0-9]{9}", mobile)) {
			toCheckMobile=false;
			}
		}
	//	profile.setMobileNo(mobile);
		String email=null;
		boolean toCheckEmail=true;
		 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"; 
		while(toCheckEmail) {
		System.out.println("enter email-ID");
		 email=sc.nextLine();
		
		 if(Pattern.matches(emailRegex,email)) {
			 toCheckEmail=false;
		 }
		}
	//	profile.setEmailID(email);
		String pass=null;
		boolean toCheckPassword=true;
		while(toCheckPassword) {
		System.out.println("enter Password (correct format)");
		 pass=sc.nextLine();
		 if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", pass)) {
			 toCheckPassword=false;
		 }
		}
		//profile.setPassword(pass);
		
		
		Random rand = new Random();
			String regid=fname.substring(0, 2)+String.format("%04d", rand.nextInt(10000));
			System.out.println("please NOTE UR UNIQUE ID: "+regid);
		
		ProfileBean profileBean=new ProfileBean(regid,fname,lname,dob,gender,street,location,city,state,pincode,mobile,email,pass);
		
		
		String registration=signup.register(profileBean) ;
		if(registration!=null)
		{
			System.out.println("registration Successfull");
		}
		else {
			System.out.println("something is wrong");
		}
			
	return true;
	
	}
	
	public boolean changingPassword(CredentialsBean cb) throws IOException, ClassNotFoundException
	{
		loggr.info("Changing Password");
		
		System.out.println("enter old password");
		String passwd=sc.nextLine();
		//profile.setGender(gender);
		
		String pass=null;
		boolean toCheckPass=true;
		while(toCheckPass) {
		System.out.println("enter new password in correct format");
		 pass=sc.nextLine();
		 if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", pass)) {
			 toCheckPass=false;
		 }
		}
	//	profile.setStreet(street);
		
		System.out.println("confirm password");
		String cnfpass=sc.nextLine();
//		profile.setLocation(location);
		
		if(cb.getPassword().equals(passwd) && pass.equals(cnfpass))
		{
			cb.setPassword(pass);
			String str=signup.changePassword(cb,pass);
			
			if(str!=null)
			{
				System.out.println("password changed for user"+str);
			}
			else {
				System.out.println("Unsuccessful");
			}
		}
		else {
			System.out.println("something is not correct");
		}
		return true;
	}
	
	public boolean shipAdd() throws SQLException
	{
		
		loggr.info("Adding ships");
		
		boolean h=true;
		String shipName=null;
		while(h) {
		System.out.println("enter Ship Name");
		 shipName=sc.nextLine();
		 if(signup.validating(shipName)) {
			 h=false;
		 }
		}
	//	profile.setStreet(street);
		
		System.out.println("enter Seating Capacity");
		int seatCap=sc.nextInt();
//		profile.setLocation(location);
		
		System.out.println("enter Reservation Capacity");
		int resCap=sc.nextInt();
	//	profile.setCity(city);
		
		
		
		
		ShipBean ship=new ShipBean(shipName,seatCap,resCap);
		Random rand = new Random();
		ship.setShipID(ship.getShipName().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
		System.out.println("please NOTE UR UNIQUE ID: "+ship.getShipID());
		
		if(admin.addShip(ship)!=null) {
			
			return true;
		}
		return false;
	}

	public boolean shipModify() throws SQLException
	{
		loggr.info("Modifying Ships");
		System.out.println("enter Ship ID you wanna modify");
		String shipId=sc.nextLine();
		ShipBean ship=admin.viewByShipId(shipId);
		
		if(ship!=null) {
			
			boolean i=true;
			String shipName=null;
			while(i) {
		System.out.println("enter Ship Name");
		 shipName=sc.nextLine();
		 if(signup.validating(shipName)) {
			 i=false;
		 }
			}
		ship.setShipName(shipName);
		
		System.out.println("enter Seating Capacity");
		int seatCap=sc.nextInt();
		ship.setSeatingCapacity(seatCap);
		
		System.out.println("enter Reservation Capacity");
		int resCap=sc.nextInt();
		ship.setReservationCapacity(resCap);
		
		if(admin.modifyShip(ship)) {
			
			return true;
			}
		
		}
		else {
			System.out.println("no such id exists!");
			
		}
		return false;
	}
	
	public boolean scheduleAdd() throws SQLException
	{
		loggr.info("Adding Schedule for ship");
		
		boolean j=true;
		String ShipId=null;
		while(j) {
		System.out.println("enter ShipId");
		 ShipId=sc.nextLine();
		 if(signup.validating(ShipId)) {
			 j=false;
		 }
		}
		
		ShipBean swbean=admin.viewByShipId(ShipId);
		if(swbean==null)
		{
			System.out.println("this shipId doesnot match");
			return false;
		}
	
		boolean k=true;
		String RouteId=null;
		while(k) {
		System.out.println("enter RouteId");
		 RouteId=sc.nextLine();
		 if(signup.validating(RouteId)) {
			 k=false;
		 }
		}
		RouteBean rbean=admin.viewByRouteId(RouteId);
		if(rbean==null)
		{
			System.out.println("this routeId doesnot match");
			return false;
		}
		


		
		boolean toCheckDates=true;
		String stdate=null;
		while(toCheckDates) {
		System.out.println("Starting Date in format (dd/mm/yyyy)");
		 stdate=sc.nextLine();
		 if(Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((21|20)\\d\\d)",stdate))
			 {
				 toCheckDates=false;
			 }
		}
		String startd[]=stdate.split("/");
		LocalDate startDate=LocalDate.of(Integer.parseInt(startd[2]),Integer.parseInt(startd[1]), Integer.parseInt(startd[0]));
	
	
		
		ScheduleBean sched=new ScheduleBean(RouteId,ShipId,startDate);
		
		AdministratorImpl ad=new AdministratorImpl();
		Random rand = new Random();
		RouteBean rot=ad.viewByRouteId(sched.getRouteID());
		sched.setScheduleID(rot.getSource().substring(0, 2)+rot.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
		System.out.println("please NOTE UR UNIQUE ID: "+sched.getScheduleID());
	
		if(admin.addSchedule(sched)!=null) {
			return true;
		}
		return false;
	}
	
	public boolean scheduleModify() throws SQLException
	{
		loggr.info("Modifying schedule");
		System.out.println("enter Schedule ID you wanna modify");
		String scheduleId=sc.nextLine();
		ScheduleBean sched=admin.viewByScheduleId(scheduleId);
		
		if(sched!=null) {
		System.out.println("enter ShipId");
		String shipId=sc.nextLine();
		ShipBean sbean=admin.viewByShipId(shipId);
		if(sbean!=null)
		sched.setShipID(shipId);
		else {
			System.out.println("this shipId doesnot match");
			return false;
		}
		
		System.out.println("enter Route Id");
		String routeId=sc.nextLine();
		RouteBean rbean=admin.viewByRouteId(routeId);
		if(rbean!=null)
		{
			sched.setRouteID(routeId);
		}
		else {
			System.out.println("this routeId doesnot match");
			return false;
		}
		
		
		boolean toCheckDatete=true;
		String stdatete=null;
		while(toCheckDatete) {
		System.out.println("Starting Date in format (dd/mm/yyyy)");
		 stdatete=sc.nextLine();
		 if(Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((20|21)\\d\\d)",stdatete))
			 {
				 toCheckDatete=false;
			 }
		}
		String startdte[]=stdatete.split("/");
		LocalDate startDatete=LocalDate.of(Integer.parseInt(startdte[2]),Integer.parseInt(startdte[1]), Integer.parseInt(startdte[0]));
		sched.setStartDate(startDatete);
		
		if(admin.modifySchedule(sched)) {
			
			return true;
		}}
		else {
			System.out.println("no such id exists");
		}
		return false;
	}
	
	public boolean scheduleDelete() throws SQLException
	{
		loggr.info("Deleting Schedule");
		ArrayList<String> al=new ArrayList<String>();
		System.out.println("wanna delete any schedule?");
		String reply=sc.nextLine();
		while(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("enter ScheduleId");
			String schedId=sc.nextLine();
			if(st.findByID(schedId)==null)
			{
				System.out.println("no such id exists!");
				return false;
			}
			al.add(schedId);
			System.out.println("wanna delete more?");
			reply=sc.nextLine();
		}
	
		if(al!=null) {
		
		if(admin.removeSchedule(al)!=0) {
			return true;
		}
	
	}
	else {
		System.out.println("no item selected");
	}
		return false;
	}
	public boolean routeAdd() throws SQLException
	{
		loggr.info("Adding route");
		
		boolean l=true;
		String source=null;
		while(l) {
		System.out.println("enter source");
		source=sc.nextLine();
		 if(signup.validating(source)) {
			 l=false;
		 }
		}
		
		
		boolean m=true;
		String dest=null;
		while(m) {
		System.out.println("enter destination");
		 dest=sc.nextLine();
		 if(signup.validating(dest)) {
			 m=false;
		 }
		}
		
		System.out.println("enter TravelDistance");
		String travelDist=sc.nextLine();
	
		
		System.out.println("enter Fare");
		int fare=sc.nextInt();
		
		RouteBean rot=new RouteBean(source,dest,travelDist,fare);
		Random rand = new Random();
		rot.setRouteID(rot.getSource().substring(0, 2)+rot.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000)));
		System.out.println("please NOTE UR UNIQUE ID: "+rot.getRouteID());
		
		if(admin.addRoute(rot)!=null) {
			
			return true;
		}
		return false;
	}
	
	public boolean routeModify() throws SQLException
	{
		loggr.info("Modifying route");
		System.out.println("enter route ID you wanna modify");
		String routeId=sc.nextLine();
		RouteBean rot=admin.viewByRouteId(routeId);
		
		if(rot!=null) {
			
			
			boolean n=true;
			String source=null;
			while(n) {
		System.out.println("enter source");
		 source=sc.nextLine();
		 if(signup.validating(source)) {
			 n=false;
		 }
			}
		rot.setSource(source);
		
		
		boolean p=true;
		String dest=null;
		while(p){
		System.out.println("enter destination");
		 dest=sc.nextLine();
		 if(signup.validating(dest)) {
			 p=false;
		 }
		}
		rot.setDestination(dest);

		System.out.println("enter travel Duration");
		String tduration=sc.nextLine();
		rot.setTravelDuration(tduration);
	
		System.out.println("enter fare");
		int fare=sc.nextInt();
		rot.setFare(fare);
		
		if(admin.modifyRoute(rot)) {
			
			return true;
		}
		
		}
		else {
			System.out.println("id wrong");
		}
		return false;
	}
	
	public boolean routeDelete() throws SQLException
	{
		loggr.info("Deleting Route");
		String id="";
		System.out.println("wanna delete any schedule?");
		String reply=sc.nextLine();
		while(reply.equalsIgnoreCase("yes"))
		{
			System.out.println("enter routeId");
			String routeId=sc.nextLine();
			if(rt.findByID(routeId)==null)
			{
				System.out.println("no such id exists!");
				return false;
			}
			id+=routeId;
			System.out.println("wanna delete more?");
			reply=sc.nextLine();
		}
	
		if(id!="") 
		{
			
			if(admin.removeRoute(id)!=0) 
			{
				return true;
				
			}
			
		}
		else {
			System.out.println("no item selected");
		}
		return false;
	}
	
	
	public boolean allShips() throws SQLException
	{
		loggr.info("View All Ships ");
		ArrayList<ShipBean> al=admin.viewByAllShips();
		for(ShipBean i:al)
		{
			System.out.println(i.getShipID()+" "+i.getShipName()+" "+i.getSeatingCapacity()+" "+i.getReservationCapacity());
		}
		return true;
	}
	
	public boolean allRoutes() throws SQLException
	{
		loggr.info("View All Routes");
		ArrayList<RouteBean> al=admin.viewByAllRoute();
		for(RouteBean i:al)
		{
			System.out.println(i.getRouteID()+" "+i.getSource()+" "+i.getDestination()+" "+i.getTravelDuration()+" "+i.getFare());
		}
		return true;
	}
	
	public boolean allSchedule() throws SQLException
	{
		loggr.info("View all schedules");
		ArrayList<ScheduleBean> al=admin.viewByAllSchedule();
		for(ScheduleBean i:al)
		{
			System.out.println(i.getScheduleID()+" "+i.getShipID()+" "+i.getRouteID()+" "+i.getStartDate());
		}
		return true;
	}
	
	public String reserveTicket() throws SQLException 
	{
		loggr.info("Reserving Tickets");
		Shiping spp=new Shiping();
		ArrayList<ScheduleBean> alsche=new ArrayList();
		int flag=0;
		int count=0;
		System.out.println("enter source");
		String src=sc.nextLine();
		
		System.out.println("enter destination");
		String dest=sc.nextLine();
		
		boolean toCheck=true;
		String dates=null;
		while(toCheck) {
		System.out.println("Enter date (dd/mm/yyyy)");
		 dates=sc.nextLine();
	 if(Pattern.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((20|21)\\d\\d)",dates))
			 {
				 toCheck=false;
			 }
		}
		String sdates[]=dates.split("/");
		LocalDate startDate=LocalDate.of(Integer.parseInt(sdates[2]),Integer.parseInt(sdates[1]), Integer.parseInt(sdates[0]));
	
		
		
//		System.out.println("enter date");
//		String date=sc.nextLine();
//		String sdate[]=date.split("/");
//		LocalDate startDate=LocalDate.of(Integer.parseInt(sdate[2]),Integer.parseInt(sdate[1]),Integer.parseInt(sdate[0]));
//		
		alsche=cust.viewScheduleByRoute(src, dest, startDate);
		if(alsche==null)
		{
			System.out.println("So Ships available for such requirements!");
			return null;
		}
		for(ScheduleBean sb:alsche)
		{
			System.out.println(sb.getScheduleID()+" route: "+sb.getRouteID()+" ship:"+sb.getShipID());
		}
		
		System.out.println("enter the ship Id for details: ");
		String sid=sc.nextLine();
		
		for(ScheduleBean sb:alsche)
		{
			if(sb.getShipID().equals(sid) )
			{
				flag=1;
				ShipBean shipb =new ShipBean();
				shipb=admin.viewByShipId(sid);
				if( shipb!=null)
				{
					System.out.println("Name:"+shipb.getShipName()+" seats:"+shipb.getSeatingCapacity()+" reservationCapacity:"+shipb.getReservationCapacity());
					break;
				}
				else{
					System.out.println("no such ship exits");	
				}
			}
			
		}
		if(flag==0)
		{
		
				System.out.println("This ship is not for desired route");
			
		}
		
		
		System.out.println("enter the route Id for details: ");
		String rid=sc.nextLine();
		
		for(ScheduleBean sb:alsche)
		{
			if(sb.getRouteID().equals(rid) )
			{
				count=1;
				RouteBean routeb =new RouteBean();
				routeb=admin.viewByRouteId(rid);
				if( routeb!=null)
				{
					System.out.println("Source:"+routeb.getSource()+" Destination:"+routeb.getDestination()+" travelDuration(in hrs):"+routeb.getTravelDuration()+" Fare(in INR):"+routeb.getFare());
					break;
				}
				else{
					System.out.println("no such route exits");
				}
				
			}
			
		}
		if(count==0)
		{
		
				System.out.println("This route is not for desired route");
			
		}
		
		ReservationBean reservebn=new ReservationBean();
		ArrayList<PassengerBean> ap=new ArrayList<PassengerBean>();
		
		
		System.out.println(" enter ScheduleId to reserve");
		String rsId=sc.nextLine();

		ScheduleBean schbean=new ScheduleBean();
		schbean=admin.viewByScheduleId(rsId);
		RouteBean rtbn=new RouteBean();
		rtbn=admin.viewByRouteId(schbean.getRouteID());
		if(schbean!=null)
		{
			
		System.out.println("no Of seats");
		int seats=sc.nextInt();
	
		Random rand = new Random();
		String reserid=rtbn.getSource().substring(0, 2)+rtbn.getDestination().substring(0, 2)+String.format("%04d", rand.nextInt(10000));
		System.out.println("please NOTE UR UNIQUE ID: "+reserid);
			
		if(seats>0) {
			
			
			
			 tfare=seats*rtbn.getFare();
			 reservebn=new ReservationBean(reserid,rsId,credit.getUserID(),LocalDate.now(),schbean.getStartDate(),seats,tfare,"pending");
			 
			 
//			 
//	Shiping us=new Shiping();
//	ap=us.passengerInput(rsId,seats);
//			 
//	
//			 if(spp.paymentCredit())
//			 {
//				 System.out.println("payment success!");
//				 cust.changeBookingStatus(reservebn);
//			 }
//			
			System.out.println("Please do the payments!");
		}
		else {
			System.out.println("No seats are reserved!");
		}
		
		}
		else {
			System.out.println("no such id exists");
		}
		
		cust.reserveTicket(reservebn, ap);
		return reservebn.getReservationID();
		
	}
	
	public boolean paymentCredit() throws SQLException
	{
		loggr.info("Payment Details");
		System.out.println("seats for the passenger will only be confired after the full payment!");
		System.out.println("wanna pay?");
		String payes=sc.nextLine();
		if(payes.equalsIgnoreCase("yes"))
		{
			System.out.println("enter card");
			String card=sc.nextLine();
			
			if(pay.findByCardNumber(credit.getUserID(),card)) {
			
				System.out.println("thankyou for payment");
			}
			else {
				System.out.println("you need to enter details");
				
				
				System.out.println("card valid from");
				String validate=sc.nextLine();
				
				System.out.println("card valid to");
				String todate=sc.nextLine();
				
				System.out.println("balance");
				int bal=sc.nextInt();
				
				if(bal<tfare) {
					System.out.println("Balance is not enough! payment still pending.");
					return false;
				}
				
				System.out.println("Confirm Payment?(yes/no)");
				sc.nextLine();
				String confirmationPayment=sc.nextLine();
				
				if(confirmationPayment.equalsIgnoreCase("yes")) {
				
				PaymentBean paymentb=new PaymentBean(card,validate,todate,bal,credit.getUserID());
				pay.process(paymentb);
				}
				else {
					System.out.println("your confirmation is still pending!");
					return false;
				}
				
			}
			return true;
			
		}
		else {
			System.out.println("your confirmation is still pending!");
			return false;
		}
	}

	public ArrayList<PassengerBean> passengerInput(String rsId,int seats)
	{
		loggr.info("Passenger Details");
		ArrayList<PassengerBean> ap=new ArrayList<PassengerBean>();
		 for(int j=0;j<seats;j++)
			{
				System.out.println("enter details for passenger "+(j+1));
				
				System.out.println("enter name");
				sc.nextLine();
				String pname=sc.nextLine();
				
				System.out.println("enter age"); 
				int page=sc.nextInt();
				
				System.out.println("enter gender");
				sc.nextLine();
				String pgender=sc.nextLine();
				
				PassengerBean pb=new PassengerBean(rsId,pname,page,pgender);
				ap.add(pb);
				//System.out.println(rsId);
			}	
		// System.out.println(ap.size());
		 return ap;
		 
	}
	
}



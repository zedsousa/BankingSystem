package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Bank.CurrentAccount;

class CurrentAccountTest {

	@Test
	public void test() {
		assertEquals(10,10,0);
	}
	
	
	
	@Test
	public void less5000Balance () {
		
		assertThrows(Exception.class, ()-> new CurrentAccount("test",4999,"adwad"));
	};
	@Test
	public void Balance5000 () throws Exception {
		
		CurrentAccount c = new CurrentAccount("test",5000,"adwad");
		assertEquals(5000, c.getbalance() );
	};
	
	@Test
	public void BalanceBigger () throws Exception {
		
		CurrentAccount c = new CurrentAccount("test",5001,"adwad");
		
		assertNotEquals(5000, c.getbalance() );

	}
}

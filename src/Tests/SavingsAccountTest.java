package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Bank.SavingsAccount;
import Exceptions.MaxBalance;
import Exceptions.MaxWithdraw;

class SavingsAccountTest {
    private SavingsAccount savingsAccount;

	@BeforeEach
    public void setUp() {
		savingsAccount = new SavingsAccount("John Doe", 10000, 500);
    }
	@Test
	public void testWithdrawFailed() throws MaxWithdraw, MaxBalance {
		
		
		assertThrows(Exception.class, () -> savingsAccount.withdraw(600));
	}
	
	@Test
	public void testWithdrawSucessfull() throws MaxWithdraw, MaxBalance {
		
		savingsAccount.withdraw(300);
		
		
	}
	
	@Test
    public void testWithdrawInsufficientBalance() {
        assertThrows(MaxWithdraw.class, () -> savingsAccount.withdraw(2000));
    }

    @Test
    public void testWithdrawMaxWithdrawLimit() {
        assertThrows(MaxWithdraw.class, () -> savingsAccount.withdraw(1500));
    }
    
    @Test
    public void testSeeNetBalance() {
        assertNotEquals(10500,savingsAccount.getNetBalance());
    }

}

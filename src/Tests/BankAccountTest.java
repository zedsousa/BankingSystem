package Tests;

import Bank.BankAccount;
import Exceptions.MaxBalance;
import Exceptions.MaxWithdraw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    private BankAccount bankAccount;

    @BeforeEach
    public void setUp() {
        bankAccount = new BankAccount("John Doe", 1000, 500);
    }

    @Test
    public void testDeposit() {
        bankAccount.deposit(500);
        assertEquals(1500, bankAccount.getbalance());
    }

    @Test
    public void testWithdraw() {
        try {
            bankAccount.withdraw(500);
            assertEquals(500, bankAccount.getbalance());
        } catch (MaxWithdraw | MaxBalance e) {
            fail("Exception thrown while withdrawing: " + e.getMessage());
        }
    }

    @Test
    public void testWithdrawInsufficientBalance() {
        assertThrows(MaxWithdraw.class, () -> bankAccount.withdraw(2000));
    }

    @Test
    public void testWithdrawMaxWithdrawLimit() {
        assertThrows(MaxWithdraw.class, () -> bankAccount.withdraw(1500));
    }

    @Test
    public void testToString() {
        String expected = "Name: John Doe, Id: " + bankAccount.getAccNum() + ", Balance: 1000.0Type:class Bank.BankAccount";
        assertEquals(expected, bankAccount.toString());
    }
}

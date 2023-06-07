package Tests;

import Bank.Bank;
import Bank.BankAccount;
import Exceptions.InvalidOperation;
import Exceptions.InvalidAmount;
import Exceptions.AccNotFound;
import Exceptions.MaxBalance;
import Exceptions.MaxWithdraw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
    private Bank bank;
    
    @BeforeEach
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void addSavingsAccountTest() throws Exception {
        String expected = bank.addAccount("Bernardo Gomes", 1000, 300);
        assertEquals(expected, "Conta adicionada");
    }

    @Test
    public void addCurrentAccountTest() throws Exception {
        String expected = bank.addAccount("Luana Alves", 7000, "TL-2023-001");
        assertEquals(expected, "Conta adicionada");
    }

    @Test
    public void addStudentAccountTest() throws Exception {
        String expected = bank.addAccount("Tiago Dantas", "UFRN", 5000, 1000);
        assertEquals(expected, "Conta adicionada");
    }

    @Test
    public void addAccountTest() throws Exception {
        BankAccount bankAccount = new BankAccount("Ricardo Enrico", 1000, 500);
        String expected = bank.addAccount(bankAccount);
        assertEquals(expected, "Conta adicionada");
    }

    @Test
    public void addAccountLimitTest() throws Exception {
    	for (int i = 0; i < 100; i++) {
            BankAccount account = new BankAccount("Account " + (i + 1), 1000, 500);
            bank.addAccount(account);
        }

    	BankAccount account = new BankAccount("Account 101", 1000, 500);
    	assertThrows(InvalidOperation.class, () -> bank.addAccount(account));
    }       
    
    @Test
    public void findAccountTest() throws Exception {
        BankAccount bankAccount = new BankAccount("Amalia Fernandes", 1000, 500);
		bank.addAccount(bankAccount);
        String id = bankAccount.getAccNum();
        BankAccount findAccount = bank.findAccount(id);
        assertEquals(bankAccount, findAccount);
    }       
    
    @Test
    public void findAccountFailTest() {
        BankAccount bankAccount = new BankAccount("Amalia Fernandes", 1000, 500);
        String id = bankAccount.getAccNum();
        BankAccount findAccount = bank.findAccount(id);
        assertEquals(null, findAccount);
    }
    
    @Test
    public void depositValidAmountTest() throws Exception {
    	BankAccount bankAccount = new BankAccount("Tomas Neves", 1500, 500);
    	bank.addAccount(bankAccount);
    	bank.deposit(bankAccount.getAccNum(), 20);
        assertEquals(1520, bankAccount.getbalance());
    }

    @Test
    public void depositNegativeAmountTest() throws Exception {
    	BankAccount bankAccount = new BankAccount("Holanda Fernandes", 1000, 500);
    	bank.addAccount(bankAccount);
        assertThrows(InvalidAmount.class, () -> bank.deposit(bankAccount.getAccNum(), -500));
    }

    @Test
    public void depositNonExistingAccountTest() throws Exception {
        assertThrows(AccNotFound.class, () -> bank.deposit("12345", 500));
    }

    @Test
    public void withdrawValidAmountTest() throws Exception {
    	BankAccount bankAccount = new BankAccount("Holanda Oliveira", 1000, 500);
    	bank.addAccount(bankAccount);
        assertDoesNotThrow(() -> bank.withdraw(bankAccount.getAccNum(), 500));
        assertEquals(500, bankAccount.getbalance());
    }

    @Test
    public void withdrawAmountGreaterThanBalanceTest() throws Exception {
    	BankAccount bankAccount = new BankAccount("Holanda Guimaraes", 1000, 500);
    	bank.addAccount(bankAccount);
        assertThrows(MaxBalance.class, () -> bank.withdraw(bankAccount.getAccNum(), 1500));
    }

    @Test
    public void withdrawNegativeAmountTest() throws Exception {
    	BankAccount bankAccount = new BankAccount("Heloisa Guimaraes", 1000, 500);
    	bank.addAccount(bankAccount);
        assertThrows(InvalidAmount.class, () -> bank.withdraw(bankAccount.getAccNum(), -500));
    }

    @Test
    public void withdrawNonExistingAccountTest() {
        assertThrows(AccNotFound.class, () -> bank.withdraw("12345", 500));
    }

    @Test
    public void displayExistingAccountsTest() throws Exception {
    	BankAccount bankAccount = new BankAccount("Heloisa Guimaraes", 1000, 500);
    	bank.addAccount(bankAccount);
        String expected = "Name: Heloisa Guimaraes, Id: " + bankAccount.getAccNum() + ", Balance: 1000.0Type:class Bank.BankAccount";
        assertEquals(expected, bank.display().getElementAt(0));
    }

    @Test
    public void displayAccountsWithNullEntriesTest() throws Exception {
    	BankAccount bankAccount = new BankAccount("Nando Goes", 1000, 500);
    	bank.addAccount(bankAccount);
        bank.getAccounts()[1] = null;
        bank.getAccounts()[2] = null;
        String expected = "Name: Nando Goes, Id: " + bankAccount.getAccNum() + ", Balance: 1000.0Type:class Bank.BankAccount";
        assertEquals(expected, bank.display().getElementAt(0));
    }

    @Test
    public void displayNoAccountsTest() {
        Bank emptyBank = new Bank();
        assertTrue(emptyBank.display().isEmpty());
    }
    
    @Test
    public void setAccountsTest() {
    	BankAccount[] accounts= new BankAccount[100];
    	bank.setAccounts(accounts);
    }
}
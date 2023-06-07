package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Bank.StudentAccount;

public class StudentAccountTest {

	@Test
	public void test() {
		StudentAccount sa = new StudentAccount("ricardo", 10000, "ufpb");
	}

}

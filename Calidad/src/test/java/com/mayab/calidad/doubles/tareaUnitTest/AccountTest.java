package com.mayab.calidad.doubles.tareaUnitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.invocation.InvocationOnMock;
import org.junit.runner.RunWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class AccountTest {
	private int mockBalance;
	private Double mockBalance2;
	private Double mockComission;
	private String mockName;
	
	public AccountTest(String mockName, int mockBalance, Double mockBalance2,Double mockComission) {
		this.mockName=mockName;
		this.mockBalance=mockBalance;
		this.mockBalance2=mockBalance2;
		this.mockComission=mockComission;
	}
	
	private AlertListener alert;
	private Account accTest;
	private Transaction t;
	private ArrayList<Account>fakeDb=new ArrayList<Account>();
	
	public Double withdraw(int money, int balance,Double comission) {
		return balance-(money+balance*comission);
	}
	@Parameters
	public static Collection<Object[]>data(){
		return Arrays.asList(new Object[][]{
			{"Ivanna",1000,890.0,1/100.0},
			{"Melissa",1500, 1370.0, 2/100.0},
			{"Veronica",2000,1840.0,3/100.0}
		});
	}
	
	@Before
	public void setUp()throws Exception {
		alert=mock(AlertListener.class);
		accTest=mock(Account.class);
		t=mock(Transaction.class);
	}
	@After
	public void tearDown()throws Exception {
		
	}
	@Test
	public void test3Accounts() {
		assertThat(this.mockBalance2,is(equalTo(withdraw(100,this.mockBalance,this.mockComission))));
	}
	
	@Test
	public void testAlert() {
		accTest= new Account("Fredy",80,1,alert);
		accTest.debit(50);
		verify(alert).sendAlert("Fredy, your account balance is below 100");
	}
	
		
	
	
}

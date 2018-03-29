package scratch;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.*;

public class AssertTest {
    private Account account;

    @Before
    public void create(){
        account = new Account("Savings");

    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void exceptionRule (){
        thrown.expect(InsufficientFundsException.class);
        thrown.expectMessage("balance only 0");
        account.withdraw(100);
    }
    @Test
    public void assertConstructor(){
        assertThat(account.getName(),startsWith("Savi"));
    }

    @Test
    public void hasPositiveBalance(){
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }
    @Test
    public void assertDepositAndGetBalance(){
        account.deposit(50);
        assertThat(account.getBalance(),equalTo(50));

    }

    @Test
    public void depositIncreaseBalance(){
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() == (initialBalance  + 100));
    }
    @Test(expected = InsufficientFundsException.class)
    public void throwsWhenWithdrawingTooMuchSimpleSchool(){
        account.withdraw(100);

    }
    @Test
    public void throwsWhenWithdrawingTooMuchOldSchool (){
        try{
            account.withdraw(100);
            fail();
        }
        catch (InsufficientFundsException exceptionExpected){
                assertThat(exceptionExpected.getMessage(),is("balance only 0"));
        }
    }

}
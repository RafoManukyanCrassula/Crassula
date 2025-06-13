package suites;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import tests.*;

@Suite
@SelectClasses({
        LoginTests.class,
        CurrencyExchangeTest.class
})
public class TestSuite {
}
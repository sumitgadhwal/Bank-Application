package com.learning.sumit.bankApplication;

import com.learning.sumit.bankApplication.controller.BankRestControllerMockTest;
import com.learning.sumit.bankApplication.controller.BankRestControllerTest;
import com.learning.sumit.bankApplication.service.UserServiceMockTest;
import com.learning.sumit.bankApplication.service.UserServiceTest;
import com.learning.sumit.bankApplication.service.UserServiceTestNgTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserServiceMockTest.class,
//        UserServiceTest.class,
//        BankRestControllerTest.class,
        BankRestControllerMockTest.class
})
public class BankApplicationTestSuite {
}

package comp3350.group6.promise.tests.business;

import org.junit.Before;
import org.junit.Test;

import comp3350.group6.promise.business.NotifService;
import comp3350.group6.promise.objects.FakeDB;

public class NotifServiceTest {
    private NotifService notifService;
    /* Each time the fake DB get reinitialized */
    @Before
    public void setup() throws Exception {
        System.out.println("Starting test for NotifServiceTest");
        FakeDB.initialize();
        notifService = NotifService.getInstance(); // false init a fake DB
    }

    @Test
    public void test(){

    }
}

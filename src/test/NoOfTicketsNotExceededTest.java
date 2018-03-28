package test;

import business.service.IShowService;
import business.service.ShowService;
import org.junit.BeforeClass;

public class NoOfTicketsNotExceededTest {
    private static IShowService showService;

    @BeforeClass
    public static void setUp(){
        IShowService showService = new ShowService();
    }

}

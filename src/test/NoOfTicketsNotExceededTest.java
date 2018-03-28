package test;

import business.service.IShowService;
import business.service.ShowService;
import dataAccess.dbmodel.TicketDTO;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

public class NoOfTicketsNotExceededTest {
    private static IShowService showService = new ShowService();;
    private static ArrayList<TicketDTO>  mockDatabase = new ArrayList<>();
    private static int lastId = 0;

    @BeforeClass
    public static void setUp(){
        //booked
        mockInsert(new TicketDTO(-1, 2, 15, true));
        //not booked
        mockInsert(new TicketDTO(-1, 2, 16, false));
        mockInsert(new TicketDTO(-1, 4, 20, true));
    }

    @Test
    public void testNotExceededNotFoundReturnsTrue(){
        try{
            Assert.assertEquals(true, mockSellTicket(7, 12, 5));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void tesNotExceededBookedReturnsFalse(){
        try{
            Assert.assertEquals(false, mockSellTicket(15, 2, 4));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testNotExceededNotBookedReturnsTrue(){
        try{
            Assert.assertEquals(true, mockSellTicket(16, 2, 2));
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(expected = Exception.class)
    public void testSoldOutThrowsException() throws Exception{
        Assert.assertEquals(true, mockSellTicket(16, 2, 0));
    }


   public static boolean mockSellTicket(int seat_id, int show_id, int remaining) throws Exception {
        if (remaining != 0){
            TicketDTO t = mockFindBySeat(seat_id, show_id);
            //if no ticket for this seat yet
            if(t == null) {
                TicketDTO newT = new TicketDTO(-1, show_id, seat_id, true);
                int insertedId = mockInsert(newT);
                return (insertedId != -1);
            } else if(t.isBooked())
                return false;
            else {
                TicketDTO soldT = t;
                soldT.setBooked(true);
                int updatedRows = mockUpdate(t);
                return (updatedRows != 0);
            }
        } else{
            throw new Exception("Number of tickets exceeded! Show is sold out!");
        }
    }

    private static TicketDTO mockFindBySeat(int seatid, int showid){
        for(TicketDTO t : mockDatabase){
            if(t.getSeatId() == seatid && t.getShowId() == showid)
                return t;
        }
        return null;
    }

    private static int mockInsert(TicketDTO t){
        TicketDTO ticket = new TicketDTO(lastId, t.getShowId(), t.getSeatId(), t.isBooked());
        mockDatabase.add(ticket);
        lastId ++;
        return ticket.getTicketId();
    }

    private static int mockUpdate(TicketDTO t){
        int updated = 0;
        for(int i = 0; i< mockDatabase.size(); i++){
            TicketDTO ticket = mockDatabase.get(i);
            if(ticket.getTicketId() == t.getTicketId()){
                mockDatabase.set(i, t);
                updated++;
            }
        }
        return updated;
    }

}

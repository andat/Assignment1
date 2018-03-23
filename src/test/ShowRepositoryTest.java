package test;

import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.ShowDTO;
import dataAccess.repository.ShowRepository;

import java.sql.Date;

import static org.junit.Assert.assertNotEquals;

public class ShowRepositoryTest {
    ShowRepository showRepo;

    @org.junit.Before
    public void setUp() throws Exception {
        ConnectionFactory connectionFactory = null;
        showRepo = new ShowRepository(connectionFactory);
    }

    @org.junit.Test
    public void findAll() {
        assertNotEquals(showRepo.findAll(), null);
    }

    @org.junit.Test
    public void findById() {
    }

    @org.junit.Test
    public void insertShowTitledKean() {
        String dateString = String.format("%d-%02d-%02d", 2018, 4, 6);
        ShowDTO show = new ShowDTO();
        show.setTitle("Kean");
        show.setGenre("Drama");
        show.setDistribution("Kean: Mihai-Florian Nițu");
        show.setDate(Date.valueOf(dateString));
        show.setNoOfTickets(928);

        assertNotEquals(showRepo.insert(show), -1);
    }

    @org.junit.Test
    public void updateGenreOfShowWithId1() {
    }

    @org.junit.Test
    public void deleteShowWithId2() {
        assertNotEquals(showRepo.delete(2), 0);
    }
}
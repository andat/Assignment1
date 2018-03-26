package business.service;

import dataAccess.ConnectionFactory;
import dataAccess.repository.ITicketRepository;
import dataAccess.repository.TicketRepository;
import dataAccess.repository.TicketRepositoryCache;

public class TicketService {
    private final ITicketRepository repository;

    public TicketService() {
        this.repository = new TicketRepositoryCache(new TicketRepository(ConnectionFactory.getSingleInstance()));
    }
}

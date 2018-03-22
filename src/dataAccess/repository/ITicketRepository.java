package dataAccess.repository;

import dataAccess.dbmodel.TicketDTO;

import java.util.List;

public interface ITicketRepository {
        public List<TicketDTO> findAll();
        public TicketDTO findById(int id);
        public int insert(TicketDTO ticket);
        public int update(TicketDTO ticket);
        public int delete(int id);
}

package business.service;

import business.model.SeatModel;
import dataAccess.ConnectionFactory;
import dataAccess.dbmodel.SeatDTO;
import dataAccess.repository.ISeatRepository;
import dataAccess.repository.SeatRepository;

import java.util.ArrayList;
import java.util.List;

public class SeatService implements ISeatService {
    private final ISeatRepository repository;

    public SeatService(){
        this.repository = new SeatRepository(ConnectionFactory.getSingleInstance());
    }

    @Override
    public List<SeatModel> findAll(){
        List<SeatDTO> list = repository.findAll();
        List<SeatModel> result = new ArrayList<>();
        for(SeatDTO s : list){
            result.add(new SeatModel(s.getSeatId(), s.getRow(), s.getNumber()));
        }
        return result;
    }

    @Override
    public SeatModel findById(int id) {
        SeatDTO seat = repository.findById(id);
        SeatModel result = new SeatModel(seat.getSeatId(), seat.getRow(), seat.getNumber()) ;
        return result;
    }

    @Override
    public boolean edit(int id, String row, int number) {
        int updatedRows = repository.update(new SeatDTO(id, row, number));
        return (updatedRows != 0);
    }
}

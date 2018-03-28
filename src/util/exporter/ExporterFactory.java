package util.exporter;

import business.model.TicketModel;

import java.util.List;

public abstract class ExporterFactory {

    public abstract void export(List<TicketModel> entries, String filename);
}

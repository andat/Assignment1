package util.exporter;

import business.service.FormatType;

public class ExporterFactory {

    public TicketExporter createExporter(FormatType format){
        if(format.equals(FormatType.CSV))
            return new CSVExporter();
        else if(format.equals(FormatType.XML))
            return new XMLExporter();
        else
            return null;
    }
}

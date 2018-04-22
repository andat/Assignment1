//package com.example.Assignment2_LabApp.apimodel;
//
//import com.example.Assignment2_LabApp.model.Assignment;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import javax.validation.constraints.NotNull;
//import java.sql.Date;
//
//public class AssignmentDTO{
//
//    @NotNull
//    private String name;
//
//    @JsonFormat(pattern = "YYYY-MM-DD")
//    @DateTimeFormat(pattern = "YYYY-MM-DD")
//    private Date deadline;
//
//    private String description;
//
//    @NotNull
//    private LabDTO labDTO;
//
//    public String getName() {
//        return name;
//    }
//
//    public Date getDeadline() {
//        return deadline;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public Assignment toAssignment(){
//        Assignment a = new Assignment();
//        a.setName(this.name);
//        a.setDeadline(this.deadline);
//        a.setDescription(this.description);
//        return a;
//    }
//
//}

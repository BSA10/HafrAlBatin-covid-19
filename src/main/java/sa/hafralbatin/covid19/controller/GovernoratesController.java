package sa.hafralbatin.covid19.controller;

import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sa.hafralbatin.covid19.model.Governorates;
import sa.hafralbatin.covid19.model.dailyCases;
import sa.hafralbatin.covid19.service.Impl.GovernoratesServiceImpl;
import sa.hafralbatin.covid19.service.dailyCasesService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GovernoratesController {

    private static final Logger log = LoggerFactory.getLogger(GovernoratesController.class);

    @Autowired
    private GovernoratesServiceImpl governoratesService;
    @Autowired
    private dailyCasesService dailyCasesService;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @GetMapping("/")
    public String index(Model model){
        
        List<Governorates> governoratesList = governoratesService.getAll();
        dailyCases lastCase = dailyCasesService.getLastOne("Hafar al Batin");

        List<dailyCases> lastCasesByDates = dailyCasesService.getLastOneByDate();



        Governorates governorates = governoratesList.get(governoratesList.size()-1);

        SimpleDateFormat format = new SimpleDateFormat("HH:mm a");
        Chronology iso = ISOChronology.getInstanceUTC();
        Chronology hijri = IslamicChronology.getInstanceUTC();

        LocalDate todayIso ;
        LocalDate todayHijri ;
        long timeLong = governorates.getReported_at();
        todayIso = new LocalDate(new Date(timeLong), iso);
        todayHijri = new LocalDate(todayIso.toDateTimeAtStartOfDay() , hijri);
        String x = format.format(new Date(timeLong)) +" - " +todayHijri;

        model.addAttribute("Governorates" , governorates);
        model.addAttribute("lastCase" , lastCase.getConfirmed());
        model.addAttribute("lastUpdate",x);
        return "index";
    }


}

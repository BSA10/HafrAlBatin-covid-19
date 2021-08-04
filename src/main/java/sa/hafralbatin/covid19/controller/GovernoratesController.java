package sa.hafralbatin.covid19.controller;

import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sa.hafralbatin.covid19.model.Governorates;
import sa.hafralbatin.covid19.service.Impl.GovernoratesServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class GovernoratesController {

    @Autowired
    private GovernoratesServiceImpl governoratesService;

    @GetMapping("/")
    public String index(Model model){
        List<Governorates> governoratesList = governoratesService.getAll();
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
        model.addAttribute("lastUpdate",x);
        return "index";
    }


}

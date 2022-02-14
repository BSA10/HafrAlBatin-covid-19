package sa.hafralbatin.covid19.controller;

import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.chrono.IslamicChronology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sa.hafralbatin.covid19.model.dailyCases;
import sa.hafralbatin.covid19.service.Impl.dailyCasesServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class dailyCasesController {

    @Autowired
    private dailyCasesServiceImpl casesService;

    @GetMapping("/daily-cases")
    public String dailyCasesIndex(Model model){
        List<dailyCases> dailyCases = casesService.listAll();


        Integer hafarAlbatinCases = dailyCases.stream().filter(hafar -> hafar.getName_en().equals("Hafar Al Batin")).mapToInt(x-> x.getConfirmed()).sum();
        Integer Adh_DhibiyyahCases = dailyCases.stream().filter(hafar -> hafar.getName_en().equals("Adh Dhibiyyah")).mapToInt(x-> x.getConfirmed()).sum();
        Integer Ar_RuqaiCases = dailyCases.stream().filter(hafar -> hafar.getName_en().equals("Ar Ruqʻi")).mapToInt(x-> x.getConfirmed()).sum();
        Integer As_SuaayyirahCases = dailyCases.stream().filter(hafar -> hafar.getName_en().equals("As Suʻayyirah")).mapToInt(x-> x.getConfirmed()).sum();
        Integer SamudahCases = dailyCases.stream().filter(hafar -> hafar.getName_en().equals("Samudah")).mapToInt(x-> x.getConfirmed()).sum();
        Integer Al_QaysumahCases = dailyCases.stream().filter(hafar -> hafar.getName_en().equals("Al Qaysumah")).mapToInt(x-> x.getConfirmed()).sum();
        Integer As_SufairyCases = dailyCases.stream().filter(hafar -> hafar.getName_en().equals("As Sufairy")).mapToInt(x-> x.getConfirmed()).sum();


        model.addAttribute("hafarAlbatin",hafarAlbatinCases);
        model.addAttribute("Dhibiyyah",Adh_DhibiyyahCases);
        model.addAttribute("Rugai",Ar_RuqaiCases);
        model.addAttribute("Suayyirah",As_SuaayyirahCases);
        model.addAttribute("Samudah",SamudahCases);
        model.addAttribute("Qaysumah",Al_QaysumahCases);
        model.addAttribute("Sufairy",As_SufairyCases);


        return "daily-cases";
    }

    @GetMapping("/CasesByCity")
    public String getCasesOfCity(@RequestParam("city") String city , Model model ){
        List<dailyCases> dailyCases = casesService.listAll();
        List<dailyCases> cityCases = dailyCases.stream().filter(hafar -> hafar.getName_en().equals(city)).collect(Collectors.toList());

        model.addAttribute("cases",cityCases);

        return "CasesByCity";
    }

}

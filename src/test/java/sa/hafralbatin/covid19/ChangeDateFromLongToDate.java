package sa.hafralbatin.covid19;


import ch.qos.logback.classic.pattern.DateConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.Chronology;
import org.joda.time.LocalDate;
import org.joda.time.chrono.IslamicChronology;
import org.joda.time.chrono.ISOChronology;

public class ChangeDateFromLongToDate {

    public static void main(String[] args) {


        SimpleDateFormat format = new SimpleDateFormat("EEE, dd-MM-yyyy HH:mm:ss");

        System.out.println(format.format(new Date(1625758573614L)));
        // Fri, 20-03-2020 03:00:00 -  ٢٥ رجب ١٤٤١ هـ - recovered : Mon, 13-04-2020 13:46:00 - ٢٠ شعبان ١٤٤١ هـ
        // Wed, 22-04-2020 03:00:00 - ٢٩ شعبان ١٤٤١ هـ
        // 13 person , Thu, 23-04-2020 03:00:00 - ٣٠ شعبان ١٤٤١ هـ


//        SimpleDateFormat format = new SimpleDateFormat("EEE, dd-MM-yyyy HH:mm:ss");
//        Date date = new Date(System.currentTimeMillis());
//        System.out.println(date);

        Chronology iso = ISOChronology.getInstanceUTC();
        Chronology hijri = IslamicChronology.getInstanceUTC();

        LocalDate todayIso = new LocalDate(new Date(1625758573614L), iso);
        LocalDate todayHijri = new LocalDate(todayIso.toDateTimeAtStartOfDay(),
                hijri);
        System.out.println(todayHijri);

    }

}

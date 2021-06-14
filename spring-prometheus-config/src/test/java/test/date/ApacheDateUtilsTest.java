package test.date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

public class ApacheDateUtilsTest {

    @Test
    public void DateUtilsTest() throws ParseException {
        Date date = new Date();
        Date addDays = DateUtils.addDays(date, 2);
        Date addMinutes = DateUtils.addMinutes(date, 60);
        System.out.println(date);
        System.out.println(addDays);
        System.out.println(addMinutes);

        Date parseDate = DateUtils.parseDate("2020-10-15 22:00:00","yyyy-MM-dd HH:mm:ss");
        System.out.println(parseDate);
    }

    @Test
    public void DateFormatUtilsTest() {
        String dateString = DateFormatUtils.format(new Date(),"yyyyMMddHHmmss");
        System.out.println(dateString);
    }
}

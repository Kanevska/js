package util;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import static metadata.LoggerConst.INVALID_DATE_ENTER;

public class DateFormater {

    private static final Logger LOGGER = Logger.getLogger(DateFormater.class);

    public static java.util.Date dateFormater(String birthday) {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        java.util.Date date = new java.util.Date();
        try

        {
            date = format.parse(birthday);

        } catch (ParseException e)

        {
            try {
                date = format.parse("1800-01-01");
            } catch (ParseException e1) {
                LOGGER.error(INVALID_DATE_ENTER);
            }
        }
        return date;
    }
}


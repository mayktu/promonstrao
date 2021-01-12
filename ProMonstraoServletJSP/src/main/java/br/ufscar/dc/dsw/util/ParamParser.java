package br.ufscar.dc.dsw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class ParamParser {
    public static Long parseLong(String param) {
        if (param == null || param.isEmpty()) {
            return null;
        }
        try {
            return Long.parseLong(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Float parseFloat(String param) {
        if (param == null || param.isEmpty()) {
            return null;
        }
        try {
            return Float.parseFloat(param);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Date parseDate(String param) {
        if (param == null || param.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - hh:mm");
            return formatter.parse(param);
        } catch (ParseException e) {
            return null;
        }
    }
}

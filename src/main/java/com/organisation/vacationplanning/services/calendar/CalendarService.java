package com.organisation.vacationplanning.services.calendar;

import com.organisation.vacationplanning.database.HibernateUtil;
import com.organisation.vacationplanning.database.entities.Employee;
import com.organisation.vacationplanning.services.IVacationController;
import com.organisation.vacationplanning.utility.NotifTypes;
import com.organisation.vacationplanning.utility.Notifier;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class CalendarService implements IVacationController {

    //Управление настройками департамента/организации/подразделения

    private static final String ALL_EMPLOYEES = "from Employee";
    private HolidayDataFetcher holidayDataFetcher;
    private WebContext ctx;

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) throws Exception {
        ctx = new WebContext(webExchange, webExchange.getLocale());
        if (webExchange.getRequest().getMethod().equals("POST")) {

            handlePost(webExchange);
            handleGet(webExchange, templateEngine, writer, response);

        }
        if (webExchange.getRequest().getMethod().equals("GET")) {
            handleGet(webExchange, templateEngine, writer, response);
        }
    }

    private void handlePost(IWebExchange webExchange) {

    }

    private void handleGet(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer, HttpServletResponse response) {
        // Создание двумерного списка для представления календаря
        List<List<Day>> weeks = new ArrayList<>();
        String msg = "no data";
        // Получение текущей даты
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonth().getValue();
        if (webExchange.getRequest().containsParameter("year") &&
                webExchange.getRequest().containsParameter("month")) {
            msg = "found ";
            int receivedYear = Integer.parseInt(webExchange.getRequest().getParameterValue("year"));
            int receivedMonth = Integer.parseInt(webExchange.getRequest().getParameterValue("month"));
            if (receivedYear <= year) {
                year = receivedYear;
                month = receivedMonth;
                currentDate = LocalDate.of(year, month, 1);
                //ToDo проверка что это валидно
            }
            msg += "year:" + year + " month:" + month;
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {


            // Получение первого дня текущего месяца
            LocalDate firstDayOfMonth = currentDate.with(TemporalAdjusters.firstDayOfMonth());

            // Заполнение списка днями месяца
            HolidayDataFetcher holidayDataFetcher = new HolidayDataFetcher();
            String[] daysHoliday = holidayDataFetcher.getHolidayData(year, month);
            int dayInMonth = 1;
            while (dayInMonth <= currentDate.lengthOfMonth()) {
                List<Day> week = new ArrayList<>();
                for (int i = 1; i < 8; i++) {
                    if ((firstDayOfMonth.getDayOfWeek().getValue() == i || dayInMonth > 1) && dayInMonth <= currentDate.lengthOfMonth()) {
                        week.add(new Day(dayInMonth, daysHoliday[dayInMonth - 1]));
                        dayInMonth++;
                    } else {
                        //week.add(null);
                        week.add(new Day(dayInMonth, "hidden"));
                    }
                }
                weeks.add(week);
            }
        }
        Notifier ntf = new Notifier(ctx, "Нет данных об отпусках... (модуль в разработке) <br> " + msg, NotifTypes.MSG);

        ctx.setVariable("weeks", weeks);
        templateEngine.process("calendar", ctx, writer);
    }
}

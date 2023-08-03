package com.organisation.vacationplanning.services.calendar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HolidayDataFetcher {

    private Map<String, String> cache = new HashMap<>();
    private String baseUrl = "https://isdayoff.ru/api/getdata";

    public String[] getHolidayData(int year, int month) {
        /*
        String cacheKey = year + "-" + month;
        String cachedData = cache.get(cacheKey);
        if (cachedData != null) {
            return cachedData.split("");
        }
        */
        String apiResponse = fetchDataFromApi(year, month);
        //cache.put(cacheKey, apiResponse);
        String [] dayTypes = apiResponse.split("");
        for (int i = 0; i < dayTypes.length; i++) {
            if(dayTypes[i].equals("0")){
                dayTypes[i] = "work";
                continue;
            }

            if(dayTypes[i].equals("1")){
                dayTypes[i] = "holiday";
                continue;
            }
        }
        return dayTypes;
    }

    private String fetchDataFromApi(int year, int month) {
        try {
            URL url = new URL(baseUrl + "?year=" + year + "&month=" + month + "&cc=ru&pre=1");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            return response.toString();
        } catch (Exception e) {
            LocalDate currentDate = LocalDate.now();
            StringBuilder response = new StringBuilder();
            for (int i = 0; i < currentDate.lengthOfMonth(); i++) {
                response.append("4");
            }
            return response.toString(); // Вернуть значение по умолчанию в случае ошибки
        }
    }

}
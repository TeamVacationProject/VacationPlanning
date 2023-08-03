package com.organisation.vacationplanning.services.calendar;

public class Day {
    String commentary;
    String type;
    int number;

    public Day(int number) {
        this.number = number;
    }
    public Day(int number, String type) {
        this.number = number;
        this.type = type;

    }
    public int getNumber(){
        return this.number ;
    }
    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.number+"";
    }

}

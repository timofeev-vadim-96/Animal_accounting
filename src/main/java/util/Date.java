package util;

public class Date {
    private int year;
    private int month;
    private int date;

    public Date(int date, int month, int year) {
        this.year = year;
        this.month = month;
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("%s.%s.%s", this.date, this.month, this.year);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        Date input_date = (Date) obj;
        return this.date == input_date.getDate() &&
                this.month == input_date.getMonth() &&
                this.year == input_date.getYear();
    }
}

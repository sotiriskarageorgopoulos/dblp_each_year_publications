package kar.sot.parser;

public class Publication {
    private int pCount = 0;
    private int year;

    public int getPCount() {
        return pCount;
    }

    public void setPCount(int pCount) {
        this.pCount = pCount;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "year=" + year +
                "\tpublications="+ pCount+
                "}";
    }
}

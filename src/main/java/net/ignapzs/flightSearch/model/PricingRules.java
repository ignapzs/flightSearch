package net.ignapzs.flightSearch.model;


public class PricingRules {

    private int from;

    private int to;

    private int percentage;

    public PricingRules(int from, int to, int percentage) {
        this.from = from;
        this.to = to;
        this.percentage = percentage;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getPercentage() {
        return percentage;
    }

}

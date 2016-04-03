package com.medschedulr.medschedulr;

public class Quantity {

    private enum Description {
        pill, drops, teaspoon, tablespoon, ml, tablet;
    }

    private Description type;
    private double quantity;

    public void MedDescriptor(Description description,int quantity) {
        this.quantity = quantity;
        this.type = description;
    }

    public void MedDescriptor(int quantity) {

        this.MedDescriptor(Description.pill, quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setType(Description type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getType() {

        boolean def = false;
        String answer;
        switch (type) {
            case pill:
                answer = "pill";
                break;
            case drops:
                answer = "drop";
                break;
            case teaspoon:
                answer = "teaspoon";
                break;
            case tablespoon:
                answer = "tablespoon";
                break;
            case ml:
                answer = "ml";
                break;
            case tablet:
                answer = "tablet";
                break;
            default:
                def = true;
                answer = "of them";
        }

        if (quantity > 1 && !def) {
            answer += "s";
        }

        return answer;
    }

    @Override
    public String toString () {
        return quantity + " " + type;
    }
}

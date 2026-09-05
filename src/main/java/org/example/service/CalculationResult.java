package org.example.service;

public class CalculationResult {

    private double translationPrice;
    private double complexitySurcharge;
    private double urgencySurcharge;
    private double translatorSignaturePrice;
    private double notaryCopyPrice;
    private double apostillePrice;
    private double legalizationPrice;
    private double totalPrice;

    public double getTranslationPrice() {
        return translationPrice;
    }

    public void setTranslationPrice(double translationPrice) {
        this.translationPrice = translationPrice;
    }

    public double getComplexitySurcharge() {
        return complexitySurcharge;
    }

    public void setComplexitySurcharge(double complexitySurcharge) {
        this.complexitySurcharge = complexitySurcharge;
    }

    public double getUrgencySurcharge() {
        return urgencySurcharge;
    }

    public void setUrgencySurcharge(double urgencySurcharge) {
        this.urgencySurcharge = urgencySurcharge;
    }

    public double getTranslatorSignaturePrice() {
        return translatorSignaturePrice;
    }

    public void setTranslatorSignaturePrice(double translatorSignaturePrice) {
        this.translatorSignaturePrice = translatorSignaturePrice;
    }

    public double getNotaryCopyPrice() {
        return notaryCopyPrice;
    }

    public void setNotaryCopyPrice(double notaryCopyPrice) {
        this.notaryCopyPrice = notaryCopyPrice;
    }

    public double getApostillePrice() {
        return apostillePrice;
    }

    public void setApostillePrice(double apostillePrice) {
        this.apostillePrice = apostillePrice;
    }

    public double getLegalizationPrice() {
        return legalizationPrice;
    }

    public void setLegalizationPrice(double legalizationPrice) {
        this.legalizationPrice = legalizationPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

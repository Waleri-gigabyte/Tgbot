package org.example.service;

import org.example.repository.PriceRepository;
import org.example.session.UserSession;

public class CalculationService {

    private final PriceRepository priceRepository;

    public CalculationService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public CalculationResult calculate(UserSession session) {

        CalculationResult result = new CalculationResult();

        double translationPrice =
                calculateTranslationPrice(session);

        double complexitySurcharge =
                calculateComplexitySurcharge(
                        session,
                        translationPrice
                );

        double urgencySurcharge =
                calculateUrgencySurcharge(
                        session,
                        translationPrice,
                        complexitySurcharge
                );

        double translatorSignaturePrice =
                calculateTranslatorSignature(session);

        double notaryCopyPrice =
                calculateNotaryCopy(session);

        double apostillePrice =
                calculateApostille(session);

        double legalizationPrice =
                calculateLegalization(session);

        double totalPrice =
                translationPrice
                        + complexitySurcharge
                        + urgencySurcharge
                        + translatorSignaturePrice
                        + notaryCopyPrice
                        + apostillePrice
                        + legalizationPrice;

        result.setTranslationPrice(translationPrice);
        result.setComplexitySurcharge(complexitySurcharge);
        result.setUrgencySurcharge(urgencySurcharge);
        result.setTranslatorSignaturePrice(translatorSignaturePrice);
        result.setNotaryCopyPrice(notaryCopyPrice);
        result.setApostillePrice(apostillePrice);
        result.setLegalizationPrice(legalizationPrice);
        result.setTotalPrice(totalPrice);

        return result;
    }

    private double calculateTranslationPrice(UserSession session) {

        Double pricePerPage = priceRepository.findPrice(
                session.getSourceLanguage(),
                session.getTargetLanguage(),
                session.getDocumentType()
        );

        if (pricePerPage == null) {
            return 0;
        }

        return pricePerPage * session.getPages();
    }


    private double calculateTranslatorSignature(UserSession session) {

        if (!session.isTranslatorSignature()) {
            return 0;
        }

        boolean moreThanTenPages = session.getPages() > 10;
        // вот тут место для языка
        if ("RU".equals(session.getTargetLanguage())) {
            return moreThanTenPages ? 45 : 40;
        }

        return moreThanTenPages ? 120 : 115;
    }

    private double calculateNotaryCopy(UserSession session) {

        if (!session.isNotaryCopy()) {
            return 0;
        }

        int pages = session.getPages();

        if (pages == 1) {
            return 31;
        }

        if (pages <= 3) {
            return 41;
        }

        if (pages <= 10) {
            return 68;
        }

        return 72;
    }

    private double calculateApostille(UserSession session) {

        if (session.getApostilleType() == null) {
            return 0;
        }

        return switch (session.getApostilleType()) {

            case "REGULAR" -> 460;

            case "URGENT" -> 630;

            default -> 0;
        };
    }

    private double calculateUrgencySurcharge(
            UserSession session,
            double translationPrice,
            double complexitySurcharge
    ) {

        if (!session.isUrgentTranslation()) {
            return 0;
        }

        double priceWithComplexity =
                translationPrice + complexitySurcharge;

        return priceWithComplexity * 0.50;
    }

    private double calculateComplexitySurcharge(
            UserSession session,
            double translationPrice
    ) {

        if (!"COMPLEX".equals(session.getDocumentType())) {
            return 0;
        }

        return translationPrice * 0.10;
    }

    private double calculateLegalization(UserSession session) {

        if (!session.isLegalization()) {
            return 0;
        }

        double mfaPrice =
                session.isUrgentLegalization() ? 460 : 265;

        double consulatePrice = 165;

        String documentType =
                session.getLegalizationDocumentType();

        if (documentType == null) {
            return 0;
        }

        return switch (documentType) {

            case "EDUCATION" ->
                    105 + mfaPrice + consulatePrice;

            case "NOTARY" ->
                    105 + mfaPrice + consulatePrice;

            case "ORIGINAL" ->
                    mfaPrice + consulatePrice;

            default ->
                    0;
        };
    }
}

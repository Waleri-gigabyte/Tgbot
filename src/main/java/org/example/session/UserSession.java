package org.example.session;

public class UserSession {

    private Step step = Step.START;

    private String sourceLanguage;
    private String targetLanguage;

    private String documentType;

    private Integer pages;

    private boolean urgentTranslation;

    private boolean translatorSignature;
    private boolean notaryCopy;

    private String apostilleType;

    private boolean legalization;
    private String legalizationDocumentType;
    private boolean urgentLegalization;

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public boolean isUrgentTranslation() {
        return urgentTranslation;
    }

    public void setUrgentTranslation(boolean urgentTranslation) {
        this.urgentTranslation = urgentTranslation;
    }

    public boolean isTranslatorSignature() {
        return translatorSignature;
    }

    public void setTranslatorSignature(boolean translatorSignature) {
        this.translatorSignature = translatorSignature;
    }

    public boolean isNotaryCopy() {
        return notaryCopy;
    }

    public void setNotaryCopy(boolean notaryCopy) {
        this.notaryCopy = notaryCopy;
    }

    public String getApostilleType() {
        return apostilleType;
    }

    public void setApostilleType(String apostilleType) {
        this.apostilleType = apostilleType;
    }

    public boolean isLegalization() {
        return legalization;
    }

    public void setLegalization(boolean legalization) {
        this.legalization = legalization;
    }

    public String getLegalizationDocumentType() {
        return legalizationDocumentType;
    }

    public void setLegalizationDocumentType(String legalizationDocumentType) {
        this.legalizationDocumentType = legalizationDocumentType;
    }

    public boolean isUrgentLegalization() {
        return urgentLegalization;
    }

    public void setUrgentLegalization(boolean urgentLegalization) {
        this.urgentLegalization = urgentLegalization;
    }
}

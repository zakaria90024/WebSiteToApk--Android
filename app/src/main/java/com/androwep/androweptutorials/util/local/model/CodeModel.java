package com.androwep.androweptutorials.util.local.model;

public class CodeModel {
    private int id;
    private String codeTitle;
    private String codeDetails;
    private String codeFilter;

    public CodeModel() {
    }

    public CodeModel(int id, String codeTitle, String codeDetails, String codeFilter) {
        this.id = id;
        this.codeTitle = codeTitle;
        this.codeDetails = codeDetails;
        this.codeFilter = codeFilter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeTitle() {
        return codeTitle;
    }

    public void setCodeTitle(String codeTitle) {
        this.codeTitle = codeTitle;
    }

    public String getCodeDetails() {
        return codeDetails;
    }

    public void setCodeDetails(String codeDetails) {
        this.codeDetails = codeDetails;
    }

    public String getCodeFilter() {
        return codeFilter;
    }

    public void setCodeFilter(String codeFilter) {
        this.codeFilter = codeFilter;
    }

    @Override
    public String toString() {
        return "CodeModel{" +
                "id=" + id +
                ", codeTitle='" + codeTitle + '\'' +
                ", codeDetails='" + codeDetails + '\'' +
                ", codeFilter='" + codeFilter + '\'' +
                '}';
    }
}

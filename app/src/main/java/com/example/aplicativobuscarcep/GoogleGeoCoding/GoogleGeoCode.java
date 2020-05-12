package com.example.aplicativobuscarcep.GoogleGeoCoding;

public class GoogleGeoCode {

    public String status;
    public GoogleGeoResult[] results;
    public boolean exclude_from_slo;
    public String error_message;


    public GoogleGeoCode(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public GoogleGeoResult[] getResults() {
        return results;
    }

    public void setResults(GoogleGeoResult[] results) {
        this.results = results;
    }

    public boolean isExclude_from_slo() {
        return exclude_from_slo;
    }

    public void setExclude_from_slo(boolean exclude_from_slo) {
        this.exclude_from_slo = exclude_from_slo;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }
}

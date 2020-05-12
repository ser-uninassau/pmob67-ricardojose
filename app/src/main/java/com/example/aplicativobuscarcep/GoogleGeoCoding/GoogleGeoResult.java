package com.example.aplicativobuscarcep.GoogleGeoCoding;

public class GoogleGeoResult {

    public GoogleGeoAdressComponent [] address_components;
    public String formatted_address;
    public GoogleGeoGeometry geometry;
    public boolean partial_match;
    public String place_id;
    public String[] types;

    public GoogleGeoResult(){

    }

    public GoogleGeoAdressComponent[] getAddress_components() {
        return address_components;
    }

    public void setAddress_components(GoogleGeoAdressComponent[] address_components) {
        this.address_components = address_components;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public GoogleGeoGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(GoogleGeoGeometry geometry) {
        this.geometry = geometry;
    }

    public boolean isPartial_match() {
        return partial_match;
    }

    public void setPartial_match(boolean partial_match) {
        this.partial_match = partial_match;
    }

    public String getPlace_id() {
        return place_id;
    }

    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}

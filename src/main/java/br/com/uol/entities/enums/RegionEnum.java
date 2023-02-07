package br.com.uol.entities.enums;

public enum RegionEnum {
    NORTE("Norte"),
    NORDESTE("Nordeste"),
    SUL("Sul"),
    SUDESTE("Sudeste"),
    CENTRO_OESTE("Centro Oeste");

    private final String region;

    RegionEnum(final String region) {
        this.region = region;
    }

    public String getRegionEnum() {
        return region;
    }
}

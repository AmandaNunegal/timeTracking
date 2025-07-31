package com.nunegal.timeTracking.enums;

import lombok.Data;

public enum TypeTimekeeping {

	ENTRADA_1("Entrada 1"),
    SALIDA_1("Salida 1"),
    ENTRADA_2("Entrada 2"),
    SALIDA_2("Salida 2");
	
	private final String label;

    TypeTimekeeping(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

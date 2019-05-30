package com.codecool.termlib;

public enum Color {
    BLACK_LETTER("30"),
    RED_LETTER("31"),
    GREEN_LETTER("32"),
    YELLOW_LETTER("33"),
    BLUE_LETTER("34"),
    MAGENTA_LETTER("35"),
    CYAN_LETTER("36"),
    WHITE_LETTER("37"),

    BLACK_BACKGROUND("40"),
    RED_BACKGROUND("41"),
    GREEN_BACKGROUND("42"),
    YELLOW_BACKGROUND("43"),
    BLUE_BACKGROUND("44"),
    MAGENTA_BACKGROUND("45"),
    CYAN_BACKGROUND("46"),
    WHITE_BACKGROUND("47");

	public String colorCode;

	private Color(String asd) {
		this.colorCode = asd;
	}

}

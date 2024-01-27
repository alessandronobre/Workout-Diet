package com.devpro.msdiet.enums;

public enum Genero {

    MASCULINO(1, "Masculino"){
        @Override
        public Integer calcularTaxaMetabolicaBasal(int idade, int altura, int peso) {
            double taxaMetabolicaBasal = (10 * peso) + (6.25 * altura) - (5 * idade) + 5;
            return (int) Math.round(taxaMetabolicaBasal);
        }
    },
    FEMININO(2, "Feminino") {
        @Override
        public Integer calcularTaxaMetabolicaBasal(int idade, int altura, int peso) {
            double taxaMetabolicaBasal = (10 * peso) + (6.25 * altura) - (5 * idade) - 161;
            return (int) Math.round(taxaMetabolicaBasal);
        }
    };

    private final int id;
    private final String valor;

    Genero(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getValor() {
        return valor;
    }

    public abstract Integer calcularTaxaMetabolicaBasal(int idade, int altura, int peso);

}

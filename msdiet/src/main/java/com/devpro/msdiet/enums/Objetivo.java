package com.devpro.msdiet.enums;

public enum Objetivo {
    EMAGRECIMENTO(1, "Emagrecimento"){
        @Override
        public Integer calcularCaloriasTreino(Integer gastoTotalCalorias) {
            return Math.round(gastoTotalCalorias - (25 * gastoTotalCalorias / 100));
        }

        @Override
        public Integer calcularCaloriasDescanso(Integer caloriasTreino) {
            return Math.round(caloriasTreino - (10 * caloriasTreino / 100));
        }
    },
    HIPERTROFIA(2, "Hipertrofia") {
        @Override
        public Integer calcularCaloriasTreino(Integer gastoTotalCalorias) {
            return Math.round(gastoTotalCalorias + 200);
        }

        @Override
        public Integer calcularCaloriasDescanso(Integer caloriasTreino) {
            return Math.round(caloriasTreino - (10 * caloriasTreino / 100));
        }
    };

    private final int id;
    private final String valor;

    Objetivo(int id, String valor) {
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public String getValor() {
        return valor;
    }

    public abstract Integer calcularCaloriasTreino(Integer gastoTotalCalorias);
    public abstract Integer calcularCaloriasDescanso(Integer caloriasTreino);

}

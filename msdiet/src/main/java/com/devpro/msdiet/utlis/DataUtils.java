package com.devpro.msdiet.utlis;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {

    public static String dataAtualFormatada() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        String dataHoraFormatada = dataHoraAtual.format(formatador);
        return dataHoraFormatada;
    }
}

package org.codelab.graacc.Notificacoes.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private DateUtil() {}

    public static LocalDateTime converterStringToData(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dataFormatada = LocalDateTime.parse(data, formatter);
            return dataFormatada;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao converter Data do Agendamento - tente novamento no formato dd/MM/yyyy HH:mm");
        }
    }

    public static String formatarDataToString(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return data.format(formatter);
    }
}

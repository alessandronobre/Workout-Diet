package com.devpro.msdiet.service;


import com.devpro.msdiet.dto.MacrosDto;
import com.devpro.msdiet.enums.Genero;
import com.devpro.msdiet.enums.NivelAtividadeFisica;
import com.devpro.msdiet.enums.Objetivo;
import com.devpro.msdiet.model.Macros;
import com.devpro.msdiet.repository.MacrosRepository;
import com.devpro.msdiet.repository.PacienteRepository;
import com.devpro.msdiet.utlis.DataUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@RequiredArgsConstructor
@Service
public class MacrosService {

    private final PacienteRepository pacienteRepository;
    private final MacrosRepository macrosRepository;

	public void salvar(MacrosDto macros, Long id) {
        macrosRepository.save(gerarMacros(macros, id));
	}

    private Macros gerarMacros(MacrosDto macrosDto, Long id) {
        var paciente = pacienteRepository.buscaPacientePorId(id);

        var imc = calcularImc(paciente.getAltura(), paciente.getPeso());

        var taxaMetabolicaBasal = calcularTaxaMetabolicaBasal(paciente.getGenero(),
                paciente.getIdade(),
                paciente.getAltura(),
                paciente.getPeso());

        var gastoCaloricoTotal = calcularGastoTotalCalorias(paciente.getGenero(),
                paciente.getIdade(),
                paciente.getAltura(),
                paciente.getPeso(),
                macrosDto.getNivelAtividadeFisica());

        var proteina = calcularProteina(paciente.getPeso());

        var caloriasTreino = calcularCaloriasTreino(paciente.getGenero(),
                paciente.getIdade(),
                paciente.getAltura(),
                paciente.getPeso(),
                macrosDto.getObjetivo(),
                macrosDto.getNivelAtividadeFisica());

        var gorduraTreino = calcularGorduraTreino(paciente.getPeso());

        var carboidratoTreino = calcularCarboidratoTreino(caloriasTreino, proteina, gorduraTreino);

        var caloriasDescanso = calcularCaloriasDescanso(paciente.getGenero(),
                paciente.getIdade(),
                paciente.getAltura(),
                paciente.getPeso(),
                macrosDto.getObjetivo(),
                macrosDto.getNivelAtividadeFisica());

        var gorduraDescanso = calcularGorduraDescanso(gorduraTreino);

        var carboidratoDescanso = calcularCarboidratoDescanso(carboidratoTreino);

        var fibra = calcularFibra(caloriasTreino);

        var macros = new Macros();
        macros.setObjetivo(macrosDto.getObjetivo());
        macros.setNivelAtividadeFisica(macrosDto.getNivelAtividadeFisica());
        macros.setDataCalculo(DataUtils.dataAtualFormatada());
        macros.setImc(imc);
        macros.setTmb(taxaMetabolicaBasal);
        macros.setGastoCaloricoTotal(gastoCaloricoTotal);
        macros.setCaloriasTreino(caloriasTreino);
        macros.setProteinaTreino(proteina);
        macros.setCarboidratoTreino(carboidratoTreino);
        macros.setGorduraTreino(gorduraTreino);
        macros.setFibraTreino(fibra);
        macros.setCaloriasDescanso(caloriasDescanso);
        macros.setProteinaDescanso(proteina);
        macros.setCarboidratoDescanso(carboidratoDescanso);
        macros.setGorduraDescanso(gorduraDescanso);
        macros.setFibraDescanso(fibra);
        macros.setPaciente(paciente);
        return macros;
    }

    public void deletar(Long id) {
        macrosRepository.deleteAll(macrosRepository.buscarMacros(id));
    }

    private String calcularImc(int altura, int peso) {
        var conversor = new DecimalFormat("#,##");
        var formatAltura = conversor.format(altura);
        var converterAltura = Double.parseDouble(formatAltura);

        var formatImc = conversor.format(peso / (converterAltura * converterAltura));
        var imcFormatado = Double.parseDouble(formatImc);
        return imcFormatado + "%";
    }

    private Integer calcularTaxaMetabolicaBasal(Genero genero, int idade, int altura, int peso) {
        if (genero.equals(Genero.MASCULINO)) {
            return Genero.MASCULINO.calcularTaxaMetabolicaBasal(idade, altura, peso);
        }
        return Genero.FEMININO.calcularTaxaMetabolicaBasal(idade, altura, peso);
    }

    private Integer calcularGastoTotalCalorias(Genero genero, int idade, int altura, int peso, NivelAtividadeFisica nivelAtividade) {
        return nivelAtividade.calcularGastoTotalCalorias(calcularTaxaMetabolicaBasal(genero, idade, altura, peso));
    }

    private Integer calcularProteina(int peso) {
        return (int) Math.round(peso * 2.240);
    }

    private Integer calcularFibra(Integer caloriasTreino) {
        var fibras = 0;
        if (caloriasTreino <= 1200) {
            fibras = 10;
        } else if (caloriasTreino > 1200 && caloriasTreino <= 2200) {
            fibras = 20;

        } else if (caloriasTreino > 2200 && caloriasTreino <= 3200) {
            fibras = 30;

        } else if (caloriasTreino > 3200 && caloriasTreino <= 4200) {
            fibras = 40;
        }
        return fibras;
    }

    private Integer calcularCaloriasTreino(Genero genero, int idade, int altura, int peso, Objetivo objetivo, NivelAtividadeFisica nivelAtividade) {
        return objetivo.calcularCaloriasTreino(calcularGastoTotalCalorias(genero, idade, altura, peso, nivelAtividade));
    }

    private Integer calcularCarboidratoTreino(Integer caloriasTreino, int proteina, int gordura) {
        return (caloriasTreino - (proteina * 4) - (gordura * 9)) / 4;
    }

    private Integer calcularGorduraTreino(int peso) {
        return (int) Math.round(peso * 0.760);
    }

    private Integer calcularCaloriasDescanso(Genero genero, int idade, int altura, int peso, Objetivo objetivo, NivelAtividadeFisica nivelAtividade) {
        return objetivo.calcularCaloriasDescanso(calcularCaloriasTreino(genero, idade, altura, peso, objetivo, nivelAtividade));
    }

    private Integer calcularCarboidratoDescanso(Integer carboidratoTreino) {
        return carboidratoTreino - (20 * carboidratoTreino / 100);
    }

    private Integer calcularGorduraDescanso(Integer gorduraTreino) {
        return gorduraTreino - (9 * gorduraTreino / 100);
    }
}

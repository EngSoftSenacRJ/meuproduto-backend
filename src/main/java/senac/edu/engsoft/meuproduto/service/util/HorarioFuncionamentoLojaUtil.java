package senac.edu.engsoft.meuproduto.service.util;

import senac.edu.engsoft.meuproduto.model.HorarioFuncionamento;
import senac.edu.engsoft.meuproduto.model.Loja;

import java.time.LocalTime;

public class HorarioFuncionamentoLojaUtil {

    public static void setDefaultHorarioFuncionamentoLoja(Loja loja){
        for(int i = 0 ; i <= 6 ; i++){
            HorarioFuncionamento horarioFuncionamento = new HorarioFuncionamento();
            horarioFuncionamento.setLoja(loja);
            horarioFuncionamento.setHorarioFuncionamentoDe(LocalTime.of(9,0));
            horarioFuncionamento.setHorarioFuncionamentoAte(LocalTime.of(18,0));
            horarioFuncionamento.setAberto(true);
            if(i == 0){
                horarioFuncionamento.setDiaSemana(HorarioFuncionamento.DiaSemana.DOMINGO);
                horarioFuncionamento.setAberto(false);
                horarioFuncionamento.setHorarioFuncionamentoDe(null);
                horarioFuncionamento.setHorarioFuncionamentoAte(null);
            }
            else if(i == 1)
                horarioFuncionamento.setDiaSemana(HorarioFuncionamento.DiaSemana.SEGUNDA_FEIRA);
            else if(i == 2)
                horarioFuncionamento.setDiaSemana(HorarioFuncionamento.DiaSemana.TERCA_FEIRA);
            else if(i == 3)
                horarioFuncionamento.setDiaSemana(HorarioFuncionamento.DiaSemana.QUARTA_FEIRA);
            else if(i == 4)
                horarioFuncionamento.setDiaSemana(HorarioFuncionamento.DiaSemana.QUINTA_FEIRA);
            else if(i == 5)
                horarioFuncionamento.setDiaSemana(HorarioFuncionamento.DiaSemana.SEXTA_FEIRA);
            else if(i == 6) {
                horarioFuncionamento.setDiaSemana(HorarioFuncionamento.DiaSemana.SABADO);
                horarioFuncionamento.setHorarioFuncionamentoDe(LocalTime.of(9,0));
                horarioFuncionamento.setHorarioFuncionamentoAte(LocalTime.of(13,0));
            }
            loja.getHorarioFuncionamentoSet().add(horarioFuncionamento);
        }
    }

}

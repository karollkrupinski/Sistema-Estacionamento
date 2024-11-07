package estacionamento;

import java.util.ArrayList;
import java.util.List;

public class estacionamento {

    private List<vaga> vagas = new ArrayList<>();
    private List<String> historico = new ArrayList<>();

    public void adicionarVaga(int numero, String tamanho) {
        vagas.add(new vaga(numero, tamanho));
    }

    public boolean entradaAutomovel(String placa, String modelo, String tamanho, long horaEntrada) {
        automovel veiculo = new automovel(placa, modelo, tamanho, horaEntrada);
        for (vaga v : vagas) {
            if (v.estaDisponivel() && v.getTipoTamanho().equals(tamanho)) {
                return v.ocuparVaga(veiculo);
            }
        }
        return false;
    }

    public void saidaAutomovel(String placa, long horaSaida) {
        for (vaga v : vagas) {
            automovel veiculo = v.getAutomovelEstacionado();
            if (veiculo != null && veiculo.getPlaca().equals(placa)) {
                veiculo.setHoraSaida(horaSaida);
                long horas = veiculo.getTempoPermanencia();
                double valor = calcularValor(horas);
                historico.add("Placa: " + veiculo.getPlaca() + ", Valor pago: R$ " + valor);
                v.liberarVaga();
                System.out.println("Veículo " + placa + " saiu. Valor a pagar: R$ " + valor + ".");
                return;
            }
        }
        System.out.println("Veículo não encontrado.");
    }

    private double calcularValor(long horas) {
        if (horas <= 1) return 5.00;
        if (horas <= 3) return 10.00;
        return 15.00;
    }

    public void relatorioVagasOcupadas() {
        System.out.println("VAGAS OCUPADAS:");
        for (vaga v : vagas) {
            if (!v.estaDisponivel()) {
                System.out.println("Vaga: " + v.getNumeroVaga() + ", Placa: " + v.getAutomovelEstacionado().getPlaca() + ", Tamanho: " + v.getTipoTamanho());
            }
        }
    }

    public void relatorioHistorico() {
        System.out.println("HISTÓRICO DE VEÍCULOS:");
        for (String registro : historico) {
            System.out.println(registro);
        }
    }
}


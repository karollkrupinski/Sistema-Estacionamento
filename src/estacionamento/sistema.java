package estacionamento;

import java.util.Scanner;

public class sistema {

    public static void main(String[] args) {
        estacionamento estacionamento = new estacionamento();
        Scanner input = new Scanner(System.in);

        System.out.println("SISTEMA DE GERENCIAMENTO DE ESTACIONAMENTO");

        estacionamento.adicionarVaga(1, "pequeno");
        estacionamento.adicionarVaga(2, "médio");
        estacionamento.adicionarVaga(3, "grande");

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Entrada de veículo");
            System.out.println("2. Saída de veículo");
            System.out.println("3. Relatório de vagas ocupadas");
            System.out.println("4. Relatório de histórico");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            int opcao = input.nextInt();
            input.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Placa: ");
                    String placa = input.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = input.nextLine();
                    System.out.print("Hora de entrada (HH:mm): ");
                    String horaEntradaStr = input.nextLine();
                    long horaEntrada = converterHoraParaMilissegundos(horaEntradaStr);

                    System.out.println("Escolha o tamanho:");
                    System.out.println("1. Pequeno");
                    System.out.println("2. Médio");
                    System.out.println("3. Grande");
                    int tamanhoOp = input.nextInt();
                    input.nextLine();
                    String tamanho = definirTamanho(tamanhoOp);

                    if (!estacionamento.entradaAutomovel(placa, modelo, tamanho, horaEntrada)) {
                        System.out.println("Nenhuma vaga disponível para o tamanho " + tamanho + ".");
                    } else {
                        System.out.println("Entrada registrada com sucesso.");
                    }
                    break;

                case 2:
                    System.out.print("Placa: ");
                    placa = input.nextLine();
                    System.out.print("Hora de saída (HH:mm): ");
                    String horaSaidaStr = input.nextLine();
                    long horaSaida = converterHoraParaMilissegundos(horaSaidaStr);

                    estacionamento.saidaAutomovel(placa, horaSaida);
                    System.out.println("Saída registrada com sucesso.");
                    break;

                case 3:
                    estacionamento.relatorioVagasOcupadas();
                    break;

                case 4:
                    estacionamento.relatorioHistorico();
                    break;

                case 0:
                    System.out.println("Encerrando sistema.");
                    input.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static long converterHoraParaMilissegundos(String hora) {
        String[] partes = hora.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        long agora = System.currentTimeMillis();
        long milissegundos = agora - (agora % (1000 * 60 * 60));

        return milissegundos + (horas * 3600000) + (minutos * 60000);
    }

    private static String definirTamanho(int opcao) {
        switch (opcao) {
            case 1: return "pequeno";
            case 2: return "médio";
            case 3: return "grande";
            default: 
                System.out.println("Tamanho inválido. Usando 'pequeno'.");
                return "pequeno";
        }
    }
}

package estacionamento;

public class vaga {

    private int numeroVaga;
    private String tipoTamanho;
    private boolean statusDisponivel;
    private automovel veiculoEstacionado;

    public vaga(int numeroVaga, String tipoTamanho) {
        this.numeroVaga = numeroVaga;
        this.tipoTamanho = tipoTamanho;
        this.statusDisponivel = true;
    }

    public boolean ocuparVaga(automovel veiculo) {
        if (statusDisponivel && this.tipoTamanho.equalsIgnoreCase(veiculo.getTamanho())) {
            this.veiculoEstacionado = veiculo;
            this.statusDisponivel = false;
            return true;
        }
        return false;
    }

    public void liberarVaga() {
        this.veiculoEstacionado = null;
        this.statusDisponivel = true;
    }

    public boolean estaDisponivel() {
        return statusDisponivel;
    }

    public automovel getAutomovelEstacionado() {
        return veiculoEstacionado;
    }

    public String getTipoTamanho() {
        return tipoTamanho;
    }

    public int getNumeroVaga() {
        return numeroVaga;
    }
}


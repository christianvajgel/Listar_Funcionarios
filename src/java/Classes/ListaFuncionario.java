package Classes;

import java.util.ArrayList;

public class ListaFuncionario {
    public ArrayList<Funcionario> funcionarios = new ArrayList();

    public ListaFuncionario() {
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
package Servlets;

import Classes.Funcionario;
import Classes.ListaFuncionario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.MaskFormatter;

public class salvarfunc extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("nome");
        String CPF = request.getParameter("cpf");
        String data = request.getParameter("data");
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("d/MM/yyyy");
	LocalDate dataNascimento = LocalDate.parse(data, formatador);
        data = formatador.format(dataNascimento);
        LocalDate dataAtual = LocalDate.now();
        int idade = Period.between(dataNascimento, dataAtual).getYears();
        double salario = Double.parseDouble(request.getParameter("salario"));
        
        if (CPF.length() == 11 && idade > 18 && salario >= 500){
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Sucesso!</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Dados cadastrados com sucesso!</h1>");
                out.println("</body>");
                out.println("</html>");
            }
            try {
                MaskFormatter CPFMask = new MaskFormatter("###.###.###-##");
                CPFMask.setValueContainsLiteralCharacters(false);
                CPF = CPFMask.valueToString(CPF);
            } catch (ParseException pE) {
                System.out.println("ERRO: " + pE);
            }
            Funcionario funcionario = new Funcionario(nome, CPF, data, salario);
            ListaFuncionario lista = new ListaFuncionario();
            lista.funcionarios.add(funcionario);
            System.out.println(lista.funcionarios.get(0).getNome());
            System.out.println(lista.funcionarios.get(0).getCPF());
            System.out.println(lista.funcionarios.get(0).getDataNascimento());
            System.out.println(lista.funcionarios.get(0).getSalario());
        } else {
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Erro!</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Dados inválidos. Retornando para homepage...</h1>");
                out.println("<h1>" + nome + " | " + CPF + " | " + idade + " | " + salario + " | " + data + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        }    
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
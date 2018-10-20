package calculoSalario;
import javax.swing.*;
public class Principal {
    public static void main(String[] args) {
        Salario sal = new Salario();
        Double salario;
        int meses;
        String dependentes;
        int sair = 1;

        do {
            String menu = JOptionPane.showInputDialog(null,
                    "Escolha uma opção:\n1. Digitar Salario" +
                            "\n2. Calcular salario\n3. Calcular FGTS\n\n0. Sair",
                    "Menu",-1);
            switch (menu) {
                case "1":
                    salario = Double.parseDouble(JOptionPane.showInputDialog(null,
                            "Digite o salario bruto:", "Salario", -1));
                    dependentes = JOptionPane.showInputDialog(null,
                            "Digite o numero de dependentes:", "Salario", -1);
                    dependentes = dependentes.equals("") ? "0" : dependentes;
                    sal.cadastrar(salario, Integer.parseInt(dependentes));
                    break;
                case "2":
                    sal.calcularSalLiq();
                    sal.printSal();
                    break;
                case "3":
                    meses = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "Digite quantos meses trabalhados:", "FGTS", -1));
                    sal.calcularFGTS(meses);
                    sal.printFGTS();
                    break;
                case "0":
                    Object[] options = { "Sim", "Não" };
                    sair = JOptionPane.showOptionDialog(null,
                            "Tem certeza que deseja sair?", "Saída",
                            JOptionPane.YES_NO_OPTION, -1, null,
                            options, options[0]);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,
                            "Insira apenas números (0 a 3)", "ERRO", 0);
                    break;
            }
        }while(sair != 0);

    }
}

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
         List<Funcionario> funcionarios = new ArrayList<>();

        // Inserir todos os funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 02), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador")); 
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02), new BigDecimal("2799.93"), "Gerente"));

        // Remover o funcionário João da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // Imprimir todos os funcionários, 
        System.out.println("Lista de funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salário: " + formatarValor(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }

        // Aumenta o salário de todos os funcionários em 10%
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        }

        // Agrupar funcionários por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            funcionariosPorFuncao.putIfAbsent(funcao, new ArrayList<>());
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }

        // Imprimir os funcionários agrupados por função
        System.out.println("Funcionários agrupados por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            String funcao = entry.getKey();
            List<Funcionario> funcionariosDaFuncao = entry.getValue();
            System.out.println("Função: " + funcao);
            for (Funcionario funcionario : funcionariosDaFuncao) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("Salário: " + formatarValor(funcionario.getSalario()));
                System.out.println();
            }
        }

        // Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("Funcionários que fazem aniversário nos meses 10 e 12:");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println();
            }
        }

        // Encontrar o funcionário com a maior idade
        Funcionario funcionarioMaisVelho = funcionarios.get(0);
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getDataNascimento().isBefore(funcionarioMaisVelho.getDataNascimento())) {
                 funcionarioMaisVelho = funcionario;
            }
        }
        System.out.println("Funcionário com a maior idade:");
        System.out.println("Nome: " + funcionarioMaisVelho.getNome());
        System.out.println("Idade: " + calcularIdade(funcionarioMaisVelho.getDataNascimento()));
        System.out.println();

        // Ordenar a lista de funcionários por ordem alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));

        // Imprimir a lista de funcionários por ordem alfabética
        System.out.println("Lista de funcionários em ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salário: " + formatarValor(funcionario.getSalario()));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }
    }

    // Função para formatar o valor do solário
    private static String formatarValor(BigDecimal valor) {
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance();
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator('.');
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);
        return decimalFormat.format(valor);
    }

    // Função para calcular a idade
    private static int calcularIdade(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        return dataAtual.minusYears(dataNascimento.getYear()).getYear();
    }
}

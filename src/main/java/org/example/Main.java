package org.example;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18),
                BigDecimal.valueOf(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 05, 12),
                BigDecimal.valueOf(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 05, 02),
                BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14),
                BigDecimal.valueOf(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 01, 05),
                BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19),
                BigDecimal.valueOf(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 03, 31),
                BigDecimal.valueOf(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 07, 8),
                BigDecimal.valueOf(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 05, 24),
                BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 02),
                BigDecimal.valueOf(2799.93), "Gerente"));;

        // Remover o funcionário “João” da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // Imprimir todos os funcionários com todas suas informações
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimentoFormatted());
            System.out.println("Salário: " + funcionario.getSalario().toString().replace('.', ','));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println();
        }

        // Aumentar salário em 10%
        for (Funcionario funcionario : funcionarios) {
            funcionario.aumentarSalario(new BigDecimal("0.1"));
        }

        // Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // Imprimir os funcionários, agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("Nome: " + funcionario.getNome());
            }
            System.out.println();
        }

        // Imprimir os funcionários que fazem aniversário nos meses 10 e 12
        int[] mesesAniversario = {10, 12};
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (Arrays.stream(mesesAniversario).anyMatch(mes -> mes == mesAniversario)) {
                System.out.println("Funcionário que faz aniversário em " + mesAniversario + ": "
                        + funcionario.getNome());
            }
        }

        // Imprimir o funcionário com a maior idade
        Funcionario maisVelho = Collections.max(funcionarios, Comparator.comparingInt(Funcionario::getIdade));
        System.out.println("Funcionário mais velho: " + maisVelho.getNome() + " - Idade: " + maisVelho.getIdade());

        // Ordenar os funcionários por ordem alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));

        // Imprimir a lista de funcionários por ordem alfabética
        System.out.println("Lista de funcionários em ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }

        // Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários dos funcionários: "
                + totalSalarios.toString().replace('.', ','));

        // Imprimir quantos salários mínimos ganha cada funcionário
        double salarioMinimo = 1212.00;
        System.out.println("Quantos salários mínimos ganha cada funcionário:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome() + ": "
                    + funcionario.getSalarioEmSalariosMinimos(salarioMinimo));
        }
    }
}
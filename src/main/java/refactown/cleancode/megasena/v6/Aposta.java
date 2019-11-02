package refactown.cleancode.megasena.v6;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Representa uma aposta feita por um apostador. Ou seja, um conjunto de 6 a 15 números entre 1 e 60.
 * A aposta tem um estado imutável e não tem identidade única, sendo um "Value Object" com números.
 */
public class Aposta {


    private final List<Integer> numeros;

    public Aposta(List<Integer> numeros){
        if (!isApostaValida(numeros)){
            throw new IllegalArgumentException("Aposta inválida:" + numeros);
        }
        this.numeros = Collections.unmodifiableList(numeros); // cópia defensiva
    }

    private boolean isApostaValida(List<Integer> numerosApostados) {
        return numerosApostados.size() >= 6 && numerosApostados.size() <= 15 &&
                numerosApostados.stream().distinct().filter(n -> n >= 1 && n <= 60).
                        count() == numerosApostados.size();
    }

    public int calculaAcertos(Resultado resultado){
        return (int) numeros.stream().filter(n -> resultado.foiSorteado(n)).count();
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aposta aposta = (Aposta) o;
        return Objects.equals(numeros, aposta.numeros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeros);
    }

}
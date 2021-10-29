package br.com.gbadessa.util;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * ListFilterUtil
 * Classe responsável por controlar Métodos utilizados para facilitar o filtro das informações da lista
 */
public class ListFilterUtil {

    //region Métodos

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    //endregion Métodos
}

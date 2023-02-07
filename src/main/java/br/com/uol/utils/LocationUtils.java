package br.com.uol.utils;

import br.com.uol.entities.enums.RegionEnum;

import java.text.Normalizer;
import java.util.*;

public class LocationUtils {

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static TreeMap<String, String> getStatesByRegion(String region) {
       TreeMap<String, String> statesRegionsAndCapitals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        if(region.equalsIgnoreCase(RegionEnum.SUL.name())) {
            statesRegionsAndCapitals.put("Paraná", "Curitiba");
            statesRegionsAndCapitals.put("Santa Catarina", "Florianópolis");
            statesRegionsAndCapitals.put("Rio Grande do Sul", "Porto Alegre");
        } else if(region.equalsIgnoreCase(RegionEnum.NORTE.name())) {
            statesRegionsAndCapitals.put("Amazonas", "Manaus");
            statesRegionsAndCapitals.put("Roraima", "Boa Vista");
            statesRegionsAndCapitals.put("Amapá", "Macapá");
            statesRegionsAndCapitals.put("Pará", "Belém");
            statesRegionsAndCapitals.put("Tocantins", "Palmas");
            statesRegionsAndCapitals.put("Rondônia", "Porto Velho");
            statesRegionsAndCapitals.put("Acre", "Rio Branco");
        } else if(region.equalsIgnoreCase(RegionEnum.NORDESTE.name())) {
            statesRegionsAndCapitals.put("Maranhão", "São Luís");
            statesRegionsAndCapitals.put("Piauí", "Teresina");
            statesRegionsAndCapitals.put("Ceará", "Fortaleza");
            statesRegionsAndCapitals.put("Rio Grande do Norte", "Natal");
            statesRegionsAndCapitals.put("Pernambuco", "Recife");
            statesRegionsAndCapitals.put("Paraíba", "João Pessoa");
            statesRegionsAndCapitals.put("Sergipe", "Aracaju");
            statesRegionsAndCapitals.put("Alagoas", "Maceió");
            statesRegionsAndCapitals.put("Bahia", "Salvador");
        } else if(region.equalsIgnoreCase(RegionEnum.SUDESTE.name())) {
            statesRegionsAndCapitals.put("São Paulo", "São Paulo");
            statesRegionsAndCapitals.put("Rio de Janeiro", "Rio de Janeiro");
            statesRegionsAndCapitals.put("Minas Gerais", "Belo Horizonte");
            statesRegionsAndCapitals.put("Espírito Santo", "Vitória");
        } else if(region.equalsIgnoreCase(RegionEnum.CENTRO_OESTE.name())) {
            statesRegionsAndCapitals.put("Mato Grosso", "Cuiabá");
            statesRegionsAndCapitals.put("Mato Grosso do Sul", "Campo Grande");
            statesRegionsAndCapitals.put("Goiás", "Goiânia");
            statesRegionsAndCapitals.put("Distrito Federal", "Brasília");
        }
        return statesRegionsAndCapitals;
    }
}

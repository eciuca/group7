package live.functional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FunctionalStyle {

    public static void main(String[] args) {

        // Stil imperativ (CUM SA FACA)
        int j = 0;
        while (j < 100) {
            System.out.println("*");
            j++;
        }

        for (int i = 0; i < 100; i++) {
            System.out.println("*");
        }

        // Stil declarativ (CE SA FACA)
        IntStream.range(0, 100)
                .forEach(new IntConsumer() {

                    @Override
                    public void accept(int value) {
                        System.out.println("*");
                    }
                });

        // Versiunea 1
        IntStream.range(0, 10)
                .forEach(
                        // echivalent cu functia afiseaza numar definita mai jos
                        (int x) -> System.out.println(x)
                );

        // Versiunea 2
        IntStream.range(0, 10)
                .forEach(
                        // echivalent cu functia afiseaza numar definita mai jos
                        x -> System.out.println(x)
                );

        // Exemplu metoda cu return type non-void (functie)
        IntStream.range(0, 10)
                .mapToObj(x -> {
                    return dubleazaNumar(x);
                })
                .forEach(x -> afiseazaNumar(x));

        // Exemplu metoda cu return type non-void (functie)
        IntStream.range(0, 10)
                .mapToObj(x -> dubleazaNumar(x)) // lambda (echivalent cu linia 66)
                .forEach(x -> afiseazaNumar(x)); // lambda (echivalent cu linia 67)

        // Varianta imperative
        for (int i = 0; i < 10; i++) {
            int x = i;
            x = dubleazaNumar(x);
            afiseazaNumar(x);
        }

        // Exemplu metoda cu return type non-void (functie) + method reference
        IntStream.range(0, 10)
                .mapToObj(FunctionalStyle::dubleazaNumar) // method reference (echivalent cu linia 54)
                .forEach(FunctionalStyle::afiseazaNumar); // method reference (echivalent cu linia 55)

        List<Integer> lista = Arrays.asList(1, 2, 5, 85, 3, 7, 2);
        List<String> listaString = Arrays.asList("a", "mama", "bau");


        aplicaFunctieSiAfiseaza(x -> String.valueOf(x), 12);
        aplicaFunctieSiAfiseaza(String::valueOf, 12);


        //Imperative style iteration
        for (Integer nr : lista) {
            if (nr % 2 == 0) { // filter
                int doubleOfNr = nr * 2; // map
                System.out.println(doubleOfNr);
            }
        }

        //Declarative style iteration
        lista.stream()                                // 1    2  3    4
                .filter(nr -> nr % 2 == 0)            // nok ok  nok  ok
//                .map(n -> n * 2)                    //      4       8
                .map(FunctionalStyle::dubleazaNumar)  //      4       8
                .forEach(System.out::println);        //      4       8

        //Declarative style iteration + method references
        lista.stream()                                    // 1    2  3    4
                .filter(FunctionalStyle::nrPar)           // nok ok  nok  ok
                .map(FunctionalStyle::dubleazaNumar)      //      4       8
                .forEach(FunctionalStyle::afiseazaNumar); //      4       8

        // Maximul elementelor pare dintr-o lista
        Optional<Integer> rezultat = lista.stream()
                .filter(FunctionalStyle::nrPar)
                .max(Comparator.naturalOrder());
        System.out.println(rezultat.orElse(0));

//        Optional<String> stringOptional = Optional.empty();
        Optional<String> stringOptional = Optional.of("o valoare");
        // returneaza true daca avem o valoare
        if (stringOptional.isPresent()) {
            //folosim metoda get ca sa obtinem valoarea
            System.out.println(stringOptional.get());
        } else {
            System.out.println("Nu avem nici un string");
        }

        String valoareString = stringOptional.orElse("Nu avem nici un string");
        stringOptional.ifPresent(valoare -> {
            System.out.println("Afiseaza din ifPresent: " + valoare);
        });


        List<String> listOfStrings = lista.stream()
                .map(String::valueOf) // transformam elementul din Integer in String
                .collect(Collectors.toList());

//        String csv = listOfStrings.parallelStream() // Executie in parallel pe threaduri diferite
        String csv = listOfStrings.stream() // Executie secventiala in main thread
//                .distinct()
                .limit(3)
                .peek(x -> System.out.println("Concatenez " + x))
                .collect(Collectors.joining("; "));

        System.out.println("Primul element din listOfStrings: " + listOfStrings.get(0));
        System.out.println("listOfStrings: " + Arrays.toString(listOfStrings.toArray()));
        System.out.println("csv: " + csv);

        Supplier<Boolean> supplier = () -> true;

        BiFunction<Integer, Integer, String> sumAndReturnAsString =
                (x, y) -> String.valueOf(x + y);

        Integer sum = lista.stream().reduce(0, (x, y) -> x + y);
    }

    public static boolean nrPar(int x) {
        return x % 2 == 0;
    }

    public static void afiseazaNumar(int x) {
        System.out.println(x);
    }

    public static int dubleazaNumar(int x) {
        return x * 2;
    }

    // f: R-> String, f(x) = xstring;
    public static void aplicaFunctieSiAfiseaza(Function<Integer, String> functie, int nr) {
        String rezultat = functie.apply(nr);
        System.out.println(rezultat);
    }
}

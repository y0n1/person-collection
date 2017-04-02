package main;


import interfaces.IPerson;

import java.util.Arrays;
import java.util.Comparator;


public class Main {
    public static void main(String[] args) {

        Comparator<IPerson> byDateOfBirth = Comparator.comparing(IPerson::getDateOfBirth);
        Comparator<IPerson> byFirstName = Comparator.comparing(IPerson::getFirstName);
        Comparator<IPerson> byLastName = Comparator.comparing(IPerson::getLastName);
        Comparator<IPerson> byHeight = Comparator.comparing(IPerson::getHeight);
        Comparator<IPerson> byId = Comparator.comparing(IPerson::getId);

        PersonCollection personCollection = new PersonCollection(byDateOfBirth);
    }

    private static void printMethods(Class someClass) {
        Arrays.asList(someClass.getMethods()).forEach(method ->
                System.out.printf("%s .%s()%n", method.getReturnType().getSimpleName(), method.getName()));
    }
}
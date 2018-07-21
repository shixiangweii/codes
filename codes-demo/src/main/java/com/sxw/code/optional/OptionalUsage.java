package com.sxw.code.optional;

import java.util.Optional;

/**
 * Description:
 * User: shixiangweii
 * Date: 2018-07-21
 * Time: 12:32
 *
 * @author shixiangweii
 */
public class OptionalUsage {


    private static String getChampionName(Competition competition) {
        return Optional.ofNullable(competition).map(Competition::getResult)
                .map(CompResult::getChampion)
                .map(User::getName).orElse("1234");
    }

    private static String getString(String string) {
        Optional<String> str = Optional.ofNullable(string);
        str.ifPresent(System.out::println);
        return str.orElse("else");
    }

    private static String setName(String name) throws IllegalArgumentException {
        return Optional.ofNullable(name).filter(User::isNameValid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username."));
    }

    private static String setNameGet(String name) throws IllegalArgumentException {
        return Optional.ofNullable(name).filter(User::isNameValid)
                .orElseGet(() -> {
                    long l = System.currentTimeMillis();
                    return l + "time";
                });
    }

    public static void main(String[] args) {
        System.out.println("---------------");

        System.out.println(getChampionName(null));

        System.out.println("---------------");

        Competition competition = new Competition();

        System.out.println(getChampionName(competition));

        System.out.println("---------------");

        System.out.println(getString(null));

        System.out.println("---------------");

        System.out.println(getString("123456"));

        System.out.println("---------------");

        System.out.println(setName("12"));

        System.out.println("---------------");

        System.out.println(setNameGet(null));

        System.out.println("---------------");

        setName(null);
    }
}

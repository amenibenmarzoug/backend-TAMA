package com.eniso.tama.helpers;

import java.security.SecureRandom;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class RandomPasswordGenerator {
	
	/**
     *  Special characters allowed in password.
     */
	
	Random random = new SecureRandom();
	 
	public String generateSecureRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(getRandomNumbers(2), Stream.concat(getRandomSpecialChars(2), Stream.concat(getRandomAlphabets(2, true), getRandomAlphabets(4, false))));
        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);
        String password = charList.stream()
            .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
            .toString();
        return password;
    }

    public String generateRandomSpecialCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(33, 45)
            .build();
        return pwdGenerator.generate(length);
    }

    public String generateRandomNumbers(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(48, 57)
            .build();
        return pwdGenerator.generate(length);
    }

    public String generateRandomCharacters(int length) {
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(48, 57)
            .build();
        return pwdGenerator.generate(length);
    }

    public String generateRandomAlphabet(int length, boolean lowerCase) {
        int low;
        int hi;
        if (lowerCase) {
            low = 97;
            hi = 122;
        } else {
            low = 65;
            hi = 90;
        }
        RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(low, hi)
            .build();
        return pwdGenerator.generate(length);
    }

    public Stream<Character> getRandomAlphabets(int count, boolean upperCase) {
        IntStream characters = null;
        if (upperCase) {
            characters = random.ints(count, 65, 90);
        } else {
            characters = random.ints(count, 97, 122);
        }
        return characters.mapToObj(data -> (char) data);
    }

    public Stream<Character> getRandomNumbers(int count) {
        IntStream numbers = random.ints(count, 48, 57);
        return numbers.mapToObj(data -> (char) data);
    }

    public Stream<Character> getRandomSpecialChars(int count) {
        IntStream specialChars = random.ints(count, 33, 45);
        return specialChars.mapToObj(data -> (char) data);
    }
}

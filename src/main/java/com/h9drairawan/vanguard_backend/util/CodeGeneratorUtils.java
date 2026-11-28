package com.h9drairawan.vanguard_backend.util;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

public final class CodeGeneratorUtils {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final String DEFAULT_CHARSET = "ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
    private static final String SLUG_SUFFIX_CHARSET = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.BASIC_ISO_DATE;

    public CodeGeneratorUtils() {
    }

    public static String generateRandomCode(int length) {
        return generateRandomCode(length, DEFAULT_CHARSET);
    }

    public static String generateRandomCode(int length, String charset) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }
        Objects.requireNonNull(charset, "Charset must not be null");
        if (charset.isEmpty()) {
            throw new IllegalArgumentException("Charset must not be empty");
        }

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = SECURE_RANDOM.nextInt(charset.length());
            sb.append(charset.charAt(index));
        }
        return sb.toString();
    }

    public static String generateNumericCode(int digits) {
        if (digits <= 0 || digits > 9) {
            throw new IllegalArgumentException("Digits must be between 1 and 9");
        }
        int bound = (int) Math.pow(10, digits);
        int number = SECURE_RANDOM.nextInt(bound);
        return String.format("%0" + digits + "d", number);
    }

    public static String generatePrefixedCode(String prefix, int randomLength, boolean includeDate) {
        StringBuilder result = new StringBuilder();

        if (prefix != null && !prefix.isBlank()) {
            String cleanPrefix = prefix.trim().toUpperCase().replaceAll("[^A-Z0-9]", "");
            if (!cleanPrefix.isEmpty()) {
                result.append(cleanPrefix);
            }
        }

        if (includeDate) {
            if (!result.isEmpty()) result.append("-");
            result.append(LocalDate.now().format(DATE_FORMATTER));
        }

        if (!result.isEmpty()) result.append("-");
        result.append(generateRandomCode(randomLength));
        return result.toString();
    }

    public static String generateUrlSafeToken(int byteLength) {
        if (byteLength <= 0) {
            throw new IllegalArgumentException("Byte length must be greater than 0");
        }
        byte[] bytes = new byte[byteLength];
        SECURE_RANDOM.nextBytes(bytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    public static String generateSlug(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        String normalized = java.text.Normalizer.normalize(input, java.text.Normalizer.Form.NFKD);
        return normalized
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-+|-+$", "");
    }

    public static String generateUniqueSlug(String input, int suffixLength) {
        String base = generateSlug(input);
        if (base.isEmpty()) {
            base = "item";
        }
        String suffix = generateRandomCode(suffixLength, SLUG_SUFFIX_CHARSET);
        return base + "-" + suffix;
    }

    public static Set<String> generateUniqueBatch(int count, Supplier<String> generator, int maxAttempts) {
        if (count <= 0) {
            throw new IllegalArgumentException("Count must be greater than 0");
        }
        Objects.requireNonNull(generator, "Generator must not be null");

        Set<String> uniqueCodes = new HashSet<>(count);
        int attempts = 0;

        while (uniqueCodes.size() < count) {
            if (++attempts > maxAttempts) {
                throw new IllegalStateException("Exceeded max attempts to generate unique codes");
            }
            uniqueCodes.add(generator.get());
        }
        return uniqueCodes;
    }
}
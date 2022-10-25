package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MethodParser {

    private static final Pattern METHOD_SIGNATURE_REGEX =
            Pattern.compile("^((?<accessModifier>\\w+)\\s+)?(?<returnType>\\w+)\\s+(?<methodName>\\w+)\\s*\\((?<arguments>.*)\\)$");

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        Matcher matcher = METHOD_SIGNATURE_REGEX.matcher(signatureString.strip());

        if (!matcher.matches()) {
            throw new RuntimeException("Invalid method signature: " + signatureString);
        }

        String accessModifier = matcher.group("accessModifier");
        String returnType = matcher.group("returnType");
        String methodName = matcher.group("methodName");
        String argumentsString = matcher.group("arguments");

        return null;
    }

    private List<MethodSignature.Argument> parseArguments(String argumentsString) {
        List<MethodSignature.Argument> arguments = new ArrayList<>();

        if (argumentsString.isBlank()) {
            return arguments;
        }

        for (String argumentString : argumentsString.split("\\s*,\\s*")) {
            String[] typeAndName = argumentString.split("\\s+");

            if (typeAndName.length != 2) {
                throw new IllegalArgumentException("Invalid argument: " + argumentString);
            }

            arguments.add(new MethodSignature.Argument(typeAndName[0], typeAndName[1]));
        }

        return arguments;
    }

}

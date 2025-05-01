package com.hridoykrisna.car_management.config;

import java.math.BigInteger;

public class CustomConfiguration {

    public static String CustomMessage(String customMessage) {

        //Text to Binary
        StringBuilder messageToBinaryBuilder = new StringBuilder();
        char[] chars = customMessage.toCharArray();
        for (char c : chars) {
            messageToBinaryBuilder.append(String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0"));
        }

        //Text to Binary
        StringBuilder SecurityStringToBinaryBuilder = new StringBuilder();

        String SECURITY_STRING = ">0&W,Dc<tzUk1NymPm#hq;y5(XzI&WblVHn<YM|U&0h5.t'Q<D3~rU=`WbDk5g";
        char[] chars2 = SECURITY_STRING.toCharArray();
        for (char c2 : chars2) {
            SecurityStringToBinaryBuilder.append(String.format("%8s", Integer.toBinaryString(c2)).replaceAll(" ", "0"));
        }

        String hexString = getString(messageToBinaryBuilder, SecurityStringToBinaryBuilder);
        System.out.println("Sent Message: " + hexString);

        return hexString;
    }

    private static String getString(StringBuilder messageToBinaryBuilder, StringBuilder SecurityStringToBinaryBuilder) {
        StringBuilder SecurityCodeToBinaryBuilder = new StringBuilder();

        String SECURITY_CODE = "377104273910";
        char[] chars3 = SECURITY_CODE.toCharArray();
        for (char c3 : chars3) {
            SecurityCodeToBinaryBuilder.append(String.format("%8s", Integer.toBinaryString(c3)).replaceAll(" ", "0"));
        }

        String inputMessageBinaryString = messageToBinaryBuilder.toString();
        String securityStringBinaryString = SecurityStringToBinaryBuilder.toString();
        String securityCodeBinaryString = SecurityCodeToBinaryBuilder.toString();
        String transmitMessage = securityStringBinaryString+ securityCodeBinaryString+ inputMessageBinaryString + securityStringBinaryString + securityCodeBinaryString;

        //Binary To Hexadecimal
        String hexString = new BigInteger(transmitMessage, 2).toString(16);
        return hexString;
    }
}

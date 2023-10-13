package com.ufersa.ed2praticaoffline3.huffman;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman {

    private static HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencies) {
        //PriorityQueue<HuffmanNode> minHeap = new PriorityQueue<>();
        MinHeap<HuffmanNode> minHeap = new MinHeap<>();

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            minHeap.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (minHeap.size() > 1) {
            HuffmanNode left = minHeap.poll();
            HuffmanNode right = minHeap.poll();

            HuffmanNode merged = new HuffmanNode('\0', left.frequency + right.frequency);
            merged.left = left;
            merged.right = right;

            minHeap.add(merged);
        }

        return minHeap.poll();
    }

    private static String encodeMessage(HuffmanNode root, String message) {
        Map<Character, String> huffmanCodes = new HashMap<>();
        buildHuffmanCodes(root, "", huffmanCodes);

        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            encodedMessage.append(huffmanCodes.get(c));
        }

        return encodedMessage.toString();
    }

    private static void buildHuffmanCodes(HuffmanNode root, String code, Map<Character, String> huffmanCodes) {
        if (root == null) {
            return;
        }

        if (root.character != '\0') {
            huffmanCodes.put(root.character, code);
        }

        buildHuffmanCodes(root.left, code + "0", huffmanCodes);
        buildHuffmanCodes(root.right, code + "1", huffmanCodes);
    }

    private static String decodeHuffmanMessage(HuffmanNode root, String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedMessage.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }

            if (current.character != '\0') {
                decodedMessage.append(current.character);
                current = root;  // Volte para a raiz para decodificar o próximo caractere
            }
        }

        return decodedMessage.toString();
    }

    private static Map<Character, Integer> getFrequencias(String mensagem){
        Map<Character, Integer> frequencies = new HashMap<>();

        for (char c : mensagem.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        return frequencies;
    }
    public static RespostaCompressao comprimirMensagem(String mensagem){

        var frequencies = getFrequencias(mensagem);

        HuffmanNode root = buildHuffmanTree(frequencies);

        String encodedMessage = encodeMessage(root, mensagem);

        return new RespostaCompressao(root, encodedMessage);
    }

    public static String descomprimirMensagem(HuffmanNode raiz, String mensagemComprimida){
        return decodeHuffmanMessage(raiz,mensagemComprimida);
    }
}
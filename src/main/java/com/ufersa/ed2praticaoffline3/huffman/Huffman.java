package com.ufersa.ed2praticaoffline3.huffman;

import com.ufersa.ed2praticaoffline3.model.hashTable.HashTableEncadExt;

public class Huffman {

    private static HuffmanNode buildHuffmanTree(HashTableEncadExt<Character, Integer> frequencies) {
        MinHeap<HuffmanNode> minHeap = new MinHeap<>();

        for (var entry : frequencies.entrySet()) {
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
        HashTableEncadExt<Character, String> huffmanCodes = new HashTableEncadExt<>();
        buildHuffmanCodes(root, "", huffmanCodes);

        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            encodedMessage.append(huffmanCodes.get(c));
        }

        return encodedMessage.toString();
    }

    private static void buildHuffmanCodes(HuffmanNode root, String code, HashTableEncadExt<Character, String> huffmanCodes) {
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

    private static HashTableEncadExt<Character, Integer> getFrequencias(String mensagem){
        HashTableEncadExt<Character, Integer> frequencies = new HashTableEncadExt<>();

        for (char c : mensagem.toCharArray()) {
            var getOrDefault = frequencies.get(c);
            if(getOrDefault == null){
                getOrDefault = 0;
            }

            frequencies.put(c, getOrDefault + 1);
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
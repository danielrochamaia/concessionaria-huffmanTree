package com.ufersa.ed2praticaoffline3.huffman;

public class RespostaCompressao {
    private HuffmanNode raiz;
    private String mensagemComprimida;

    public RespostaCompressao(HuffmanNode raiz, String mensagemComprimida) {
        this.raiz = raiz;
        this.mensagemComprimida = mensagemComprimida;
    }

    public HuffmanNode getRaiz() {
        return raiz;
    }

    public String getMensagemComprimida() {
        return mensagemComprimida;
    }
}
package com.ufersa.ed2praticaoffline3.huffman;

import com.ufersa.ed2praticaoffline3.model.entities.Automovel;
import com.ufersa.ed2praticaoffline3.model.entities.Condutor;

import java.util.ArrayList;
import java.util.List;

public class UtilsHuffman {

    public static Automovel converterEmAutomovel(HuffmanNode raizAtual, String automovelStringComprimido){
        var automovelStringDescomprimido = Huffman.descomprimirMensagem(raizAtual, automovelStringComprimido);
        String[] dadosAutomovel = automovelStringDescomprimido.split(";");
        var automovel = new Automovel();
        automovel.setPlaca(dadosAutomovel[0]);
        automovel.setRenavam(dadosAutomovel[1]);
        automovel.setModelo(dadosAutomovel[2]);
        automovel.setDataFabricacao(dadosAutomovel[3]);
        automovel.setCondutor(new Condutor(dadosAutomovel[4], dadosAutomovel[5]));
        return automovel;
    }

    public static List<Automovel> converterEmListaAutomovel(HuffmanNode raizAtual, String automoveisComprimidos){
        List<Automovel> list = new ArrayList<>();
        var automoveisStringDescomprimidos = Huffman.descomprimirMensagem(raizAtual, automoveisComprimidos);
        String[] dadosAutomoveis = automoveisStringDescomprimidos.split(";");
        var total = dadosAutomoveis.length / 6;
        for(int i = 0; total > 0; i += 6, total--){
            var automovel = new Automovel();
            automovel.setPlaca(dadosAutomoveis[i]);
            automovel.setRenavam(dadosAutomoveis[i+1]);
            automovel.setModelo(dadosAutomoveis[i+2]);
            automovel.setDataFabricacao(dadosAutomoveis[i+3]);
            automovel.setCondutor(new Condutor(dadosAutomoveis[i+4], dadosAutomoveis[i+5]));
            list.add(automovel);
        }
        return list;
    }

    public static RespostaCompressao comprimir(Automovel automovel){
        var automovelString = automovel.toStringCompressao();
        var respostaCompressao = Huffman.comprimirMensagem(automovelString);
        return respostaCompressao;
    }

    public static RespostaCompressao comprimir(String string){
        var respostaCompressao = Huffman.comprimirMensagem(string);
        return respostaCompressao;
    }
}

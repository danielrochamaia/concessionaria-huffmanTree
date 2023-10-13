package com.ufersa.ed2praticaoffline3.servidor.servidores;

import com.ufersa.ed2praticaoffline3.huffman.Huffman;
import com.ufersa.ed2praticaoffline3.huffman.HuffmanNode;
import com.ufersa.ed2praticaoffline3.huffman.UtilsHuffman;
import com.ufersa.ed2praticaoffline3.model.entities.Automovel;
import com.ufersa.ed2praticaoffline3.model.hashTable.HashTableEncadExt;
import com.ufersa.ed2praticaoffline3.servidor.interfaces.IConcessioanariaServidor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConcessionariaServidor implements IConcessioanariaServidor {

    private static final int CAPACITY = 85;
    private int repetido;
    private HuffmanNode raizAtual;
    private HashTableEncadExt<String, Automovel> hashTable;

    public ConcessionariaServidor() throws IOException {
        this.hashTable = new HashTableEncadExt<String, Automovel>(CAPACITY);
        repetido = 0;
    }

    public int addAutomovel(String automovelStringComprimido) {

        var automovel = UtilsHuffman.converterEmAutomovel(raizAtual, automovelStringComprimido);

        var renavam = automovel.getRenavam();
        if(hashTable.containsKey(renavam)){
            System.out.println("REPETIDO!");
            repetido++;
        }
        hashTable.put(automovel.getRenavam(), automovel);
        return repetido;
    }

    public String removerAutomovel(String chaveComprimida) {
        var chaveDescomprimida = Huffman.descomprimirMensagem(getRaiz(), chaveComprimida);
        if(!hashTable.containsKey(chaveDescomprimida)){
            return "Chave não existe";
        }

        hashTable.remove(chaveDescomprimida);
        //add log
        return "Automóvel removido!";
    }


    public String buscarTodos() {
        StringBuilder sb = new StringBuilder();
        for(var obj : hashTable.entrySet()){
            if(obj != null){
                sb.append(obj.getValue().toStringCompressao());
            }
        }
        var respostaCompressao = UtilsHuffman.comprimir(sb.toString());
        var todosAutomoveisComprimidos = respostaCompressao.getMensagemComprimida();
        setRaiz(respostaCompressao.getRaiz());
        return todosAutomoveisComprimidos;
    }


    public String buscarPorChave(String chaveComprimida) {
        var chaveDescomprimida = Huffman.descomprimirMensagem(getRaiz(),chaveComprimida);
        var automovel = hashTable.get(chaveDescomprimida);
        var automovelComprimido = comprimir(automovel);
        return automovelComprimido;
    }

    public void setRaiz(HuffmanNode raiz) {
        raizAtual = raiz;
    }

    public HuffmanNode getRaiz() {
        return raizAtual;
    }

    public String editar(String automovelStringComprimido, String chaveComprimida) {
        var chaveDescomprimida = Huffman.descomprimirMensagem(getRaiz(),chaveComprimida);
        var automovel = hashTable.get(chaveDescomprimida);
        return null;
    }

    private String comprimir(Automovel automovel){
        var respostaCompressao = UtilsHuffman.comprimir(automovel);
        setRaiz(respostaCompressao.getRaiz());
        return respostaCompressao.getMensagemComprimida();
    }
}

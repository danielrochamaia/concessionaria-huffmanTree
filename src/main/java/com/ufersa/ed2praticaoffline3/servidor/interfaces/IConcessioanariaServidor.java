package com.ufersa.ed2praticaoffline3.servidor.interfaces;

import com.ufersa.ed2praticaoffline3.huffman.HuffmanNode;
import com.ufersa.ed2praticaoffline3.model.Protocolos.AutomovelResponse;
import com.ufersa.ed2praticaoffline3.model.entities.Automovel;

import java.util.List;

public interface IConcessioanariaServidor {
    int addAutomovel(String automovelStringComprimido);

    String removerAutomovel(String chave);

    String buscarTodos();

    String buscarPorChave(String chaveComprimida);

    void setRaiz(HuffmanNode raiz);

    HuffmanNode getRaiz();

    String editar(String automovelStringComprimido, String chaveComprimida);

}

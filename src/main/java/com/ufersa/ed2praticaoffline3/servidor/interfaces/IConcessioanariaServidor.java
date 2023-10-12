package com.ufersa.ed2praticaoffline3.servidor.interfaces;

import com.ufersa.ed2praticaoffline3.model.Protocolos.AutomovelResponse;
import com.ufersa.ed2praticaoffline3.model.entities.Automovel;

import java.util.List;

public interface IConcessioanariaServidor {
    int addAutomovel(Automovel automovel);

    String removerAutomovel(String chave);

    List<AutomovelResponse> buscarTodos();

    Automovel buscarPorChave(String chave);

}

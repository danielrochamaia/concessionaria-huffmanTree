package com.ufersa.ed2praticaoffline3.client.interfaces;

import com.ufersa.ed2praticaoffline3.model.Protocolos.AutomovelProtocolo;
import com.ufersa.ed2praticaoffline3.model.Protocolos.AutomovelResponse;
import com.ufersa.ed2praticaoffline3.model.entities.Automovel;

import java.util.List;

public interface IConcessionariaClient {
    String addAutomovel(List<AutomovelProtocolo> automovelProtocolos);

    String editarAutomovel(AutomovelProtocolo automovelProtocolo, String chave);

    List<Automovel> buscarAutomoveis();

    AutomovelResponse buscarAutomovel(String chave);

    String deletarAutomovel(String chave);

}

package com.ufersa.ed2praticaoffline3.client.services;

import com.ufersa.ed2praticaoffline3.huffman.UtilsHuffman;
import com.ufersa.ed2praticaoffline3.model.Protocolos.AutomovelResponse;
import com.ufersa.ed2praticaoffline3.model.entities.Automovel;
import com.ufersa.ed2praticaoffline3.model.entities.Condutor;
import com.ufersa.ed2praticaoffline3.model.Protocolos.AutomovelProtocolo;
import com.ufersa.ed2praticaoffline3.client.interfaces.IConcessionariaClient;
import com.ufersa.ed2praticaoffline3.servidor.interfaces.IConcessioanariaServidor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcessionariaClient implements IConcessionariaClient {

    private IConcessioanariaServidor concessioanariaServidor;

    public ConcessionariaClient(IConcessioanariaServidor concessioanariaServidor){
        this.concessioanariaServidor = concessioanariaServidor;

    }

    public String addAutomovel(List<AutomovelProtocolo> automovelProtocolos) {
        int repetidos = 0;
        for(int i = 0; i <= automovelProtocolos.size()-1;i++){
            var automovel = new Automovel();
            automovel = setDados(automovel, automovelProtocolos.get(i));

            var automovelComprimido = comprimir(automovel);

            repetidos = concessioanariaServidor.addAutomovel(automovelComprimido);
        }
        return automovelProtocolos.size() - repetidos + " automóveis adicionados!";
    }

    public String editarAutomovel(AutomovelProtocolo automovelProtocolo, String chave) {
        deletarAutomovel(chave);
        var automovel = new Automovel();
        automovel = setDados(automovel, automovelProtocolo);
        var automovelComprimido = comprimir(automovel);
        concessioanariaServidor.addAutomovel(automovelComprimido);
        return "Editado com sucesso!";
    }

    public List<Automovel> buscarAutomoveis() {
        var automoveisComprimidos = concessioanariaServidor.buscarTodos();
        var automoveisObjetos = UtilsHuffman.converterEmListaAutomovel(concessioanariaServidor.getRaiz(), automoveisComprimidos);
        return automoveisObjetos;
    }

    public AutomovelResponse buscarAutomovel(String chave) {
        var automovelDescomprimido = buscarAutomovelDescomprimido(chave);
        return new AutomovelResponse(chave, automovelDescomprimido);
    }

    public String deletarAutomovel(String chave) {
        var chaveComprimida = comprimir(chave);
        return concessioanariaServidor.removerAutomovel(chaveComprimida);
    }

    private Automovel setDados(Automovel automovel, AutomovelProtocolo automovelProtocolo){
        automovel.setCondutor(new Condutor(automovelProtocolo.nomeCondutor, automovelProtocolo.cpfCondutor));
        automovel.setRenavam(automovelProtocolo.renavam);
        automovel.setModelo(automovelProtocolo.modelo);
        automovel.setPlaca(automovelProtocolo.placa);
        automovel.setDataFabricacao(automovelProtocolo.dataFabricacao);
        return automovel;
    }

    private String comprimir(String mensagem){
        var respostaCompressao = UtilsHuffman.comprimir(mensagem);
        concessioanariaServidor.setRaiz(respostaCompressao.getRaiz());
        return respostaCompressao.getMensagemComprimida();
    }

    private String comprimir(Automovel automovel){
        var respostaCompressao = UtilsHuffman.comprimir(automovel);
        concessioanariaServidor.setRaiz(respostaCompressao.getRaiz());
        return respostaCompressao.getMensagemComprimida();
    }

    private Automovel buscarAutomovelDescomprimido(String chave){
        var stringComprimida = comprimir(chave);
        var automovelComprimido = concessioanariaServidor.buscarPorChave(stringComprimida);
        var automovelDescomprimido = UtilsHuffman.converterEmAutomovel(concessioanariaServidor.getRaiz(), automovelComprimido);
        return  automovelDescomprimido;
    }
}
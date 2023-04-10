package app.ports.providers;

import core.entities.Logradouro;
import core.validations.CEP;

public interface ICEPProvider {
    Logradouro getAddress(CEP cep) throws Exception;
}
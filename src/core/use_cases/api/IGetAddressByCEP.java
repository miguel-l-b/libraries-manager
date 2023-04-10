package core.use_cases.api;

import core.entities.Logradouro;
import core.validations.CEP;

public interface IGetAddressByCEP {
    Logradouro GetAddressByCEP(String cep);
}
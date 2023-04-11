package core.entities;

public class Logradouro implements Cloneable {
    private String cep;
    private String complemento;
    private String logradouro;
    private String bairro;
    private String estado;
    private String cidade;
    private InfoEstado estado_info;
    private InfoCidade cidade_info;
    
    public String getCep () { return this.cep; }
    public String getLogradouro() { return this.logradouro; }
    public String getComplemento() { return this.complemento; }
    public String getBairro() { return this.bairro; }
    public  String getEstado() { return this.estado; }
    public  String getCidade() { return this.cidade; }
    public InfoEstado getEstado_info() { 
        try { return (InfoEstado)this.estado_info.clone(); }
        catch (CloneNotSupportedException e) { return null; } 
    }
    public InfoCidade getCidade_info() {
        try { return (InfoCidade)this.cidade_info.clone(); }
        catch (CloneNotSupportedException e) { return null; }
    }
    
    public void setLogradouro(String logradouro) throws Exception {
        if (logradouro == null || logradouro.isEmpty())
            throw new Exception ("Logradouro ausente");

        this.logradouro = logradouro;
    }
    
    public void setComplemento(String complemento) throws Exception {
        if(complemento == null || complemento.isEmpty())
            throw new Exception("Complemento ausente");
        
        this.complemento = complemento;
    }

    public void setBairro(String bairro) throws Exception {
        if (bairro == null || bairro.isEmpty())
            throw new Exception ("Bairro ausente");

        this.bairro = bairro;
    }

    public void setCidade(String cidade) throws Exception {
        if (cidade==null || cidade.isEmpty())
            throw new Exception ("Cidade ausente");

        this.cidade = cidade;
    }
    
    public void setCidade_info(InfoCidade infoCidade) throws Exception {
        if (infoCidade==null)
            throw new Exception ("Informacao de cidade ausente");

        this.cidade_info = (InfoCidade)infoCidade.clone();
    }

    public void setEstado(String estado) throws Exception {
        if (estado == null || estado.isEmpty())
            throw new Exception ("Estado ausente");

        this.estado = estado;
    }

    public void setEstado_info(InfoEstado infoEstado) throws Exception {
        if (infoEstado==null)
            throw new Exception ("Informacao de estado ausente");

         this.estado_info = (InfoEstado)infoEstado.clone();
    }

    public void setCep(String cep) throws Exception {
        if (cep==null || cep.isEmpty())
            throw new Exception ("Logradouro ausente");

        this.cep = cep;
    }

    public Logradouro (String complemento,
                       String logradouro, String bairro,
                       String cidade, InfoCidade cidade_info,
                       String estado, InfoEstado estado_info,
                       String cep) throws Exception {
        this.setComplemento (complemento);
        this.setLogradouro  (logradouro);
        this.setBairro      (bairro);
        this.setCidade      (cidade);
        this.setCidade_info (cidade_info);
        this.setEstado      (estado);
        this.setEstado_info (estado_info);
        this.setCep         (cep); 
    }
    public Logradouro (Logradouro modelo) throws Exception {
        if (modelo == null) throw new Exception("Modelo inexistente");

        this.logradouro = modelo.logradouro;
        this.complemento = modelo.complemento;
        this.cidade = modelo.cidade;
        this.cidade_info = (InfoCidade)modelo.cidade_info.clone();
        this.estado = modelo.estado;
        this.estado_info = (InfoEstado)modelo.estado_info.clone();
        this.cep = modelo.cep;
    }
    public Logradouro () { }

    @Override
    public String toString ()
    {
        return "Logradouro: "+
               this.logradouro+
               "\nComplemento: "+
               this.complemento+
               "\nCidade.....: "+
               this.cidade+
               " / "+
               this.cidade_info+
               "\nEstado.....: "+
               this.estado+
               " / "+
               this.estado_info+
               "\nC.E.P......: "+
               this.cep;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this==obj) return true;
        if (obj==null) return false;

        if (!(obj instanceof Logradouro)) return false;
        Logradouro cep = (Logradouro)obj;

        if (!this.logradouro.equals(cep.logradouro)) return false;
        if ((this.complemento==null && cep.complemento!=null) ||
            (this.complemento!=null && cep.complemento==null) ||
            !this.complemento.equals(cep.complemento)) 
            return false;
        if (!this.cidade.equals(cep.cidade)) return false;
        if (!this.cidade_info.equals(cep.cidade_info)) return false;
        if (!this.estado.equals(cep.estado)) return false;
        if (!this.estado_info.equals(cep.estado_info)) return false;
        if (!this.cep.equals(cep.cep)) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int hash= 23;

        hash = 3 * hash + this.logradouro.hashCode();
        if (this.complemento != null) hash = 5 * hash + this.complemento.hashCode();
        hash = 7 * hash + this.cidade.hashCode();
        hash = 9 * hash + this.cidade_info.hashCode();
        hash = 11 * hash + this.estado.hashCode();
        hash = 13 * hash + this.estado_info.hashCode();
        hash = 17 * hash + this.cep.hashCode();

        if (hash < 0) hash *= -1;

        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new Logradouro(this); }
        catch (Exception e) { throw new CloneNotSupportedException(e.getMessage()); }
    }
}
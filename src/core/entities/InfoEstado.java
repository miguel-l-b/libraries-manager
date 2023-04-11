package core.entities;

public class InfoEstado implements Cloneable
{
    private String nome;
    private String codigo_ibge;
    private String area_km2;
    
    public InfoEstado() { }

    public InfoEstado(String nome, String codigoIBGE, String areaEmKm2) throws Exception {
        this.setNome        (nome);
        this.setCodigo_ibge (codigoIBGE);
        this.setArea_km2    (areaEmKm2);
    }

    public InfoEstado(InfoEstado modelo) throws Exception {
        if (modelo==null)
            throw new Exception ("Modelo inexistente");

        this.nome        = modelo.nome;
        this.codigo_ibge = modelo.codigo_ibge;
        this.area_km2    = modelo.area_km2;
    }

    public String getNome() { return this.nome; }
    public String getCodigo_ibge() { return this.codigo_ibge; }
    public String getArea_km2() { return this.area_km2; }

    public void setNome(String nome) throws Exception {
        if (nome == null || nome.isEmpty())
            throw new Exception ("Nome ausente");

        this.nome = nome;
    }

    public void setCodigo_ibge (String codigoIBGE) throws Exception {
        if (codigoIBGE==null || codigoIBGE.isEmpty())
            throw new Exception ("Codigo do IBGE ausente");

        this.codigo_ibge = codigoIBGE;
    }

    public void setArea_km2 (String areaEmKm2) throws Exception {
        if (areaEmKm2==null || areaEmKm2.isEmpty())
            throw new Exception ("Area ausente");

        this.area_km2 = areaEmKm2;
    }    

    @Override
    public String toString() {
        return this.nome+
            " / Codigo IBGE: "+
            this.codigo_ibge+
            " / Area(km2): "+
            this.area_km2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) return false;
        if (this==obj) return true;
        if (!(obj instanceof InfoEstado)) return false;
        if (!this.nome.equals(((InfoEstado)obj).nome)) return false;
        if (!this.codigo_ibge.equals(((InfoEstado)obj).codigo_ibge)) return false;
        if (!this.area_km2.equals(((InfoEstado)obj).area_km2)) return false;

        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 1;

        hash = 3 * hash + this.nome.hashCode();
        hash = 5 * hash + this.codigo_ibge.hashCode();
        hash = 7 * hash + this.area_km2.hashCode();

        if(hash < 0) hash *= -1;

        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new InfoEstado(this); }
        catch (Exception e) { throw new CloneNotSupportedException(e.getMessage()); }
    }
}

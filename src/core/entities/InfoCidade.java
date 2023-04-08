package core.entities;

public class InfoCidade implements Cloneable
{
    private String codigo_ibge;
    private String area_km2;
    
    public InfoCidade() { }
    
    public InfoCidade(String codigoIBGE, String areaEmKm2) throws Exception {
        this.setCodigo_ibge(codigoIBGE);
        this.setArea_km2(areaEmKm2);
    }
    
    public InfoCidade(InfoCidade modelo) throws Exception {
        if (modelo == null) throw new Exception ("Modelo inexistente");

        this.codigo_ibge = modelo.codigo_ibge;
        this.area_km2 = modelo.area_km2;
    }
    
    public  String getCodigo_ibge() { return this.codigo_ibge; }
    public  String getArea_km2() { return this.area_km2; }
    
    public void setCodigo_ibge(String codigoIBGE) throws Exception {
        if(codigoIBGE == null || codigoIBGE.isEmpty())
            throw new Exception ("Codigo do IBGE ausente");
        
        this.codigo_ibge = codigoIBGE;
    }
    
    public void setArea_km2(String areaEmKm2) throws Exception {
        if (areaEmKm2==null || areaEmKm2.length()==0)
            throw new Exception ("Area ausente");

        this.area_km2 = areaEmKm2;
    }

    public String toString () {
        return "Codigo IBGE: "+
               this.codigo_ibge+
               " / Area(km2): "+
               this.area_km2;
    }

    public boolean equals (Object obj) {
        if (this==obj) return true;
        if (obj==null) return false;

        if (!(obj instanceof InfoCidade)) return false;

        if (!this.codigo_ibge.equals(((InfoCidade)obj).codigo_ibge)) return false;
        if (!this.area_km2.equals(((InfoCidade)obj).area_km2)) return false;

        return true;
    }

    public int hashCode() {
        int hash = 1;

        hash = 3 * hash + this.codigo_ibge.hashCode();
        hash = 5 * hash + this.area_km2.hashCode();

        return hash;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        try { return new InfoCidade(this); }
        catch (Exception e) { throw new CloneNotSupportedException(e.getMessage()); }
    }
}

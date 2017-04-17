package th.co.ais.tdims.model;

public class Report {
    private String site;
    private String usageType;
    private String chargeType;
    private String env;
    private int total;
    private int available;
    private int inUse;
    private int lost;
    private int unAvailable;
    private int pending;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getInUse() {
        return inUse;
    }

    public void setInUse(int inUse) {
        this.inUse = inUse;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getUnAvailable() {
        return unAvailable;
    }

    public void setUnAvailable(int unAvailable) {
        this.unAvailable = unAvailable;
    }

    public int getPending() {
        return pending;
    }

    public void setPending(int pending) {
        this.pending = pending;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
    
    
}

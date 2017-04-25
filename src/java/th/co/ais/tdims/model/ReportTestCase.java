
package th.co.ais.tdims.model;


public class ReportTestCase {
    private String year;
    private String type;
    private int testcase;
    private int manualStep;
    private int autoStep;
    private int allStep;
    private int defect;
    private int issue;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTestcase() {
        return testcase;
    }

    public void setTestcase(int testcase) {
        this.testcase = testcase;
    }

    public int getManualStep() {
        return manualStep;
    }

    public void setManualStep(int manualStep) {
        this.manualStep = manualStep;
    }

    public int getAutoStep() {
        return autoStep;
    }

    public void setAutoStep(int autoStep) {
        this.autoStep = autoStep;
    }

    public int getAllStep() {
        return allStep;
    }

    public void setAllStep(int allStep) {
        this.allStep = allStep;
    }

    public int getDefect() {
        return defect;
    }

    public void setDefect(int defect) {
        this.defect = defect;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }
}

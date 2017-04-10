/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.tdims.model;


public class Testcase {
    private String testcaseId;
    private String projectId;
    private String systems;
    private String enviroment;
    private String issueNo;
    private String pathDir;   
    private String testcaseDetails;
    private String testcaseTitle;
    private String defectNo;    
    private String createDate;
    private String step;
    private String createBy;
    private String type;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
    
    public String getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    public String getSystems() {
        return systems;
    }

    public void setSystems(String systems) {
        this.systems = systems;
    }

    public String getEnviroment() {
        return enviroment;
    }

    public void setEnviroment(String enviroment) {
        this.enviroment = enviroment;
    }

    public String getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(String issueNo) {
        this.issueNo = issueNo;
    }

    public String getPathDir() {
        return pathDir;
    }

    public void setPathDir(String pathDir) {
        this.pathDir = pathDir;
    }

    public String getTestcaseDetails() {
        return testcaseDetails;
    }

    public void setTestcaseDetails(String testcaseDetails) {
        this.testcaseDetails = testcaseDetails;
    }

    public String getTestcaseTitle() {
        return testcaseTitle;
    }

    public void setTestcaseTitle(String testcaseTitle) {
        this.testcaseTitle = testcaseTitle;
    }

    public String getDefectNo() {
        return defectNo;
    }

    public void setDefectNo(String defectNo) {
        this.defectNo = defectNo;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
}

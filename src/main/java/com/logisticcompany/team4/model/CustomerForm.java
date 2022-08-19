package com.logisticcompany.team4.model;

import javax.persistence.*;

@Entity
@Table(name = "customerForms")
public class CustomerForm {

    @Id
    @Column(name = "customerForm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String CFirstName;
    String CLastName;
    String CEmailAddress;
    String CPhoneNumber;
    String content;
    String answer;
    boolean IsAnswered;

    @ManyToOne
    @JoinColumn(name = "company_id")
    Company relatedCompany;

    public CustomerForm() {
        this.IsAnswered=false;
    }

    public CustomerForm(int id, String CFirstName, String CLastName, String CEmailAddress, String CPhoneNumber, String content, Company relatedCompany) {
        this.id = id;
        this.CFirstName = CFirstName;
        this.CLastName = CLastName;
        this.CEmailAddress = CEmailAddress;
        this.CPhoneNumber = CPhoneNumber;
        this.content = content;
        this.relatedCompany = relatedCompany;
        this.IsAnswered = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCFirstName() {
        return CFirstName;
    }

    public void setCFirstName(String CFirstName) {
        this.CFirstName = CFirstName;
    }

    public String getCLastName() {
        return CLastName;
    }

    public void setCLastName(String CLastName) {
        this.CLastName = CLastName;
    }

    public String getCEmailAddress() {
        return CEmailAddress;
    }

    public void setCEmailAddress(String CEmailAddress) {
        this.CEmailAddress = CEmailAddress;
    }

    public String getCPhoneNumber() {
        return CPhoneNumber;
    }

    public void setCPhoneNumber(String CPhoneNumber) {
        this.CPhoneNumber = CPhoneNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswered() {
        return IsAnswered;
    }

    public void setAnswered(boolean answered) {
        IsAnswered = answered;
    }

    public void setIsAnswered(boolean answered) {
        IsAnswered = answered;
    }

    public Company getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(Company relatedCompany) {
        this.relatedCompany = relatedCompany;
    }
}

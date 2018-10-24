package org.txttodbservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String parameter1;

    private String parameter2;

    private String parameter3;

    public Analysis(String parameter1, String parameter2, String parameter3) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
        this.parameter3 = parameter3;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParameter1() {
        return parameter1;
    }

    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
    }

    public String getParameter2() {
        return parameter2;
    }

    public void setParameter2(String parameter2) {
        this.parameter2 = parameter2;
    }

    public String getParameter3() {
        return parameter3;
    }

    public void setParameter3(String parameter3) {
        this.parameter3 = parameter3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Analysis analysis = (Analysis) o;
        return id == analysis.id &&
                Objects.equals(parameter1, analysis.parameter1) &&
                Objects.equals(parameter2, analysis.parameter2) &&
                Objects.equals(parameter3, analysis.parameter3);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, parameter1, parameter2, parameter3);
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id=" + id +
                ", parameter1='" + parameter1 + '\'' +
                ", parameter2='" + parameter2 + '\'' +
                ", parameter3='" + parameter3 + '\'' +
                '}';
    }
}
